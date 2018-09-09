package com.gougoucompany.designpattern.proxysecond;

import java.rmi.RemoteException;

//我们创建另一个类，GumballMonitor糖果监听器，以便取得及其的位置，糖果的库存量以及当前的机器状态，并打印出报告(实际开发生成日志如stdout,txt文件等)
public class GumballMonitor{
	GumballMachineRemote gumballMachine; //使用多种类型的糖果机

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
