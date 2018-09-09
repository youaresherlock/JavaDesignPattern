package com.gougoucompany.designpattern.proxysecond;

import java.net.MalformedURLException;
import java.rmi.*;

public class GumballMachineTestDrive {
	public static void main(String[] args) {
//		int count = 0;
//		
//		if(args.length < 2) {
//			System.out.println("GumballMachine <name> <inventory>");
//			System.exit(1);
//		}
//		
//		count = Integer.parseInt(args[1]);
		try {
			String name = "rmitest";
			GumballMachine gumballMachine = new GumballMachine(name, 10);
			Naming.rebind("//" + name + "/gumballmachine" , gumballMachine);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
	}
}


































