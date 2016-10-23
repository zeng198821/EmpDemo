<%--
  Created by IntelliJ IDEA.
  User: zeng
  Date: 2016-10-22
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%--
    只需要在需要分页的地方导入此页面即可
<jsp:include page="split_page.jsp">
    <jsp:param name="currentPage" value="<%=currentPage%>"/>
    <jsp:param name="lineSize" value="<%=lineSize%>"/>
    <jsp:param name="allRecorders" value="<%=allRecorders%>"/>
    <jsp:param name="keyWord" value="<%=keyWord%>"/>
    <jsp:param name="searchFlag" value="TRUE"/>
    <jsp:param name="lineSizeFlag" value="TRUE"/>
</jsp:include>
--%>
<html>
<head>
    <title>JSP + Oracle应用</title>
    <style type="text/css">
        body,td{
            font-size:13px ;
        }
    </style>
    <script language="javascript">
        function changeColor(obj,color){
            obj.bgColor = color ;
        }
        function goInsert(thisurl){
            window.open(thisurl,"雇员管理","width=600,height=420,scrollbars=yes,resizable=yes");
        }
        function goUpdate(thisurl){
            window.open(thisurl,"雇员管理","width=600,height=420,scrollbars=yes,resizable=yes");
        }
        function goDelete(thisurl){
            window.open(thisurl,"雇员管理","width=360,height=200,scrollbars=yes,resizable=yes");
        }
    </script>
</head>
<body>
    <%
    // 解决提交时的中文乱码问题
    request.setCharacterEncoding("GBK") ;
%>
    <%
    int currentPage = 1 ;   // 当前所在是第一页
    int lineSize = 5 ;      // 每页显示5条记录
    int allRecorders = 0 ;  // 总记录数，需要计算
    int pageSize = 0 ;      // 总页数，需要计算
    int line[] = {5,10,15,20,25,30,45,50,100} ;
    // 分页要跳转的路径
    String SPURL = "" ;
    // 接收查询内容
    String keyWord = request.getParameter("keyWord") ;
    String searchFlag = "FALSE" ;
    String lineSizeFlag = "FALSE" ;
%>
    <%
    try{
        searchFlag = request.getParameter("searchFlag").toUpperCase() ;
    }catch(Exception e){}
    try{
        lineSizeFlag = request.getParameter("lineSizeFlag").toUpperCase() ;
    }catch(Exception e){}
    if(keyWord==null){
        keyWord = "" ;
    }
%>
    <%
    try{
        // 修改页数
        currentPage = Integer.parseInt(request.getParameter("currentPage")) ;
    }catch(Exception e){}
    try{
        // 修改行数
        lineSize = Integer.parseInt(request.getParameter("lineSize")) ;
    }catch(Exception e){}
    try{
        // 记录数
        allRecorders = Integer.parseInt(request.getParameter("allRecorders")) ;
    }catch(Exception e){}
%>
    <%
    pageSize = (allRecorders + lineSize - 1) / lineSize ;
    if(pageSize==1){
        currentPage = 1 ;
    }
    if(pageSize==0){
        pageSize = 1 ;
    }
%>
<script language="javaScript">
    function go(c){
        document.getElementById("cp").value = c ;
        <%
            if("TRUE".equals(lineSizeFlag)){
        %>
        document.getElementById("ls").value = document.getElementById("lssel").value ;
        <%
            }
        %>
        document.spform.submit() ;  // 提交表单
    }
    function goLs(ls){
        document.getElementById("ls").value = ls ;
        document.getElementById("cp").value = document.getElementById("cpsel").value ;
        document.spform.submit() ;  // 提交表单
    }
    function goS(){
        document.getElementById("cp").value = document.getElementById("cpsel").value ;
        <%
            if("TRUE".equals(lineSizeFlag)){
        %>
        document.getElementById("ls").value = document.getElementById("lssel").value ;
        <%
            }
        %>
    }
</script>
<form action="<%=SPURL%>" name="spform" method="post" onsubmit="goS()">
    <%
        if("TRUE".equals(searchFlag)){
    %>
    输入查询关键字：<input type="text" name="keyword" value="<%=keyWord%>">
    <input class="btn btn-default" type="submit" value="查询"><br>
    <%
        }
    %>
    <input class="btn btn-default" type="button" value="首页" onClick="go(1)" <%=currentPage==1?"disabled":""%>>
    <input class="btn btn-default" type="button" value="上一页" onClick="go(<%=currentPage-1%>)"  <%=currentPage==1?"disabled":""%>>
    <input class="btn btn-default" type="button" value="下一页" onClick="go(<%=currentPage+1%>)" <%=currentPage==pageSize?"disabled":""%>>
    <input class="btn btn-default" type="button" value="尾页" onClick="go(<%=pageSize%>)" <%=currentPage==pageSize?"disabled":""%>>
    跳转到第
    <SELECT name="cpsel" onchange="go(this.value)">
        <%
            for(int i=1;i<=pageSize;i++){
        %>
        <OPTION value="<%=i%>" <%=currentPage==i?"SELECTED":""%>><%=i%></OPTION>
        <%
            }
        %>
    </SELECT>
    页
    <%
        if("TRUE".equals(lineSizeFlag)){
    %>
    每页显示
    <SELECT name="lssel" onChange="goLs(this.value)">
        <%
            for(int i=0;i<line.length;i++){
        %>
        <OPTION value="<%=line[i]%>" <%=lineSize==line[i]?"SELECTED":""%>><%=line[i]%></OPTION>
        <%
            }
        %>
    </SELECT>
    条
    <%
        }
    %>
    <input type="hidden" name="cp" value="">
    <%
        if("TRUE".equals(lineSizeFlag)){
    %>
    <input type="hidden" name="ls" value="">
    <%
        }
    %>
</form>
