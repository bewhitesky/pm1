package com.cszx.common.tag;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.cszx.common.validator.CustomRegular;
import com.cszx.common.validator.CustomValidator;
import com.cszx.util.PropertiesUtil;
import com.cszx.util.StringUtil;

public class ValidatorTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String field;

	private String fieldName;

	private Map<Object, Class> validateMap = new HashMap<Object, Class>();

	@Override
	public int doEndTag() throws JspException {
		validateMap.put("CustomRegular", CustomValidator.class);
		if (bodyContent != null) {
			JspWriter out = bodyContent.getEnclosingWriter();
			Document input = null;
			SAXBuilder sb = new SAXBuilder();
			try {
				String content = bodyContent.getString();
				StringReader contentReader = new StringReader(content);
				Map<String, String> validateMap = getCustomValidation(field);
				String validateType = validateMap.get("validateType");
				input = sb.build(contentReader);
				Element root = input.getRootElement();
				StringBuffer validateBuffer = new StringBuffer();
				validateBuffer.append("validType:[");
				if (StringUtil.isNotEmpty(validateType)) {
					validateBuffer.append(validateType);
				}
				validateBuffer.append("]");
				String dataOptions = root.getAttributeValue("data-options");
				if (StringUtil.isNotEmpty(dataOptions)) {
					root.setAttribute("data-options", dataOptions + "," + validateBuffer.toString());
				} else {
					root.setAttribute("data-options", validateBuffer.toString());
				}

				out.print(docToString(input));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.doEndTag();
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String docToString(Document doc) {
		Format format = Format.getPrettyFormat();
		format.setEncoding("UTF-8");
		XMLOutputter xmlOut = new XMLOutputter(format);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		String xmlString = "";
		try {
			xmlOut.output(doc, bo);
			xmlString = bo.toString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString;

	}

	public Map<String, String> getCustomValidation(String field) {
		String beanName = field.substring(0, field.indexOf("."));
		String fieldName = field.substring(field.indexOf(".") + 1);
		String beanPath = PropertiesUtil.getPropertyValue(beanName, "/validateBean.properties");
		Class<?> modelClass;
		ClassLoader loader = ValidatorTag.class.getClassLoader();
		Map<String, String> result = new HashMap<String, String>();
		StringBuffer validateType = null;
		try {
			validateType = new StringBuffer();
			modelClass = loader.loadClass(beanPath);
			Field targetField = getTargetField(fieldName, modelClass);
			Annotation[] annotations = targetField.getAnnotations();
			for (Annotation annotation : annotations) {
				String classPathString = annotation.annotationType().getName();
				if (StringUtil.isNotEmpty(classPathString)) {
					int index = classPathString.lastIndexOf(".");
					if (index > 0) {
						classPathString = classPathString.substring(index + 1);
						Class<?> validateClass = validateMap.get(classPathString);
						if (validateClass != null) {
							if (validateType.length() > 0) {
								validateType.append(",");
							}
							Map<String, Object> params = new HashMap<String, Object>();
							if (validateClass == CustomValidator.class) {
								CustomRegular customRegular = targetField.getAnnotation(CustomRegular.class);
								params.put("regType", customRegular.regType());
								params.put("mes", customRegular.message());
							}

							validateType.append(StringUtil.doNullString(getValidateInfo(params)));
							result.put("validateType", validateType.toString());
						}

					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public Field getTargetField(String fieldName, Class<?> modelClass) {
		Field[] theFields = modelClass.getDeclaredFields();
		Field targetField = null;
		for (Field f : theFields) {
			String fName = f.getName();
			if (fName.equals(fieldName)) {
				targetField = f;
				break;
			}
		}
		return targetField;
	}

	private String getValidateInfo(Map<String, Object> params) {
		String regType = String.valueOf(params.get("regType"));
		String regmes = String.valueOf(params.get("mes"));
		String regTypeMessage = "{DEFAULT.MESSAGE}".equals(regmes) ? regType + ".MESSAGE"
				: regmes.substring(regmes.indexOf("{") + 1, regmes.lastIndexOf("}"));
		if (StringUtil.isNotEmpty(regType)) {
			StringBuffer validateInfo = new StringBuffer();
			String validateType = regType.substring(0, regType.indexOf("."));
			String reg = PropertiesUtil.getPropertyValue(regType, "/regType.properties");
			String mes = PropertiesUtil.getPropertyValue(regTypeMessage, "/messages.properties");
			validateInfo.append("'").append(validateType).append("[\"").append(reg).append("\",\"").append(mes)
					.append("\"]'");
			return validateInfo.toString();
		} else {
			return "";
		}

	}

}
