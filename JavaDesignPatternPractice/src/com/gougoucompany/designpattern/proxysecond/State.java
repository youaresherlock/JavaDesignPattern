package com.gougoucompany.designpattern.proxysecond;

/**  
* <p>FileName: State.java</p>  
* <p>Tile: State</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018��8��31�� ����10:11:52
* @version 1.0  
*/
public interface State {
	
	//���е�״̬�Ķ����ӿ�
	void InsertQuarter();
	
	void ejectQuarter();
	
	void turnCrank();
	
	void dispense();
}
