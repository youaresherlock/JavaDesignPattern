package com.gougoucompany.designpattern.memento;

//备忘录类
public class Memento {
	//要恢复对象的状态
	private String state;
	
	public Memento(String state) {
		// TODO Auto-generated constructor stub
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
}
