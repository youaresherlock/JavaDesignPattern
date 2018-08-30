/*
* @Author: Clarence
* @Date:   2018-08-31 00:14:35
* @Last Modified by:   Clarence
* @Last Modified time: 2018-08-31 01:43:19
*/
package com.gougoucompany.designpattern.statusfirst;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**  
* <p>FileName: GamballMachine.java</p>  
* <p>Tile: GamballMachine</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 上午12:47:22
* @version 1.0  
*/
/*
状态模式
策略模式和状态模式密不可分，策略模式是围绕可以互换的算法来创建成功业务的，然而，状态走的是更高
的路，它通过改变对象内部的状态来帮助对象控制自己的行为。
例子: 糖果机都有自己的接口或者自己内部的方法，通过不同状态来调用对应的方法，来控制自身的行为。
糖果机的状态主要有四种: 没有25分钱 有25分钱 售出糖果 糖果售罄
系统中发生的动作是从这些状态中过度。
*/
//创建一个类，它的作用就是一个状态机，对每一个动作，都创建了一个对应的方法，这些方法利用条件语句来决定在每个状态内什么行为是恰当的

public class GamballMachine {

	//四种状态
	final static int SOLD_OUT = 0; 
	final static int NO_QUARTER = 1;
	final static int HAS_QUARTER = 2;
	final static int SOLD = 3;
	
	int state = SOLD_OUT; //一开始糖果机状态为糖果售罄
	int count = 0; //糖果机中糖果数目
	
	public GamballMachine(int count) {
		this.count = count;
		if(count > 0) {
			state = NO_QUARTER;
		}
	}
	
	//当投入25分钱对应的行为
	public void insertQuarter() {
		if(state == HAS_QUARTER) {
			System.out.println("You can't insert another quarter"); //用户已经投过钱了
		} else if(state == NO_QUARTER) {
			//接受用户投入25分，转换状态
			state = HAS_QUARTER;
			System.out.println("You inserted a quarter");
		} else if(state == SOLD_OUT) {
			//售罄不能投入
			System.out.println("You can't insert a quarter, the machine is sold out");
		} else if(state == SOLD) {
			//等待状态转换
			System.out.println("Please wait, we're already giving you a gumball");
		}
	}
	
	//用户试着退出25分钱
	public void ejectQuarter() {
		if(state == HAS_QUARTER){
			System.out.println("Quarter returned");
			state = NO_QUARTER; //有25分钱退出
		} else if(state == NO_QUARTER) {
			System.out.println("You haven't inserted a quarter"); //用户没有投入25分
		} else if(state == SOLD) {
			// 用户转动了手柄，不能退了
			System.out.println("Sorry, you already turned the crank");
		} else if(state == SOLD_OUT) {
			//糖果售罄状态，就不能接受25分钱，不能退
			System.out.println("You can't eject, you haven't inserted a quarter yet");
		}
	}
	
	//用户试着转动手柄
	public void turnCrank() {
		if(state == SOLD) { //用户已经得到糖果了
			System.out.println("Turning twice doesn't get you another gumball!");
		} else if(state == NO_QUARTER) {
			//转动手柄，但是没有投币
			System.out.println("You turned but there's no quarter");
		} else if(state == SOLD_OUT) {
			//没有任何糖果
			System.out.println("You turned, but there are no gumballs");
		} else if(state == HAS_QUARTER) {
			//有硬币，同时用户转动手柄，改变状态，发放糖果
			System.out.println("You turned...");
			state = SOLD;
			dispense(); //发放
		}
	}

	//发放糖果，系统内部的行为
	private void dispense() {
		if(state == SOLD) {
			//出售标志，发放
			System.out.println("A gumball comes rolling out the slot");
			count = count - 1; //这里不需要做判断数量，用户投币时候已经做了判断
			if(count == 0) {
				//糖果状态变为售罄
				System.out.println("Ooops, out of gamballs!");
				state = SOLD_OUT;
			} else {
				state = NO_QUARTER;
			}
		} else if(state == NO_QUARTER) { //根本不会出现这种情况
			System.out.println("You need to pay first");
		} else if(state == SOLD_OUT) { //不会出现这种情况 没有糖果只能对应糖果售罄的状态，看是否有环路来决定
			//没糖果了
			System.out.println("No gumball dispensed");
		} else if(state == HAS_QUARTER) {
			System.out.println("No gumball dispensed");
		}
	}
}

//测试类
package com.gougoucompany.designpattern.statusfirst;

/**  
* <p>FileName: GumballMachineTestDrive.java</p>  
* <p>Tile: GumballMachineTestDrive</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 上午1:24:07
* @version 1.0  
*/
public class GumballMachineTestDrive {
	public static void main(String args[]) {
		GumballMachine gumballMachine = new GumballMachine(5);
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
	}
}
/*
result:
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 5 gumballs
Machine is waiting for quarter

You inserted a quarter
You turned...
A gumball comes rolling out the slot
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 4 gumballs
Machine is waiting for quarter

You inserted a quarter
Quarter returned
You turned but there's no quarter
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 4 gumballs
Machine is waiting for quarter

You inserted a quarter
You turned...
A gumball comes rolling out the slot
You inserted a quarter
You can't insert another quarter
You turned...
A gumball comes rolling out the slot
You haven't inserted a quarter
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 2 gumballs
Machine is waiting for quarter

You inserted a quarter
You can't insert another quarter
You turned...
A gumball comes rolling out the slot
You inserted a quarter
You turned...
A gumball comes rolling out the slot
Ooops, out of gamballs!
You can't insert a quarter, the machine is sold out
You turned, but there are no gumballs
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 0 gumballs
Machine is waiting for quarter
*/




















































































































