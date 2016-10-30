package cn.edu.zjvtit.empdemo.dao.impl;

/**
 * Created by zeng on 2016-10-22.
 */


import cn.edu.zjvtit.empdemo.dao.IEmpDAO;
import cn.edu.zjvtit.empdemo.vo.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class EmpDAOImpl implements IEmpDAO {

    private Connection conn=null;
    public EmpDAOImpl(Connection conn){
        this.conn=conn;
    }
    //添加用户信息
    public boolean doCreate(Emp emp) throws Exception {
        // TODO Auto-generated method stub
        boolean flag=false;
        PreparedStatement pstmt=null;
        try{
            this.conn.setAutoCommit(false);     //手动提交
            String sql="INSERT INTO emp1(empno,ename,job,hiredate,sal,comm,photo)VALUES(?,?,?,?,?,?,?)";
            pstmt=this.conn.prepareStatement(sql);
            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getEname());
            pstmt.setString(3, emp.getJob());
            pstmt.setDate(4, new java.sql.Date(emp.getHiredate().getTime()));
            pstmt.setFloat(5, emp.getSal());
            pstmt.setFloat(6, emp.getComm());
            pstmt.setString(7, emp.getPhoto());
            int count=pstmt.executeUpdate();
            this.conn.commit();     //提交
            if(count>0){
                flag=true;
            }
        }catch(Exception e){
            this.conn.rollback();
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }
    //删除
    public boolean doDelete(int empno) throws Exception {
        // TODO Auto-generated method stub
        boolean flag=false;
        PreparedStatement pstmt=null;
        try{
            String sql="DELETE FROM emp1 WHERE empno=?";
            pstmt=this.conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            int count=pstmt.executeUpdate();
            if(count>0){
                flag=true;
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }
    //更新
    public boolean doUpdate(Emp emp) throws Exception {
        // TODO Auto-generated method stub
        boolean flag=false;
        PreparedStatement pstmt=null;
        try{
            String sql="UPDATE emp1 SET ename=?,job=?,hiredate=?,sal=?,comm=?,photo=? WHERE empno=?";
            pstmt=this.conn.prepareStatement(sql);
            pstmt.setString(1, emp.getEname());
            pstmt.setString(2, emp.getJob());
            pstmt.setDate(3, new java.sql.Date(emp.getHiredate().getTime()));
            pstmt.setFloat(4, emp.getSal());
            pstmt.setFloat(5, emp.getComm());
            pstmt.setString(6, emp.getPhoto());
            pstmt.setInt(7, emp.getEmpno());
            int count=pstmt.executeUpdate();
            System.out.println("emp_UPDATE_SQL==="+sql);
            if(count>0){
                flag=true;
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }
    //查询全部信息
    public List findAll(int currentPage, int lineSize, String keyword)
            throws Exception {
        // TODO Auto-generated method stub
        List all=new ArrayList();
        PreparedStatement pstmt=null;
        String sql=null;
        if(keyword==null||"".equals(keyword)){
            sql="SELECT  empno,ename,job,hiredate,sal,comm,photo FROM " +
                    "(SELECT  empno,ename,job,hiredate,sal,comm,photo FROM " +
                    "(SELECT  * FROM emp1 ORDER BY empno ASC)temptlb1 ORDER BY empno DESC)temptlb2 ORDER BY empno asc LIMIT " + currentPage +","+ lineSize;
        }else{
            sql="SELECT empno,ename,job,hiredate,sal,comm,photo FROM emp1 WHERE empno LIKE '%"+keyword+"%' " +
                    "OR ename LIKE '%"+keyword+"%' OR job LIKE '%"+keyword+"%' OR hiredate LIKE '%"+keyword+"%'" +
                    " OR sal LIKE '%"+keyword+"%' OR comm LIKE '%"+keyword+"%' ORDER BY empno asc LIMIT " + currentPage +","+ lineSize;
        }
        try{
            pstmt=this.conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs=pstmt.executeQuery();
            Emp emp=null;
            while(rs.next()){
                emp=new Emp();
                emp.setEmpno(rs.getInt(1));
                emp.setEname(rs.getString(2));
                emp.setJob(rs.getString(3));
                emp.setHiredate(rs.getDate(4));
                emp.setSal(rs.getFloat(5));
                emp.setComm(rs.getFloat(6));
                emp.setPhoto(rs.getString(7));
                all.add(emp);
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return all;
    }
    //按ID查询
    public Emp findEmpById(int empno) throws Exception {
        // TODO Auto-generated method stub
        Emp emp=null;
        PreparedStatement pstmt=null;
        try{
            String sql="SELECT empno,ename,job,hiredate,sal,comm,photo FROM emp1 WHERE empno=?";
            pstmt=this.conn.prepareStatement(sql);
            pstmt.setInt(1, empno);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                emp=new Emp();
                emp.setEmpno(rs.getInt(1));
                emp.setEname(rs.getString(2));
                emp.setJob(rs.getString(3));
                emp.setHiredate(rs.getDate(4));
                emp.setSal(rs.getFloat(5));
                emp.setComm(rs.getFloat(6));
                emp.setPhoto(rs.getString(7));
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return emp;
    }
    //查询数据表里  记录集
    public int getAllCount(String keyword) throws Exception {
        // TODO Auto-generated method stub
        int count=0;
        PreparedStatement pstmt=null;
        try{
            String sql="SELECT COUNT(empno) FROM emp1 WHERE empno LIKE ? OR ename LIKE ? " +
                    "OR job LIKE ? OR hiredate LIKE ? OR sal LIKE ? OR comm LIKE ? OR photo LIKE ? ";
            pstmt=this.conn.prepareStatement(sql);
            pstmt.setString(1, "%"+keyword+"%");
            pstmt.setString(2, "%"+keyword+"%");
            pstmt.setString(3, "%"+keyword+"%");
            pstmt.setString(4, "%"+keyword+"%");
            pstmt.setString(5, "%"+keyword+"%");
            pstmt.setString(6, "%"+keyword+"%");
            pstmt.setString(7, "%"+keyword+"%");
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);         //返回数据表里的所有数据集      geeInt(1):方法，查询表里所有数据集
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return count;
    }
    //验证用户名是否存在
    public boolean getAllEname(String ename)throws Exception{
        boolean flag=false;
        PreparedStatement pstmt=null;
        try{
            String sql="SELECT COUNT(ename) FROM emp1 WHERE ename=?";
            pstmt=this.conn.prepareStatement(sql);
            pstmt.setString(1, ename);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)>0){
                    flag=true;
                    System.out.println("impl===flag=="+rs.getInt(1));
                    System.out.println("impl===flag=="+flag);
                }else{
                    flag=false;
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                pstmt.close();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }


}
