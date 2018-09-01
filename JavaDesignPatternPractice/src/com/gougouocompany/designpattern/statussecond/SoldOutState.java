package com.gougouocompany.designpattern.statussecond;

/**  
* <p>FileName: SoldOutState.java</p>  
* <p>Tile: SoldOutState</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年9月1日 上午9:14:56
* @version 1.0  
*/
public class SoldOutState implements State {
	GumballMachine gumballMachine;

	public SoldOutState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void InsertQuarter() {
		// TODO Auto-generated method stub
		System.out.println("You can't insert a quarter, the machine is sold out");
	}

	@Override
	public void ejectQuarter() {
		// TODO Auto-generated method stub
		System.out.println("You can't eject, you haven't inserted a quarter yet");
	}

	@Override
	public void turnCrank() {
		// TODO Auto-generated method stub
		System.out.println("You turned, but there are no gumballs!");
	}

	@Override
	public void dispense() {
		// TODO Auto-generated method stub\
		System.out.println("No gumball dispensed");
	}

}
