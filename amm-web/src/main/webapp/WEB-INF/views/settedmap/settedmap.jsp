<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>还车系统</title>
    <%@include file="/WEB-INF/views/header.jsp"%>

    <%--引入js--%>
    <script src="/static/js/model/settedmap/settedmap.js"></script>

    <%--百度地图--%>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=CkMtoGnveHNnuaoiqkV4MRnGIH3Fp151"></script>
    <style type="text/css">
        body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #container{width:100%;height:500px;}
        #result {width:100%}
    </style>

    <script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>

</head>
<body>

<%--datagrid--%>
<table id="settedMapDatagrid"></table>

<%--页面的按钮--%>
<div id="datagridToolbar">
    <form id="searchForm">
        <a href="javascript:;" data-method="reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
        <a href="javascript:;" data-method="setted" onclick="showmap()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">还车线路地址</a>
        <input type="text" name="address" />
        <a href="javascript:void(0);" data-method="search"  class="easyui-linkbutton"
           data-options="iconCls:'icon-search',plain:true">查询</a>
    </form>
</div>


<%--百度地图弹出框--%>
<div id="mapDialog" class="easyui-dialog" title="还车路线" style="width:1000px;height: 600px"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed: true">
    <%--百度地图id--%>
    <div id="container"></div>

    <div id="result"></div>
    <button id="run">开始</button>
</div>

<script type="text/javascript">


    function showmap() {

        //获取选中的一行地址
        var row =$("#settedMapDatagrid").datagrid("getSelected");

        // 创建地址解析器实例
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        var pointdata=null; //定义一个临时变量（用于动态获取到终点位置）
        myGeo.getPoint(row.address, function(point){
            pointdata=point;
        }, "成都");

        var map = new BMap.Map("container");          // 创建地图实例
        var point = new BMap.Point(104.06218641571,30.593691247414);  // 创建点坐标:成都的
        map.centerAndZoom(point, 12);                 // 初始化地图，设置中心点坐标和地图级别
        //鼠标事件
        map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用

        //根据浏览器定位
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                var mk = new BMap.Marker(r.point);

                /*小汽车*/
                var lushu;
                // 实例化一个驾车导航用来生成路线
                var drv = new BMap.DrivingRoute('成都', {
                    onSearchComplete: function(res) {
                        if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
                            var plan = res.getPlan(0);
                            var arrPois =[];
                            for(var j=0;j<plan.getNumRoutes();j++){
                                var route = plan.getRoute(j);
                                arrPois= arrPois.concat(route.getPath());
                            }
                            map.addOverlay(new BMap.Polyline(arrPois, {strokeColor: '#111'}));
                            map.setViewport(arrPois);

                            lushu = new BMapLib.LuShu(map,arrPois,{
                                defaultContent:"",//"从天安门到百度大厦"
                                autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                                icon  : new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                                speed: 4500,
                                enableRotation:true,//是否设置marker随着道路的走向进行旋转
                                landmarkPois: [
                                    {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},
                                    {lng:116.315391,lat:39.964429,html:'高速公路收费<div><img src="http://map.baidu.com/img/logo-map.gif"/></div>',pauseTime:3},
                                    {lng:116.381476,lat:39.974073,html:'肯德基早餐<div><img src="http://ishouji.baidu.com/resource/images/map/show_pic04.gif"/></div>',pauseTime:2}
                                ]});
                        }
                    }
                });
                var start=new BMap.Point(r.point.lng,r.point.lat);
                var end=new BMap.Point(pointdata.lng,pointdata.lat);
                drv.search(start, end);

                //绑定事件
                $("run").onclick = function(){
                    lushu.start();
                };
                function $(element){
                    return document.getElementById(element);
                }

            }else {
                alert('failed'+this.getStatus());
            }
        },{enableHighAccuracy: true})
    }
</script>

</body>
</html>
