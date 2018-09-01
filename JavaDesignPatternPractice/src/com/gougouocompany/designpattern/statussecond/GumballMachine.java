package com.gougouocompany.designpattern.statussecond;

import java.awt.Container;

/**  
* <p>FileName: GumballMachine.java</p>  
* <p>Tile: GumballMachine</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 下午10:19:34
* @version 1.0  
*/
public class GumballMachine {
	
	//所有的状态
	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	State winnerState;

	//表示糖果机的状态，是一个对象，而不是一个整数
	State state = soldOutState;
	int count = 0;
	
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
		winnerState = new WinnerState(this);
		this.count = numberGumballs;
		if(numberGumballs > 0) {
			state = noQuarterState; //反之售罄状态
		}
	}
	
	public void insertQuarter() {
		state.InsertQuarter();
	}
	
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank(); 
		state.dispense();
	}
	
	public void  setState(State state) {
		this.state = state;
	}
	
	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if(count != 0) {
			count = count - 1;
		}
	}

	public State getSoldOutState() {
		return soldOutState;
	}

	public void setSoldOutState(State soldOutState) {
		this.soldOutState = soldOutState;
	}

	public State getNoQuarterState() {
		return noQuarterState;
	}

	public void setNoQuarterState(State noQuarterState) {
		this.noQuarterState = noQuarterState;
	}

	public State getHasQuarterState() {
		return hasQuarterState;
	}

	public void setHasQuarterState(State hasQuarterState) {
		this.hasQuarterState = hasQuarterState;
	}

	public State getSoldState() {
		return soldState;
	}

	public void setSoldState(State soldState) {
		this.soldState = soldState;
	}
	
	public State getWinnerState() {
		return winnerState;
	}

	public void setWinnerState(State winnerState) {
		this.winnerState = winnerState;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer printString = new StringBuffer();
		printString.append("Mighty Gumballl, Inc.\nJava-enabled Standing Gumball Model #2004\nInventory: ");
		printString.append(String.valueOf(count) + " gumballs\n");
		printString.append("Machine is waiting for quarter\n");
		return printString.toString();
	}
}

/*
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 5 gumballs
Machine is waiting for quarter

You inserted a quarter
You turned....
A gumball comes rolling out the slot...
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 4 gumballs
Machine is waiting for quarter

You inserted a quarter
You turned....
A gumball comes rolling out the slot...
You inserted a quarter
You turned....
YOU'RE A WINNER! You get two gumballs for your quarter
A gumball comes rolling out the slot...
A gumball comes rolling out the slot...
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 1 gumballs
Machine is waiting for quarter
 */
































