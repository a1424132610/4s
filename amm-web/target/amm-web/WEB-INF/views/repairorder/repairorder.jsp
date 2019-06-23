<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Title</title>
    <%--引入公共依赖--%>
   <%@include file="/WEB-INF/views/header.jsp"%>
    <script src="/static/js/model/repairorder/repairorder.js"></script>
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height:350px;background-color: black;">
    <%--datagrid--%>
         <table id="repairorderDatagrid"></table>
    </div>
    <div data-options="region:'center'">
        <iframe id="children" fit="true" style="height: 100%;width: 100%;" src="/repairorderitem/index"></iframe>
    </div>
</div>
<%--toolbar--%>
<div id="toolbar">
    <form id="searchForm">
    <a data-cmd="add" href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-add" plain="true">添加</a>
    <a data-cmd="edit" href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-edit" plain="true" >编辑</a>
    <a data-cmd="delete" href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove" plain="true" >删除</a>
    <a data-cmd="refresh" href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-reload" plain="true" >刷新</a>
    <a data-cmd="query" href="javascript:void(0)" class="easyui-linkbutton c5" iconCls="icon-search" plain="true" >查询明细</a>
    <a data-cmd="addQuery" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-add" plain="true" >添加明细</a>
    <a data-cmd="setted" href="javascript:void(0)" class="easyui-linkbutton c7" iconCls="icon-search" plain="true" >结算</a>

    车牌号： <input type="text" class="easyui-textbox"  name="username" style="width: 100px">
    <a href="javascript:void(0);"  data-cmd="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
    </form>
</div>
<%--添加或者编辑的dialog--%>
<div id="repairorderDlg" class="easyui-dialog" style="width: 400px"
     closed="true" buttons="#dlg-buttons">
    <form id="repairorderForm" method="post" novalidate style="margin:0;padding:20px 50px">
        <%--编辑隐藏域的处理--%>
        <input type="hidden" name="id" >
        <table>
          <%--  <tr>
                <td>
                    维修工单号:<input type="text" name="id">
                </td>
            </tr>--%>
            <tr>
                <td>
                    客户名称&ensp;&ensp;:<input type="text" name="custormer">
                </td>
            </tr>
            <tr>
                <td>
                    车牌号&ensp;&ensp;&ensp;&ensp;:<input type="text" name="carnum">
                </td>
            </tr>
            <%--<tr>
                <td>
                    创建时间:<input type="text" name="createtime" class="easyui-datetimebox">
                </td>
            </tr>--%>
           <%-- <tr>
                <td>
                    状态:<input type="text" name="status">
                </td>
            </tr>--%>
            <tr>
                <td>
                    维修人员&ensp;&ensp;:<input type="text" name="optid.optid"  class="easyui-combobox"
                                data-options="
                                         url: '/util/queryMaintainer',
                                        valueField:'optid',
                                        textField:'optName',
                                        panelHeight:'auto'"
                >
                </td>
            </tr>
            <tr>
                <td>
                    地址&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;:<input type="text" name="address">
                </td>
            </tr>
        </table>
    </form>
</div>
<%--dialog的button--%>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" data-cmd="submit" style="width:90px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-reload" data-cmd="reset" style="width:90px">重置</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c8" iconCls="icon-cancel" data-cmd="cancel" style="width:90px">取消</a>
    </div>

</body>
</html>
