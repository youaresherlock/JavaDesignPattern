package com.gougoucompany.designpattern.proxyfirst;

/**  
* <p>FileName: GumballMonitor.java</p>  
* <p>Tile: GumballMonitor</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年9月1日 下午10:15:03
* @version 1.0  
*/
//我们创建另一个类，GumballMonitor糖果监听器，以便取得及其的位置，糖果的库存量以及当前的机器状态，并打印出报告(实际开发生成日志如stdout,txt文件等)
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
