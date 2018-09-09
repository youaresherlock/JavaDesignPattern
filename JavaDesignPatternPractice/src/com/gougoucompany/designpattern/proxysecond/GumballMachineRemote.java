package com.gougoucompany.designpattern.proxysecond;

import java.rmi.Remote;
import java.rmi.*;

public interface GumballMachineRemote extends Remote {
	
	/*
	 1.我们需要为GumballMachine创建一个远程接口，该接口提供了一些可以远程调用的方法
	 2.确定接口的所有返回类型都是可序列化的
	 3.在一个具体类中，实现此接口
	*/
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;
}






















































