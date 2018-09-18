package com.gougoucompany.designpattern.monitor;

import java.util.EventListener;
// EventListener: A tagging interface that all event listener interfaces must extend.
//所有事件监听器接口都要继承这个标签接口
public interface MonitorListener extends EventListener{
	/*
	The root class from which all event state objects shall be derived.
	All Events are constructed with a reference to the object, the "source"
	, that is logically deemed to be the object upon which the Event in
	question initially occurred upon.
	*/
	public void handleEvent(PrintEvent event);
}