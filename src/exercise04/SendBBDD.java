package exercise04;

import java.util.List;
import java.util.Vector;

public class SendBBDD implements Runnable {
	private List<Voice> voices = new Vector<Voice>();
	public SendBBDD() {
		super();
	}

	public void addVoice(Voice paramVoice) {
		this.voices.add(paramVoice);
	}
	
	@Override
	public void run() {
		for (Voice voice : this.voices) {
			System.out.println("Voices:" + voice.toString());
		}
		
	}

}
