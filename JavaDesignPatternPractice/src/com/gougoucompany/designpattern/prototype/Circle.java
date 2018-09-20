package com.gougoucompany.designpattern.prototype;

public class Circle extends Shape {
	
	public Circle() {
		type = "Circle";
	}

	@Override
	void draw() {
		// TODO Auto-generated method stub
		System.out.println("Inside Circle::draw() method."); 
	}

}
