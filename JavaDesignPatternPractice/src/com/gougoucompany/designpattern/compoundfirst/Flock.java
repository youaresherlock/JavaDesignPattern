package com.gougoucompany.designpattern.compoundfirst;

import java.util.ArrayList;
import java.util.Iterator;
/*
迭代器模式与组合模式
组合模式允许我们像对待单个对象一样对待对象集合
如果要管理这些鸭子，我们需要将鸭子视为一个集合，甚至是子集合(subcollection),我们可以用组合模式来完成
也就是说客户可以调用同一个方法来管理所有的不同种群的鸭子
*/
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
