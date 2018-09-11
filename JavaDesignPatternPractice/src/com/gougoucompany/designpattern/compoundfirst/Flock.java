package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;
/*
������ģʽ�����ģʽ
���ģʽ����������Դ���������һ���Դ����󼯺�
���Ҫ������ЩѼ�ӣ�������Ҫ��Ѽ����Ϊһ�����ϣ��������Ӽ���(subcollection),���ǿ��������ģʽ�����
Ҳ����˵�ͻ����Ե���ͬһ���������������еĲ�ͬ��Ⱥ��Ѽ��
*/
//�����Ҫ��Ҷ�ڵ�Ԫ��һ��ʵ����ͬ�Ľӿ�(�����������ܵݹ���ã����ҵ���һ�ξͿ�����)
public class Flock implements Quackable {
	ArrayList<Quackable> quackers = new ArrayList<>();
	
	/**
	 * ��֮ǰר�Ž������ģʽ�Ĳ˵��Ͳ˵����У�������һ����ͬ�ķ�����Ҳ����add()����
	 * ��Ƶĺô���Ҷ�ڵ�����֮����"͸����"���������жϵ����ǲ˵���ǲ˵�������
	 * ���ǽ�Flockֻ��add()������Duckû��add()��������Ϊ��Ӷ�����û������ģ�����
	 * ͸���ԱȽϲ�ͻ������Ҫ����add(),����ȷ����Quackable������Flock���С�
	 * �������п��ǰɣ�
	 * <p>Title: add</p>  
	 * <p>Description: </p>  
	 * @param quacker
	 */
	public void add(Quackable quacker) {
		quackers.add(quacker);
	}
	
	//Ⱥ�����У�������
	@Override
	public void quack() {
		Iterator<Quackable> iterator = quackers.iterator(); //����ʹ���˵�����ģʽ
		while(iterator.hasNext()) {
			Quackable quacker = (Quackable) iterator.next();
			quacker.quack();
		}
	}

	/**
	 * ������ǹ۲�һ����ϣ��͵��ڹ۲���������ڵ����ж�������ˣ�����ע��Ҫ�۲�ĳ��Ⱥ(flock),
	 * �͵���ע��Ҫ�۲����еĺ��ӣ���������������һ��Ⱥ(Ѽ����Ⱥ����Ѽ��ȺҲ��Ѽ��)
	 * ����Flockע��۲���ʱ����ʵ������Flock�е�����Quackableע��۲��ߣ�������һ��Ѽ�ӻ���һȺѼ��
	 */
	@Override
	public void registerObserver(Observer observer) {
		Iterator<Quackable> iterator = quackers.iterator();
		while(iterator.hasNext()) {
			Quackable duck = (Quackable) iterator.next(); //������һ��Ⱥ��Ҳ������һ��Ѽ��
			//�����һ��Ⱥ�������ȵݹ飬ֱ�������ӽڵ㶼ע����ɹ۲���
			duck.registerObserver(observer);
		}

	}

	//����ÿ��Ѽ�Ӷ�ʵ���������������˿���Ϊ��
	@Override
	public void notifyObservers() {
	}

}
