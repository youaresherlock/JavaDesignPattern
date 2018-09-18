package com.gougoucompany.designpattern.monitor;

//������
public class MonitorTestDrive {
	public static void main(String[] args) {
		EventSource eventSource = new EventSource();
		eventSource.addListener(new MonitorListener() {
			
			@Override
			public void handleEvent(PrintEvent event) {
				// TODO Auto-generated method stub
				event.doEvent();
				//���ݲ�ͬ���¼�Դִ�в�ͬ���߼�����
				if(event.getSource().equals("openWindows")) {
					System.out.println("doOpen");
				}
				if(event.getSource().equals("closeWindows")){
					System.out.println("doClose");
				}
			}
		});
		
		/*
		 * ����openWindows�¼���֪ͨ���е��¼�������
		 * ��open�¼�����Ȥ��listener����ִ��
		 */
		eventSource.notifyListenerEvents(new PrintEvent("openWindows"));
	
		// �����˽�����ﻹ������һЩ�䶯�����ض����¼��ṩ�ض��Ĺ�ע�������¼�����
		//��ע�ر��¼���ʵ�ֻص��ӿ�
		EventSource windows = new EventSource();
		windows.addCloseWindowListener(new MonitorListener() {
			
			@Override
			public void handleEvent(PrintEvent event) {
				// TODO Auto-generated method stub
				event.doEvent();
				if(event.getSource().equals("closeWindows")) {
					System.out.println("ͨ��addCloseWindowListener����ע�رմ����¼�����ִ�гɹ�"
							+ "closeWindows");
				}
			}
		});
		
		//���ڹرն���,�����ǲ������ư�ťע���������Ȼ������������¼���ִ�м������ж�Ӧ�¼��Ķ���
		windows.doCloseWindows();
	}
}

/*
result:
֪ͨһ���¼�Դ source: openWindows
doOpen
��ע�رմ����¼�
֪ͨһ���¼�Դ source: closeWindows
ͨ��onCloseWindows����ע�رմ����¼�����ִ�гɹ�closeWindows
*/























