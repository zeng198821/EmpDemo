package cn.edu.zjvtit.empdemo.servlet;

/**
 * Created by zeng on 2016-10-22.
 */


import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zjvtit.empdemo.factory.DAOFactory;
import cn.edu.zjvtit.empdemo.util.JsonHelp;
import cn.edu.zjvtit.empdemo.vo.CommData;
import cn.edu.zjvtit.empdemo.vo.Emp;
import cn.edu.zjvtit.empdemo.vo.RespData;


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
        if(p.equals("doUpdatePage")){
            this.doUpdatePage(req, resp);
        }
        if(p.equals("doUpdate")){
            this.doUpdate(req, resp);
        }
        if(p.equals("doDelete")){
            this.doDelete(req, resp);
        }
        if(p.equals("getAllEname")){
            this.getAllEname(req, resp);
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
            tmpdata.setMessage(tmpresult ? "创建用户成功" : "创建用户失败");
        }catch (Exception ex){
            System.out.println(ex.toString());
            tmpdata.setMessage(ex.toString());
        }
        return tmpdata;
    }


    public void doCreate(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        List all=new ArrayList();
        //SmartUpload smart=new SmartUpload();
        Emp emp=new Emp();
        int empno=0;
        String ename=null;
        String job=null;
        Date hiredate=null;
        float sal=0.0f;
        float comm=0.0f;
        String photo="nophoto.jpg";
        try{
//
//            emp.setEmpno(empno);       smart.initialize(this.getServletConfig(),req,resp);         //初始化上传
//            smart.upload();                     //准备上传
//
//            empno=Integer.parseInt(smart.getRequest().getParameter("empno"));
//            ename=smart.getRequest().getParameter("ename");
//            job=smart.getRequest().getParameter("job");
//            hiredate=new SimpleDateFormat("yyyy-mm-dd").parse(smart.getRequest().getParameter("hiredate"));
//            sal=Float.parseFloat(smart.getRequest().getParameter("sal"));
//            comm=Float.parseFloat(smart.getRequest().getParameter("comm"));
//            if(smart.getFiles().getFile(0).getSize()>0){     //判断是否有上传文件
//                IPTimeStamp its=new IPTimeStamp(req.getRemoteAddr());
//                拼凑上传文件名称
//                photo=its.getIPTimeStampRand()+"."+smart.getFiles().getFile(0).getFileExt();
//            }
            emp.setEname(ename);
            emp.setJob(job);
            emp.setHiredate(hiredate);
            emp.setSal(sal);
            emp.setComm(comm);
            emp.setPhoto(photo);
            if(DAOFactory.getIEmpDAOInstance().doCreate(emp)){
//                if(smart.getFiles().getFile(0).getSize()>0){
//                    //添加成功，保存上传文件
//                    smart.getFiles().getFile(0).saveAs(getServletContext().getRealPath("/")+"jsp/upload/"+photo);
//                }
                all.add("职员信息添加成功！");
            }else{
                all.add("职员信息添加失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        req.setAttribute("infoInsert", all);
        req.getRequestDispatcher("jsp/admin/emp/emp_insert_do.jsp").forward(req, resp);
    }
    public void doUpdatePage(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        int empno=0;
        Emp emp=null;
        try{
            empno=Integer.parseInt(req.getParameter("empno"));
            emp=DAOFactory.getIEmpDAOInstance().findEmpById(empno);
        }catch(Exception e){
            e.printStackTrace();
        }
        req.setAttribute("infoUpdatePage", emp);
        req.getRequestDispatcher("jsp/admin/emp/emp_update.jsp").forward(req, resp);
    }
    public void doUpdate(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        List all=new ArrayList();
//        SmartUpload smart=new SmartUpload();
        Emp emp=new Emp();

        int empno=0;
        String ename=null;
        String job=null;
        Date hiredate=null;
        float sal=0.0f;
        float comm=0.0f;
//        String photo=smart.getRequest().getParameter("pic");

        try{
//            smart.initialize(getServletConfig(),req,resp);      //初始化上传
//            smart.upload();                     //准备上传
//
//            empno=Integer.parseInt(smart.getRequest().getParameter("empno"));
//            ename=smart.getRequest().getParameter("ename");
//            job=smart.getRequest().getParameter("job");
//            hiredate=new SimpleDateFormat("yyyy-mm-dd").parse(smart.getRequest().getParameter("hiredate"));
//            sal=Float.parseFloat(smart.getRequest().getParameter("sal"));
//            comm=Float.parseFloat(smart.getRequest().getParameter("comm"));
//            if(smart.getFiles().getFile(0).getSize()>0){
//                IPTimeStamp its=new IPTimeStamp(req.getRemoteAddr());
//                photo=its.getIPTimeStampRand()+"."+smart.getFiles().getFile(0).getFileExt();        //拼凑上传文件名称
//            }
            emp.setEmpno(empno);
            emp.setEname(ename);
            emp.setJob(job);
            emp.setHiredate(hiredate);
            emp.setSal(sal);
            emp.setComm(comm);
//            emp.setPhoto(photo);
            if(DAOFactory.getIEmpDAOInstance().doUpdate(emp)){
//                if(smart.getFiles().getFile(0).getSize()>0){
//                    //保存上传文件
//                    smart.getFiles().getFile(0).saveAs(getServletContext().getRealPath("/")+"jsp/upload/"+photo);
//                }
                all.add("员工信息修改成功！");
            }else{
                all.add("员工信息修改失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        req.setAttribute("infoUpdate", all);
        req.getRequestDispatcher("./emp/emp_update_do.jsp").forward(req, resp);
    }
    public void doDelete(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        int empno=0;
        String photo=null;
        List all=new ArrayList();
        try{
            empno=Integer.parseInt(req.getParameter("empno"));
            photo=req.getParameter("photo");
            System.out.println("photo=="+photo);
            if(DAOFactory.getIEmpDAOInstance().doDelete(empno)){
                if(!(photo.equals("nophoto.jpg"))){
//                    File f=new File(this.getServletContext().getRealPath("/")+"jsp/upload/"+photo);     //找到当前文件
//                    System.out.println("当前文件是否存在=="+f.exists());
//                    if(f.exists()){         //判断当前文件或者文件目录是否存在，则
//                        f.delete();         //则进行删除
//                    }
                }
                all.add("文件删除成功！");
            }else{
                all.add("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        req.setAttribute("infoDelete", all);
        req.getRequestDispatcher("jsp/admin/emp/emp_delete_do.jsp").forward(req, resp);
    }
    public void getAllEname(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("gb2312");
        resp.setContentType("text/html;charset=gb2312");
        String ename=null;
        PrintWriter out=resp.getWriter();
        try{
            System.out.println("++++++后台取javascript传递参数++++++++"+req.getParameter("ename"));
//1.String name = URLDecoder.decode("客户端传输过来的中文字符","UTF-8");
            ename=URLDecoder.decode(req.getParameter("ename"),"gb2312");
            System.out.println("********servlet********"+ename);
            if(DAOFactory.getIEmpDAOInstance().getAllEname(ename)){
                out.print("true");
            }else{
                out.print("false");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }






}
