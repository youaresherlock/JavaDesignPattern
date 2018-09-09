package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
1.实现远程接口
服务必须实现远程接口，也即是客户将要调用的方法的接口
2.扩展UnicastRemoteObject
为了成为远程服务对象，你的对象需要某些"远程的"功能，扩展java.rmi.server.UnicastRemoteObject,让超类来实现
3.设计一个不带参数的构造器，并声明RemoteException
4.用RMI Registry注册此服务
这时候已经有一个远程服务了，必须让他可以被远程客户调用，你需要做的是将此服务实例化，然后放进RMI Registry中，要先确保RMI 
Registry正在运行，否则注册会失败。

*/
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//当类被实例化的时候，超类的构造器总会被调用，如果超类的构造器抛出异常，那么你只能声明子类的构造器也抛出异常
	protected MyRemoteImpl() throws RemoteException {
		//子类每一个构造方法的第一条语句默认都是:super()
		super();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Server says, 'Hey'";
	}
	
	public static void main(String[] args) {
		try {
			/**
			 * 为你的服务命名，好让客户用来在注册表中再找它，并在RMI registry中注册此名字和此服务，
			 * 当你绑定(bind)服务对象时，RMI会把服务换成stub,然后把stub放到registry中 
			 */
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("RemoteHello", service);
		} catch(Exception ex) {}
	}
	
}
