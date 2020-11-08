package me.believeGod.test;

import me.believeGod.dao.AddressDao;
import me.believeGod.dao.DiscountDao;
import me.believeGod.dao.ScoreDao;
import me.believeGod.dao.UserDao;
import me.believeGod.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.*;

/**
 * @ClassName UserDaoTest
 * @Description TODO
 * @Author Tim
 * @Date 2020/6/15 16:34
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ScoreDao scoreDao;


    /**
     * 测试在坏事据的条件下，含级联的类多种不同的加载数据方法的加载情况
     * eg.表存在外键ID,但外键ID引用的数据丢失了的情况。
     */
    @Test
    @Transactional
    public void testDamagedDataFetch(){


        // exitst方法
        System.out.println("exist:"+userDao.existsById(1));

        // count方法
       System.out.println("count:" +  userDao.count());

        // getOne方法
        User one = userDao.getOne(1);
        System.out.println(one==null?"null":"not null");

        // findAll方法
        List<User> all = userDao.findAll();
        System.out.println("list.length" + all.size());
        User user = all.get(0);
        System.out.println(user==null?"null":"not null");

        // findById方法
        Optional<User> byId = userDao.findById(1);
        Assert.assertTrue("找不到对象",byId.isPresent());
        User user2 = byId.get();

        System.out.println("getDiscount:"+(user2.getDiscount()==null?"null":"not null"));

    }

    /**
     * 测试关联数据在没有事务的情况下能否加载出来
     */
    @Test
    @Transactional
    public void testCascadeLazyLoad(){
        Optional<User> optionalUser = userDao.findById(1);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            Address address = user.getAddress();
            System.out.println("address:"+address.getArea());

            Discount discount = user.getDiscount();
            System.out.println("discount:"+discount.getDiscountValue());

            Set<Order> orders = user.getOrders();
            Iterator<Order> iterator = orders.iterator();
            while(iterator.hasNext()){
                System.out.println("order:"+iterator.next().getTotalSum());
            }

            Set<Role> roles = user.getRoles();
            Iterator<Role> iterator1 = roles.iterator();
            while(iterator1.hasNext()){
                System.out.println("role:"+iterator1.next().getDescription());
            }
        }
    }


    /**
     * 测试级联保存，是否会保存处于瞬间态或者人造的
     * eg.默认级联设置 ，不设置ID ,异常
     * <br/>最高级联设置，不设置ID,成功
     * <br/>默认级联设置，设置ID，插入了坏数据
     * <br/>最高级联设置，设置ID，异常
     */
    @Test
    public void testCascadeSave01(){
        Discount discount=new Discount();
        // 控制是否设置ID
        discount.setId(2);
        discount.setDiscountValue(20d);
        discount.setValidtePeroid(new Date());


        User user=new User();
        user.setUserName("tim");
        user.setPassword("123456");
        user.setDiscount(discount);

        userDao.save(user);
        Assert.assertNotNull("用户Id不存在",user.getId());
        Assert.assertNotNull("折扣Id不存在",discount.getId());
        System.out.println(user.getId());
        System.out.println(discount.getId());

    }


    /**
     * 测试级联保存，是否会保存处于持久态 or 游离态
     * eg.默认级联设置 ，设置discount不存在ID，调用Save(),discount会根据ID策略持久化，user会按照discount对象的值保存，错误
     * <br/>最高级联设置，设置discount不存在ID，调用Save(),插入了discount,user异常
     * <br/>默认级联设置，设置discount存在ID，调用Save(),discount会更新，，user会按照discount对象的值保存，正确
     * <br/>最高级联设置，设置discount存在ID，调用Save(),discount会更新,User异常
     */
    @Test
    public void testCascadeSave02(){
        Discount discount=new Discount();
        // 控制是否设置ID
        discount.setId(10);
        discount.setDiscountValue(30d);
        discount.setValidtePeroid(new Date());
        // 保存实体类
        discountDao.save(discount);

        // 修改 discount
//        discount.setDiscountValue(80d);

        User user=new User();
        user.setUserName("tim");
        user.setPassword("123456");
        user.setDiscount(discount);

        userDao.save(user);
        Assert.assertNotNull("用户Id不存在",user.getId());
        Assert.assertNotNull("折扣Id不存在",discount.getId());
        System.out.println(user.getId());
        System.out.println(discount.getId());

    }

    /**
     * 测试级联保存，是否会保存处于持久态 or 游离态
     * eg.默认级联设置 ，加载出discount，再保存User，单方面插入了ID数据。
     * <br/>最高级联设置，加载出discount,再保存User，异常
     * <br/>默认级联设置，
     * <br/>最高级联设置，
     */
    @Test
    public void testCascadeSave03(){
        Optional<Discount> byId = discountDao.findById(1);
        Discount discount = byId.get();

        // 修改 discount
        discount.setDiscountValue(80d);

        User user=new User();
        user.setUserName("tim");
        user.setPassword("123456");
        user.setDiscount(discount);

        userDao.save(user);
        Assert.assertNotNull("用户Id不存在",user.getId());
        Assert.assertNotNull("折扣Id不存在",discount.getId());
        System.out.println(user.getId());
        System.out.println(discount.getId());

    }

    /**
     * 测试级联的对象，修改后，是否能影响到主对象
     * 结果不能。
     */
    @Test
    public void testAfterModify(){
        Optional<Discount> byId = discountDao.findById(1);
        Optional<Address> byId1 = addressDao.findById(1);
        Discount discount = byId.get();
        Address address = byId1.get();

        Optional<User> byId2 = userDao.findById(1);
        User user = byId2.get();

        discount.setDiscountValue(88d);
        address.setArea("baiyun");
        addressDao.save(address);

        Address address1 = user.getAddress();
        Discount discount1 = user.getDiscount();
        System.out.println("area:" + address1.getArea());
//        System.out.println("discount:"+discount1.getDiscountValue());

    }

    /**
     * 测试dao接口是否需要显式声明符合 Spring Data JPA 的方法。
     */
    @Test
    public void testAutoMethod(){
        Optional<User> byUserName = userDao.findByUserName("张三");
        Assert.assertTrue("用户找不到",byUserName.isPresent());
        System.out.println("password:"+byUserName.get().getPassword());

        List<Address> addressList = addressDao.findByCity("Guangzhou");
        Assert.assertTrue("列表不大于0",addressList.size()>0);
        for(Address address:addressList){
            System.out.println("area:"+address.getArea());
        }

        Address baiyun = addressDao.findByArea("baiyun");
        Assert.assertNotNull("地址空", baiyun);
        System.out.println("province:"+baiyun.getProvince());
    }

    @Test
    public void testOverride(){
        System.out.println(addressDao.test());
//        Assert.assertNull(addressDao.findByArea("baiyun"));
        List<Address> addressList = addressDao.findByCity("Guangzhou");

        Assert.assertNull(addressDao.findByCity("Guangzhou"));
    }


    @Test
    public void testCombindedKey(){

        Score score =new Score();
        score.setCourseName("语文");
        score.setStudentName("李明");
        score.setValue(78);
        scoreDao.save(score);

      /*  Score score2 =new Score();
        score.setCourseName("语文");
        score.setStudentName("张三");
        score.setValue(44);
        scoreDao.save(score);


        Score score3 =new Score();
        score.setCourseName("数学");
        score.setStudentName("李明");
        score.setValue(39);
        scoreDao.save(score);
        ScoreKey scoreKey = new ScoreKey();
        scoreKey.setCourseName("语文");
        scoreKey.setStudentName("李明");*/
//        System.out.println("scoreDao.existsById(scoreKey) = " + scoreDao.existsById(scoreKey));
    }
}
