package com.gougoucompany.designpattern.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//DJView��һ���۲��ߣ�ͬʱ����ʵʱ���ĺ�BPM�ĸı�

import javax.swing.BorderFactory;
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
		//viewFrame.setSize(new Dimension(500,  400));
		bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		beatBar = new BeatBar();
		beatBar.setValue(0);
		JPanel bpmPanel = new JPanel(new GridLayout(2,  1));
		bpmPanel.add(beatBar);
		bpmPanel.add(bpmOutputLabel);
		viewPanel.add(bpmPanel);
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewFrame.pack();
		viewFrame.setSize(600, 400);
		viewFrame.setVisible(true);
	}
	
	//���������û����Ƶ�����Ĵ���
	public void createControls() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		controlFrame = new JFrame("Control");
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//controlFrame.setSize(new Dimension(100, 80));
		
		controlPanel = new JPanel(new GridLayout(1, 2));
		
		menuBar = new JMenuBar();
		menu = new JMenu("DJ Control"); //�˵�
		startMenuItem = new JMenuItem("Start");
		menu.add(startMenuItem); //��ӿ�ʼ�˵�
		startMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.start(); ////����������start������ģ�Ϳ�ʼ���Ʋ������ֹ��̵��߼�
			}
		});
		stopMenuItem = new JMenuItem("Stop");
		menu.add(stopMenuItem); //���ֹͣ�˵���
		stopMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.stop();
			}
		});
		JMenuItem exit = new JMenuItem("Quit"); //����˳��˵�
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		menu.add(exit);
		menuBar.add(menu);
		controlFrame.setJMenuBar(menuBar);
		
		bpmTextField = new JTextField(2);
		bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
		setBPMButton = new JButton("Set");
		setBPMButton.setSize(new Dimension(10, 40));
		increaseBPMButton = new JButton(">>");
		decreaseBPMButton = new JButton("<<");
		//ע�᱾�����set,<<,>>����¼��ļ���
		setBPMButton.addActionListener(this);
		increaseBPMButton.addActionListener(this);
		decreaseBPMButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		
		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);
		
		JPanel enterPanel = new JPanel(new GridLayout(1, 2));
		enterPanel.add(bpmLabel);
		enterPanel.add(bpmTextField);
		JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
		insideControlPanel.add(enterPanel);
		insideControlPanel.add(setBPMButton);
		insideControlPanel.add(buttonPanel);
		controlPanel.add(insideControlPanel);
		
		bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		controlFrame.getRootPane().setDefaultButton(setBPMButton);
		controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);
		
		controlFrame.pack();
		controlFrame.setSize(800, 400);
		controlFrame.setVisible(true);
	}

	/*
            ģ��BPM״̬�ı�ʱ��updateBPM()�����ᱻ���á�
            ��ʱ�����Ǹ��µ�ǰBPM����ʾ�����ǿ���ͨ��ֱ������ģ�Ͷ��õ����ֵ
    */
	@Override
	public void updateBPM() {
		// TODO Auto-generated method stub
		if(model != null) {
			int bpm = model.getBPM();
			if(bpm == 0) {
				if(bpmOutputLabel != null) {
					bpmOutputLabel.setText("offline");
				}
			} else {
				if(bpmOutputLabel != null) {
					bpmOutputLabel.setText("Current BPM: " + model.getBPM());
				}
			}
		}
	}

	/**
	 * ģ�Ϳ�ʼһ���µĽ���ʱ��updateBeat()�����ᱻ���á���ʱ����������������һ�£���������
	 * Ϊ100�������������д��������֡�
	 */
	@Override
	public void updateBeat() {
		// TODO Auto-generated method stub
		if(beatBar != null) {
			beatBar.setValue(100);
		}
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
	
	//��ͬ��ť������������Ϣ��������������һ���ı�ģ�͵�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == setBPMButton) {
			//���bpmTextField��Ϊ�գ��������ݲ��ǿ��ַ���
			if(bpmTextField != null &&  !bpmTextField.getText().isEmpty()) {
				int bpm = Integer.parseInt(bpmTextField.getText());
				controller.setBPM(bpm);
			} 
		} else if(e.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if(e.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
	}
}












