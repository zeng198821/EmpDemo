<%--
  Created by IntelliJ IDEA.
  User: zeng
  Date: 2016-10-22
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>职员注册页面</title>
</head>
<script type="text/javascript" src="../js/demo.js"></script>
<script type="text/javascript">
    var xmlHttp;
    var flag;
    function createXMLHttp(){
        if(window.XMLHttpRequest){
            xmlHttp=new XMLHttpRequest();
        }else{
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    function checkEname(ename){
        createXMLHttp();
        encodeURI(encodeURI(ename));//解决中文乱码问题两次编码

        xmlHttp.open("POST","<%=request.getContextPath() %>/EmpServlet?p=getAllEname&ename="+ename);      //设置一个请求，
        alert("ename="+ename);
        xmlHttp.onreadystatechange=checkEnameCallback;      //设置请求完成之后处理的回调函数
        xmlHttp.send(null);             //发送请求，不传递任何参数
        document.getElementById("msg").innerHTML="正在验证.....";

    }
    function checkEnameCallback(){
        if(xmlHttp.readyState==4){
            alert("status==="+xmlHttp.status);
            if(xmlHttp.status==200){
                alert("responseText=="+xmlHttp.responseText);
//              var text=xmlHttp.responseText;      //接受返回的内容
                var text=xmlHttp.responseText;
                if(text=="true"){
                    flag=false;
                    document.getElementById("msg").innerHTML="重复的用户名，请更换用户名！";
                }else{
                    flag=true;
                    document.getElementById("msg").innerHTML="此用户名可以注册！";
                }
            }
        }
    }
    function checkForm(){
        return flag;
    }
    //关闭窗口
    function closeWin(){
        window.close();
    }
</script>
<body>
<%
    request.setCharacterEncoding("gb2312");
%>
<script type="text/javascript">
    var xmlHttp;
    var flag;
    function createXMLHttp(){
        if(window.XMLHttpRequest){
            xmlHttp=new XMLHttpRequest();
        }else{
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    function checkEname(ename){
        createXMLHttp();
        alert("ename="+ename);
        xmlHttp.open("POST","<%=request.getContextPath() %>/EmpServlet?p=getAllEname&ename="+ename);      //设置一个请求，
        xmlHttp.onreadystatechange=checkEnameCallback;      //设置请求完成之后处理的回调函数
        xmlHttp.send(null);             //发送请求，不传递任何参数
        document.getElementById("msg").innerHTML="正在验证.....";

    }
    function checkEnameCallback(){
        if(xmlHttp.readyState==4){
            alert("status==="+xmlHttp.status);
            if(xmlHttp.status==200){
                alert("responseText=="+xmlHttp.responseText);
                var text=xmlHttp.responseText;      //接受返回的内容
                if(text=="true"){
                    flag=false;
                    document.getElementById("msg").innerHTML="重复的用户名，请更换用户名！";
                }else{
                    flag=true;
                    document.getElementById("msg").innerHTML="此用户名可以注册！";
                }
            }
        }
    }
    function checkForm(){
        return flag;
    }
    //关闭窗口
    function closeWin(){
        window.close();
    }
</script>
<center>
    <form action="<%=request.getContextPath() %>/EmpServlet?p=emp_insert" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
        <table border="1" width="80%">
            <tr>
                <td colspan="2" align="center"><h2>添加职员</h2></td>
            </tr>
            <tr>
                <td>职员编号</td>
                <td><input type="text" name="empno"></td>
            </tr>
            <tr>
                <td>职员姓名</td>
                <td><input type="text" name="ename" onblur="checkEname(this.value)"><span id="msg"></span></td>
            </tr>
            <tr>
                <td>职员职位</td>
                <td><input type="text" name="job"></td>
            </tr>
            <tr>
                <td>入职日期</td>
                <td>
                    <input type="text" name="hiredate" size="15" maxlength="15"
                           onclick='popUpCalendar(this,this,"yyyy-mm-dd")' readonly="true">
                </td>
            </tr>
            <tr>
                <td>职员工资</td>
                <td><input type="text" name="sal"></td>
            </tr>
            <tr>
                <td>职员奖金</td>
                <td><input type="text" name="comm"></td>
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
                    <input type="submit" class="btn btn-primary" value="提交">
                    <input type="reset" class="btn btn-warning" value="重置">
                </td>
            </tr>
        </table>
    </form>
    <a href="#" onclick="closeWin()">关闭窗口</a>
</center>
</body>
</html>

