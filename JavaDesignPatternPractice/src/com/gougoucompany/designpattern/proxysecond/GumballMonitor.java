package com.gougoucompany.designpattern.proxysecond;

import java.rmi.RemoteException;

//���Ǵ�����һ���࣬GumballMonitor�ǹ����������Ա�ȡ�ü����λ�ã��ǹ��Ŀ�����Լ���ǰ�Ļ���״̬������ӡ������(ʵ�ʿ���������־��stdout,txt�ļ���)
public class GumballMonitor{
	GumballMachineRemote gumballMachine; //ʹ�ö������͵��ǹ���

	public GumballMonitor(GumballMachineRemote machine){
		this.gumballMachine = machine;
	}

	public void report(){
		try {
			System.out.println("Gumball Machine: " + gumballMachine.getLocation());
			System.out.println("Current inventory: " + gumballMachine.getCount() + " gumballs");
			System.out.println("Current state: " + gumballMachine.getState());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
