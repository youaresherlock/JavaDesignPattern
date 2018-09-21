/*
备忘录模式
	备忘录模式(Memento Patten)又叫做快照模式(Snapshot Pattern)或Token模式，是对象的行为模式。
备忘录对象是一个用来存储另一个对象内部状态的快照的快照的对象。备忘录模式的用意是在不破坏封装的条件下，
将一个对象的状态捕捉(Capture)住，并外部化，存储起来，从而可以在将来适合的时候把这个对象还原到存储起来的状态。
备忘录模式常常与命令模式和迭代器模式一同使用。

意图: 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。

主要解决: 
	所谓备忘录模式就是在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以
在以后将对象恢复到原先保存的状态。

何时使用: 
	很多时候我们总是需要记录一个对象的内部状态，这样做的目的就是为了允许用户取消不确定或者错误的操作，能够恢复到
原先的状态。
	我的理解是(我是java后端工程师，Python也写一些)，一般游戏存档是通过序列化的形式来实现的，序列化机制只保存对象的类型信息(如果
属性引用了对象，则会序列化下去)，属性的类型信息和属性值(和方法无关)，这样用户重新登录或者进入对应时间戳存档就会将序列化的内容加载
进来,将对应的对象的恢复成用户存档时的状态。比如血量，魔法，武器，盔甲，地图位置坐标等等。备忘录模式感觉还是适合小操作的恢复(毕竟都是保存
在内存中是吧)。

如何解决: 通过一个备忘录类专门存储对象状态

关键代码: 客户不与备忘录类耦合，与备忘录管理类耦合

实用实例: 
	1.游戏存档
	2.windows中的ctrl+z
	3.IE中的后退(一个栈保存历史状态)
	4.数据库的事务管理

优点: 
	1.给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态
	2.实现了信息的封装，使得用户不需要关心状态的保存细节

缺点: 
	消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。

使用场景: 
	1.需要保存/恢复数据的相关状态场景。
	2.提供一个可回滚的操作

注意事项: 
	1.为了符合迪米特原则，还要增加一个管理备忘录的类。(迪米特原则: 最少知识原则，一个对象应当对其他对象有尽可能少的了解，
不要和陌生人说话，记得前面我们提到过这个原则)
	2.为了节约内存，可使用原型模式+备忘录模式


例子:
备忘录模式使用三个类Memento、Originator和CareTaker.Memento包含了要被恢复的对象的状态。Originator创建
并在Memento对象中存储状态。Caretaker对象负责从Memento中恢复对象的状态。
MementoPatternDemo,我们的演示类使用CareTaker和Originator对象来显示对象的状态恢复。
*/

//Memento类
package com.gougoucompany.designpattern.memento;

public class Memento {
	private String state;
	
	public Memento(String state) {
		// TODO Auto-generated constructor stub
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
}


package com.gougoucompany.designpattern.memento;

//发起人
public class Originator {
	private String state;
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	public Memento saveStateToMemento() {
		return new Memento(state);
	}
	
	public void getStateFromMemento(Memento memento) {
		state = memento.getState();
	}
}


//看护人类
package com.gougoucompany.designpattern.memento;

import java.util.ArrayList;
import java.util.List;

//CareTaker
public class CareTaker {
	private List<Memento> mementoList = new ArrayList<Memento>();
	
	public void add(Memento state) {
		mementoList.add(state);
	}
	
	public Memento get(int index) {
		return mementoList.get(index);
	}
}

//测试类
package com.gougoucompany.designpattern.memento;

public class MementoPatternDemo {
	public static void main(String[] args) {
		Originator originator = new Originator();
		CareTaker careTaker = new CareTaker();
		originator.setState("State #1");
		careTaker.add(originator.saveStateToMemento());
		originator.setState("State #2");
		careTaker.add(originator.saveStateToMemento());
		
		originator.setState("State #3");
		System.out.println("Current State: " + originator.getState());
		originator.getStateFromMemento(careTaker.get(0));
		System.out.println("First saved State: " + originator.getState());
		originator.getStateFromMemento(careTaker.get(1));
		System.out.println("Second saved State: " + originator.getState());
	}
}

























