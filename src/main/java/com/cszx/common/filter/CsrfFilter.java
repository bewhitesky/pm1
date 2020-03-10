package com.cszx.common.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CsrfFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			// 获取请求url地址
			String url = req.getRequestURL().toString();
			url = url.substring(url.indexOf("http://") + 7, url.lastIndexOf(":"));
			String host = req.getRemoteHost();
			String referurl = req.getHeader("Referer");
			// logger.info("referurl----->" + referurl);
			boolean flag = false;
			// if (referurl.contains(url) && referurl.contains("/pm")) {
			// flag = true;
			// }
			if (true) {
				chain.doFilter(request, response);
			} else {

				req.getRequestDispatcher("/").forward(req, res);

				// 记录跨站请求日志
				String log = "";
				String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				// String clientIp = WebUtil.getHost(req);
				//
				// log = clientIp + "||" + date + "||" + referurl + "||" +
				// url;
				// logger.warn(log);
				return;
			}

		} catch (Exception e) {
			// logger.error("doFilter", e);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
