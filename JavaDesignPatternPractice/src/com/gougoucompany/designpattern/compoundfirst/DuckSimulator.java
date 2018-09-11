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
		//����Quackable����
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		//�����û�б�QuackCounter�����Σ���˲��������
		Quackable gooseDuck = new GooseAdapter(new Goose()); //ͨ�������������ö��Ѽ��һ�� �������Ľ�������˲���װ��
		
		//����һ��Flock,Ȼ���һЩQuackable��������Flock����Ⱥ
		Flock flockOfDucks = new Flock(); 
		
		flockOfDucks.add(redheadDuck);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(gooseDuck);
		
		Flock flockOfMallards = new Flock();
		//������ͷѼС����
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
		Quackologist quackologist = new Quackologist(); //�۲���
		flockOfDucks.registerObserver(quackologist); //��quackologistע���Ϊһ��Ⱥ�Ĺ۲���
		simulator(flockOfDucks);
		
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
	}
	
	void simulator(Quackable duck) {
		duck.quack();
	}
}
