package com.gougoucompany.designpattern.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class HeartModel implements HeartModelInterface, Runnable {
	ArrayList<BeatObserver> beatObservers = new ArrayList<>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<>();
	int time = 1000;
	int bpm = 90;
	Random random = new Random(System.currentTimeMillis());
	Thread thread;
	
	public HeartModel() {
		thread = new Thread(this);
		thread.start();
	}
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int lastrate = -1;
		
		for(;;) {
			int change = random.nextInt(10);
			if(random.nextInt(2) == 0) {
				change = 0 - change;
			}
			int rate = 60000 / (time + change);
			if(rate < 120 && rate > 50) {
				time += change;
				notifyBeatObservers();
				if(rate != lastrate) {
					lastrate = rate;
					notifyBPMObservers();
				}
			}
			try {
				Thread.sleep(time);
			} catch(Exception e) {}
		}
	}

	@Override
	public int getHeartRate() {
		// TODO Auto-generated method stub
		return (int)6000/time;
	}

	@Override
	public void registerObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		beatObservers.add(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		int i = beatObservers.indexOf(o);
		if(i >= 0) {
			beatObservers.remove(i);
		}
	}
	
	public void notifyBeatObservers() {
		Iterator<BeatObserver> iterator = beatObservers.iterator();
		while(iterator.hasNext()) {
			BeatObserver observer = (BeatObserver)iterator.next();
			observer.updateBeat();
		}
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
			BPMObserver observer = (BPMObserver) iterator.next();
			observer.updateBPM();
		}
	}

}















