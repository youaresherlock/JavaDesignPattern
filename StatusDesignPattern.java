/*
* @Author: Clarence
* @Date:   2018-08-31 00:14:35
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-01 10:18:00
*/
package com.gougoucompany.designpattern.statusfirst;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**  
* <p>FileName: GamballMachine.java</p>  
* <p>Tile: GamballMachine</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 上午12:47:22
* @version 1.0  
*/
/*
状态模式
策略模式和状态模式密不可分，策略模式是围绕可以互换的算法来创建成功业务的，然而，状态走的是更高
的路，它通过改变对象内部的状态来帮助对象控制自己的行为。
例子: 糖果机都有自己的接口或者自己内部的方法，通过不同状态来调用对应的方法，来控制自身的行为。
糖果机的状态主要有四种: 没有25分钱 有25分钱 售出糖果 糖果售罄
系统中发生的动作是从这些状态中过度。
*/
//创建一个类，它的作用就是一个状态机，对每一个动作，都创建了一个对应的方法，这些方法利用条件语句来决定在每个状态内什么行为是恰当的

public class GamballMachine {

	//四种状态
	final static int SOLD_OUT = 0; 
	final static int NO_QUARTER = 1;
	final static int HAS_QUARTER = 2;
	final static int SOLD = 3;
	
	int state = SOLD_OUT; //一开始糖果机状态为糖果售罄
	int count = 0; //糖果机中糖果数目
	
	public GamballMachine(int count) {
		this.count = count;
		if(count > 0) {
			state = NO_QUARTER;
		}
	}
	
	//当投入25分钱对应的行为
	public void insertQuarter() {
		if(state == HAS_QUARTER) {
			System.out.println("You can't insert another quarter"); //用户已经投过钱了
		} else if(state == NO_QUARTER) {
			//接受用户投入25分，转换状态
			state = HAS_QUARTER;
			System.out.println("You inserted a quarter");
		} else if(state == SOLD_OUT) {
			//售罄不能投入
			System.out.println("You can't insert a quarter, the machine is sold out");
		} else if(state == SOLD) {
			//等待状态转换
			System.out.println("Please wait, we're already giving you a gumball");
		}
	}
	
	//用户试着退出25分钱
	public void ejectQuarter() {
		if(state == HAS_QUARTER){
			System.out.println("Quarter returned");
			state = NO_QUARTER; //有25分钱退出
		} else if(state == NO_QUARTER) {
			System.out.println("You haven't inserted a quarter"); //用户没有投入25分
		} else if(state == SOLD) {
			// 用户转动了手柄，不能退了
			System.out.println("Sorry, you already turned the crank");
		} else if(state == SOLD_OUT) {
			//糖果售罄状态，就不能接受25分钱，不能退
			System.out.println("You can't eject, you haven't inserted a quarter yet");
		}
	}
	
	//用户试着转动手柄
	public void turnCrank() {
		if(state == SOLD) { //用户已经得到糖果了
			System.out.println("Turning twice doesn't get you another gumball!");
		} else if(state == NO_QUARTER) {
			//转动手柄，但是没有投币
			System.out.println("You turned but there's no quarter");
		} else if(state == SOLD_OUT) {
			//没有任何糖果
			System.out.println("You turned, but there are no gumballs");
		} else if(state == HAS_QUARTER) {
			//有硬币，同时用户转动手柄，改变状态，发放糖果
			System.out.println("You turned...");
			state = SOLD;
			dispense(); //发放
		}
	}

	//发放糖果，系统内部的行为
	private void dispense() {
		if(state == SOLD) {
			//出售标志，发放
			System.out.println("A gumball comes rolling out the slot");
			count = count - 1; //这里不需要做判断数量，用户投币时候已经做了判断
			if(count == 0) {
				//糖果状态变为售罄
				System.out.println("Ooops, out of gamballs!");
				state = SOLD_OUT;
			} else {
				state = NO_QUARTER;
			}
		} else if(state == NO_QUARTER) { //根本不会出现这种情况
			System.out.println("You need to pay first");
		} else if(state == SOLD_OUT) { //不会出现这种情况 没有糖果只能对应糖果售罄的状态，看是否有环路来决定
			//没糖果了
			System.out.println("No gumball dispensed");
		} else if(state == HAS_QUARTER) {
			System.out.println("No gumball dispensed");
		}
	}
}

