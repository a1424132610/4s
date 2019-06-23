package com.fours.controller.login;


import com.alibaba.fastjson.JSON;
import com.fours.domain.User;
import com.fours.util.JsonResult;
import com.fours.utils.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    //跳转登陆页面
    @RequestMapping("/index")
    public String jumpLogin(){
        return "index";
    }


    /**
     * 登陆验证方法
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult authoriz(User user){
        request.getSession().setAttribute("user",user );
        //获得当前的账号
        Subject subject = SecurityUtils.getSubject();
        //如何没有验证，就会进入验证
        if (!subject.isAuthenticated()){
            //获取token，将账号密码传入
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            try{
                //根据toke完成登录功能,自动调用MyRealm
                subject.login(token);
            }catch (UnknownAccountException e){
                return  JsonResult.error("账号不存在");
            }catch (IncorrectCredentialsException e){
                e.printStackTrace();
                return JsonResult.error("密码错误");
            }catch (AuthenticationException e){
                e.printStackTrace();
                return JsonResult.error("网络错误");
            }
         }
         //成功后就将用户存放到session中
        user = (User) subject.getPrincipal();
        UserContext.setUser(user);
        return JsonResult.getJsonResult();
    }


    @RequestMapping("/callback")
    public String weixinLogin(String state,String code){
        System.out.println("code:"+code+" ; state:"+state);
        //根据code获取token
        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd853562a0548a7d0&secret=4a5d5615f93f24bdba2ba8534642dbb6&code="+code+"&grant_type=authorization_code";
        String tokenStr = HttpClientUtil.doGet(tokenUrl);
        Map<String,String> tokenMap = JSON.parseObject(tokenStr , Map.class) ;
        String accessToken = tokenMap.get("access_token");
        String openId = tokenMap.get("openid");
        System.out.println(accessToken);

        //获取用户资源 /sns/userinfo
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId;
        String userInfoStr = HttpClientUtil.doGet(userInfoUrl);
        Map<String,String> userInfoMap = JSON.parseObject(userInfoStr , Map.class) ;
        System.out.println(userInfoMap);

        return "";
    }
}
