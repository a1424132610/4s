var repairorderitemDatagrid;
var repairorderitemDlg;
var  repairorderitemForm;
var settedDlg;
var  settedForm;
$(function () {
    //变量的抽取:
     repairorderitemDatagrid=$('#repairorderitemDatagrid');
     repairorderitemDlg=$('#repairorderitemDlg');
     repairorderitemForm=$('#repairorderitemForm');
     settedDlg=$('#settedDlg');
     settedForm=$('#settedForm');

    //datagrid
    repairorderitemDatagrid.datagrid({
       /* url: '/repairorderitem/page',*/
        fit:true,
        title:'维修工单明细',
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        toolbar:"#toolbar",
        columns: [[
            /*使用遍历:遍历的是Velocity中上下文的对象:
            * fieldList
            *                  {field: 'id', title: 'id', width: '10%',align:  'center'},
            * */
           {field: 'opid', title: '维修工单号', width: '14%',align:  'center'},
           {field: 'mainid', title: '维修员', width: '14%',align:  'center'},
           {field: 'pid', title: '维修配件', width: '16%',align:  'center'},
           {field: 'amt1', title: '配件价格', width: '14%',align:  'center'},
           {field: 'amt2', title: '工时费', width: '14%',align:  'center'},
           {field: 'num', title: '配件数量', width: '14%',align:  'center'},
           {field: 'totalamt', title: '总金额', width: '14%',align:  'center',
               formatter:function (v, r) {
                    if(r.num && r.amt1){
                        return (r.amt1*r.num)+r.amt2;
                    }
                    return 0;
               }},
        ]]
    });

    //按钮的事件
    var cmdObj={
        'add':function () {
            //弹出一个dialog,里面装form表单  repairorderitemDlg
            repairorderitemDlg.dialog('open').dialog('center').dialog('setTitle','明细添加');
            //清空表单
            repairorderitemForm.form('clear');
        },
        'edit':function () {
            //先获取编辑的数据
            var row = repairorderitemDatagrid.datagrid('getSelected');
            //做一个判断:是否选中
            if (row){
                //弹出dialog,进行数据的回显
                //弹出一个dialog,里面装form表单  repairorderitemDlg
                repairorderitemDlg.dialog('open').dialog('center').dialog('setTitle','明细修改');
                //清空表单
                repairorderitemForm.form('clear');
                //表单数据的回显:
                repairorderitemForm.form('load',row);
            }else{
                $.messager.alert('温馨提示','请选中需要编辑的行!!','warning');
            }
        },
        'delete':function () {
            //先获取删除的数据
            var row = repairorderitemDatagrid.datagrid('getSelected');
            //做一个判断:是否选中
            if (row){
                $.messager.confirm('温馨提示','你确定删除:[<font color="pink">'+row.name+"</font>]吗?",function(r){
                    // 选中:确认的操作:调用后台的删除方法:发送ajax调用
                    if (r){
                        // 发送ajax调用 $.get(url,params,function(d){},type)
                        $.get('/repairorderitem/delete?id='+row.id,function (d) {
                            //d的处理
                          if(d.success){
                              //true:成功
                              $.messager.alert('操作提示',d.msg,'info');
                              //页面刷新
                              cmdObj.refresh();
                          }else{
                              //false:失败:提示
                              $.messager.alert('错误提示',d.msg,'error');
                          }
                        },'json')
                    }
                });
            }else{
                $.messager.alert('温馨提示','请选中需要删除的行!!','warning');
            }
        },
        'refresh':function () {
            //datagrid的重新加载: $("selector").datagrid('funName');
            repairorderitemDatagrid.datagrid('reload');
        },
        submit:function(){
            //提交:表单的提交:
            repairorderitemForm.form('submit', {
                    url:'/repairorderitem/saveOrUpdate',
                onSubmit: function(){
            },
            success:function(data){
             // {"success":true,"msg":"操作成功"}==>json字符串
                var $data = $.parseJSON(data);
                //弹框提示和刷新
                if($data.success){
                    $.messager.alert('操作提示',$data.msg,'info');
                }else{
                    $.messager.alert('错误提示',$data.msg,'error');
                }
                repairorderitemDlg.dialog('close');
                //页面刷新
                cmdObj.refresh();
                loadDate($("#oid").val());

            }
        });
        },
        'cancel':function(){
            //关闭dialog,不清空form,在打开dialog的时候清空
            repairorderitemDlg.dialog('close');
        },'remove':function(){
            //关闭dialog,不清空form,在打开dialog的时候清空
            settedDlg.dialog('close');
        },
        save:function () {
            //提交:表单的提交:
            settedForm.form('submit', {
                url:'/setted/saveOrUpdate',
                onSubmit: function(){
                },
                success:function(data){
                    // {"success":true,"msg":"操作成功"}==>json字符串
                    var $data = $.parseJSON(data);
                    //弹框提示和刷新
                    if($data.success){
                        $.messager.alert('操作提示',$data.msg,'info');
                        $.get("/util/updateZt?id="+id,function () {
                        });
                    }else{
                        $.messager.alert('错误提示',$data.msg,'error');
                    }
                    settedDlg.dialog('close');
                    //页面刷新
                    cmdObj.refresh();
                    $.get("/util/queryRepairorderitem?id="+id,function (res) {
                        if(res["items"]){
                            var newRes = res.items;
                            for(var i in newRes){
                                newRes[i].opid = id;
                                newRes[i].mainid = newRes[i].mainid.optName;
                                newRes[i].pid = newRes[i].pid.partsName;
                            }
                            return repairorderitemDatagrid.datagrid("loadData",newRes);
                        }
                    });
                    // window.open("http://127.0.0.1/setted/index");
                }
            });
        },
        showSetted:function () {
            window.location.href="/setted/index";
        }
    };

    //toolbar绑定事件:如果按钮禁用了怎么办?下午具体实现
    $("a[data-cmd]").on('click',function () {
        //data-cmd="add"
        var dataCmd=$(this).data('cmd');
        cmdObj[dataCmd]();
    });
});

