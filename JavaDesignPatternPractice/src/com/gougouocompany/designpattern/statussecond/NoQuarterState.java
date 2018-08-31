package com.gougouocompany.designpattern.statussecond;


/**  
* <p>FileName: NoQuarterSate.java</p>  
* <p>Tile: NoQuarterSate</p>  
* <p>Description: 糖果机初始化状态类，没有25分钱</p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 下午10:13:33
* @version 1.0  
*/
public class NoQuarterState implements State{
	GumballMachine gumballMachine;
	
	public NoQuarterState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void InsertQuarter() {
		// TODO Auto-generated method stub
		System.out.println("You inserted a quarter");
		gumballMachine.setState(gumballMachine.getHasQuarterState());
		
	}

	@Override
	public void ejectQuarter() {
		// TODO Auto-generated method stub
		System.out.println("You haven't inserted a quarter");
	}

	@Override
	public void turnCrank() {
		// TODO Auto-generated method stub
		System.out.println("You turned, but there's no quarter");
	}

	@Override
	public void dispense() {
		// TODO Auto-generated method stub
		System.out.println("You need to pay first");
	}

}












































