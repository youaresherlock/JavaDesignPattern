package com.gougoucompany.designpattern.monitor;

import java.util.EventObject;
//�¼�����
public class PrintEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public PrintEvent(Object source) {
		super(source);
	}
	
	public void doEvent() {
		//getSource The object on which the Event initially occurred.
		System.out.println("֪ͨһ���¼�Դ source: " + this.getSource());
	}

}
