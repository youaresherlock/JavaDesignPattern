package com.gougouocompany.designpattern.statussecond;

/**  
* <p>FileName: HasQuarterState.java</p>  
* <p>Tile: HasQuarterState</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年9月1日 上午9:14:44
* @version 1.0  
*/
public class HasQuarterState implements State {
	GumballMachine gumballMachine;
	
	public HasQuarterState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void InsertQuarter() {
		// TODO Auto-generated method stub
		System.out.println("You can't insert another quarter");
	}

	@Override
	public void ejectQuarter() {
		// TODO Auto-generated method stub
		System.out.println("Quarter returned"); 
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}

	@Override
	public void turnCrank() {
		// TODO Auto-generated method stub
		System.out.println("You turned....");
		gumballMachine.setState(gumballMachine.getSoldState());
	}

	@Override
	public void dispense() {
		// TODO Auto-generated method stub
		System.out.println("No gumball dispensed");
	}

}
