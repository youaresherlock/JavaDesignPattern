package com.gougoucompany.designpattern.flyweight;

import java.util.HashMap;

public class ShapeFactory {
	//HashTable���̰߳�ȫ�ģ���ʵ�ַ�ʽ���ڶ�Ӧ�ķ����ϼ���synchronized�ؼ��֣�Ч�ʲ���
	private static final HashMap<String, Shape> circleMap = 
			new HashMap<>();
	
	public static Shape getCircle(String color) {
		/*
		 Returns the value to which the specified key 
		 is mapped, or null if this map contains no mapping
		 for the key.
		 */
		Circle circle = (Circle)circleMap.get(color);
		
		//���û�л����ڣ������´���������Ͱ�׿������RecyclerView��������
		if(circle == null) {
			circle = new Circle(color);
			circleMap.put(color, circle);
			System.out.println("Creating circle of color : " + color);
		}
		
		return circle;
	}
}











