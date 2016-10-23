package cn.edu.zjvtit.empdemo.factory;

/**
 * Created by zeng on 2016-10-22.
 */

import cn.edu.zjvtit.empdemo.dao.IEmpDAO;
import cn.edu.zjvtit.empdemo.dao.proxy.EmpDAOProxy;


public class DAOFactory {

    public static IEmpDAO getIEmpDAOInstance(){
        return new EmpDAOProxy();
    }

}
