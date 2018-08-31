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