<%--
  Created by IntelliJ IDEA.
  User: zeng
  Date: 2016-10-22
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="cn.edu.zjvtit.empdemo.vo.Emp" %>
<html>
<head>
    <title>更新页面</title>
</head>
<script type="text/javascript" src="../js/demo.js"></script>
<script type="text/javascript">
    function closeWin(){
        window.close();
    }
</script>
<body>
<center>
    <%
        Emp emp=(Emp)request.getAttribute("infoUpdatePage");
        if(emp!=null){
    %>

    <form action="<%=request.getContextPath() %>/EmpServlet?p=doUpdate" method="post" enctype="multipart/form-data">
        <table border="1" width="80%">
            <tr>
                <td colspan="2" align="center"><h2>修改职员</h2></td>
            </tr>
            <tr>
                <td>职员编号</td>
                <td><%=emp.getEmpno() %></td>
            </tr>
            <tr>
                <td>职员姓名</td>
                <td><input type="text" name="ename" value="<%=emp.getEname() %>"></td>
                <td rowspan="6"><img src="../../upload/<%=emp.getPhoto() %>" width="100" height="155"></td>
            </tr>
            <tr>
                <td>职员职位</td>
                <td><input type="text" name="job" value="<%=emp.getJob() %>"></td>
            </tr>
            <tr>
                <td>入职日期</td>
                <td>
                    <input type="text" name="hiredate" size="15" maxlength="15"
                           onclick='popUpCalendar(this,this,"yyyy-mm-dd")' readonly="true" value="<%=emp.getHiredate() %>">
                </td>
            </tr>
            <tr>
                <td>职员工资</td>
                <td><input type="text" name="sal" value="<%=emp.getSal() %>"></td>
            </tr>
            <tr>
                <td>职员奖金</td>
                <td><input type="text" name="comm" value="<%=emp.getComm() %>"></td>
            </tr>
            <tr>
                <td>职员照片</td>
                <td>
                    <input type="file" name="pic"><br>
                    <font color="red" size="2">如果不想上传，可以不选！</font>
                </td>

            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="hidden" name="empno" value="<%=emp.getEmpno() %>">
                    <input type="hidden" name="pic" value="<%=emp.getPhoto() %>">
                    <input type="submit" value="提交">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
    <%
        }
    %>
    <a href="#" onclick="closeWin()">关闭窗口</a>
</center>
</body>
</html>
