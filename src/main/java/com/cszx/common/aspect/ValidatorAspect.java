package com.cszx.common.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidatorAspect {

	/**
	 * 切点处理
	 *
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		BindingResult errors = null;
		Object[] args = pjp.getArgs();
		if (null != args && args.length != 0) {
			for (Object object : args) {
				if (object instanceof BindingResult) {
					errors = (BindingResult) object;
					break;
				}
			}
		}
		if (errors != null && errors.hasErrors()) {
			Map<String, Object> err = new HashMap<String, Object>();
			err.put("mes", "校验不通过");
			return err;
		}

		// if (result.hasErrors()) {

		// }
		// resultMap.put("errors", result.getFieldErrors());
		// return null;
		// }
		return pjp.proceed();
	}

}