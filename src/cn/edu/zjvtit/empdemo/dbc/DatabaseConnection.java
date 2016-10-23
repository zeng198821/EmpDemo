package cn.edu.zjvtit.empdemo.dbc;

/**
 * Created by zeng on 2016-10-22.
 */

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

    private static final String DBDRIVER="com.mysql.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/zjvtit";
    private static final String DBUSER="root";
    private static final String DBPASSWORD="password";
    private Connection conn=null;
    public DatabaseConnection(){
        try{
            Class.forName(DBDRIVER);
            this.conn=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.conn;
    }
    public void closeConnection(){
        if(this.conn!=null){
            try{
                this.conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
