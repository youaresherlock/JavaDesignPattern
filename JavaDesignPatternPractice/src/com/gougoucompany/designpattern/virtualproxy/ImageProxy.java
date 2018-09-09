package com.gougoucompany.designpattern.virtualproxy;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
我们前面的所有例子都是远程代理的实现方法
但代理模式的变体很多
代理模式: 
	为另一个对象提供一个替身或占位符以控制对这个对象的访问
远程代理控制访问远程对象
虚拟代理控制访问开销大的资源
保护代理基于权限控制对资源的访问
如果有学习过安卓开发的话，绘制图片时，会在主线程中开启一个单独的线程来负责网络请求和结果处理，
主线程根据请求到的结果来进行界面的绘制，这时候我们可以会用另一个图片或字符串设置字体后来显示在界面上，
提醒用户进行等待。简单的方式是利用虚拟代理，虚拟代理可以代理Icon,管理背景的加载，并在加载未完成前显示提示字符串，加载完成之后显示
加载的图片。
*/
public class ImageProxy implements Icon {
	ImageIcon imageIcon; //加载后显示的图片
	URL imageURL; //图像的URL地址
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
							c.repaint(); //重绘
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















































