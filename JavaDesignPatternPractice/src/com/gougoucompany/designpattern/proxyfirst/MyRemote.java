package com.gougoucompany.designpattern.proxyfirst;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
1.Remote是一个标记接口，没有方法
2.声明所有的方法都会抛出RemoteException，用户会了解到这个是有风险的方法
3.确定变量和返回值是属于基本数据类型或者可序列化类型。自己的类要实现Serializable接口,因为要服务端要序列化通过网络传输给客户
*/
public interface MyRemote extends Remote{
	public String sayHello() throws RemoteException;
}
