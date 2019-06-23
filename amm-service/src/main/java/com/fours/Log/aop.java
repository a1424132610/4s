package com.fours.Log;

import com.fours.domain.Log;
import com.fours.domain.User;
import com.fours.mapper.LogMapper;
import com.fours.util.GetIp;
import com.fours.util.LogAnno;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;


/**
 * AOP实现日志
 */
@Component
@Aspect
public class aop {
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        return request;
    }



    @Autowired
    private LogMapper logtableService;// 日志Service


    /**
     * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.fours.service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // 1.方法执行前的处理，相当于前置通知
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        LogAnno logAnno = method.getAnnotation(LogAnno.class);
        // 创建一个日志对象(准备记录日志)
        Log logtable = new Log();
        if (logAnno != null) {
            // 获取操作描述的属性值
            String operateType = logAnno.operateType();
            logtable.setFunction(operateType);// 有注解。则将注解的属性值设置为操作的功能的值
        }else {
            logtable.setFunction(method.getName());//没注解，则将调用的方法设置为操作的功能
        }
        User user = (User)getHttpServletRequest().getSession().getAttribute("user");
        //User user= (User)request.getSession().getAttribute("user");
        logtable.setOpUser(user.getUsername());// 设置操作人
        logtable.setOpIp(GetIp.getIpAddress(getHttpServletRequest()));//设置Ip地址



        Object result = null;
        try {
            //让代理方法执行
            result = pjp.proceed();
            // 2.相当于后置通知(方法成功执行之后走这里)
            logtable.setParams("操作正常");// 设置操作的参数
        } catch (SQLException e) {
            // 3.相当于异常通知部分
            logtable.setParams("操作失败");// 设置操作的参数
        } finally {
            // 4.相当于最终通知
            logtable.setOpTime(new Date());// 设置操作日期
            try {
                logtableService.addLog(logtable);// 添加日志记录
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }
}
