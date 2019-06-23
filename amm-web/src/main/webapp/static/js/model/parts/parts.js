
$(function () {
 var partsDatagrid = $("#partsDatagrid");
    var partsDialog = $('#partsDialog');
    var partsDialogForm = $('#partsDialogForm');
    // var keywordInput = $('#keyword');
//同步绑定方法
    var methods={
        add:function () {
            //清空表单
            partsDialogForm.form("clear");
            //弹出模态框
            partsDialog.dialog("open").dialog("center");
        },
        edit:function () {
            //获取选择行
          var row = partsDatagrid.datagrid("getSelected");

            if (row !=null){
                //数据回显
                partsDialogForm.form("load",row);
                //弹出模态框
                partsDialog.dialog("open").dialog("center");

            }else {
                $.messager.alert("警告","选中编辑","error");
            }
        },
        remove:function () {
            //获取选择行
            var row = partsDatagrid.datagrid("getSelected");
            if(row !=null){
                //获取选中行id
              var partsid =  row.id;
              $.post("/parts/delete",{id:partsid},function (data) {
                  if(data.success){
                      $.messager.alert("提示","删除成功","info");
                      //刷新界面
                      methods.reload();
                  }else {
                      $.messager.alert("警告","操作有误","error")
                  }
              })
            }else {
                $.messager.alert("提示","没选中删除的话 删除所有奥","error");
            }
        },
        //刷新
        reload:function () {
            partsDatagrid.datagrid("reload");
        },
        //关闭
        close:function () {
            partsDialog.dialog("close");
        },
        save:function () {
            //提交表单
            partsDialogForm.form('submit', {
                url:"/parts/saveOrUpdate",
                onSubmit: function(){
                    return true;
                },
                success:function(data){
                    data = $.parseJSON(data) ;
                    if(data.success){
                        $.messager.alert("提示","操作成功","info");
                        methods.close();
                        //刷新页面
                        methods.reload();
                    }else{
                        $.messager.alert("提示",data.msg,"error");
                    }
                }
            });

        },
        // search:function () {
        //     //获取关键字
        //     var keyword = keywordInput.val() ;
        //     //重新加载列表，带着keyword这个查询条件
        //     partsDatagrid.datagrid("load",{"keyword":keyword});
        // }
    }
//给A标签绑定点击事件
    $("[data-method]").click(function () {
       var method= $(this).data("method");
       //动态调用
        methods[method]();
    });

    //弹出窗口的渲染
    partsDialog.dialog({
        title: '添加/编辑',
        closed: true,
        cache: false,
        buttons:'#partsDialogButtons',
        modal: true,
        width:400
    });

    partsDatagrid.datagrid({
        url:'/parts/selectAll',
        fit:true,
        singleSelect:true,
        pagination:true,
        fitColumns:true,
       toolbar:'#partsDatagridToolbar',
        columns:[[
            {field:'id',title:'配件编号',width:100},
            {field:'partsName',title:'配件名称',width:100},
            {field:'price',title:'价格',width:100},
            {field:'createTime',title:'创建时间',width:100},
            {field:'num',title:'数量',width:100},
            {field:'warnNum',title:'警告数量',width:100},
            {field:'context',title:'配件描述',width:100}
        ]]
    });
})

