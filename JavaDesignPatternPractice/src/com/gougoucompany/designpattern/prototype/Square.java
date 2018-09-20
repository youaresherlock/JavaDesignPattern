package com.gougoucompany.designpattern.prototype;

public class Square extends Shape {
	
	public Square() {
		type = "Square";
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Inside Square::draw() methond."); 
	}
}
