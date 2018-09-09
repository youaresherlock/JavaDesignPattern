package com.gougoucomany.designpattern.dynamicproxy;

/*
下面我们将使用Java API中java.lang.reflect包中的代理支持，在运行时动态的创建一个代理类(感兴趣的可以研究一下Java反射)
实现一个或多个接口，并将方法的调用转发到你所指定的类。由于实际的代理类是在运行的时候创建的，因此这个Java技术称之为动态代理
我们比如有一个PersonBean接口类，允许设置或者获得一个人的信息。由于不同的人可能允许调用的方法不同，因此需要动态创建两个代理，
创建步骤:
1.创建两个InvokeHandler
	在提供方法调用的时候，会触发Handler中的invoke方法，以此来进行权限设置
2.写代码创建动态代理
3.利用适当的代理来包装PersonBean对象
*/
public interface PersonBean {
	String getName();
	
	String getGender();
	
	String getInterests();
	
	int getHotOrNotRating();
	
	void setName(String name);
	
	void setGender(String gender);
	
	void setInterests(String interests);
	
	void setHotOrNotRating(int rating);
}




















