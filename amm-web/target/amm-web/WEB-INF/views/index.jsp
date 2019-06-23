<%--
  Created by IntelliJ IDEA.
  User: xiri
  Date: 2019/5/30
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>4S维修管理系统</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/htmleaf-demo.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/unlock.css"/>
    <style type="text/css">
        .wrapper {
            padding: 1rem 10%;
            margin: 2rem auto;
            min-width: 300px;
            max-width: 1200px;
            width: 70%;
        }
        .bar {
            margin: 5px;
            height: 40px;
            width: 300px;
        }
    </style>
</head>
<body>
    <!-- contact-form -->
    <div class="message warning">
        <div class="inset">
            <div class="login-head">
                <h1>4S维修管理后台登录</h1>
            </div>
            <form id="loginForm" method="post" >
                <li>
                    <input type="text" class="text"  name="username" value="admin" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}"><a href="#" class=" icon user"></a>
                </li>
                <div class="clear"> </div>
                <li>
                    <input type="password" value="aa" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"> <a href="#" class="icon lock"></a>
                </li>
                <div class="clear"></div>
                <div class="bar1 bar"></div>

                <div class="submit">
                    <input id="exportCameraButton"  type="button" disabled="disabled" onclick="submitForm()" value="Sign in" >
                    <h4><a href="#">Lost your Password ?</a></h4>
                    <div class="clear"></div>
                </div>
            </form>

        </div>
    </div>
    </div>
    <div class="clear"> </div>
</body>
<script src="/static/js/jquery.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="/static/js/jquery.min.js"><\/script>')</script>
<script src='/static/js/unlock.js'></script>
<script>var __links = document.querySelectorAll('a');function __linkClick(e) { parent.window.postMessage(this.href, '*');} ;for (var i = 0, l = __links.length; i < l; i++) {if ( __links[i].getAttribute('data-t') == '_blank' ) { __links[i].addEventListener('click', __linkClick, false);}}</script>
<script type="text/javascript">
    $(document).ready(function(c) {
        $('.bar1').slideToUnlock({
            successFunc:function () {
                $("#exportCameraButton").removeAttr("disabled");;
            }
        });
    });

    /**
     * form表单提交
     */
    function submitForm(){
        var data = $("#loginForm").serialize();
        $.post("/login/login",data,function (res) {
            if(res.success){
                window.location.href = "/main";
            }else{
                $.messager.alert("登录失败", res.msg, "error");
            }
        })
    }




</script>
</html>