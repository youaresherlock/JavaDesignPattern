package headfirst.designpatterns.combined.djview;

import javax.sound.midi.*;

import java.util.*;

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

	/*
	A hardware or software device that plays back a MIDI sequence is known as a sequencer. A MIDI sequence contains lists of 
	time-stamped MIDI data, such as might be read from a standard MIDI file. Most sequencers also provide functions for creating
	 and editing sequences.
	The Sequencer interface includes methods for the following basic MIDI sequencer operations:

	obtaining a sequence from MIDI file data
	starting and stopping playback
	moving to an arbitrary position in the sequence
	changing the tempo (speed) of playback
	synchronizing playback to an internal clock or to received MIDI messages
	controlling the timing of another device
	*/
	Sequencer sequencer;
	ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	int bpm = 90;
	/*
	A Sequence is a data structure containing musical information (often an entire song or composition) that can be played 
	back by a Sequencer object. Specifically, the Sequence contains timing information and one or more tracks. Each track 
	consists of a series of MIDI events (such as note-ons, note-offs, program changes, and meta-events). The sequence's timing 
	information specifies the type of unit that is used to time-stamp the events in the sequence.
	A Sequence can be created from a MIDI file by reading the file into an input stream and invoking one of the
	getSequence methods of MidiSystem. A sequence can also be built from scratch by adding new Tracks to an empty Sequence
	, and adding MidiEvent objects to these Tracks.
	*/
	Sequence sequence;
	/*
	A MIDI track is an independent stream of MIDI events (time-stamped MIDI data) that can be stored along with 
	other tracks in a standard MIDI file. 
	*/
	Track track;

	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}

	public void on() {
		System.out.println("Starting the sequencer");
		sequencer.start();
		setBPM(90);
	}

	public void off() {
		setBPM(0);
		sequencer.stop();
	}

	public void setBPM(int bpm) {
		this.bpm = bpm;
		//Sets the tempo in beats per minute. The actual tempo of playback is the product of the specified value and the tempo factor.
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers(); //通知所有的BPM观察者,BPM已经改变了
	}

	public int getBPM() {
		return bpm;
	}

	//当新的节拍开始时，通知所有全部的beatObserver,新的节拍开始了
	void beatEvent() {
		notifyBeatObservers(); 
	}


	public void registerObserver(BeatObserver o) {
		beatObservers.add(o); //注册beat观察者
	}

	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); i++) {
			BeatObserver observer = (BeatObserver)beatObservers.get(i);
			observer.updateBeat();
		}
	}

	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o); //注册称为BPM观察者
	}

	public void notifyBPMObservers() {
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			observer.updateBPM();
		}
	}


	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o); //第一次出现的位置,没有为-1
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}



	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}


	/*
	A MetaMessage is a MidiMessage that is not meaningful to synthesizers,
	 but that can be stored in a MIDI file and interpreted by a sequencer program. 
	*/
	public void meta(MetaMessage message) {
		//message.getType() an integer representing the MetaMessage type
		if (message.getType() == 47) {
			beatEvent();
			sequencer.start();
			setBPM(getBPM());
		}
	}

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			//Opens the device, indicating that it should now acquire any system resources it requires and become operational.
			sequencer.open();
			sequencer.addMetaEventListener(this); //使用了事件监听模式，下节我们会讲解这块遗漏的部分(观察者模式的进一步变形)
			/*
			Sequence.PPQ,The tempo-based timing type, for which the resolution is expressed in pulses (ticks) per quarter note.
			看文档看的蛋疼，每一四分音符几个tick(pulses per quarter note)，音乐的世界我不懂
			The sequence will contain no initial tracks. Tracks may be added to or removed from the sequence
			using createTrack() and deleteTrack(javax.sound.midi.Track).
			*/
			sequence = new Sequence(Sequence.PPQ,4);
			/*
			Creates a new, initially empty track as part of this sequence.
			*/
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM()); //set beats per minute
			//A value indicating that looping should continue indefinitely rather than complete after a specific number of loops.不断循环播放
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	public void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};

		sequence.deleteTrack(null);
		track = sequence.createTrack();

		makeTracks(trackList);
		track.add(makeEvent(192,9,1,0,4));      
		try {
			//Sets the current sequence on which the sequencer operates.
			sequencer.setSequence(sequence);                    
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	public void makeTracks(int[] list) {        

		for (int i = 0; i < list.length; i++) {
			int key = list[i];

			if (key != 0) {
				//	add​(MidiEvent event)	Adds a new event to the track.
				// Command value for Note On message (0x90, or 144).
				track.add(makeEvent(144,9,key, 100, i));
				// Command value for Note Off message (0x80, or 128).
				track.add(makeEvent(128,9,key, 100, i+1));
			}
		}
	}

	public  MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		//MIDI events contain a MIDI message and a corresponding time-stamp expressed in ticks
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			//Constructs a new ShortMessage which represents a channel MIDI message that takes up to two data bytes.
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);

		} catch(Exception e) {
			e.printStackTrace(); 
		}
		return event;
	}
}


