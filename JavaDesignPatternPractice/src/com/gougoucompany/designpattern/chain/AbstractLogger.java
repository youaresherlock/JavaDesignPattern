package com.gougoucompany.designpattern.chain;
//��������ļ�¼����
public abstract class AbstractLogger {
	public static int INFO = 1;
	public static int DEBUG = 2;
	public static int ERROR = 3;
	
	protected int level;
	
	//�������е���һ��Ԫ��
	protected AbstractLogger nextLogger;
	
	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger = nextLogger;
	}
	
	public void logMessage(int level, String message) {
		//����python��loggingģ���װ��ϵͳ��־ģ�飬���оͿ���������־���𣬼��𲻹��ǲ���
		//�ڿ���̨�ϴ�ӡ�ģ�ֻ�ܱ��浽��־���ݿ����������־�ļ�
		if(this.level <= level) {
			write(message);
		}
		if(nextLogger != null) {
			nextLogger.logMessage(level,  message);
		}
	}
	
	protected abstract void write(String message);
	
}






























