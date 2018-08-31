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
	
	//表示糖果机的状态，是一个对象，而不是一个整数
	State state = soldOutState;
	int count = 0;
	
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
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
}
































