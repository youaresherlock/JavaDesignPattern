/*
* @Author: Clarence
* @Date:   2018-09-11 08:21:02
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-14 22:56:03
*/
/*
复合模式
有一些威力强大的OO设计同时使用多个设计模式，模式通常被一起使用，并被组合在同一个设计解决方案中
复合模式在一个解决方案中结合两个或多个模式，以解决一般或重复的问题，最常见的复合模式就是MVC(模型视图控制器
Model-View-Controller)
*/

//我们将介绍一个鸭子的程序，会用到各种不同的模式，以便更加深刻理解实际应用
//创建一个Quackable接口，赋予具体类呱呱叫的能力
package com.gougoucompany.designpattern.compoundfirst;

public interface Quackable {
	public void quack(); 
}

//红头鸭和绿头鸭具体实现
package com.gougoucompany.designpattern.compoundfirst;

public class MallardDuck implements Quackable{
	
	public void quack() {
		System.out.println("Quack");
	}

}

package com.gougoucompany.designpattern.compoundfirst;

public class RedheadDuck implements Quackable{
	
	public void quack() {
		System.out.println("Quack");
	}

}


//鸭鸣器和橡皮鸭(呱呱叫、吱吱叫)
package com.gougoucompany.designpattern.compoundfirst;

public class DuckCall implements Quackable{
	public void quack() {
		System.out.println("Kwak");
	}
}

package com.gougoucompany.designpattern.compoundfirst;

public class RubberDuck implements Quackable{
	public void quack() {
		System.out.println("Squeak");
	}
}

//主类
package com.gougoucompany.designpattern.compoundfirst;

//主类，产生鸭子，鸭子叫的模拟器方法
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulator();
	}
	
	void simulator() {
		Quackable mallardDuck = new MallardDuck();
		Quackable redheadDuck = new RedheadDuck();
		Quackable rubberDuck = new RubberDuck();
		Quackable duckCall = new DuckCall();
		
		System.out.println("\nDuck Simulator");
		
		simulator(mallardDuck);		
		simulator(redheadDuck);		
		simulator(rubberDuck);		
		simulator(duckCall);		
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}

/*
result:

Duck Simulator
Quack
Quack
Squeak
Kwak
*/

//鹅，咯咯叫
package com.gougoucompany.designpattern.compoundfirst;

public class Goose {
	public void honk() {
		System.out.println("Honk");
	}
}

//鹅和鸭子都会叫，我们为了在模拟器中使用鹅，可以使用适配器模式，将鹅适配成鸭子
package com.gougoucompany.designpattern.compoundfirst;

//适配器会实现目标接口
public class GooseAdapter implements Quackable {
	Goose goose;
	
	//构造器要传入要适配的鹅对象
	public GooseAdapter(Goose goose) {
		this.goose = goose;
	}

	//当调用quack()时，会委托到鹅的honk()方法
	@Override
	public void quack() {
		goose.honk();
	}

}

//现在我们更改主类DuckSimulator
package com.gougoucompany.designpattern.compoundfirst;

//主类，产生鸭子，鸭子叫的模拟器方法
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulator();
	}
	
	void simulator() {
		Quackable mallardDuck = new MallardDuck();
		Quackable redheadDuck = new RedheadDuck();
		Quackable rubberDuck = new RubberDuck();
		Quackable duckCall = new DuckCall();
		Quackable gooseDuck = new GooseAdapter(new Goose()); //通过适配器可以让鹅和鸭子一样
		
		
		System.out.println("\nDuck Simulator");
		
		simulator(mallardDuck);		
		simulator(redheadDuck);		
		simulator(rubberDuck);		
		simulator(duckCall);
		simulator(gooseDuck);
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}

/*
result:

Duck Simulator
Quack
Quack
Squeak
Kwak
Honk
*/

/*我们想在一群鸭子中，计算鸭子呱呱叫的次数,如何在不变化鸭子类的情况下，计算呱呱叫的次数
我们需要创建一个装饰者，通过把鸭子包装进装饰者对象，给鸭子一些新的行为(计算呱呱叫的次数的行为，而不需要修改鸭子的代码)
*/
package com.gougoucompany.designpattern.compoundfirst;

