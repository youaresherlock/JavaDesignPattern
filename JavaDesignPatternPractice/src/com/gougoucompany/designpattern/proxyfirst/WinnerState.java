package com.gougoucompany.designpattern.proxyfirst;

import org.w3c.dom.css.ElementCSSInlineStyle;

/**  
* <p>FileName: WinnerState.java</p>  
* <p>Tile: WinnerState</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年9月1日 下午7:15:31
* @version 1.0  
*/
public class WinnerState implements State {
	GumballMachine gumballMachine;
	
	public WinnerState(GumballMachine gumballMachine) {
		// TODO Auto-generated constructor stub
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void InsertQuarter() {
		// TODO Auto-generated method stub
		System.out.println("Please wait, we're already giving you a gumball");
	}

	@Override
	public void ejectQuarter() {
		// TODO Auto-generated method stub
		System.out.println("Sorry, you already turned the crank");
	}

	@Override
	public void turnCrank() {
		// TODO Auto-generated method stub
		System.out.println("Turning twice doesn't get you another gumball!");
	}

	@Override
	public void dispense() {
		// TODO Auto-generated method stub
		System.out.println("YOU'RE A WINNER! You get two gumballs for your quarter");
		gumballMachine.releaseBall();
		//这里其实不用直接判断糖果数量，因为进入赢家状态的唯一状态是HasQuareterState，并且用户转动曲柄，里面已经对数目进行了判断，这里不知道书里不考虑，可能是为了简单起见
		if(gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState()); //如果有第二个糖果就释放，没有就改变糖果机为售罄状态
		} else {
			gumballMachine.releaseBall(); //释放糖果之后判断糖果数量，如果有则进入没有投币状态，如果没有则糖果机进入售罄状态。
			if(gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("Oops, out of gumballs!"); 
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
	}

}




