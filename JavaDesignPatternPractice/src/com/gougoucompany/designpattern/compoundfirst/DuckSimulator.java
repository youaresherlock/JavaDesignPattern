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
