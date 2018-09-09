package com.gougoucompany.designpattern.proxysecond;

import java.util.Random;

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
	//实现随机数，还要增加一个进入WinnerState状态的装换，顾客会从糖果机投币后状态进入转动曲柄状态。
	Random randomWinner = new Random(System.currentTimeMillis()); //使用long种子创建一个新的随机数生成器
	transient GumballMachine gumballMachine;
	
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
		int winner = randomWinner.nextInt(10); //[0,10)
		if( winner == 0 && gumballMachine.getCount() > 1) {
			gumballMachine.setState(gumballMachine.getWinnerState());
		} else {
			gumballMachine.setState(gumballMachine.getSoldState());
		}
	}

	@Override
	public void dispense() {
		// TODO Auto-generated method stub
		System.out.println("No gumball dispensed");
	}

}














