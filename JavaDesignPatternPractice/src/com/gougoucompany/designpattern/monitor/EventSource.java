package com.gougoucompany.designpattern.monitor;

import java.util.Iterator;
import java.util.Vector;

/*
 �¼�Դ
 �¼�Դ�����¼��������ڣ�������������ע�ᡢ������֪ͨ
 */
public class EventSource {
	//�������б���������¼�Դ���¼���ע����������Լ�����б�
	private Vector<MonitorListener> listenerList = new Vector<>();
	
	//ע�������
	public void addListener(MonitorListener eventListener) {
		listenerList.add(eventListener);
	}
	
	//ɾ��������
	public void removeListener(MonitorListener eventListener) {
		int i = listenerList.indexOf(eventListener);
		if(i >= 0) {
			listenerList.remove(eventListener);
		}
	}
	
	//�����ⲿ�¼���֪ͨ���еļ�����
	public void notifyListenerEvents(PrintEvent event) {
//		Iterator<MonitorListener> iterator = listenerList.iterator();
//		while(iterator.hasNext()) {
//			MonitorListener monitorListener = (MonitorListener)iterator.next();
//			monitorListener.handleEvent(event);
//		}
		for(MonitorListener moniterListener : listenerList) {
			moniterListener.handleEvent(event);
		}
	}
	
	//���ض����¼��ṩ�ض��Ĺ�ע�����ʹ����¼�
	public void addCloseWindowListener(MonitorListener eventListener) {
		System.out.println("��ע�رմ����¼�");
		listenerList.add(eventListener);
	}
	
	public void doCloseWindows() {
		this.notifyListenerEvents(new PrintEvent("closeWindows"));
	}
}

/*
result:
֪ͨһ���¼�Դ source: openWindows
doOpen
 */
