$(function () {
    //获取到组件
    var settedMapDatagrid=$("#settedMapDatagrid");
    //地图的弹出框
    var mapDialog = $("#mapDialog");

    /*格式化支付方式
    function formatterStatus(v,r) {
        if (v ==0){
            return "<span style='color: #00bbee'>支付宝</span>";
        }else if(v==1){
            return "<span style='color: #00ee00'>微信</span>";
        }else {
            return "<span style='color: red'>现金</span>";
        }
    }*/

    itsource={
        // 刷新
        reload(){
            settedMapDatagrid.datagrid("load");
        },
        //查询
        search() {
            var jsonObj =  $("#searchForm").serializeObject();
            settedMapDatagrid.datagrid("load",jsonObj);
        },
        setted(){
            var row = settedMapDatagrid.datagrid("getSelected");
            if(row){
                mapDialog.dialog("open").dialog("center");
            }else {
                $.messager.alert('警告','<h1><span style="color: magenta">陛下请翻牌选臣妾嘛</span></h1>');
            }
        },
    };




    //绑定事件
    $("[data-method]").click(function () {
        var method = $(this).data("method");
        itsource[method]();
    });


    //界面渲染
    settedMapDatagrid.datagrid({
        url:'/settedMap/page',
        fit:true,
        singleSelect:true,
        pagination:true,
        fitColumns:true,
        toolbar:'#datagridToolbar',
        columns:[[
            {field:'custormerId',title:'客户名称',width:100},
            {field:'settedTime',title:'结算时间',width:100},
            {field:'totalMoney',title:'总金额',width:100},
            // {field:'payId',title:'支付方式',formatter:formatterStatus,width:100},
            {field:'address',title:'地址',width:100},
        ]]
    });
});