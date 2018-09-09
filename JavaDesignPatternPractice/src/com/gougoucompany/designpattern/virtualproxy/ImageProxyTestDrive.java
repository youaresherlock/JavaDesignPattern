package com.gougoucompany.designpattern.virtualproxy;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ImageProxyTestDrive {
		ImageComponent imageComponent;
		JFrame frame = new JFrame("CD Cover Viewer");
		JMenuBar menuBar;
		JMenu menu;
		Hashtable<String, String> cds = new Hashtable<>();
		
		public static void main(String args[]) throws Exception {
			ImageProxyTestDrive imageProxyTestDrive = new ImageProxyTestDrive();
		}
		
		public ImageProxyTestDrive() throws Exception{
			cds.put("Buddha Bar", "http://images.amazon.com/images/P/B00009XBYK.01.LZZZZZZZ.jpg");
	        cds.put("Ima", "http://images.amazon.com/images/P/B000005IRM.01.LZZZZZZZ.jpg");
	        cds.put("Karma", "http://images.amazon.com/images/P/B000005DCB.01.LZZZZZZZ.gif");
	        cds.put("MCMXC A.D.", "http://images.amazon.com/images/P/B000002URV.01.LZZZZZZZ.jpg");
	        cds.put("Northern Exposure", "http://images.amazon.com/images/P/B000003SFN.01.LZZZZZZZ.jpg");
	        cds.put("Selected Ambient Works, Vol. 2", "http://images.amazon.com/images/P/B000002MNZ.01.LZZZZZZZ.jpg");
	        
	        URL initialURL = new URL((String)cds.get("Selected Ambient Works, Vol. 2"));
	        menuBar = new JMenuBar();
	        menu = new JMenu("Favorite CDs");
	        menuBar.add(menu);
	        frame.setJMenuBar(menuBar);
	        
	        for(Iterator it = cds.keySet().iterator(); it.hasNext();) {
	        	String name = (String) it.next();
	        	JMenuItem menuItem = new JMenuItem(name);
	        	menu.add(menuItem);
	        	menuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						imageComponent.setIcon(new ImageProxy(getCDUrl(e.getActionCommand())));
					}
				});
	        }
	        
	        
	        //建立框架和菜单
	        Icon icon = new ImageProxy(initialURL);
	        imageComponent = new ImageComponent(icon);
	        frame.getContentPane().add(imageComponent);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800, 600);
	        frame.setVisible(true);
		}
		
		URL getCDUrl(String name) {
			try {
				return new URL((String)cds.get(name)); 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
}







































































