package com.gougoucompany.designpattern.proxyfirst;


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
		GumballMachine gumballMachine = new GumballMachine("hello",  10);
		
		GumballMonitor monitor = new GumballMonitor(gumballMachine);
		
		monitor.report();
	}
}


