//需要实现目标接口，QuackCounter是一个装饰者
public class QuackCounter implements Quackable {
	Quackable duck; //用一个实例变量来记录被装饰的呱呱叫者
	static int numberOfQuacks; //静态变量跟踪所有的呱呱叫的次数
	
	//将被装饰者传入构造器
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}

	@Override
	public void quack() {
		duck.quack();
		numberOfQuacks++;
	}
	
	public static int getQuacks() {
		return numberOfQuacks;
	}
}

//现在修改主类
package com.gougoucompany.designpattern.compoundfirst;

//主类，产生鸭子，鸭子叫的模拟器方法
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulator();
	}
	
	void simulator() {
		Quackable mallardDuck = new QuackCounter(new MallardDuck());
		Quackable redheadDuck = new QuackCounter(new RedheadDuck());
		Quackable rubberDuck = new QuackCounter(new RubberDuck());
		Quackable duckCall = new QuackCounter(new DuckCall());
		Quackable gooseDuck = new GooseAdapter(new Goose()); //通过适配器可以让鹅和鸭子一样 不计入鹅的叫声，因此不被装饰
		
		
		System.out.println("\nDuck Simulator: With Decorator");
		
		simulator(mallardDuck);		
		simulator(redheadDuck);		
		simulator(rubberDuck);		
		simulator(duckCall);
		simulator(gooseDuck);
		
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}

/*
result:

Duck Simulator: With Decorator
Quack
Quack
Squeak
Kwak
Honk
The ducks quacked 4 times
*/

/*
工厂模式:
我们看到鸭子对象必须被装饰者装饰之后才可以获得计数的行为，我们现在将创建鸭子和装饰鸭子两部分包装起来
需要保证每个鸭子都是被装饰者装饰过的，因此我们要创建一个工厂，创建装饰过的鸭子,这个工厂要生产各种不同类型
的鸭子的产品家族，因此要用抽象工厂模式
*/
//抽象工厂
package com.gougoucompany.designpattern.compoundfirst;

/*
工厂模式:
我们看到鸭子对象必须被装饰者装饰之后才可以获得计数的行为，我们现在将创建鸭子和装饰鸭子两部分包装起来
需要保证每个鸭子都是被装饰者装饰过的，因此我们要创建一个工厂，创建装饰过的鸭子,这个工厂要生产各种不同类型
的鸭子的产品家族，因此要用抽象工厂模式
*/
//抽象工厂
public abstract class AbstractDuckFactory {
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedheadDuck();
	public abstract Quackable createDuckCall();
	public abstract Quackable createRubberDuck();
}

//此工厂创建没有装饰者的鸭子
package com.gougoucompany.designpattern.compoundfirst;

public class DuckFactory extends AbstractDuckFactory {

	/**
	 * 每个方法创建一个产品，一种特定种类的Quackable
	 */
	@Override
	public Quackable createMallardDuck() {
		return new MallardDuck();
	}

	@Override
	public Quackable createRedheadDuck() {
		return new RedheadDuck();
	}

	@Override
	public Quackable createDuckCall() {
		return new DuckCall();
	}

	@Override
	public Quackable createRubberDuck() {
		return new RubberDuck();
	}

}

//此工厂创建被装饰过的鸭子产品 CountingDuckFactory扩展自抽象工厂
package com.gougoucompany.designpattern.compoundfirst;

public class CountingDuckFactory extends AbstractDuckFactory{

	@Override
	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck());
	}

	@Override
	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedheadDuck());
	}

	@Override
	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}

	@Override
	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	}
}


//修改主类
package com.gougoucompany.designpattern.compoundfirst;

//主类，产生鸭子，鸭子叫的模拟器方法
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		//首先，创建工厂，准备把它传入simulate()方法
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulator(duckFactory);
	}
	
	/**
	 * 此方法需要一个AbstractDuckFactory参数，利用它创建鸭子，通过传入
	 * 不同的工厂，就会得到不同的产品家族
	 * <p>Title: simulator</p>  
	 * <p>Description: </p>
	 */
	void simulator(AbstractDuckFactory duckFactory) {
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable gooseDuck = new GooseAdapter(new Goose()); //通过适配器可以让鹅和鸭子一样 不计入鹅的叫声，因此不被装饰
		
		
		System.out.println("\nDuck Simulator: With Abstract Factory");
		
		simulator(mallardDuck);		
		simulator(redheadDuck);		
		simulator(rubberDuck);		
		simulator(duckCall);
		simulator(gooseDuck);
		
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}

