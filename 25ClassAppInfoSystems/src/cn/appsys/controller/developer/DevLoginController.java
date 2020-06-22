package cn.appsys.controller.developer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;
@Controller
@RequestMapping("/dev")
public class DevLoginController {
	
	private Logger logger = Logger.getLogger(DevLoginController.class);
	
	@Resource
	private DevUserService devUserService;
	
	
	/**
	 *登录跳转 
	 */
	@RequestMapping("/login")
	public String login(){
		logger.info("DevUserService>>>>>>>>>>>>>>>>>>login");
		return "devlogin";
		
	}
	
	/**
	 *登录判断 
	 */
	@RequestMapping("/dologin")
	public String dologin(@RequestParam String devCode,
			@RequestParam String devPassword,
			HttpServletRequest request,
			HttpSession session){
		logger.info("DevUserService>>>>>>>>>>>>>>>>>>dologin");
		DevUser devUser = null;
		try {
			devUser = devUserService.login(devCode, devPassword);
			if(devUser!=null){
				//登录成功 跳转页面
				session.setAttribute(Constants.DEV_USER_SESSION, devUser);
			}else {
				//返回页面
				request.setAttribute("error", "用户名或者密码不正确");
				return "devlogin";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "devlogin";
	}
	
	
	
}
