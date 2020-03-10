package com.cszx.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cszx.util.PropertiesUtil;
import com.cszx.util.StringUtil;

public class CustomValidator implements ConstraintValidator<CustomRegular, Object> {

	/**
	 * @Fields regType:正则类型
	 */
	private String regType;

	/**
	 * @Fields regTypeMessage:提示信息
	 */
	private String regTypeMessage;

	@Override
	public void initialize(CustomRegular customRegular) {
		this.regType = customRegular.regType();
		this.regTypeMessage = "DEFAULT.MESSAGE".equals(customRegular.message()) ? customRegular.regType() + ".MESSAGE"
				: customRegular.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean isValid = true;
		if (value != null && StringUtil.isNotEmpty(value.toString())) {
			String reg = PropertiesUtil.getPropertyValue(regType, "/regType.properties");
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(value.toString());
			isValid = matcher.matches();
		}
		return isValid;
	}

}