/*
result:

Duck Simulator: With Abstract Factory
Quack
Quack
Squeak
Kwak
Honk
The ducks quacked 4 times
*/


/*
迭代器模式与组合模式
组合模式允许我们像对待单个对象一样对待对象集合
如果要管理这些鸭子，我们需要将鸭子视为一个集合，甚至是子集合(subcollection),我们可以用组合模式来完成
也就是说客户可以调用同一个方法来管理所有的不同种群的鸭子
*/
package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;
//组合需要和叶节点元素一样实现相同的接口(这样方法才能递归调用，并且调用一次就可以了)
public class Flock implements Quackable {
	ArrayList<Quackable> quackers = new ArrayList<>();
	
	/**
	 * 在之前专门讲解组合模式的菜单和菜单项中，我们有一组相同的方法，也包括add()方法
	 * 设计的好处是叶节点和组合之间是"透明的"，你无需判断到底是菜单项还是菜单。这里
	 * 我们将Flock只有add()方法，Duck没有add()方法，因为添加东西是没有意义的，但是
	 * 透明性比较差，客户如果想要调用add(),得先确定该Quackable对象是Flock才行。
	 * 所以折中考虑吧！
	 * <p>Title: add</p>  
	 * <p>Description: </p>  
	 * @param quacker
	 */
	public void add(Quackable quacker) {
		quackers.add(quacker);
	}
	
	//群体鸣叫，呱呱呱
	@Override
	public void quack() {
		Iterator<Quackable> iterator = quackers.iterator(); //这里使用了迭代器模式
		while(iterator.hasNext()) {
			Quackable quacker = (Quackable) iterator.next();
			quacker.quack();
		}
	}

}

//修改主类
package com.gougoucompany.designpattern.compoundfirst;

//主类，产生鸭子，鸭子叫的模拟器方法
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		//首先，创建工厂，准备把它传入simulate()方法
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulator(duckFactory);
	}
	
	/**
	 * 此方法需要一个AbstractDuckFactory参数，利用它创建鸭子，通过传入
	 * 不同的工厂，就会得到不同的产品家族
	 * <p>Title: simulator</p>  
	 * <p>Description: </p>
	 */
	void simulator(AbstractDuckFactory duckFactory) {
		//创建Quackable对象
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable gooseDuck = new GooseAdapter(new Goose()); //通过适配器可以让鹅和鸭子一样 不计入鹅的叫声，因此不被装饰
		
		System.out.println("\nDuck Simulator: With composite - Flocks");
		
		//创建一个Flock,然后把一些Quackable塞给他，Flock是主群
		Flock flockOfDucks = new Flock();
		
		flockOfDucks.add(redheadDuck);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(gooseDuck);
		
		Flock flockOfMallards = new Flock();
		//创建绿头鸭小家族
		Quackable mallardOne = duckFactory.createMallardDuck();
		Quackable mallardTwo = duckFactory.createMallardDuck();
		Quackable mallardThree = duckFactory.createMallardDuck();
		Quackable mallardFour = duckFactory.createMallardDuck();
		
		flockOfMallards.add(mallardOne);
		flockOfMallards.add(mallardTwo);
		flockOfMallards.add(mallardThree);
		flockOfMallards.add(mallardFour);
		
		flockOfDucks.add(flockOfMallards); //将绿头鸭群加入主群
		
		//测试一整群
		System.out.println("\nDuck Simulator: Whole Flock Simulation");
		simulator(flockOfDucks);
		
		//只测试绿头鸭群
		System.out.println("\nDuck Simulator: Mallard Flock Simulation");
		simulator(flockOfMallards);
		
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}

/*
result:

Duck Simulator: With composite - Flocks

Duck Simulator: Whole Flock Simulation
Quack
Squeak
Kwak
Honk
Quack
Quack
Quack
Quack

Duck Simulator: Mallard Flock Simulation
Quack
Quack
Quack
Quack
The ducks quacked 11 times

*/