function loadDate(id) {
        $.get("/util/queryRepairorderitem?id="+id,function (res) {
            if(res["items"].length!=0){
                var newRes = res.items;
                for(var i in newRes){
                    newRes[i].opid = id;
                    newRes[i].mainid = newRes[i].mainid.optName;
                    newRes[i].pid = newRes[i].pid.partsName;
                }
                return repairorderitemDatagrid.datagrid("loadData",newRes);
            }else {
                $.messager.alert('操作提示',"没有对应的明细",'info');
                var newRes = res.items;
                for(var i in newRes){
                    newRes[i].opid = id;
                    newRes[i].mainid = newRes[i].mainid.optName;
                    newRes[i].pid = newRes[i].pid.partsName;
                }
                return repairorderitemDatagrid.datagrid("loadData",newRes);
            }
        });
    }
/*添加维修明细*/
function addDate(id) {
    //弹出一个dialog,里面装form表单  repairorderitemDlg
    repairorderitemDlg.dialog('open').dialog('center').dialog('setTitle','明细添加');
    //清空表单
    repairorderitemForm.form('clear');
    $("#oid").val(id);
}
var id;
function setteds(row) {
    id = row.id;
    loadDate(id);
    //
    $.get("/util/queryRepairorderitem?id="+id,function (res) {
        var total=0;
        for(var i in res.items){
            var item = res.items[i];
            total += (item["num"])*(item["amt1"])+(item["amt2"]);
        }
        row.totalMoney=total;
        row.amountId=total;
        row.repairorderId=row.id;
        row.custormerId=row.custormer;
        var payId=0;
        row.payId=payId
        row.zt=false;
        //console.debug(res.items[0]["num"]);
        if(res["items"].length!=0){
            //弹出一个dialog,里面装form表单  settedDlg
            settedDlg.dialog('open').dialog('center').dialog('setTitle','结算单');
            //清空表单
            settedForm.form('clear');
            //表单数据的回显:
            //console.debug(row);
            settedForm.form('load',row);
        }
    });
}

