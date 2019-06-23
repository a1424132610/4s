<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%--引入公共依赖--%>
   <%@include file="/WEB-INF/views/header.jsp"%>
    <script src="/static/js/model/repairorderitem/repairorderitem.js"></script>
</head>
<body>
<%--datagrid--%>
<table id="repairorderitemDatagrid"></table>
<%--toolbar--%>
<div id="toolbar">
    <%--<a data-cmd="add" href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-add" plain="true">添加</a>
    <%--<a data-cmd="edit" href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-edit" plain="true" >编辑</a>--%>
    <a data-cmd="showSetted" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-show" plain="true" >查看结算单</a>
    <%--<a data-cmd="refresh" href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-reload" plain="true" >刷新</a>--%>
</div>
<%--添加或者编辑的dialog--%>
<div id="repairorderitemDlg" class="easyui-dialog" style="width: 400px;z-index: 99999999"
     closed="true" buttons="#dlg-buttons">
    <form id="repairorderitemForm" method="post" novalidate style="margin:0;padding:20px 50px">
        <%--编辑隐藏域的处理--%>
        <input type="hidden" name="id" >
            <input id="oid" type="hidden" name="opid" >
        <table>
           <%-- <tr>
                <td>
                    维修工单号:<input type="text" name="opid">
                </td>
            </tr>--%>
            <tr>
                <td>
                    维修员&ensp;&ensp;&ensp;&ensp;:<input type="text" name="mainid.optid"  class="easyui-combobox"
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
                    维修配件&ensp;&ensp;:<input type="text" name="pid.id"  class="easyui-combobox"
                                            data-options="
                                         url: '/util/queryParts',
                                        valueField:'id',
                                        textField:'partsName',
                                        panelHeight:'auto'"
                >
                </td>
            </tr>

            <tr>
                <td>
                    配件价格&ensp;&ensp;:<input type="text" name="amt1">
                </td>
            </tr>
            <tr>
                <td>
                    工时费&ensp;&ensp;&ensp;&ensp;:<input type="text" name="amt2">
                </td>
            </tr>
            <tr>
                <td>
                    配件数量&ensp;&ensp;:<input type="text" name="num">
                </td>
            </tr>
            <%--<tr>
                <td>
                    总金额:<input type="text" name="totalamt">
                </td>
            </tr>--%>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" data-cmd="submit" style="width:90px">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton c8" iconCls="icon-cancel" data-cmd="cancel" style="width:90px">取消</a>
</div>


<%--dialog的button--%>
<%--结算单--%>
<div id="settedDlg" class="easyui-dialog" style="width: 400px"
     closed="true" buttons="#setted-buttons">
    <form id="settedForm" method="post" novalidate style="margin:0;padding:20px 50px">
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
                    支付方式:<select class="easyui-combobox" name="payId" style="width: 147px"
                                   data-options="panelHeight:'auto'" >
                                    <option value="0">微信支付</option>
                                    <option value="-1">支付宝支付</option>
                                    <option value="1">银行卡支付</option>
                             </select>
                </td>
            </tr>
            <tr>
                <td>
                    维修单号:<input type="text" name="repairorderId">
                </td>
            </tr>
            <tr>
                <td>
                    地址&ensp;&ensp;&ensp;&ensp;:<input type="text" name="address">
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="setted-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-ok" data-cmd="save" style="width:90px">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-cancel" data-cmd="remove" style="width:90px">取消</a>
</div>
</body>
</html>
