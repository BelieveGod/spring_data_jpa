package me.believeGod.entity;

import java.io.Serializable;

/**
 * @ClassName ScoreKey
 * @Description TODO
 * @Author believeGod
 * @Date 2020/9/11 11:48
 * @Version 1.0
 */
public class ScoreKey implements Serializable {

    private String courseName;

    private String studentName;

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
