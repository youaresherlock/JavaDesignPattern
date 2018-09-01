package com.gougoucompany.designpattern.proxyfirst;

import org.w3c.dom.css.ElementCSSInlineStyle;

/**  
* <p>FileName: WinnerState.java</p>  
* <p>Tile: WinnerState</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018��9��1�� ����7:15:31
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
		//������ʵ����ֱ���ж��ǹ���������Ϊ����Ӯ��״̬��Ψһ״̬��HasQuareterState�������û�ת�������������Ѿ�����Ŀ�������жϣ����ﲻ֪�����ﲻ���ǣ�������Ϊ�˼����
		if(gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState()); //����еڶ����ǹ����ͷţ�û�о͸ı��ǹ���Ϊ����״̬
		} else {
			gumballMachine.releaseBall(); //�ͷ��ǹ�֮���ж��ǹ�����������������û��Ͷ��״̬�����û�����ǹ�����������״̬��
			if(gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("Oops, out of gumballs!"); 
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
	}

}




