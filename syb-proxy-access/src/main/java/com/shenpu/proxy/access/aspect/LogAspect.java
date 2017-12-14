package com.shenpu.proxy.access.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.shenpu.base.utils.RadomUtil;
import com.shenpu.proxy.access.domain.InsureLog;
import com.shenpu.proxy.access.service.InsureLogService;

/**
 * 统一日志管理
 * @author ws
 */
@Aspect
@Component
public class LogAspect {
	
	private Logger logger = LogManager.getLogger(LogAspect.class.getName());
	
	private ThreadLocal<InsureLog> tlocal = new ThreadLocal<InsureLog>();
	
	@Autowired
	private InsureLogService insureLogService;
	
    @Pointcut("execution(public * com.shenpu.proxy.access.controller..*.*(..))")
    public void log(){
    	
    }

    @Before("log()")
    public void before(JoinPoint joinPoint){
    	try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String uri = request.getRequestURI();
            String remoteAddr = getIpAddr(request);
            Object[] paramsArray = joinPoint.getArgs();
            String args = argsArrayToString(paramsArray);
            //存储用户信息
//            HttpSession session = request.getSession();
//            Object attribute = session.getAttribute("user");
            InsureLog optLog = new InsureLog();
            optLog.setInsureLogId(RadomUtil.get32BitUUID());
            optLog.setReqTime(new Date());
            optLog.setReqStr(args != null ? args.toString() : "");
            optLog.setIp(remoteAddr);
            optLog.setUrl(uri);
            optLog.setLogType("承保");
            optLog.setReqTime(new Date());
            tlocal.set(optLog);
        } catch (Exception e) {
            logger.error("<<<<<操作请求日志记录失败doBefore()异常:{}", e);
        }
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) {
        try {
        	logger.info(">>>>>>>>>>>>>>>>>>"+ret);
            // 处理完请求，返回内容
        	InsureLog optLog = tlocal.get();
            optLog.setRspStr(ret.toString());
            Date beginTime = optLog.getReqTime();
            optLog.setRspTime(new Date());
            long requestTime = (System.currentTimeMillis() - beginTime.getTime()) / 1000;
            optLog.setElapsedTime(requestTime);
            
            insureLogService.save(optLog);
        } catch (Exception e) {
            logger.error("<<<<<<<操作请求日志记录失败doAfterReturning()异常：{}", e);
        }
    }
    
    
    /**
     * 获取登录用户远程主机ip地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip!=null && ip.length()>15){
            if(ip.indexOf(",")>0){  
            	ip = ip.substring(0,ip.indexOf(","));  
            }  
        }  
        return ip;
    }

    
    /**
     * 请求参数拼装
     * 
     * @param paramsArray
     * @return
     */
	private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                Object jsonObj = JSON.toJSON(paramsArray[i]);
                params += jsonObj.toString() + " ";
            }
        }
        return params.trim();
    }

}
