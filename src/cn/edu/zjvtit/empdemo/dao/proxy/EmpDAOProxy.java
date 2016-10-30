package cn.edu.zjvtit.empdemo.dao.proxy;

/**
 * Created by zeng on 2016-10-22.
 */


import java.util.List;
import cn.edu.zjvtit.empdemo.dao.IEmpDAO;
import cn.edu.zjvtit.empdemo.dao.impl.EmpDAOImpl;
import cn.edu.zjvtit.empdemo.dbc.DatabaseConnection;
import cn.edu.zjvtit.empdemo.vo.Emp;


public class EmpDAOProxy implements IEmpDAO {

    private DatabaseConnection dbc=null;
    private IEmpDAO dao=null;
    int count=0;
    public EmpDAOProxy(){
        try{
            this.dbc=new DatabaseConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        this.dao=new EmpDAOImpl(this.dbc.getConnection());
    }

    public boolean doCreate(Emp emp) throws Exception {
        // TODO Auto-generated method stub
        boolean flag=false;
        try{
            if(this.dao.findEmpById(emp.getEmpno())==null){
                flag=this.dao.doCreate(emp);            //调用真实主题类
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                this.dbc.closeConnection();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }

    public boolean doDelete(int empno) throws Exception {
        // TODO Auto-generated method stub
        boolean flag=false;
        try{
            flag=this.dao.doDelete(empno);              //调用真实主题类
        }catch(Exception e){
            throw e;
        }finally{
            try{
                this.dbc.closeConnection();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }

    public boolean doUpdate(Emp emp) throws Exception {
        // TODO Auto-generated method stub
        boolean flag=false;
        try{
            flag=this.dao.doUpdate(emp);            //调用真实主题类
        }catch(Exception e){
            throw e;
        }finally{
            try{
                this.dbc.closeConnection();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }

    public List findAll(int currentPage, int lineSize, String keyword)
            throws Exception {
        // TODO Auto-generated method stub
        List all=null;
        try{
            all=this.dao.findAll(currentPage, lineSize, keyword);       //调用真实主题类
            count=this.dao.getAllCount(keyword);        //取得最大记录数   //调用真实主题类
        }catch(Exception e){
            throw e;
        }finally{
            try{
                this.dbc.closeConnection();
            }catch(Exception e){
                throw e;
            }
        }
        return all;
    }

    public Emp findEmpById(int empno) throws Exception {
        // TODO Auto-generated method stub
        Emp emp=null;
        try{
            emp=this.dao.findEmpById(empno);            //调用真实主题类
        }catch(Exception e){
            throw e;
        }finally{
            try{
                this.dbc.closeConnection();
            }catch(Exception e){
                throw e;
            }
        }
        return emp;
    }

    public int getAllCount(String keyword) throws Exception {
        // TODO Auto-generated method stub
        count=this.dao.getAllCount(keyword);
        return this.count;
    }

    public boolean getAllEname(String ename)throws Exception{
        boolean flag=false;
        try{
            flag=this.dao.getAllEname(ename);
        }catch(Exception e){
            throw e;
        }finally{
            try{
                this.dbc.closeConnection();
            }catch(Exception e){
                throw e;
            }
        }
        return flag;
    }


}
