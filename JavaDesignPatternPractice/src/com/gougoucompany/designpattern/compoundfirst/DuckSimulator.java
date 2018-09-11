package com.gougoucompany.designpattern.compoundfirst;

//���࣬����Ѽ�ӣ�Ѽ�ӽе�ģ��������
public class DuckSimulator {
	public static void main(String args[]) {
		DuckSimulator simulator = new DuckSimulator();
		//���ȣ�����������׼����������simulate()����
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulator(duckFactory);
	}
	
	/**
	 * �˷�����Ҫһ��AbstractDuckFactory����������������Ѽ�ӣ�ͨ������
	 * ��ͬ�Ĺ������ͻ�õ���ͬ�Ĳ�Ʒ����
	 * <p>Title: simulator</p>  
	 * <p>Description: </p>
	 */
	void simulator(AbstractDuckFactory duckFactory) {
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable gooseDuck = new GooseAdapter(new Goose()); //ͨ�������������ö��Ѽ��һ�� �������Ľ�������˲���װ��
		
		
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
