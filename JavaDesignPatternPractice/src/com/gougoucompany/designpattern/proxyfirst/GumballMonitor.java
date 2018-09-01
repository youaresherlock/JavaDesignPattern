package com.gougoucompany.designpattern.proxyfirst;

/**  
* <p>FileName: GumballMonitor.java</p>  
* <p>Tile: GumballMonitor</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018��9��1�� ����10:15:03
* @version 1.0  
*/
//���Ǵ�����һ���࣬GumballMonitor�ǹ����������Ա�ȡ�ü����λ�ã��ǹ��Ŀ�����Լ���ǰ�Ļ���״̬������ӡ������(ʵ�ʿ���������־��stdout,txt�ļ���)
public class GumballMonitor{
	GumballMachine gumballMachine;

	public GumballMonitor(GumballMachine machine){
		this.gumballMachine = machine;
	}

	public void report(){
		System.out.println("Gumball Machine: " + gumballMachine.getLocation());
		System.out.println("Current inventory: " + gumballMachine.getCount() + " gumballs");
		System.out.println("Current state: " + gumballMachine.getState());
	}
}
