package com.gougoucompany.designpattern.callback;

//�ص���(���ڵ��ûص���������)
public class Caller {
	public void call(ICallBack callBack) {
		System.out.println("Start...");
		callBack.callBack();
		System.out.println("End...");
	}
}
