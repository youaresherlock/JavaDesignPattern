package com.gougoucompany.designpattern.flyweight;

import java.util.HashMap;

public class ShapeFactory {
	//HashTable是线程安全的，其实现方式是在对应的方法上加上synchronized关键字，效率不高
	private static final HashMap<String, Shape> circleMap = 
			new HashMap<>();
	
	public static Shape getCircle(String color) {
		/*
		 Returns the value to which the specified key 
		 is mapped, or null if this map contains no mapping
		 for the key.
		 */
		Circle circle = (Circle)circleMap.get(color);
		
		//如果没有缓存在，则重新创建，这个和安卓开发的RecyclerView缓存类似
		if(circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);
			System.out.println("Creating circle of color : " + color);
		}
		
		return circle;
	}
}











