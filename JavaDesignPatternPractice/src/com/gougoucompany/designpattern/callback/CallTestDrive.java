package com.gougoucompany.designpattern.callback;

//�ص�����
public class CallTestDrive {
	public static void main(String args[]) {
		Caller call = new Caller();
		//��һ��д��
//		call.call(new ICallBack() {
//			
//			@Override
//			public void callBack() {
//				// TODO Auto-generated method stub
//				System.out.println("�ص��������óɹ�!");
//			}
//		});
		
		//�ڶ���д��
		ICallBack callBack = new ICallBack() {
			@Override
			public void callBack() {
				// TODO Auto-generated method stub
				System.out.println("�ص������ص��ɹ�!");
			}
		};
		call.call(callBack);
		
		/*
		 ������д����ʵ�����ICallBack�ӿ���
		 class CallBackC implements ICallBack{
		 	public void callBack(){
		 	System.out.println("�ص������ص��ɹ�!");
		 	}
		 }
		 call.call(new CallBackC());
		 */
	}
}

/*
 result:
 Start...
  �ص������ص��ɹ�!
 End...

 */













