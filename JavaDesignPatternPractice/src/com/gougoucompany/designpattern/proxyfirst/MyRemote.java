package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
1.Remote��һ����ǽӿڣ�û�з���
2.�������еķ��������׳�RemoteException���û����˽⵽������з��յķ���
3.ȷ�������ͷ���ֵ�����ڻ����������ͻ��߿����л����͡��Լ�����Ҫʵ��Serializable�ӿ�,��ΪҪ�����Ҫ���л�ͨ�����紫����ͻ�
*/
public interface MyRemote extends Remote{
	public String sayHello() throws RemoteException;
}
