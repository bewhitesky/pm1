package com.cszx.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cszx.util.JSONUtils;

public class MySimpleMappingExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		// 判断是否ajax请求
		if (!(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 如果不是ajax，JSP格式返回
			// 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			if (e instanceof BusinessException) {
				map.put("errorMsg", e.getMessage().toString()
						.substring(e.getMessage().indexOf(":") != -1 ? e.getMessage().indexOf(":") : 0));
			} else {
				map.put("errorMsg", "系统异常！");
			}
			// 这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
			e.printStackTrace();
			// 对于非ajax请求，我们都统一跳转到error.jsp页面
			return new ModelAndView("/error", map);
		} else {
			// 如果是ajax请求，JSON格式返回
			try {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", false);
				// 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
				if (e instanceof BusinessException) {
					if (e.getMessage().indexOf(":") != -1) {
						map.put("mes", e.getMessage().toString()
								.substring(e.getMessage().indexOf(":") != -1 ? e.getMessage().indexOf(":") + 1 : 0));
					} else {
						map.put("mes", e.getMessage());
					}

				} else {
					map.put("mes", "系统异常！");
				}

				writer.write(JSONUtils.toJSONString(map));
				writer.flush();
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
}
