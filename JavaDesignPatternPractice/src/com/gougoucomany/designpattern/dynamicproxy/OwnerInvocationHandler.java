package com.gougoucomany.designpattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.print.attribute.standard.PrinterStateReason;

public class OwnerInvocationHandler implements InvocationHandler{
	
	PersonBean person;
	
	public OwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	@Override
	/**
	 * proxy: 代理
	 * Method: 代理正在调用的方法
	 * args: 代理正在调用的方法的参数
	 * 每次proxy的方法被调用，就会导致proxy调用此方法
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if(method.getName().startsWith("get")) {
				//自己可以调用get类型的方法
				return method.invoke(person, args);
			} else if(method.getName().equals("setHotOrNotRating")) {
				//自己不能调用这个方法,如果调用报出没有访问权限异常
				throw new IllegalAccessException();
			} else if(method.getName().startsWith("set")) {
				return method.invoke(person, args);
			}
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
