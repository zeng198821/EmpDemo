package cn.edu.zjvtit.empdemo.util;

/**
 * Created by zeng on 2016-10-22.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class IPTimeStamp {

    private String ip=null;
    public IPTimeStamp(String ip){
        this.ip=ip;
    }
    //上传文件命名：IP+时间戳+3位随机数
    public String getIPTimeStampRand(){
        StringBuffer buf=new StringBuffer();
        //添加当前IP地址
        if(this.ip!=null){
            String str[]=this.ip.split("\\.");      //进行拆分IP地址
            for(int i=0;i<str.length;i++){
                buf.append(this.addZero(str[i], 3));    //位数不够3位的，进行补0操作
            }
        }
        //添加时间戳
        buf.append(this.getTimeStamp());
        //添加3为随机数
        Random ran=new Random();
        for(int i=0;i<3;i++){        //循环3次
            buf.append(ran.nextInt(10));        //取得一位0到10之间的随机整数
        }
        return buf.toString();
    }
    //补0操作
    public String addZero(String str,int len){
        StringBuffer buf=new StringBuffer();
        buf.append(str);
        while(buf.length()<len){
            buf.insert(0, "0");         //进行添加0操作
        }
        return buf.toString();
    }
    //时间戳
    public String getTimeStamp(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");     //取得当前时间
        return sdf.format(new Date());
    }


}
