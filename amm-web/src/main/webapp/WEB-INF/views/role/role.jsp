<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Title</title>
    <%--引入公共依赖--%>
   <%@include file="/WEB-INF/views/header.jsp"%>
    <script src="/static/js/model/role/role.js"></script>
</head>
<body>
<%--datagrid--%>
<table id="roleDatagrid"></table>
<%--toolbar--%>
<div id="toolbar">
    <a data-method="add" href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-add" plain="true">添加</a>
    <a data-method="update" href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-edit" plain="true" >编辑</a>
    <a data-method="delete" href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove" plain="true" >删除</a>
    <a data-method="refresh" href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-reload" plain="true" >刷新</a>
</div>
<%--添加或者编辑的dialog--%>
<!-- 弹出层，装form表单 -->
<div id="addform" class="easyui-dialog" title="添加/修改" style="width: 920px;height: 615px"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#dlg-buttons',constrain:true">
    <!-- form表单 -->
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="editid">
        名称
        <input type="text" class="easyui-textbox" name="name">
        编号
        <input type="text" class="easyui-textbox" name="sn">
        <div class="easyui-layout" style="width:900px;height:500px;">
            <div data-options="region:'west',split:true" title="已有权限" style="width:50%;height: 100%">
                <table id="mypermission">
                    <thead>
                    <tr>
                        <th data-options="field:'name',width:100,align:'center'">权限名</th>
                        <th data-options="field:'url',width:100,align:'center',formatter:urlformatter">url</th>
                        <th data-options="field:'sn',width:100,align:'center',formatter:snformatter">权限编号</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div data-options="region:'center',title:'添加权限',iconCls:'icon-ok'">
                <table id="permissionAll">
                    <thead>
                    <tr>
                        <th data-options="field:'name',width:100,align:'center'">权限名</th>
                        <th data-options="field:'url',width:100,align:'center',formatter:urlformatter">url</th>
                        <th data-options="field:'sn',width:100,align:'center',formatter:snformatter">权限编号</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </form>
</div>


<%--dialog的button--%>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" data-method="save" style="width:90px">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton c8" iconCls="icon-cancel" data-method="cancel" style="width:90px">取消</a>
</div>
</body>
</html>
