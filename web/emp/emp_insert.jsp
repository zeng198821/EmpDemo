<%--
  Created by IntelliJ IDEA.
  User: zeng
  Date: 2016-10-22
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工添加页面</title>
</head>
<script type="text/javascript" src="../js/demo.js"></script>

<style type="text/css" >
    input{
        border-radius: 4px;
    }
</style>
<body>
<div style="width: 500px;text-align: center;">
    <form id="mainForm" class="form-horizontal"  role="form">
        <h2>添加员工</h2>

        <div class="form-group">
            <label for="empno" class="col-md-4 control-label">员工编号</label>
            <div class="col-md-6">
                <input type="text" class="form-control" field="empno" id="empno" placeholder="请输入员工编号...">
            </div>
        </div>

        <div class="form-group" >
            <label for="empname" class="col-md-4 control-label">员工姓名</label>
            <div class="col-md-6">
                <input type="text" class="form-control" field="ename" id="empname" placeholder="请输入员工姓名...">
            </div>
        </div>


        <div class="form-group">
            <label for="job" class="col-md-4 control-label">职位</label>
            <div class="col-md-6">
                <input type="text" class="form-control" field="job" id="job" placeholder="请输入职位...">
            </div>
        </div>


        <div class="form-group">
            <label for="hiredate" class="col-md-4 control-label">入职日期</label>
            <div class="col-md-6">
                <input type="text" class="form-control datepicker" field="hiredate" id="hiredate" placeholder="请选择入职日期..." readonly />
            </div>
            <%--<div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-MM-dd" data-link-field="hiredate" data-link-format="yyyy-mm-dd">--%>
                <%--<input class="form-control" size="16" type="text" value=""  name="hiredatea" id="hiredate" readonly placeholder="请选择入职日期...">--%>
                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
            <%--</div>--%>
        </div>

        <div class="form-group">
            <label for="sal" class="col-md-4 control-label">工资</label>
            <div class="col-md-6">
                <input type="text" class="form-control" field="sal" id="sal" placeholder="请输入员工工资...">
            </div>
        </div>

        <div class="form-group">
            <label for="comm" class="col-md-4 control-label">奖金</label>
            <div class="col-md-6">
                <input type="text" class="form-control" field="comm" id="comm" placeholder="请输入员工奖金...">
            </div>
            <input type="text" hidden value="nophoto.jpg" field="photo">
        </div>

        <button type="submit" onclick="Function_EmpInsert.saveData()" class="btn btn-primary">提交</button>

        <button type="reset" class="btn btn-warning">重置</button>
    </form>
</div>
<script type="application/javascript">

    /**
     * 页面常量信息
     */
    var Const_EmpInsert = {
        /**
         * 主数据表单ID
         */
        dataFormId:"mainForm",
        /**
         * 新增信息提交服务名
         */
        addServersName:"emp_insert",
        /**
         * 修改信息提交服务名
         */
        editServersName:"emp_update",
        /**
         * 提交的服务Url
         */
        serversUrl:bootPATH + "EmpServlet"

    };
    /**
     * 页面变量信息
     */
    var Variable_EmpInsert = {
        /**
         * 页面编辑类型 add:新增模式 edit:编辑模式 view:查看模式 none:未知模式
         */
        pagetype : "add",
    };

    /**
     * 页面函数信息
     */
    var Function_EmpInsert = {
        /**
         * 初始化页面数据
         */
        init : function (){
            //初始化日期选择控件
            $('.datepicker').datetimepicker({
                language:  'zh-CN',clearBtn:true,todayBtn:true,autoclose:true,
                todayHighlight: 1,startView: 2,minView: 2,forceParse: 0,
                format: "yyyy-mm-dd"
            });
        },

        /**
         * 保存数据至数据库
         */
        saveData : function () {
                var tmpSubmitData = zeng.packetSubmitData(Variable_EmpInsert.pagetype == "add" ? Const_EmpInsert.addServersName :Const_EmpInsert.editServersName
                        ,zeng.form.getformdata(Const_EmpInsert.dataFormId));
                zeng.request.requestAjax(Const_EmpInsert.serversUrl,tmpSubmitData,null,null,null,
                function (data_para) {
                    console.log("success");
                },function (data_para) {
                            console.log("error");
                        })

        },
        /**
         * 检查姓名是否重复
         * @param Name_para 姓名
         */
        checkName : function (Name_para) {

        }

    };
    /**
     * 页面事件信息
     */
    var Event_EmpInsert = {

    };
    Function_EmpInsert.init();



</script>
</body>
</html>

