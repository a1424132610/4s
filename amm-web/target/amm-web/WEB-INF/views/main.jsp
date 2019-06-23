
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/header.jsp"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>Title</title>
    <%--百度地图--%>
    <style type="text/css">
        body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #allmap{width:100%;height:500px;}
        p{margin-left:5px; font-size:14px;}
    </style>

    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=CkMtoGnveHNnuaoiqkV4MRnGIH3Fp151"></script>

    <style type="text/css">
        .logo{
            margin-top: 10px;
            margin-left: 10px;
        }
    </style>
    <script type="text/javascript" >
        $(function () {
            $('#menu').tree({
                'url':'/menu/findMenus',
                'animate':true,
                'lines':true,
                'onClick':function (node) {
                    var url = node.url;
                    //判断选项卡是否存在
                    if(url){
                        if($("#tt").tabs("getTab",node.text)){
                            $("#tt").tabs("select",node.text)
                        }else {
                            //如果不存在则动态添加
                            $("#tt").tabs('add',{
                                title: node.text,
                                content: "<iframe src='"+node.url+"' frameborder='0' width='100%' height='100%' />",
                                closable:true
                            });
                        }
                    }
                }
            });

        });

        var data = [{
            text: 'Forms',
            iconCls: 'fa fa-wpforms',
            state: 'open',
            children: [{
                text: 'Form Element'
            },{
                text: 'Wizard'
            },{
                text: 'File Upload'
            }]
        },{
            text: 'Mail',
            iconCls: 'fa fa-at',
            selected: true,
            children: [{
                text: 'Inbox'
            },{
                text: 'Sent'
            },{
                text: 'Trash',
                children: [{
                    text: 'Item1'
                },{
                    text: 'Item2'
                }]
            }]
        },{
            text: 'Layout',
            iconCls: 'fa fa-table',
            children: [{
                text: 'Panel'
            },{
                text: 'Accordion'
            },{
                text: 'Tabs'
            }]
        }];

    </script>
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height:100px;background-color: black;">
        <div class="logo">
            <img src="/static/images/logo.jpg" style="width: 80px;height: 60px">
            <span style="color: white;font-size: 30px;padding-left: 30px">牛杰的4S店</span>
        </div>

        <div class="loginUser">
            <a href="/logout" style="color: red;float: right;margin-right: 10px">
                注销
            </a>
            <span style="color:white;float: right;margin-right: 10px">欢迎<shiro:principal property="username"></shiro:principal>登录</span>
        </div>
    </div>
    <div data-options="region:'west',title:'菜单'" style="width:150px;">
        <ul id="menu"></ul>
    </div>

    <div data-options="region:'center'">
        <div id="tt" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="主页" style="height: 100%;width: 100%; display:none;" >
                <%--百度地图id--%>
                <div>
                    <img src="/static/images/广告位.jpg" style="height: 100px;width: 100%;">
                </div>
                <div id="allmap" style="height: 100%"></div>
            </div>
        </div>
    </div>

</div>

</body>

<script type="text/javascript">
    // 百度地图API功能
    var sContent =
        "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>源码时代4S专业制造厂</h4>" +
        "<img style='float:right;margin:4px' id='imgDemo' src='/static/images/1.jpg' width='139' height='104' title='修仙族'/>" +
        "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>源码时代位于 府城大道西段天府新谷1号楼6楼，与仁和春天广场隔海相望。电话：8848</p>" +
        "</div>";
    var sContent2 =
        "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>春熙路维修商</h4>" +
        "<img style='float:right;margin:4px' id='imgDemo2' src='/static/images/2.jpg' width='139' height='104' title='修仙族'/>" +
        "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>春熙路专业维修商电话：双击666</p>" +
        "</div>";
    var sContent3 =
        "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>全栈九眼桥</h4>" +
        "<img style='float:right;margin:4px' id='imgDemo3' src='/static/images/3.jpg' width='139' height='104' title='修仙族'/>" +
        "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>成都市九眼桥 万能服务区 电话：12581</p>" +
        "</div>";

    var map = new BMap.Map("allmap");
    var point = new BMap.Point(104.06221336863,30.593694180736);
    var marker = new BMap.Marker(point);
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    map.centerAndZoom(point, 12);
    map.addOverlay(marker);
    marker.addEventListener("click", function(){
        this.openInfoWindow(infoWindow);
        //图片加载完毕重绘infowindow
        document.getElementById('imgDemo').onload = function (){
            infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
        }
    });
    //二
    var point2 = new BMap.Point(104.084159,30.66232);
    var marker2 = new BMap.Marker(point2);
    var infoWindow2 = new BMap.InfoWindow(sContent2);  // 创建信息窗口对象
    map.centerAndZoom(point2, 12);
    map.addOverlay(marker2);
    marker2.addEventListener("click", function(){
        this.openInfoWindow(infoWindow2);
        //图片加载完毕重绘infowindow
        document.getElementById('imgDemo2').onload = function (){
            infoWindow2.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
        }
    });
    //三
    var point3 = new BMap.Point(104.095653,30.645703);
    var marker3 = new BMap.Marker(point3);
    var infoWindow3 = new BMap.InfoWindow(sContent3);  // 创建信息窗口对象
    map.centerAndZoom(point3, 12);
    map.addOverlay(marker3);
    marker3.addEventListener("click", function(){
        this.openInfoWindow(infoWindow3);
        //图片加载完毕重绘infowindow
        document.getElementById('imgDemo3').onload = function (){
            infoWindow3.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
        }
    });

    // 鼠标事件
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    //根据浏览器点位
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);
            map.panTo(r.point);
            // alert('您的位置：'+r.point.lng+','+r.point.lat);
        }
        else {
            $.messager.alert('温馨提示','欢迎登录牛杰的4S店!!','warning');
        }
    },{enableHighAccuracy: true})
</script>

</html>


