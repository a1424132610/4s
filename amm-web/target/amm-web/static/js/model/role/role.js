function persFettr(value,row) {
    var role = [];
    if (row){
        $.each(row.permissions,function (index,obj) {
            role.push(obj.name);
        })
    }
    return role.toString();
}

function urlformatter(value,row) {
    if(row){
        var resource = row.resource;
        return resource.url;
    }
}

function snformatter(value,row) {
    if(row){
        var resource = row.resource;
        return resource.sn;
    }
}


$(function () {
    //获得数据表格
    var roleDatagrid = $("#roleDatagrid");
    //获得弹出框
    var $addform = $("#addform");
    //获得表单
    var $editform = $("#editForm");
    //获得权限的数据表格
    var permissions = $("#permissionAll");
    var mypermissions = $("#mypermission");

    //顶部工具栏添加事件
    $("a[data-method]").on("click", function () {
        var $method = $(this).attr("data-method");
        //动态调用方法
        method[$method]();
    });

    roleDatagrid.datagrid({
        url: '/role/page',
        fit:true,
        title:'角色管理',
        rownumbers:true,
        pagination:true,
        fit:true,
        singleSelect:true,
        toolbar:"#toolbar",
        columns: [[
           {field: 'sn', title: '编号', width: '30%',align:'center'},
           {field: 'name', title: '角色名称', width: '30%',align:'center'},
           {field: 'permissionId', title: '权限', width: '40%',align:'center',formatter:persFettr}
        ]]
    });

    //按钮的事件
    var method = {
        add() {
            $addform.dialog("center").dialog("open").form("clear");
            mypermissions.datagrid("loadData",[]);
        },
        update() {
            //数据回显
            //获得选中的数据
            var row = roleDatagrid.datagrid("getSelected");
            if (row) {
                //将窗口绝对居中
                $addform.dialog("center").dialog("open").form("clear");
                var newRow = [];
                $.extend(newRow,row.permissions);
                mypermissions.datagrid("loadData",newRow);
                $editform.form("load",row);
            } else {
                $.messager.alert('提示', '您还没有选中需要修改的信息哟!!!');
            }
        },
        delete() {
            var $selected = roleDatagrid.datagrid("getSelected");
            if ($selected) {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        $.get("/role/delete", {id: $selected.id}, function (msg) {
                            if (msg == '删除成功!!') {
                                //刷新当前页面
                                $.messager.alert('提示', '恭喜您删除成功');
                                roleDatagrid.datagrid("reload");
                            } else {
                                $.messager.alert('提示', msg);
                                roleDatagrid.datagrid("reload");
                            }
                        });
                    }
                });
            } else {
                $.messager.alert('警告', '您还没有选中需要删除的信息哟!!!');
            }
        },
        //提交表单
        save() {
            var $url = "/role/saveOrUpdate";
            if ($("#editid").val()) {
                $url = "/role/saveOrUpdate?cmd=upd";
            }
            $editform.form('submit', {
                url: $url,
                onSubmit: function (param) {
                    var rows = mypermissions.datagrid("getRows");
                    $.each(rows,function (i,v) {
                        param["permissions["+i+"].id"] = v.id;
                    });
                    return $editform.form("validate")
                },
                success: function (data) {
                    $.messager.alert('提示', data);
                    $addform.dialog("close");
                    roleDatagrid.datagrid("reload");
                    parent.location.reload();
                }
            });
        },
        cancel() {
            $addform.dialog("close");
        },
        //高级查询
        search() {
            //获得查询表单的所有值
            var $search = $("#search-form").serializeObject();
            //使用load发生请求载入数据
            $information.datagrid("load", $search);
        },
        addRow(index,row){
            var rows = mypermissions.datagrid("getRows");
            if (rows.length){
                for(var i=0;i < rows.length;i++){
                    if (row.id == rows[i].id){
                        $.messager.show({
                            title:'提示',
                            msg:'不能重复哟',
                            timeout:2000,
                            showType:'slide'
                        });
                        return;
                    }
                }
            }
            mypermissions.datagrid("appendRow",row);
        },
        removeRow(index,row){
            mypermissions.datagrid("deleteRow",index);
        }
    }
    permissions.datagrid({
        url:'/permission/page',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        fit:true,
        onDblClickRow:method.addRow
    });
    mypermissions.datagrid({
        fitColumns:true,
        singleSelect:true,
        fit:true,
        onDblClickRow:method.removeRow
    });
});