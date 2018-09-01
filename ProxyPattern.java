/*
* @Author: Clarence
* @Date:   2018-09-01 21:43:05
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-01 22:24:11
*/

/*
代理模式
	如果需要对服务的访问进行控制，代理就是控制和管理访问
在上次的状态模式中的代码进行修改
我们要可以给用户一份糖果机报告(糖果机糖果数量，糖果机状态，糖果机位置)
*/
public class GumballMachine{
	//其他实例变量
	String location;

	public GumballMachine(String location, int count){
		//构造器内其他的代码
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	//其他方法
}

//我们创建另一个类，GumballMonitor糖果监听器，以便取得及其的位置，糖果的库存量以及当前的机器状态，并打印出报告(实际开发生成日志如stdout,txt文件等)
public class GumballMonitor{
	GumballMachine gumballMachine;

	public GumballMonitor(GumballMachine machine){
		this.gumballMachine = machine;
	}

	public void report(){
		System.out.println("Gumball Machine: " + machine.getLocation())	
		System.out.pritnln("Current inventory: " + machine.getCount() + " gumballs")
		System.out.println("Current state: " + machine.getState());
	}
}

//测试生成报告
package com.gougoucompany.designpattern.proxyfirst;

public class GumballMachineTestDrive {
	public static void main(String[] args) {
//		int count = 0;
//		
//		if(args.length < 2) {
//			System.out.println("GumballMachine <name> <inventory>");
//			System.exit(1);
//		}
//		
//		count = Integer.parseInt(args[1]);
		GumballMachine gumballMachine = new GumballMachine("hello",  10);
		
		GumballMonitor monitor = new GumballMonitor(gumballMachine);
		
		monitor.report();
	}
}


































