package com.gougoucompany.designpattern.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//DJView��һ���۲��ߣ�ͬʱ����ʵʱ���ĺ�BPM�ĸı�

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class DJView implements ActionListener, BeatObserver, BPMObserver {
	//��ͼ����ģ�ͺͿ�����������
	BeatModelInterface model;
	ControllerInterface controller;
	
	//����ģ����ͼ��������
	JFrame viewFrame;
	JPanel viewPanel;
	BeatBar beatBar;
	JLabel bpmOutputLabel;
	//���������û����ƵĽ�������
	JFrame controlFrame;
	JPanel controlPanel;
	JLabel bpmLabel; //bpm��ʾ��ǩ
	JTextField bpmTextField; //bpm�ı���
	JButton setBPMButton; //����bpm��ť
	JButton increaseBPMButton; //����bpm��ť
	JButton decreaseBPMButton; //����bpm��ť
	JMenuBar menuBar; //�˵���
	JMenu menu; //�˵�
	JMenuItem startMenuItem; //��ʼ�˵���
	JMenuItem stopMenuItem; //ֹͣ�˵���
	
	public DJView(ControllerInterface controller, BeatModelInterface model) {
		this.controller = controller;
		this.model = model;
		//ע���ΪBeatObserver��BPMObserver�۲���
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
	}
	
	//�������а���ģ����ͼ��������
	public void createView() {
		//Create all Swing components here
		viewPanel = new JPanel(new GridLayout(1,  2));
		viewFrame = new JFrame("View");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewFrame.setSize(new Dimension(100,  80));
		bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		beatBar = new BeatBar();
		beatBar.setValue(0);
		JPanel bpmPanel = new JPanel(new GridLayout(2,  1));
		bpmPanel.add(beatBar);
		bpmPanel.add(bpmOutputLabel);
		viewPanel.add(bpmPanel);
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewFrame.pack();
		viewFrame.setVisible(true);
	}
	
	//���������û����Ƶ�����Ĵ���
	public void createControls() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		controlFrame = new JFrame("Control");
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controlFrame.setSize(new Dimension(100, 80));
		
		controlPanel = new JPanel(new GridLayout(1, 2));
		
		menuBar = new JMenuBar();
		menu = new JMenu("DJ Control"); //�˵�
		startMenuItem = new JMenuItem("Start");
		menu.add(startMenuItem);
		startMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.start(); ////����������start������ģ�Ϳ�ʼ���Ʋ������ֹ��̵��߼�
			}
		});
	}

	@Override
	public void updateBPM() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBeat() {
		// TODO Auto-generated method stub

	}
	
	/*
	 * ��Щ�������˵��е�start��stop����enable��disable,
	 * ����������������Щ�ӿڷ����ı��û�����
	 */
	public void enableStopMenuItem() {
		stopMenuItem.setEnabled(true);
	}
	
	public void disableStopMenuItem() {
		stopMenuItem.setEnabled(false);
	}
	
	public void enableStartMenuItem() {
		startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
		startMenuItem.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}












