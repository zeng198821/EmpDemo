package cn.edu.zjvtit.empdemo.test;

/**
 * Created by zeng on 2016-10-22.
 */


import java.util.Date;

import cn.edu.zjvtit.empdemo.vo.Emp;
import cn.edu.zjvtit.empdemo.factory.DAOFactory;


public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Emp emp=new Emp();
        emp.setEmpno(79);
        emp.setEname("lzch");
        emp.setJob("工程师");
        emp.setHiredate(new Date());
        emp.setSal(5555.5f);
        emp.setComm(350.9f);
        emp.setPhoto("nophoto.jpg");
        try{
            if(DAOFactory.getIEmpDAOInstance().doCreate(emp)){
                System.out.println("插入成功！");
            }else{
                System.out.println("插入失败！");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
