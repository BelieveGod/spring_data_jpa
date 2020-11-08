package me.believeGod.entity;

import javax.persistence.*;

/**
 * @ClassName Score
 * @Description TODO
 * @Author believeGod
 * @Date 2020/9/11 11:45
 * @Version 1.0
 */

@Entity
@Table(name = "TB_SCORE")
@IdClass(ScoreKey.class)
public class Score {

    @Id
    @Column(name="COURSE_NAME")
    private String courseName;


    @Id
    @Column(name="STUDENT_NAME")
    private String studentName;


    @Column(name="VALUE")
    private Integer value;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
