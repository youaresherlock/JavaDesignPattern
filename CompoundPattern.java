/*
* @Author: Clarence
* @Date:   2018-09-11 08:21:02
* @Last Modified by:   Clarence
* @Last Modified time: 2018-09-11 12:28:40
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