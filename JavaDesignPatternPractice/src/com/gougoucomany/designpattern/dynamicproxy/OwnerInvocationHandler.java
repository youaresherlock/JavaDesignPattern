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
	 * proxy: ����
	 * Method: �������ڵ��õķ���
	 * args: �������ڵ��õķ����Ĳ���
	 * ÿ��proxy�ķ��������ã��ͻᵼ��proxy���ô˷���
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if(method.getName().startsWith("get")) {
				//�Լ����Ե���get���͵ķ���
				return method.invoke(person, args);
			} else if(method.getName().equals("setHotOrNotRating")) {
				//�Լ����ܵ����������,������ñ���û�з���Ȩ���쳣
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
