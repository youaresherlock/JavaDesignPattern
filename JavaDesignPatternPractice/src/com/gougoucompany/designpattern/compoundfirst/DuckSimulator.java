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
