package com.gougoucompany.designpattern.mvc;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.xml.ws.handler.MessageContext;

/*
模型: 持有所有的数据、状态和程序逻辑
The MetaEventListener interface should be implemented by classes whose instances need to be notified when a Sequencer 
has processed a MetaMessage. To register a MetaEventListener object to receive such notifications, pass it as the
 argument to the addMetaEventListener method of Sequencer.
 Sequencer处理源信息时，BeatModel实例需要被通知
void	meta​(MetaMessage meta)	这个接口只有一个抽象方法，
	Invoked when a Sequencer has encountered and processed a MetaMessage in the Sequence it is processing.
*/
public class BeatModel implements BeatModelInterface, MetaEventListener {
	Sequencer sequencer;
	ArrayList<BeatObserver> beatObservers = new ArrayList<>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<>();
	int bpm = 90;
	Sequence sequence;
	Track track;	

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setUpMidi();
		buildTrackAndStart();
	}
	
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		} catch(Exception e){ 
			e.printStackTrace();
		}
	}
	
	public void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};
		
		sequence.deleteTrack(null);
		track = sequence.createTrack();
		
		makeTracks(trackList);
		track.add(makeEvent(192, 9, 1, 0, 4));
		try {
			sequencer.setSequence(sequence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makeTracks(int[] list) {
		for(int i = 0; i < list.length; i++) {
			int key = list[i];
			
			if(key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i+1));
			}
		}
	}
	
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage shortMessage = new ShortMessage(); 
			shortMessage.setMessage(comd, chan, one ,two);
			event = new MidiEvent(shortMessage, tick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public void on() {
		// TODO Auto-generated method stub
		System.out.println("Starting the sequencer");
		sequencer.start();
		setBPM(90);
	}

	@Override
	public void off() {
		// TODO Auto-generated method stub
		setBPM(0);
		sequencer.stop();
	}

	@Override
	public void setBPM(int bpm) {
		// TODO Auto-generated method stub
		this.bpm = bpm;
		sequencer.setTempoInBPM(bpm);
		notifyBPMObservers();
	}

	@Override
	public int getBPM() {
		// TODO Auto-generated method stub
		return bpm;
	}
	
	//新的节拍开始，通知观察者
	void beatEvent() {
		notifyBeatObservers();
	}

	@Override
	public void registerObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		beatObservers.add(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		int i = beatObservers.indexOf(o); //元素不存在会返回-1
		if(i >= 0) {
			beatObservers.remove(i);
		}
	}
	
	public void notifyBeatObservers() {
		//可以使用三种基本的遍历方法
		Iterator<BeatObserver> iterator = beatObservers.iterator();
		while(iterator.hasNext()) {
			BeatObserver observer = (BeatObserver)iterator.next();
			observer.updateBeat();
		}
		
//		for(BeatObserver observer : beatObservers) {
//			observer.updateBeat(); //for-each 使用的还是内置的迭代器
//		}
//		
//		for(int i = 0; i < beatObservers.size(); i ++) {
//			BeatObserver observer = beatObservers.get(i);
//			observer.updateBeat();
//		}
	}

	@Override
	public void registerObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		bpmObservers.add(o);
	}

	@Override
	public void removeObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		int i = bpmObservers.indexOf(o);
		if(i >= 0) {
			bpmObservers.remove(i);
		}
	}
	
	public void notifyBPMObservers() {
		Iterator<BPMObserver> iterator = bpmObservers.iterator();
		while(iterator.hasNext()) {
			BPMObserver observer = iterator.next();
			observer.updateBPM();
		}
	}

	@Override
	public void meta(MetaMessage message) {
		// TODO Auto-generated method stub
		if(message.getType() == 47) {
			beatEvent(); //节拍改变通知观察者
			sequencer.start();
			setBPM(getBPM()); //bpm可能改变，通知所有的观察者
		}
	}

}









































































































