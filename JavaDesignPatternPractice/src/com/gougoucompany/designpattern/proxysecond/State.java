package com.gougoucompany.designpattern.proxysecond;

import java.io.Serializable;

//将返回的State类型改成可序列化的类型
public interface State extends Serializable{
	
	//所有的状态的动作接口
	void InsertQuarter();
	
	void ejectQuarter();
	
	void turnCrank();
	
	void dispense();
}
