package com.gougoucompany.designpattern.proxysecond;

import java.util.Random;

/**  
* <p>FileName: HasQuarterState.java</p>  
* <p>Tile: HasQuarterState</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018��9��1�� ����9:14:44
* @version 1.0  
*/
public class HasQuarterState implements State {
	//ʵ�����������Ҫ����һ������WinnerState״̬��װ�����˿ͻ���ǹ���Ͷ�Һ�״̬����ת������״̬��
	Random randomWinner = new Random(System.currentTimeMillis()); //ʹ��long���Ӵ���һ���µ������������
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














