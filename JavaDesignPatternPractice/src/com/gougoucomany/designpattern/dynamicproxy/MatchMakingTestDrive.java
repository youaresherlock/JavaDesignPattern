package com.gougoucomany.designpattern.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;

public class MatchMakingTestDrive {
	HashMap<String, PersonBean> datingDB = new HashMap<>();
	
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
	
	public void drive() {
		PersonBean joe = getPersonFromDatabase("joe Javabean");
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy");
		try {
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());
		
		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("bolling, Go");
		} catch (Exception e) {
			System.out.println("Can't set interests from non owner proxy");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Raing is " + nonOwnerProxy.getHotOrNotRating());
		
	}
	
	public MatchMakingTestDrive() {
		initializeDatabase();
	}
	
	//需要一个person对象，然后返回它的代理，利用Proxy类的静态newProxyInstance()创建代理
	PersonBean getOwnerProxy(PersonBean person) {
		
		return (PersonBean) Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new OwnerInvocationHandler(person));
	}
	
	PersonBean getNonOwnerProxy(PersonBean person) {
		
		return (PersonBean)Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new NonOwnerInvocationHandler(person));
	}
	
	PersonBean getPersonFromDatabase(String name) {
		return (PersonBean)datingDB.get(name);
	}
	
	void initializeDatabase() {
		PersonBean joe = new PersonBeanImpl();
		joe.setName("joe Javabean");
		joe.setInterests("cars, computers, music");
		joe.setHotOrNotRating(7);
		datingDB.put(joe.getName(), joe);
		
		PersonBean kelly = new PersonBeanImpl();
		kelly.setName("Kelly klosure");
		kelly.setInterests("ebay, movies, music");
		kelly.setHotOrNotRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
}






























































