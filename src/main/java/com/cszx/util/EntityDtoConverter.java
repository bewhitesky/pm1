package com.cszx.util;

import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;

public class EntityDtoConverter {

	private Object obj = null;

	private Object objDto = null;

 

public  Object DtoConvertEntity(Object objDto, Object obj) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException{

	return this.entityConvertDto( objDto, obj);

}

public Object entityConvertDto(Object obj, Object objDto) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException {

	Class<?> clazz = obj.getClass();


	this.obj = obj;

	this.objDto = objDto;

	Class<?> dtoClazz = objDto.getClass();


	Field[] fields = clazz.getDeclaredFields();

	for (Field f : fields) {

		invoke1(clazz.getDeclaredMethods(), dtoClazz.getDeclaredMethods(), f.getName());

	}

	return objDto;

}


private  void invoke1(Method[] methods, Method[] methodDtos, String name) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

	String upperName = Character.toUpperCase(name.charAt(0))+ name.substring(1);

	String setterName = "set" + upperName;

	String getterName = "get" + upperName;


	Method method = null;

	Method  methodDto = null;



	method = this.getMethodByName(methods, getterName);

 

	methodDto = this.getMethodByName(methodDtos, setterName);

	if (method != null && methodDto != null) {

		methodDto.invoke(this.objDto, method.invoke(this.obj, null));

	}

}

private Method getMethodByName(Method[] methods, String methodName) {

	for (Method m: methods) {

		if (m.getName().equals(methodName)) {

			return m;

		}

	}

	return null;

}
}
