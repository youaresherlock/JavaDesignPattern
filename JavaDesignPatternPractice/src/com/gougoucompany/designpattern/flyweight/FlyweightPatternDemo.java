package com.gougoucompany.designpattern.flyweight;

public class FlyweightPatternDemo {
	private static final String colors[] =
		{"Red", "Green", "Blue", "White", "Black"};
	
	public static void main(String args[]) {
		for(int i = 0; i < 20; ++i) {
			Circle circle = 
					(Circle)ShapeFactory.getCircle(getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.setRadius(100);
			circle.draw();
		}
	}
	
	private static String getRandomColor() {
		return colors[(int)(Math.random() * colors.length)];
	}
	
	private static int getRandomX() {
		return (int)(Math.random() * 100);
	}
	
	private static int getRandomY() {
		return (int)(Math.random() * 100);
	}

}

/*
 Creating circle of color : White
Cicle: Draw() [Color : White, x: 56, y: 56, radius: 100
Creating circle of color : Green
Cicle: Draw() [Color : Green, x: 85, y: 5, radius: 100
Creating circle of color : Red
Cicle: Draw() [Color : Red, x: 92, y: 95, radius: 100
Cicle: Draw() [Color : Red, x: 72, y: 88, radius: 100
Cicle: Draw() [Color : Green, x: 61, y: 48, radius: 100
Cicle: Draw() [Color : Green, x: 26, y: 85, radius: 100
Cicle: Draw() [Color : Green, x: 39, y: 31, radius: 100
Cicle: Draw() [Color : Green, x: 53, y: 77, radius: 100
Creating circle of color : Black
Cicle: Draw() [Color : Black, x: 81, y: 15, radius: 100
Cicle: Draw() [Color : Green, x: 41, y: 70, radius: 100
Creating circle of color : Blue
Cicle: Draw() [Color : Blue, x: 87, y: 36, radius: 100
Cicle: Draw() [Color : Blue, x: 72, y: 80, radius: 100
Cicle: Draw() [Color : White, x: 61, y: 28, radius: 100
Cicle: Draw() [Color : Red, x: 97, y: 20, radius: 100
Cicle: Draw() [Color : White, x: 16, y: 99, radius: 100
Cicle: Draw() [Color : Green, x: 91, y: 35, radius: 100
Cicle: Draw() [Color : Blue, x: 66, y: 64, radius: 100
Cicle: Draw() [Color : White, x: 3, y: 75, radius: 100
Cicle: Draw() [Color : Green, x: 4, y: 82, radius: 100
Cicle: Draw() [Color : Green, x: 58, y: 39, radius: 100
 */




























