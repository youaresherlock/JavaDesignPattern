package com.gougoucompany.designpattern.prototype;

import java.util.Hashtable;

//����һ���࣬�����ݿ��ȡʵ���࣬�������Ǵ洢��һ��Hashtable��
public class ShapeCache {
	
	private static Hashtable<String, Shape> shapeMap =
			new Hashtable<>();
	
	public static Shape getShape(String shapeId) {
		Shape cachedShape = shapeMap.get(shapeId);
		return (Shape)cachedShape.clone();
	}
	
	//��ÿ����״���������ݿ��ѯ������������״
	//shapeMap.put(shapeKey, shape);
	//����,����Ҫ���������״
	public static void loadCache() {
		Circle circle = new Circle();
		circle.setId("1");
		shapeMap.put(circle.getId(), circle);
		
		Square square = new Square();
		square.setId("2");
		shapeMap.put(square.getId(), square);
		
		Rectangle rectangle = new Rectangle();
		rectangle.setId("3");
		shapeMap.put(rectangle.getId(), rectangle);
	}
	
}

/*
result:
Shape : Circle
Shape : Square
Shape : Rectangle

 */














































