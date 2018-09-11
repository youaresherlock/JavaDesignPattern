package com.gougoucompany.designpattern.compoundfirst;

//���Ѽ�Ӷ���У�����Ϊ����ģ������ʹ�ö죬����ʹ��������ģʽ�����������Ѽ��
//��������ʵ��Ŀ��ӿ�
public class GooseAdapter implements Quackable {
	Observable observable;
	Goose goose;
	
	//������Ҫ����Ҫ����Ķ����
	public GooseAdapter(Goose goose) {
		this.goose = goose;
		observable = new Observable(this);
	}

	//������quack()ʱ����ί�е����honk()����
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