/*
观察者模式
我们现在需要追踪个别的鸭子，有一个模式可以观察对象的行为: 观察者模式
*/
package com.gougoucompany.designpattern.compoundfirst;

/*
 * Observable就是被观察的对象，需要注册删除和通知观察者的方法，这里简单起见我们只加入注册和通知观察者方法
 * QuackObservable是一个接口，任何想被观察的Quackable都必须实现QuackObservable接口
 */
public interface QuackObservable {
	/**
	 * 具有注册观察者的方法，任何实现了Observer接口的观察者都可以监听呱呱叫


	 * <p>Title: registerObserver</p>  
	 * <p>Description: </p>  
	 * @param observer
	 */
	public void registerObserver(Observer observer); 
	public void notifyObservers(); //通知观察者的方法
	
}

//让所有的鸭子都实现观察者接口，因此我们只需要将Quackable接口继承QuackObservable
package com.gougoucompany.designpattern.compoundfirst;

//我们将介绍一个鸭子的程序，会用到各种不同的模式，以便更加深刻理解实际应用
//创建一个Quackable接口，赋予具体类呱呱叫的能力
public interface Quackable extends QuackObservable{
	public void quack(); 
}


/*
需要在实现Quackable接口的具体类中实现具体的成为被观察者的抽象方法
但是我们要实现的被观察者的抽象方法都是相同的，因此我们将QuackObservable所有的调用都委托给
Observable辅助类
*/
//Observable辅助类,实现了所有必要的功能，将其插入一个类，就可以让该类将工作委托给Observable
package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable implements QuackObservable {
	ArrayList<Observer> observers = new ArrayList<>(); //存储所有的观察者对象
	QuackObservable duck; //被观察者对象
	
	public Observable(QuackObservable duck) {
		this.duck = duck; //需要记录当前是哪个被观察者对象在呱呱叫
	}

	//注册观察者的代码
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	//通知观察者的代码
	@Override
	public void notifyObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()) {
			Observer observer = iterator.next();
			observer.update(duck);
		}
	
	}
}


//让所有鸭子具体类(所有继承Quackble的具体类，鸭子，被装饰者装饰的鸭子，或群)实现被观察者接口，这里其实我觉得可以用继承的方式来实现代码更加简洁一些
//红头鸭
package com.gougoucompany.designpattern.compoundfirst;

public class RedheadDuck implements Quackable{
	Observable observable;
	
	public RedheadDuck() {
		observable = new Observable(this);
	}

	@Override
	public void quack() {
		System.out.println("Quack");
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}
}

//橡皮鸭
package com.gougoucompany.designpattern.compoundfirst;

public class RubberDuck implements Quackable{
	Observable observable;
	
	public RubberDuck() {
		observable = new Observable(this);
	}
	
	public void quack() {
		System.out.println("Squeak");
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}
}


//绿头鸭
package com.gougoucompany.designpattern.compoundfirst;

public class MallardDuck implements Quackable{
	Observable observable; 
	
	public MallardDuck() {
		observable = new Observable(this); //构造器中传入被观察者的引用
	}
	
	public void quack() {
		System.out.println("Quack");
		notifyObservers(); //被观察者发生呱呱叫行为就通知所有注册的观察者
	}

	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}

}

//鸭鸣器
package com.gougoucompany.designpattern.compoundfirst;

public class DuckCall implements Quackable{
	Observable observable;
	
	public DuckCall() {
		observable = new Observable(this);
	}
	
	public void quack() {
		System.out.println("Kwak");
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}
}

//鹅适配成鸭子 鹅适配器
package com.gougoucompany.designpattern.compoundfirst;

//鹅和鸭子都会叫，我们为了在模拟器中使用鹅，可以使用适配器模式，将鹅适配成鸭子
//适配器会实现目标接口
public class GooseAdapter implements Quackable {
	Observable observable;
	Goose goose;
	
	//构造器要传入要适配的鹅对象
	public GooseAdapter(Goose goose) {
		this.goose = goose;
		observable = new Observable(this);
	}

