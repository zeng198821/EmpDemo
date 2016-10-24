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

<style type="text/css" >
    input{
        border-radius: 4px;
    }

</style>

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
<div style="width: 500px;text-align: center;">
    <form class="form-horizontal"  role="form">
        <h2>添加职员</h2>

        <div class="form-group">
            <label for="empno" class="col-md-4 control-label">员工编号</label>
            <div class="col-md-6 input-group">
                <input type="text" class="form-control" name="empno" id="empno" placeholder="请输入员工编号...">
            </div>
        </div>

        <div class="form-group" >
            <label for="empname" class="col-md-4 control-label">员工姓名</label>
            <div class="col-md-6 input-group">
                <input type="text" class="form-control" name="empname" id="empname" placeholder="请输入员工姓名...">
            </div>
        </div>


        <div class="form-group">
            <label for="job" class="col-md-4 control-label">职位</label>
            <div class="col-md-6 input-group">
                <input type="text" class="form-control" name="job" id="job" placeholder="请输入职位...">
            </div>
        </div>


        <div class="form-group">
            <label for="hiredate" class="col-md-4 control-label">入职日期</label>
            <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-MM-dd" data-link-field="hiredate" data-link-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" value=""  name="hiredatea" id="hiredate" readonly placeholder="请选择入职日期...">
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>

        <div class="form-group">
            <label for="sal" class="col-md-4 control-label">工资</label>
            <div class="col-md-6 input-group">
                <input type="text" class="form-control" name="sal" id="sal" placeholder="请输入员工工资...">
            </div>
        </div>

        <div class="form-group">
            <label for="comm" class="col-md-4 control-label">奖金</label>
            <div class="col-md-6 input-group">
                <input type="text" class="form-control" name="comm" id="comm" placeholder="请输入员工奖金...">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">提交</button>

        <button type="reset" class="btn btn-warning">重置</button>
    </form>
    <a href="#" onclick="closeWin()">关闭窗口</a>
</div>
<script type="application/javascript">
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

</script>
</body>
</html>

