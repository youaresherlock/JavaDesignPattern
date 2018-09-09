package com.gougoucompany.designpattern.proxysecond;

import java.io.Serializable;

//�����ص�State���͸ĳɿ����л�������
public interface State extends Serializable{
	
	//���е�״̬�Ķ����ӿ�
	void InsertQuarter();
	
	void ejectQuarter();
	
	void turnCrank();
	
	void dispense();
}
