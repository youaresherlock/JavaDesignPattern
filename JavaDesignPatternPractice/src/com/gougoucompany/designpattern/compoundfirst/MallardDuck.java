package com.gougoucompany.designpattern.compoundfirst;

public class MallardDuck implements Quackable{
	Observable observable; 
	
	public MallardDuck() {
		observable = new Observable(this); //�������д��뱻�۲��ߵ�����
	}
	
	public void quack() {
		System.out.println("Quack");
		notifyObservers(); //���۲��߷������ɽ���Ϊ��֪ͨ����ע��Ĺ۲���
	}

	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}

	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}

}
