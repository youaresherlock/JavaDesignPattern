package com.gougoucompany.designpattern.virtualproxy;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
����ǰ����������Ӷ���Զ�̴����ʵ�ַ���
������ģʽ�ı���ܶ�
����ģʽ: 
	Ϊ��һ�������ṩһ�������ռλ���Կ��ƶ��������ķ���
Զ�̴�����Ʒ���Զ�̶���
���������Ʒ��ʿ��������Դ
�����������Ȩ�޿��ƶ���Դ�ķ���
�����ѧϰ����׿�����Ļ�������ͼƬʱ���������߳��п���һ���������߳���������������ͽ������
���̸߳������󵽵Ľ�������н���Ļ��ƣ���ʱ�����ǿ��Ի�����һ��ͼƬ���ַ����������������ʾ�ڽ����ϣ�
�����û����еȴ����򵥵ķ�ʽ����������������������Դ���Icon,�������ļ��أ����ڼ���δ���ǰ��ʾ��ʾ�ַ������������֮����ʾ
���ص�ͼƬ��
*/
public class ImageProxy implements Icon {
	ImageIcon imageIcon; //���غ���ʾ��ͼƬ
	URL imageURL; //ͼ���URL��ַ
	Thread retrievalThread; 
	boolean retrieving = false;
	
	public ImageProxy(URL url) {
		this.imageURL = url;
	}

	@Override
	public int getIconWidth() {
		if(imageIcon != null) {
			return imageIcon.getIconWidth();
		} else {
			return 800;
		}
	}

	@Override
	public int getIconHeight() {
		if(imageIcon != null) {
			return imageIcon.getIconHeight();
		} else {
			return 600;
		}
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		if(imageIcon != null) {
			imageIcon.paintIcon(c, g, x, y);
		} else {
			g.drawString("Loading CD cover, please wait...", x + 300, y + 190);
			if(!retrieving) {
				retrieving = true;
				
				retrievalThread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							imageIcon = new ImageIcon(imageURL, "CD Cover");
							c.repaint(); //�ػ�
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				});
				
				retrievalThread.start();
			}
		}
	}

}















































