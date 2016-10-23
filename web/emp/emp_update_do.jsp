<%--
  Created by IntelliJ IDEA.
  User: zeng
  Date: 2016-10-22
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="java.util.List,java.util.Iterator" %>
<html>
<head>
    <title>修改</title>
    <script type="text/javascript" src="../js/demo.js"></script>
</head>
<script type="text/javascript">
    opener.window.location.reload() ;       //重新读取，刷新
    function closeWin(){
        window.close();
    }
</script>
<body>
<center>
    <h2>职员管理程序</h2>
    <hr>
    <%
        List all=(List)request.getAttribute("infoUpdate");
        Iterator iter=all.iterator();
        while(iter.hasNext()){
    %>
    <h2><%=iter.next() %></h2>
    <%
        }
    %>
    <h2><a href="#" onclick="closeWin()">关闭窗口</a></h2>
</center>
</body>
</html>
