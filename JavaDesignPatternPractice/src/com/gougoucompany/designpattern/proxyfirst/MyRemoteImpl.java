package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
1.ʵ��Զ�̽ӿ�
�������ʵ��Զ�̽ӿڣ�Ҳ���ǿͻ���Ҫ���õķ����Ľӿ�
2.��չUnicastRemoteObject
Ϊ�˳�ΪԶ�̷��������Ķ�����ҪĳЩ"Զ�̵�"���ܣ���չjava.rmi.server.UnicastRemoteObject,�ó�����ʵ��
3.���һ�����������Ĺ�������������RemoteException
4.��RMI Registryע��˷���
��ʱ���Ѿ���һ��Զ�̷����ˣ������������Ա�Զ�̿ͻ����ã�����Ҫ�����ǽ��˷���ʵ������Ȼ��Ž�RMI Registry�У�Ҫ��ȷ��RMI 
Registry�������У�����ע���ʧ�ܡ�

*/
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//���౻ʵ������ʱ�򣬳���Ĺ������ܻᱻ���ã��������Ĺ������׳��쳣����ô��ֻ����������Ĺ�����Ҳ�׳��쳣
	protected MyRemoteImpl() throws RemoteException {
		//����ÿһ�����췽���ĵ�һ�����Ĭ�϶���:super()
		super();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Server says, 'Hey'";
	}
	
	public static void main(String[] args) {
		try {
			/**
			 * Ϊ��ķ������������ÿͻ�������ע�����������������RMI registry��ע������ֺʹ˷���
			 * �����(bind)�������ʱ��RMI��ѷ��񻻳�stub,Ȼ���stub�ŵ�registry�� 
			 */
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("RemoteHello", service);
		} catch(Exception ex) {}
	}
	
}
