//url数据格式化
function urlFormatter(v,r) {
    if(r){
        return r.resource.url;
    }
}
function snFormatter(v,r) {
    if(r){
        return r.resource.sn;
    }
}


$(function () {
    //变量的抽取:
     var permissionDatagrid=$('#permissionDatagrid');
     var permissionDlg=$('#permissionDlg');
     var  permissionForm=$('#permissionForm');

    //datagrid
    permissionDatagrid.datagrid({
        url: '/permission/page',
        fit:true,
        title:'部门管理',
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        toolbar:"#toolbar",
        columns: [[
            /*使用遍历:遍历的是Velocity中上下文的对象:
            * fieldList
            *                  {field: 'id', title: 'id', width: '10%',align:  'center'},
            * */
           {field: 'name', title: '权限名称', width: '10%',align:  'center'},
           {field: 'url', title: '权限资源', width: '10%',align:  'center',formatter:urlFormatter},
           {field: 'sn', title: '权限编号', width: '10%',align:  'center',formatter:snFormatter}
        ]]
    });

    //按钮的事件
    var cmdObj={
        'add':function () {
            //弹出一个dialog,里面装form表单  permissionDlg
            permissionDlg.dialog('open').dialog('center').dialog('setTitle','部门添加');
            //清空表单
            permissionForm.form('clear');
        },
        'edit':function () {
            //先获取编辑的数据
            var row = permissionDatagrid.datagrid('getSelected');
            //做一个判断:是否选中
            if (row){
                //弹出dialog,进行数据的回显
                //弹出一个dialog,里面装form表单  permissionDlg
                permissionDlg.dialog('open').dialog('center').dialog('setTitle','部门修改');
                //清空表单
                permissionForm.form('clear');
                row["resource.url"] = row.resource.url;
                row["resource.sn"] = row.resource.sn;
                //表单数据的回显:
                permissionForm.form('load',row);
            }else{
                $.messager.alert('温馨提示','请选中需要编辑的行!!','warning');
            }
        },
        'delete':function () {
            //先获取删除的数据
            var row = permissionDatagrid.datagrid('getSelected');
            //做一个判断:是否选中
            if (row){
                console.debug(row);
                $.messager.confirm('温馨提示','你确定删除:[<font color="pink">'+row.name+"</font>]吗?",function(r){
                    // 选中:确认的操作:调用后台的删除方法:发送ajax调用
                    if (r){
                        // 发送ajax调用 $.get(url,params,function(d){},type)
                        $.get('/permission/delete',{'id':row.id,'resource.id':row.resource.id},function (d) {
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
            permissionDatagrid.datagrid('reload');
        },
        'submit':function(){
            //提交:表单的提交:
            permissionForm.form('submit', {
                url:'/permission/saveOrUpdate',
                onSubmit: function(v){

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
                permissionDlg.dialog('close');
                //页面刷新
                cmdObj.refresh();
            }
        });
        },
        'cancel':function(){
            //关闭dialog,不清空form,在打开dialog的时候清空
            permissionDlg.dialog('close');
        }

    };

    //toolbar绑定事件:如果按钮禁用了怎么办?下午具体实现
    $("a[data-cmd]").on('click',function () {
        //data-cmd="add"
        var dataCmd=$(this).data('cmd');
        cmdObj[dataCmd]();
    });
});