	//当调用quack()时，会委托到鹅的honk()方法
	@Override
	public void quack() {
		goose.honk();
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}

}


//实现Flock和QuackCounter,让他们实现QuackObservable接口，成为被观察者
package com.gougoucompany.designpattern.compoundfirst;

/*我们想在一群鸭子中，计算鸭子呱呱叫的次数,如何在不变化鸭子类的情况下，计算呱呱叫的次数
我们需要创建一个装饰者，通过把鸭子包装进装饰者对象，给鸭子一些新的行为(计算呱呱叫的次数的行为，而不需要修改鸭子的代码)
*/
//需要实现目标接口，QuackCounter是一个装饰者
public class QuackCounter implements Quackable {
	Quackable duck; //用一个实例变量来记录被装饰的呱呱叫者
	static int numberOfQuacks; //静态变量跟踪所有的呱呱叫的次数
	
	//将被装饰者传入构造器
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}

	@Override
	public void quack() {
		duck.quack(); //当调用quack()方法，鸭子鸣叫，因为鸭子具体类已经在quack()中调用了通知观察者的方法，因此不需要在此方法中添加
		numberOfQuacks++;
	}
	
	public static int getQuacks() {
		return numberOfQuacks;
	}

	/**
	 * 被装饰者装饰的鸭子也实现了Quackable接口，所以也是被观察者QuackObservable
	 */
	@Override
	public void registerObserver(Observer observer) {
		duck.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		duck.notifyObservers();
	}
}


package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;
/*
迭代器模式与组合模式
组合模式允许我们像对待单个对象一样对待对象集合
如果要管理这些鸭子，我们需要将鸭子视为一个集合，甚至是子集合(subcollection),我们可以用组合模式来完成
也就是说客户可以调用同一个方法来管理所有的不同种群的鸭子
*/
//组合需要和叶节点元素一样实现相同的接口(这样方法才能递归调用，并且调用一次就可以了)
public class Flock implements Quackable {
	ArrayList<Quackable> quackers = new ArrayList<>();
	
	/**
	 * 在之前专门讲解组合模式的菜单和菜单项中，我们有一组相同的方法，也包括add()方法
	 * 设计的好处是叶节点和组合之间是"透明的"，你无需判断到底是菜单项还是菜单。这里
	 * 我们将Flock只有add()方法，Duck没有add()方法，因为添加东西是没有意义的，但是
	 * 透明性比较差，客户如果想要调用add(),得先确定该Quackable对象是Flock才行。
	 * 所以折中考虑吧！
	 * <p>Title: add</p>  
	 * <p>Description: </p>  
	 * @param quacker
	 */
	public void add(Quackable quacker) {
		quackers.add(quacker);
	}
	
	//群体鸣叫，呱呱呱
	@Override
	public void quack() {
		Iterator<Quackable> iterator = quackers.iterator(); //这里使用了迭代器模式
		while(iterator.hasNext()) {
			Quackable quacker = (Quackable) iterator.next();
			quacker.quack();
		}
	}

	/**
	 * 如果我们观察一个组合，就等于观察我们组合内的所有东西，因此，当你注册要观察某个群(flock),
	 * 就等于注册要观察所有的孩子，这甚至还包括另一个群(鸭子主群中有鸭子群也有鸭子)
	 * 当向Flock注册观察者时，其实等于向Flock中的所有Quackable注册观察者，不管是一个鸭子还是一群鸭子
	 */
	@Override
	public void registerObserver(Observer observer) {
		Iterator<Quackable> iterator = quackers.iterator();
		while(iterator.hasNext()) {
			Quackable duck = (Quackable) iterator.next(); //可能是一个群，也可能是一个鸭子
			//如果是一个群，则会深度递归，直到所有子节点都注册完成观察者
			duck.registerObserver(observer);
		}

	}

	//这里每个鸭子都实现了这个方法，因此可以为空
	@Override
	public void notifyObservers() {
	}

}

//观察者接口和观察者
package com.gougoucompany.designpattern.compoundfirst;


//观察者接口，只有一个抽象方法update(),需要传入正在呱呱叫的对象(QuackObservable)
public interface Observer {
	public void update(QuackObservable duck);
}


