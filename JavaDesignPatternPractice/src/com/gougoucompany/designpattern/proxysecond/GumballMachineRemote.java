package com.gougoucompany.designpattern.proxysecond;

import java.rmi.Remote;
import java.rmi.*;

public interface GumballMachineRemote extends Remote {
	
	/*
	 1.������ҪΪGumballMachine����һ��Զ�̽ӿڣ��ýӿ��ṩ��һЩ����Զ�̵��õķ���
	 2.ȷ���ӿڵ����з������Ͷ��ǿ����л���
	 3.��һ���������У�ʵ�ִ˽ӿ�
	*/
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public State getState() throws RemoteException;
}






















































