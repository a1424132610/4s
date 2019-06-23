$(function () {
    //变量的抽取:
     var repairorderDatagrid=$('#repairorderDatagrid');
     var repairorderDlg=$('#repairorderDlg');
     var  repairorderForm=$('#repairorderForm');
    //datagrid
    repairorderDatagrid.datagrid({
        url: '/repairorder/page',
        fit:true,
        title:'维修单管理',
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        toolbar:"#toolbar",
        columns: [[
            /*使用遍历:遍历的是Velocity中上下文的对象:
            * fieldList
            *                  {field: 'id', title: 'id', width: '10%',align:  'center'},
            * */
           {field: 'id', title: '维修工单号', width: '14%',align:  'center'},
           {field: 'custormer', title: '客户名称', width: '14%',align:  'center'},
           {field: 'carnum', title: '车牌号', width: '14%',align:  'center'},
           {field: 'createtime', title: '创建时间', width: '16%',align:  'center'},
           {field: 'status', title: '状态', width: '14%',align:  'center',
               formatter:function (v,r) {
                    if(v){
                        return "<span style='color: #00ee00'>已录入</span>"
                    }
                    return "<span style='color: grey'><s>未录入</s></span>"
               }},
           {field: 'optid', title: '维修人员', width: '14%',align:  'center',
               formatter:function (v,r) {
                if(v){
                    return v.optName;
                }
                return "";
               }},
           {field: 'address', title: '地址', width: '14%',align:  'center'},
        ]]
    });

    //按钮的事件
    var cmdObj={
        'add':function () {
            //弹出一个dialog,里面装form表单  repairorderDlg
            repairorderDlg.dialog('open').dialog('center').dialog('setTitle','添加维修单');
            //清空表单
            repairorderForm.form('clear');
        },
        'edit':function () {
            //先获取编辑的数据
            var row = repairorderDatagrid.datagrid('getSelected');
            //做一个判断:是否选中
            if (row){
                //弹出dialog,进行数据的回显
                //弹出一个dialog,里面装form表单  repairorderDlg
                repairorderDlg.dialog('open').dialog('center').dialog('setTitle','修改维修单');
                //清空表单
                repairorderForm.form('clear');
                if(row.optid){
                    row['optid.optid']=row.optid.optid;
                }
                //表单数据的回显:
                repairorderForm.form('load',row);
            }else{
                $.messager.alert('温馨提示','请选中需要编辑的行!!','warning');
            }
        },
        'delete':function () {
            //先获取删除的数据
            var row = repairorderDatagrid.datagrid('getSelected');
            //做一个判断:是否选中
            if (row){
                $.messager.confirm('温馨提示','你确定删除:[<font color="pink">'+row.custormer+"</font>]吗?",function(r){
                    // 选中:确认的操作:调用后台的删除方法:发送ajax调用
                    if (r){
                        // 发送ajax调用 $.get(url,params,function(d){},type)
                        $.get('/repairorder/delete?id='+row.id,function (d) {
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
            repairorderDatagrid.datagrid('reload');
        },
        'submit':function(){
            //提交:表单的提交:
            repairorderForm.form('submit', {
                    url:'/repairorder/saveOrUpdate',
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
                repairorderDlg.dialog('close');
                //页面刷新
                cmdObj.refresh();
            }
        });
        },
        'cancel':function(){
            //关闭dialog,不清空form,在打开dialog的时候清空
            repairorderDlg.dialog('close');
        },
        'search':function () {
            var jsonObj =  $("#searchForm").serializeObject();
            repairorderDatagrid.datagrid("load",jsonObj);
        },
        reset:function () {
            //清空表单
            repairorderForm.form('clear');
        },
        query:function () {
            var row = repairorderDatagrid.datagrid('getSelected');
            if(row){
                $("#children")[0].contentWindow.loadDate(row.id);
            }
            //提交:表单的提交:
           /* if (row){
                $.get("/util/queryRepairorderitem?id="+row.id);
            }*/

        },
        addQuery:function () {
            var row = repairorderDatagrid.datagrid('getSelected');
            if(row){
                $("#children")[0].contentWindow.addDate(row.id);
            }
        },
        setted:function () {
            var row = repairorderDatagrid.datagrid('getSelected');
            if(row){
                $("#children")[0].contentWindow.setteds(row);
            }
        },
    };

    //toolbar绑定事件:如果按钮禁用了怎么办?下午具体实现
    $("a[data-cmd]").on('click',function () {
        var dataCmd=$(this).data('cmd');
        cmdObj[dataCmd]();
    });
});