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

}