package com.gougoucompany.designpattern.compoundfirst;

//观察者继承Observer接口
package com.gougoucompany.designpattern.compoundfirst;

//观察者继承Observer接口
public class Quackologist implements Observer {

	@Override
	public void update(QuackObservable duck) {
		System.out.println("Quackologist: " + duck.getClass().getSimpleName() + " just quacked.");
	}
}


//修改主类DuckSimulator
package com.gougoucompany.designpattern.compoundfirst;

//主类，产生鸭子，鸭子叫的模拟器方法
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		//首先，创建工厂，准备把它传入simulate()方法
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulator(duckFactory);
	}
	
	/**
	 * 此方法需要一个AbstractDuckFactory参数，利用它创建鸭子，通过传入
	 * 不同的工厂，就会得到不同的产品家族
	 * <p>Title: simulator</p>  
	 * <p>Description: </p>
	 */
	void simulator(AbstractDuckFactory duckFactory) {
		//创建Quackable对象
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		//这里鹅没有被QuackCounter所修饰，因此不加入计数
		Quackable gooseDuck = new GooseAdapter(new Goose()); //通过适配器可以让鹅和鸭子一样 不计入鹅的叫声，因此不被装饰
		
		//创建一个Flock,然后把一些Quackable塞给他，Flock是主群
		Flock flockOfDucks = new Flock(); 
		
		flockOfDucks.add(redheadDuck);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(gooseDuck);
		
		Flock flockOfMallards = new Flock();
		//创建绿头鸭小家族
		Quackable mallardOne = duckFactory.createMallardDuck();
		Quackable mallardTwo = duckFactory.createMallardDuck();
		Quackable mallardThree = duckFactory.createMallardDuck();
		Quackable mallardFour = duckFactory.createMallardDuck();
		
		flockOfMallards.add(mallardOne);
		flockOfMallards.add(mallardTwo);
		flockOfMallards.add(mallardThree);
		flockOfMallards.add(mallardFour);
		
		flockOfDucks.add(flockOfMallards);
		
		System.out.println("\nDuck Simulator: With Observer");
		Quackologist quackologist = new Quackologist(); //观察者
		flockOfDucks.registerObserver(quackologist); //将quackologist注册成为一个群的观察者
		simulator(flockOfDucks);
		
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}

/*
result:

Duck Simulator: With Observer
Quack
Quackologist: RedheadDuck just quacked.
Squeak
Quackologist: RubberDuck just quacked.
Kwak
Quackologist: DuckCall just quacked.
Honk
Quackologist: GooseAdapter just quacked.
Quack
Quackologist: MallardDuck just quacked.
Quack
Quackologist: MallardDuck just quacked.
Quack
Quackologist: MallardDuck just quacked.
Quack
Quackologist: MallardDuck just quacked.
The ducks quacked 7 times
这里一共计数7次，因为鹅没有被QuackCounter所修饰，因此没有鸣叫次数计数的行为
*/


//下面是MVC模式的实战
/*
模型是负责维护所有的数据、状态和应用逻辑。BeatModel主要工作是管理节拍，维护当前BPM的状态和许多产生MIDI事件
的代码，以便产生我们听到的节拍，同时也暴露一些接口，让控制器操纵节拍，让视图和控制器获得模型的状态
另外模型使用了观察者模式，需要注册，删除观察者方法，以及一个存储观察者的集合
*/

//模型接口
package com.gougoucompany.designpattern.mvc;

/*
模型使用观察者模式，我们需要让对象注册成为观察者并发送出通知
*/
public interface BeatModelInterface {
	
	//下面四个方法，是供控制器调用的，控制器根据用户在界面上的操作而对模型做出适当的处理
	void initialize();
	
	void on();
	
	void off();
	
	void setBPM(int bpm);
	
	//下面这些方法允许视图和控制器取得状态，并变成观察者
	int getBPM();
	
	void registerObserver(BeatObserver o);//注册成为节拍观察者
	
	void removeObserver(BeatObserver o);
	
	void registerObserver(BPMObserver o); //注册成为BPM观察者
	
	void removeObserver(BPMObserver o);
}


