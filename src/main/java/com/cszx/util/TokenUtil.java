package com.cszx.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenUtil {
	/**
	 * 生成token并存到cookie和session中
	 * @param request
	 * @param response
	 */
	public static void setToken(HttpServletRequest request,HttpServletResponse response){
		String tokenValue=UUID.randomUUID().toString();
		response=CookieUtil.setCookie(response, "token",tokenValue, 1000);
		request.getSession().setAttribute("token", tokenValue);
	}
	public static boolean validateToken(HttpServletRequest request,HttpServletResponse response){
		Cookie cookie=CookieUtil.getCookieByName(request, "token");
		String tokenValue=null;
		if(cookie!=null){
			tokenValue=cookie.getValue();
		}
		String tokenValue2=(String) request.getSession().getAttribute("token");
		request.getSession().removeAttribute("token");
		if(tokenValue2==null){
			return false;
		}
		if(tokenValue !=null && tokenValue.equals(tokenValue2)){
			setToken(request, response);//token验证成功，重新生成token
			return true;
		}else{
			return false;
		}
	}
	
}
