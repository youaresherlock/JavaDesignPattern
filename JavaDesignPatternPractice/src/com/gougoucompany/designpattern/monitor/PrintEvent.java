package com.gougoucompany.designpattern.monitor;

import java.util.EventObject;
//事件对象
public class PrintEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public PrintEvent(Object source) {
		super(source);
	}
	
	public void doEvent() {
		//getSource The object on which the Event initially occurred.
		System.out.println("通知一个事件源 source: " + this.getSource());
	}

}
