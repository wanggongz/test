package com.shenpu.proxy.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shenpu.proxy.pay.domain.Order;
import com.shenpu.proxy.pay.service.impl.OrderService;
import com.shenpu.proxy.pay.utils.Settings;
import com.shenpu.proxy.pay.utils.WebUtils;
import com.shenpu.proxy.pay.utils.WxConstants;


/**
 * 
 * @desc 微信获取接口共同参数控制器 
 *
 * @author hcy
 *
 * 2016年10月18日
 */
@Controller
@RequestMapping("/wxcommon")
public class WxCommonController {

	private final static Logger logger = LoggerFactory.getLogger(WxCommonController.class);
	
	@Autowired 
	private WxConstants wxConstants;
	
	@Resource OrderService orderService;
	
	@Autowired
	private Settings settings;
	
	/**
	 * 请求获取openid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/openid")
	public ModelAndView getOpenid(
			@RequestParam(value = "code", required = true) String code,
			HttpServletRequest request, HttpServletResponse response,RedirectAttributes model) {
		String orderNo = request.getParameter("orderNo");
		try {
			request.setCharacterEncoding("UTF-8");
			String openid = wxConstants.getOpenid(code);
			
			request.getSession().setAttribute("openid", openid);
			if ("".equals(openid) || null==openid) {
				logger.error("获取的openid为空,openid="+openid);
				return null;
			}else {
				Order orderEntity = orderService.queryOrderByOrderno(orderNo);
				logger.info("获取的openid为："+openid);
				ModelAndView view = new ModelAndView("gatewayinfo");
				view.addObject("orderNo", orderEntity.getOrderno());
				view.addObject("success_url", WebUtils.urlEncode(settings.getSitePath()+"/success.hsml"));
				view.addObject("totalfee", orderEntity.getTotalfee());
				view.addObject("body", orderEntity.getBody());
				return view;
			}
		} catch (Exception e) {
			logger.error("请求获取的openid失败:", e);
			return null;
		} 
	}
	
	
	/**
	 * 请求获取的accesstoken
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/accesstoken")
	public String getAccessToken(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			String accessToken = wxConstants.getAccessToken();
			if ("".equals(accessToken) || null==accessToken) {
				printWriter.print("{\"statusCode\":\"0\",\"accessToken\":\"\"}");
				logger.error("获取的accesstoken为空,accesstoken="+accessToken);
			}else {
				printWriter.print("{\"statusCode\":\"1\",\"accessToken\":\""+accessToken+"\"}");
				logger.info("获取的accesstoken为："+accessToken);
			}
		} catch (Exception e) {
			logger.error("请求获取的accesstoken失败:", e);
			return null;
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		return null;
	}
	
	/**
	 * 请求获取的ticket
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/ticket")
	public String getTicket(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			request.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			String ticket = wxConstants.getTicket();
			if ("".equals(ticket) || null==ticket) {
				printWriter.print("{\"statusCode\":\"0\",\"ticket\":\"\"}");
				logger.error("获取的ticket为空,ticket="+ticket);
			}else {
				printWriter.print("{\"statusCode\":\"1\",\"ticket\":\""+ticket+"\"}");
				logger.info("获取的ticket为："+ticket);
			}
		} catch (Exception e) {
			logger.error("请求获取的ticket失败:", e);
			return null;
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		return null;
	}
	
}
