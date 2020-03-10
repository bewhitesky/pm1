package com.cszx.common.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class WithNavibarFormAuthenticationFilter extends FormAuthenticationFilter {

	// @Resource
	// private UserService userService;

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		// HttpServletRequest httpReq=(HttpServletRequest)request;
		//
		// String loginId=(String)SecurityUtils.getSubject().getPrincipal();
		// List<Menu> menu=userService.getMenu(loginId);
		// httpReq.getSession().setAttribute("menu", menu);
		String aa = "";
		return super.onLoginSuccess(token, subject, request, response);
	}

	// @Override
	// protected boolean onAccessDenied(ServletRequest request, ServletResponse
	// response) throws Exception {
	// HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	// if (isAjax(request)) {
	// Map<String, String> resultMap = new HashMap<String, String>();
	//
	// httpServletResponse.setCharacterEncoding("UTF-8");
	// httpServletResponse.setContentType("application/json");
	// resultMap.put("code", "403");
	// resultMap.put("msg", "登录过期,请重新登录！");
	// httpServletResponse.getWriter().write(JSONObject.toJSON(resultMap).toString());
	// }
	// return false;
	// }

	// @Override
	// protected boolean onAccessDenied(ServletRequest request, ServletResponse
	// response) throws Exception {
	// HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	// if (isLoginRequest(request, response)) {
	// return true;
	// } else {
	// if (isAjax(request)) {
	// SecurityUtils.getSubject().logout();
	// Map<String, String> resultMap = new HashMap<String, String>();
	// httpServletResponse.setCharacterEncoding("UTF-8");
	// httpServletResponse.setContentType("application/json");
	// resultMap.put("code", "403");
	// resultMap.put("msg", "登录过期,请重新登录！");
	// httpServletResponse.getWriter().write(JSONObject.toJSON(resultMap).toString());
	//
	// } else {
	// saveRequestAndRedirectToLogin(request, response);
	// }
	// return false;
	// }
	//
	// }

	/**
	 * 判断是否是ajax的请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjax(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
