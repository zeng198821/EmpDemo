package cn.edu.zjvtit.empdemo.dao;

import java.util.List;

import cn.edu.zjvtit.empdemo.vo.Emp;

/**
 * Created by zeng on 2016-10-22.
 */
public interface IEmpDAO {
    public boolean doCreate(Emp emp)throws Exception;
    public boolean doUpdate(Emp emp)throws Exception;
    public boolean doDelete(int empno)throws Exception;
    public List findAll(int currentPage,int lineSize,String keyword)throws Exception;
    public int getAllCount(String keyword)throws Exception;
    public Emp findEmpById(int empno)throws Exception;
    public boolean getAllEname(String ename)throws Exception;
}
