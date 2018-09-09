package com.gougoucompany.designpattern.virtualproxy;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;

public class ImageComponent extends JComponent{
	private Icon icon;
	
	public ImageComponent(Icon icon) {
		this.icon = icon;
	}
	
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	
	//Graphics类绘制必须在一个容器中进行
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		int x = (800 - w) / 2;
		int y = (600 - h) / 2;
		icon.paintIcon(this, g, x, y);
	}
}
