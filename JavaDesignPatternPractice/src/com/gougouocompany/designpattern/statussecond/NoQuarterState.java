package com.gougouocompany.designpattern.statussecond;


/**  
* <p>FileName: NoQuarterSate.java</p>  
* <p>Tile: NoQuarterSate</p>  
* <p>Description: �ǹ�����ʼ��״̬�࣬û��25��Ǯ</p>  
* @author Clarence
* @company gougouCompany
* @date 2018��8��31�� ����10:13:33
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












































