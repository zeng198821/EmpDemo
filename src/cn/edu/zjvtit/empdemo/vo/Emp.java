package cn.edu.zjvtit.empdemo.vo;

/**
 * Created by zeng on 2016-10-22.
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

/**
 * 员工类
 */
public class Emp {
    /**
     * 员工ID
     */
    private int empno;
    /**
     * 员工姓名
     */
    private String ename;
    /**
     * 员工职位
     */
    private String job;
    /**
     * 员工入职日期
     */
    private Date hiredate;
    /**
     * 员工工资
     */
    private float sal;
    /**
     * 员工奖金
     */
    private float comm;
    /**
     * 员工照片
     */
    private String photo;

    /**
     * 获取员工奖金
     * @return
     */
    public float getComm() {
        return comm;
    }

    /**
     * 设置员工奖金
     * @param comm 奖金
     */
    public void setComm(float comm) {
        this.comm = comm;
    }

    /**
     * 获取员工ID
     * @return 员工ID
     */
    public int getEmpno() {
        return empno;
    }

    /**
     * 设置员工ID
     * @param empno 员工ID
     */
    public void setEmpno(int empno) {
        this.empno = empno;
    }

    /**
     * 获取员工姓名
     * @return 员工姓名
     */
    public String getEname() {
        return ename;
    }

    /**
     * 设置员工姓名
     * @param ename 员工姓名
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * 获取员工入职日期
     * @return 员工入职日期
     */
    public Date getHiredate() {
        return hiredate;
    }

    /**
     * 设置员工入职日期
     * @param hiredate 员工入职日期
     */
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * 获取员工职位
     * @return 员工职位
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置员工职位
     * @param job 员工职位
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取员工照片路径
     * @return 员工照片路径
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置员工照片路径
     * @param photo 员工照片路径
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取员工工资
     * @return 员工工资
     */
    public float getSal() {
        return sal;
    }

    /**
     * 设置员工工资
     * @param sal 工资
     */
    public void setSal(float sal) {
        this.sal = sal;
    }

}