//观察者接口
package com.gougoucompany.designpattern.mvc;

//节拍观察者接口
public interface BeatObserver {
	void updateBeat();
}


package com.gougoucompany.designpattern.mvc;

//BPM观察者接口
public interface BPMObserver {
	void updateBPM();
}

//控制器接口
package headfirst.designpatterns.combined.djview;
 
/*
模型使用观察者模式，我们需要让对象注册成为观察者并发送出通知
*/
public interface BeatModelInterface {

	//下面四个方法，是供控制器调用的，控制器根据用户在界面上的操作而对模型做出适当的处理
	void initialize();
  
	void on();
  
	void off();
  
    void setBPM(int bpm);
  
  //下面这些方法允许视图和控制器取得状态，并变成观察者
	int getBPM();
  
	void registerObserver(BeatObserver o); //注册成为节拍观察者
  
	void removeObserver(BeatObserver o);
  
	void registerObserver(BPMObserver o); // 注册成为BPM观察者
  
	void removeObserver(BPMObserver o);
}


//节奏控制器
package headfirst.designpatterns.combined.djview;

/*
控制器实现ControllerInterface接口
*/  
public class BeatController implements ControllerInterface {
	/*
	控制器在MVC设计中起到桥梁的作用，所以它拥有模型和视图的实例
	*/
	BeatModelInterface model;
	DJView view;
   
   /*
   将模型传入控制器的构造器中
   */
	public BeatController(BeatModelInterface model) {
		this.model = model;
		//创建实例并初始化视图和模型
		view = new DJView(this, model);
        view.createView();
        view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}
  
  //用户从菜单中选择start,触发点击事件，然后视图中会调用start控制模型和视图，下面同理
	public void start() {
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
  
	public void stop() {
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
    
	public void increaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm + 1);
	}
    
	public void decreaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm - 1);
  	}
  
 	public void setBPM(int bpm) {
		model.setBPM(bpm);
	}
}


//模型
package headfirst.designpatterns.combined.djview;

import javax.sound.midi.*;

import java.util.*;

/*
模型: 持有所有的数据、状态和程序逻辑
The MetaEventListener interface should be implemented by classes whose instances need to be notified when a Sequencer 
has processed a MetaMessage. To register a MetaEventListener object to receive such notifications, pass it as the
 argument to the addMetaEventListener method of Sequencer.
 Sequencer处理源信息时，BeatModel实例需要被通知
void	meta​(MetaMessage meta)	这个接口只有一个抽象方法，
	Invoked when a Sequencer has encountered and processed a MetaMessage in the Sequence it is processing.
*/
public class BeatModel implements BeatModelInterface, MetaEventListener {

