package com.gougoucompany.designpattern.statusfirst;


/**  
* <p>FileName: GumballMachine.java</p>  
* <p>Tile: GumballMachine</p>  
* <p>Description: </p>  
* @author Clarence
* @company gougouCompany
* @date 2018��8��31�� ����12:47:22
* @version 1.0  
*/
/*
״̬ģʽ
����ģʽ��״̬ģʽ�ܲ��ɷ֣�����ģʽ��Χ�ƿ��Ի������㷨�������ɹ�ҵ��ģ�Ȼ����״̬�ߵ��Ǹ���
��·����ͨ���ı�����ڲ���״̬��������������Լ�����Ϊ��
����: �ǹ��������Լ��Ľӿڻ����Լ��ڲ��ķ�����ͨ����ͬ״̬�����ö�Ӧ�ķ������������������Ϊ��
�ǹ�����״̬��Ҫ������: û��25��Ǯ ��25��Ǯ �۳��ǹ� �ǹ�����
ϵͳ�з����Ķ����Ǵ���Щ״̬�й��ȡ�
*/
//����һ���࣬�������þ���һ��״̬������ÿһ����������������һ����Ӧ�ķ�������Щ�����������������������ÿ��״̬��ʲô��Ϊ��ǡ����

public class GumballMachine {

	//����״̬
	final static int SOLD_OUT = 0; 
	final static int NO_QUARTER = 1;
	final static int HAS_QUARTER = 2;
	final static int SOLD = 3;
	
	int state = SOLD_OUT; //һ��ʼ�ǹ���״̬Ϊ�ǹ�����
	int count = 0; //�ǹ������ǹ���Ŀ
	
	public GumballMachine(int count) {
		this.count = count;
		if(count > 0) {
			state = NO_QUARTER;
		}
	}
	
	//��Ͷ��25��Ǯ��Ӧ����Ϊ
	public void insertQuarter() {
		if(state == HAS_QUARTER) {
			System.out.println("You can't insert another quarter"); //�û��Ѿ�Ͷ��Ǯ��
		} else if(state == NO_QUARTER) {
			//�����û�Ͷ��25�֣�ת��״̬
			state = HAS_QUARTER;
			System.out.println("You inserted a quarter");
		} else if(state == SOLD_OUT) {
			//��������Ͷ��
			System.out.println("You can't insert a quarter, the machine is sold out");
		} else if(state == SOLD) {
			//�ȴ�״̬ת��
			System.out.println("Please wait, we're already giving you a gumball");
		}
	}
	
	//�û������˳�25��Ǯ
	public void ejectQuarter() {
		if(state == HAS_QUARTER){
			System.out.println("Quarter returned");
			state = NO_QUARTER; //��25��Ǯ�˳�
		} else if(state == NO_QUARTER) {
			System.out.println("You haven't inserted a quarter"); //�û�û��Ͷ��25��
		} else if(state == SOLD) {
			// �û�ת�����ֱ�����������
			System.out.println("Sorry, you already turned the crank");
		} else if(state == SOLD_OUT) {
			//�ǹ�����״̬���Ͳ��ܽ���25��Ǯ��������
			System.out.println("You can't eject, you haven't inserted a quarter yet");
		}
	}
	
	//�û�����ת���ֱ�
	public void turnCrank() {
		if(state == SOLD) { //�û��Ѿ��õ��ǹ���
			System.out.println("Turning twice doesn't get you another gumball!");
		} else if(state == NO_QUARTER) {
			//ת���ֱ�������û��Ͷ��
			System.out.println("You turned but there's no quarter");
		} else if(state == SOLD_OUT) {
			//û���κ��ǹ�
			System.out.println("You turned, but there are no gumballs");
		} else if(state == HAS_QUARTER) {
			//��Ӳ�ң�ͬʱ�û�ת���ֱ����ı�״̬�������ǹ�
			System.out.println("You turned...");
			state = SOLD;
			dispense(); //����
		}
	}

	//�����ǹ���ϵͳ�ڲ�����Ϊ
	private void dispense() {
		if(state == SOLD) {
			//���۱�־������
			System.out.println("A gumball comes rolling out the slot");
			count = count - 1; //���ﲻ��Ҫ���ж��������û�Ͷ��ʱ���Ѿ������ж�
			if(count == 0) {
				//�ǹ�״̬��Ϊ����
				System.out.println("Ooops, out of gamballs!");
				state = SOLD_OUT;
			} else {
				state = NO_QUARTER;
			}
		} else if(state == NO_QUARTER) { //������������������
			System.out.println("You need to pay first");
		} else if(state == SOLD_OUT) { //�������������� û���ǹ�ֻ�ܶ�Ӧ�ǹ�������״̬�����Ƿ��л�·������
			//û�ǹ���
			System.out.println("No gumball dispensed");
		} else if(state == HAS_QUARTER) {
			System.out.println("No gumball dispensed");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer printString = new StringBuffer();
		printString.append("Mighty Gumballl, Inc.\nJava-enabled Standing Gumball Model #2004\nInventory: ");
		printString.append(String.valueOf(count) + " gumballs\n");
		printString.append("Machine is waiting for quarter\n");
		return printString.toString();
	}
}





















































































































