package cn.edu.zjvtit.empdemo.servlet;

/**
 * Created by zeng on 2016-10-22.
 */


import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.StandardSocketOptions;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zjvtit.empdemo.dao.impl.EmpDAOImpl;
import cn.edu.zjvtit.empdemo.factory.DAOFactory;
import cn.edu.zjvtit.empdemo.util.JsonHelp;
import cn.edu.zjvtit.empdemo.vo.*;


public class EmpServlet extends HttpServlet{

    /**
     * 服务回调函数
     * @param req 请求对象
     * @param resp 回发对象
     * @throws ServletException 错误类型
     * @throws IOException 错误类型
     */
    public void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{

        String tmpPostBuffer = readJSONString(req);
        CommData tmpCommData = null;
        try{
            tmpCommData = (CommData) JsonHelp.formatJson(tmpPostBuffer,CommData.class);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        if(tmpCommData == null){
            return;
        }
        String p = tmpCommData.getServersName();
        if(p.isEmpty()){
            return;
        }
        if(p.equals("emp_insert")){
            Emp tmpEmp = null;
            tmpEmp = (Emp)JsonHelp.formatJson(tmpCommData.getSubmitData(),Emp.class);
            doResponse(resp,doCreate(tmpEmp));
        }
        if(p.equals("emp_info")){
            Emp tmpEmp = null;
            tmpEmp = (Emp)JsonHelp.formatJson(tmpCommData.getSubmitData(),Emp.class);
            try {
                tmpEmp = DAOFactory.getIEmpDAOInstance().findEmpById(tmpEmp.getEmpno());
            }catch (Exception ex){
                System.out.printf(ex.toString());
            }
            doResponse(resp,tmpEmp);
        }
        if(p.equals("emp_list")){
            HashMap<String,Object> tmpresult=new HashMap<String,Object>();
            List tmpdata = null;
            QueryParameter tmppara = null;
            int tmpTotal=0;
            tmppara = (QueryParameter)JsonHelp.formatJson(tmpCommData.getSubmitData(),QueryParameter.class);
            try{
                tmpdata = DAOFactory.getIEmpDAOInstance().findAll(tmppara.getNumber(),tmppara.getSize(),tmppara.getKeyValue());
                tmpTotal = DAOFactory.getIEmpDAOInstance().getAllCount(tmppara.getKeyValue());
                tmpresult.put("datalist",tmpdata);
                tmpresult.put("total",tmpTotal);
            }catch (Exception ex){
                System.out.printf(ex.toString());
            }
            doResponse(resp,tmpresult);
        }

        if(p.equals("emp_update")){
            Emp tmpEmp = null;
            tmpEmp = (Emp)JsonHelp.formatJson(tmpCommData.getSubmitData(),Emp.class);
            doResponse(resp,doUpdate(tmpEmp));
        }

        if(p.equals("emp_delete")){
            List<Emp> tmpEmp = null;
            tmpEmp = (List<Emp>)JsonHelp.formatJson(tmpCommData.getSubmitData(),JsonHelp.getCollectionType(List.class,Emp.class));
            doResponse(resp,doDelete(tmpEmp));
        }
    }

    /**
     * 读取客户端提交过来的Json数据
     * @param request HttpServletRequest请求
     * @return 客户端提交的Json数据
     */
    private String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }



    /**
     * 执行回发数据
     * @param resp 回发对象
     * @param respData_para 回发数据对象
     */
    private  void doResponse(HttpServletResponse resp,Object respData_para){
        PrintWriter tmpPrintWriter = null;
        String tmpOutPutStr="";
        try {
            resp.setContentType("text/html;charset=UTF-8");
            tmpPrintWriter = resp.getWriter();
            tmpOutPutStr = JsonHelp.makeJson(respData_para);
            if(tmpPrintWriter != null){
                tmpPrintWriter.write(tmpOutPutStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (tmpPrintWriter != null){
                try {
                    tmpPrintWriter.flush();
                    tmpPrintWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 创建员工
     * @param emp_para 员工对象
     * @return 执行结果
     */
    public RespData doCreate(Emp emp_para){
        RespData tmpdata = new RespData();
        try {
            boolean tmpresult = DAOFactory.getIEmpDAOInstance().doCreate(emp_para);
            tmpdata.setResult(tmpresult);
            tmpdata.setMessage(tmpresult ? "创建员工信息成功" : "创建员工信息失败");
        }catch (Exception ex){
            System.out.println(ex.toString());
            tmpdata.setMessage(ex.toString());
        }
        return tmpdata;
    }

    /**
     *  更新员工信息
     * @param emp_para 员工信息
     * @return 执行结果
     */
    public RespData doUpdate(Emp emp_para){
        RespData tmpdata = new RespData();
        try {
            boolean tmpresult = DAOFactory.getIEmpDAOInstance().doUpdate(emp_para);
            tmpdata.setResult(tmpresult);
            tmpdata.setMessage(tmpresult ? "更新员工信息成功" : "更新员工信息失败");
        }catch (Exception ex){
            System.out.println(ex.toString());
            tmpdata.setMessage(ex.toString());
        }
        return tmpdata;
    }

    /**
     * 删除单个员工
     * @param emp_para 员工信息
     * @return 执行结果
     */
    public RespData doDelete(Emp emp_para){
        RespData tmpdata = new RespData();
        try {
            boolean tmpresult = DAOFactory.getIEmpDAOInstance().doDelete(emp_para.getEmpno());
            tmpdata.setResult(tmpresult);
            tmpdata.setMessage(tmpresult ? "删除员工信息成功" : "删除员工信息失败");
        }catch (Exception ex){
            System.out.println(ex.toString());
            tmpdata.setMessage(ex.toString());
        }
        return tmpdata;
    }

    /**
     * 删除多个员工
     * @param emp_para 员工信息列表
     * @return 执行结果
     */
    public RespData doDelete(List<Emp> emp_para){
        RespData tmpdata = new RespData();
        for (Emp tmp: emp_para ) {
            tmpdata = doDelete(tmp);
            if(!tmpdata.isResult()){
                break;
            }
        }
        return tmpdata;
    }


}
