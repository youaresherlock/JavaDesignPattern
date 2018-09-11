package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;

/*
��Ҫ��ʵ��Quackable�ӿڵľ�������ʵ�־���ĳ�Ϊ���۲��ߵĳ��󷽷�
��������Ҫʵ�ֵı��۲��ߵĳ��󷽷�������ͬ�ģ�������ǽ�QuackObservable���еĵ��ö�ί�и�
Observable������
*/
//Observable������,ʵ�������б�Ҫ�Ĺ��ܣ��������һ���࣬�Ϳ����ø��ཫ����ί�и�Observable

public class Observable implements QuackObservable {
	ArrayList<Observer> observers = new ArrayList<>(); //�洢���еĹ۲��߶���
	QuackObservable duck; //���۲��߶���
	
	public Observable(QuackObservable duck) {
		this.duck = duck; //��Ҫ��¼��ǰ���ĸ����۲��߶��������ɽ�
	}

	//ע��۲��ߵĴ���
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	//֪ͨ�۲��ߵĴ���
	@Override
	public void notifyObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()) {
			Observer observer = iterator.next();
			observer.update(duck);
		}
	
	}
}







