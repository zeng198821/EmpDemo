<%--
  Created by IntelliJ IDEA.
  User: zeng
  Date: 2016-10-22
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="java.util.List,java.util.Iterator" %>
<%@page import="cn.edu.zjvtit.empdemo.vo.Emp" %>
<%@page import="cn.edu.zjvtit.empdemo.dao.proxy.EmpDAOProxy,cn.edu.zjvtit.empdemo.dao.IEmpDAO" %>
<html>
<head>
    <title>职员查询程序</title>
</head>
<style>
    body,td{
        font-size:13px;
    }
</style>
<script type="text/javascript" src="../js/demo.js"></script>
<script type="text/javascript">
    function changeColor(obj,color){
        obj.bgColor=color;
    }
    function goInsert(thisurl){
        window.open(thisurl,"雇员管理","width=600,height=420,scrollbars=yes,resizable=yes");
    }
    function goUpdate(thisurl){
        window.open(thisurl,"雇员管理","width=600,height=420,scrollbars=yes,resizable=yes");
    }
    function goDelete(thisurl){
        window.open(thisurl,"雇员管理","width=600,height=420,scrollbars=yes,resizable=yes");
    }
</script>
<body>
<center>
    <%
        int lineSize=5;         //每页显示5条记录
        int currentPage=1;      //当前第一页
        int allRecorders=0;     //显示数据表里总记录数，需要计算
        List all=null;
        try{
            // 修改页数
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        }catch(Exception e){}
        try{
            // 修改行数
            lineSize = Integer.parseInt(request.getParameter("ls")) ;
        }catch(Exception e){}
    %>
    <h2>职员管理程序</h2>
    <hr>
    <%
        String keyword=request.getParameter("keyword");
        if(keyword==null){
            keyword="";
        }
    %>
    <h2><a href="#" onClick="goInsert('emp_insert.jsp')">添加职员</a></h2>
    <%
        try{
            IEmpDAO dao=new EmpDAOProxy();
            all=dao.findAll(currentPage,lineSize,keyword);      //查询全部
            allRecorders=dao.getAllCount(keyword);          //全部记录数
    %>
    <jsp:include page="split_page.jsp">
        <jsp:param name="currentPage" value="<%=currentPage%>"/>
        <jsp:param name="lineSize" value="<%=lineSize%>"/>
        <jsp:param name="allRecorders" value="<%=allRecorders%>"/>
        <jsp:param name="keyword" value="<%=keyword%>"/>
        <jsp:param name="searchFlag" value="TRUE"/>
        <jsp:param name="lineSizeFlag" value="TRUE"/>
    </jsp:include>
    <table border="1" width="80%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
        <tr>
            <td>职员编号</td>
            <td>职员姓名</td>
            <td>职员职位</td>
            <td>入职日期</td>
            <td>职员工资</td>
            <td>职员奖金</td>
            <td>职员照片</td>
            <td>操作</td>
        </tr>
        <%
            Iterator iter=all.iterator();
            while(iter.hasNext()){
                Emp emp=(Emp)iter.next();
        %>
        <tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
            <td><%=emp.getEmpno() %></td>
            <td><%=emp.getEname() %></td>
            <td><%=emp.getJob() %></td>
            <td><%=emp.getHiredate() %></td>
            <td><%=emp.getSal() %></td>
            <td><%=emp.getComm() %></td>
            <td><img src="../../upload/<%=emp.getPhoto() %>" width="50" height="40"></td>
            <td>
                <a href="#" class="btn btn-default" onclick="goUpdate('<%=request.getContextPath() %>/EmpServlet?p=doUpdatePage&empno=<%=emp.getEmpno() %>')">修改</a>
                <a href="#" class="btn btn-default" onclick="goDelete('<%=request.getContextPath() %>/EmpServlet?p=doDelete&empno=<%=emp.getEmpno() %>&photo=<%=emp.getPhoto() %>')">删除</a>
            </td>
        </tr>
        <%
            }
        %>
        <%
            }catch(Exception e){
                e.printStackTrace();
            }
        %>
    </table>
</center>
</body>
</html>
