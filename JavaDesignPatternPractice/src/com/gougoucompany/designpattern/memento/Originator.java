package com.gougoucompany.designpattern.memento;

//¿´»¤ÈË
public class Originator {
	private String state;
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	public Memento saveStateToMemento() {
		return new Memento(state);
	}
	
	public void getStateFromMemento(Memento memento) {
		state = memento.getState();
	}
}



