//测试类
package com.gougoucompany.designpattern.statusfirst;

/**  
* <p>FileName: GumballMachineTestDrive.java</p>  
* <p>Tile: GumballMachineTestDrive</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 上午1:24:07
* @version 1.0  
*/
public class GumballMachineTestDrive {
	public static void main(String args[]) {
		GumballMachine gumballMachine = new GumballMachine(5);
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
	}
}
/*
result:
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 5 gumballs
Machine is waiting for quarter

You inserted a quarter
You turned...
A gumball comes rolling out the slot
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 4 gumballs
Machine is waiting for quarter

You inserted a quarter
Quarter returned
You turned but there's no quarter
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 4 gumballs
Machine is waiting for quarter

You inserted a quarter
You turned...
A gumball comes rolling out the slot
You inserted a quarter
You can't insert another quarter
You turned...
A gumball comes rolling out the slot
You haven't inserted a quarter
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 2 gumballs
Machine is waiting for quarter

You inserted a quarter
You can't insert another quarter
You turned...
A gumball comes rolling out the slot
You inserted a quarter
You turned...
A gumball comes rolling out the slot
Ooops, out of gamballs!
You can't insert a quarter, the machine is sold out
You turned, but there are no gumballs
Mighty Gumballl, Inc.
Java-enabled Standing Gumball Model #2004
Inventory: 0 gumballs
Machine is waiting for quarter
*/


/*
现在需求发生了变化，糖果公司增加了一种状态赢家状态，即10个人有1人可以得到免费的糖果
当曲柄被转动时，有%10的几率掉下来是两颗糖果
我看的是HeadFirst设计模式书，这块我觉得可以直接产生随机数0-9，如果是0，则2，非0，则1，对应发放在dispense()
SOLD状态执行减少糖果数量，以及发放的操作。暂时感觉不需要像书中这么麻烦 这是因为SOLD和dispense()是一一对应的，如果状态图是多对一，成环状，那么就是书中的情况
我们需要在之前的类中增加一种状态，另外在四种方法中增加一个赢家状态的条件判断，以及该行为下对应的操作

评价这种改动方案:
1.没有遵守开放-关闭原则，也就是尽量少改动类，将变化的地方没有解耦，封装，改动的地方太多了，几乎是所有代码
2.不符合面向对象 面向对象三大特征是继承多态封装，我们都没有用到
3.变动需求，可能出现bug
*/

//重新设计，我们推翻原有代码，重写将状态对象封装在各自的类中，然后在动作发生时委托给当前状态
/*
定义State interface 公有抽象方法是insertQuarter()、ejectQuarter()、trunCrank()、dispense()
为机器中的每个状态实现状态类，这些类将负责在对应的状态下进行机器的行为SoldState、SoldOutState、NoQuarterState、HasQuarterState、WinnerState
将动作委托到状态类，状态类都实现State接口，这样的做法是有限状态机，状态太多会类爆炸的情况
*/

package com.gougouocompany.designpattern.statussecond;

/**  
* <p>FileName: State.java</p>  
* <p>Tile: State</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 下午10:11:52
* @version 1.0  
*/
public interface State {
	
	//所有的状态的动作接口
	void InsertQuarter();
	
	void ejectQuarter();
	
	void turnCrank();
	
	void dispense();
}

package com.gougouocompany.designpattern.statussecond;

/**  
* <p>FileName: SoldState.java</p>  
* <p>Tile: SoldState</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年9月1日 上午9:15:11
* @version 1.0  
*/
public class SoldState implements State {
	
	GumballMachine gumballMachine;
	
	public SoldState(GumballMachine gumballMachine) {
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
		gumballMachine.releaseBall();
		if(gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		} else {
			System.out.println("Oops, out of gumballs!");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}

}

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


	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
}































































































































































































