package com.gougoucompany.designpattern.statusfirst;

/**  
* <p>FileName: GumballMachineTestDrive.java</p>  
* <p>Tile: GumballMachineTestDrive</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018��8��31�� ����1:24:07
* @version 1.0  
*/
public class GumballMachineTestDrive {
	public static void main(String args[]) {
		GumballMachine gumballMachine = new GumballMachine(5);
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();
		
		System.out.println(gumballMachine);
		
		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		System.out.println(gumballMachine);
	}
}

/*
�����������˱仯���ǹ���˾������һ��״̬Ӯ��״̬����10������1�˿��Եõ���ѵ��ǹ�
��������ת��ʱ����%10�ļ��ʵ������������ǹ�
�ҿ�����HeadFirst���ģʽ�飬����Ҿ��ÿ���ֱ�Ӳ��������0-9�������0����2����0����1����Ӧ������dispense()
SOLD״ִ̬�м����ǹ��������Լ����ŵĲ�������ʱ�о�����Ҫ��������ô�鷳 ������ΪSOLD��dispense()��һһ��Ӧ�ģ����״̬ͼ�Ƕ��һ���ɻ�״����ô�������е����
������Ҫ��֮ǰ����������һ��״̬�����������ַ���������һ��Ӯ��״̬�������жϣ��Լ�����Ϊ�¶�Ӧ�Ĳ���

�������ָĶ�����:
1.û�����ؿ���-�ر�ԭ��Ҳ���Ǿ����ٸĶ��࣬���仯�ĵط�û�н����װ���Ķ��ĵط�̫���ˣ����������д���
2.������������� ����������������Ǽ̳ж�̬��װ�����Ƕ�û���õ�
3.�䶯���󣬿��ܳ���bug
*/