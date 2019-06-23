package com.fours.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//自定义权限过滤器
public class AmmPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter{

    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal()==null){
            //没有登陆成功的操作
            //保存请求并且重定向直登陆页面
            this.saveRequestAndRedirectToLogin(request, response);
        }else {
            //登陆成功过后的操作
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            //获得头当中有没有X-Requested-With的头信息,如果有就是AJAX请求,如果没有就是普通请求
            String s = req.getHeader("X-Requested-With");
            if (s != null && "XMLHttpRequest".equals(s)){
               resp.setContentType("text/json; charset=UTF-8");
               resp.getWriter().print("{\"success\":false,\"msg\":\"没有权限\"}");
            }else {
                String url = this.getUnauthorizedUrl();
                if (StringUtils.hasText(url)) {
                    WebUtils.issueRedirect(request, response, url);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }


}
