<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Title</title>
    <%--引入公共依赖--%>
   <%@include file="/WEB-INF/views/header.jsp"%>
    <script src="/static/js/model/setted/setted.js"></script>
</head>
<body>
<%--datagrid--%>
<table id="settedDatagrid"></table>
<%--toolbar--%>
<div id="toolbar">
   <%-- <a data-cmd="add" href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-add" plain="true">添加</a>
    <a data-cmd="edit" href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-edit" plain="true" >编辑</a>
    <a data-cmd="delete" href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove" plain="true" >删除</a>--%>
       <a data-cmd="back" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-back" plain="true" >返回</a>
       <a data-cmd="xuzhao" href="javascript:void(0)" class="easyui-linkbutton c8" iconCls="icon-remove" plain="true" >取消结算</a>
       <a data-cmd="refresh" href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-reload" plain="true" >刷新</a>
</div>
<%--添加或者编辑的dialog--%>
<div id="settedDlg" class="easyui-dialog" style="width: 400px"
     closed="true" buttons="#dlg-buttons">
    <form id="settedForm" method="post" novalidate style="margin:0;padding:20px 50px">
        <%--编辑隐藏域的处理--%>
        <input type="hidden" name="id" >
        <table>
            <tr>
                <td>
                    客户名称:<input type="text" name="custormerId">
                </td>
            </tr>
            <tr>
                <td>
                    应付金额:<input type="text" name="amountId">
                </td>
            </tr>
            <tr>
                <td>
                    实付金额:<input type="text" name="totalMoney">
                </td>
            </tr>
            <tr>
                <td>
                    支付方式:<input type="text" name="payId">
                </td>
            </tr>
            <tr>
                <td>
                    维修工单号:<input type="text" name="repairorderId">
                </td>
            </tr>
            <tr>
                <td>
                    结算时间:<input type="text" name="settedTime">
                </td>
            </tr>
            <tr>
                <td>
                    地址:<input type="text" name="address">
                </td>
            </tr>

        </table>
    </form>
</div>
<%--dialog的button--%>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" data-cmd="submit" style="width:90px">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton c8" iconCls="icon-cancel" data-cmd="cancel" style="width:90px">取消</a>
</div>
</body>
</html>