	/*
	A hardware or software device that plays back a MIDI sequence is known as a sequencer. A MIDI sequence contains lists of 
	time-stamped MIDI data, such as might be read from a standard MIDI file. Most sequencers also provide functions for creating
	 and editing sequences.
	The Sequencer interface includes methods for the following basic MIDI sequencer operations:

	obtaining a sequence from MIDI file data
	starting and stopping playback
	moving to an arbitrary position in the sequence
	changing the tempo (speed) of playback
	synchronizing playback to an internal clock or to received MIDI messages
	controlling the timing of another device
	*/
	Sequencer sequencer;
	ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	int bpm = 90;
	/*
	A Sequence is a data structure containing musical information (often an entire song or composition) that can be played 
	back by a Sequencer object. Specifically, the Sequence contains timing information and one or more tracks. Each track 
	consists of a series of MIDI events (such as note-ons, note-offs, program changes, and meta-events). The sequence's timing 
	information specifies the type of unit that is used to time-stamp the events in the sequence.
	A Sequence can be created from a MIDI file by reading the file into an input stream and invoking one of the
	getSequence methods of MidiSystem. A sequence can also be built from scratch by adding new Tracks to an empty Sequence
	, and adding MidiEvent objects to these Tracks.
	*/
	Sequence sequence;
	/*
	A MIDI track is an independent stream of MIDI events (time-stamped MIDI data) that can be stored along with 
	other tracks in a standard MIDI file. 
	*/
	Track track;

	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}

	public void on() {
		System.out.println("Starting the sequencer");
		sequencer.start();
		setBPM(90);
	}

	public void off() {
		setBPM(0);
		sequencer.stop();
	}

	public void setBPM(int bpm) {
		this.bpm = bpm;
		//Sets the tempo in beats per minute. The actual tempo of playback is the product of the specified value and the tempo factor.
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers(); //通知所有的BPM观察者,BPM已经改变了
	}

	public int getBPM() {
		return bpm;
	}

	//当新的节拍开始时，通知所有全部的beatObserver,新的节拍开始了
	void beatEvent() {
		notifyBeatObservers(); 
	}


	public void registerObserver(BeatObserver o) {
		beatObservers.add(o); //注册beat观察者
	}

	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); i++) {
			BeatObserver observer = (BeatObserver)beatObservers.get(i);
			observer.updateBeat();
		}
	}

	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o); //注册称为BPM观察者
	}

	public void notifyBPMObservers() {
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			observer.updateBPM();
		}
	}


	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o); //第一次出现的位置,没有为-1
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}



	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}


	/*
	A MetaMessage is a MidiMessage that is not meaningful to synthesizers,
	 but that can be stored in a MIDI file and interpreted by a sequencer program. 
	*/
	public void meta(MetaMessage message) {
		//message.getType() an integer representing the MetaMessage type
		if (message.getType() == 47) {
			beatEvent();
			sequencer.start();
			setBPM(getBPM());
		}
	}

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			//Opens the device, indicating that it should now acquire any system resources it requires and become operational.
			sequencer.open();
			sequencer.addMetaEventListener(this); //使用了事件监听模式，下节我们会讲解这块遗漏的部分(观察者模式的进一步变形)
			/*
			Sequence.PPQ,The tempo-based timing type, for which the resolution is expressed in pulses (ticks) per quarter note.
			看文档看的蛋疼，每一四分音符几个tick(pulses per quarter note)，音乐的世界我不懂
			The sequence will contain no initial tracks. Tracks may be added to or removed from the sequence
			using createTrack() and deleteTrack(javax.sound.midi.Track).
			*/
			sequence = new Sequence(Sequence.PPQ,4);
			/*
			Creates a new, initially empty track as part of this sequence.
			*/
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM()); //set beats per minute
			//A value indicating that looping should continue indefinitely rather than complete after a specific number of loops.不断循环播放
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	public void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};

		sequence.deleteTrack(null);
		track = sequence.createTrack();

		makeTracks(trackList);
		track.add(makeEvent(192,9,1,0,4));      
		try {
			//Sets the current sequence on which the sequencer operates.
			sequencer.setSequence(sequence);                    
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	public void makeTracks(int[] list) {        

		for (int i = 0; i < list.length; i++) {
			int key = list[i];

			if (key != 0) {
				//	add​(MidiEvent event)	Adds a new event to the track.
				// Command value for Note On message (0x90, or 144).
				track.add(makeEvent(144,9,key, 100, i));
				// Command value for Note Off message (0x80, or 128).
				track.add(makeEvent(128,9,key, 100, i+1));
			}
		}
	}

	public  MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		//MIDI events contain a MIDI message and a corresponding time-stamp expressed in ticks
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			//Constructs a new ShortMessage which represents a channel MIDI message that takes up to two data bytes.
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);

		} catch(Exception e) {
			e.printStackTrace(); 
		}
		return event;
	}
}


//脉动柱组件
package com.gougoucompany.designpattern.mvc;

import javax.swing.JProgressBar;

//脉动柱组件
public class BeatBar extends JProgressBar implements Runnable{
	private static final long serialVersionUID = 2L;
	JProgressBar progresssBar; //进度条
	Thread thread;
	
	public BeatBar() {
		thread = new Thread(this);
		//设置最大值，继承下来的方法
		setMaximum(100);
		thread.start();
	}

	//线程的run方法是一个死循环，因此始终监听脉动柱的数值变化，来进行进度条的设置
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;;) {
			int value = getValue();
			value = (int)(value * .75); //从设置的值开始缩减到接近0
			setValue(value);
			repaint();
			try {
				Thread.sleep(50);//每50毫秒更新一次
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}

}



//实现视图

















































































































































