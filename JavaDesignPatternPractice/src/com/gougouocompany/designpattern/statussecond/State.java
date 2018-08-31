package com.gougouocompany.designpattern.statussecond;

/**  
* <p>FileName: State.java</p>  
* <p>Tile: State</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018年8月31日 下午10:11:52
* @version 1.0  
*/
public interface State {
	
	void InsertQuarter();
	
	void ejectQuarter();
	
	void turnCrank();
	
	void dispense();
}
