package magengine.util;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundUtil {

	private static SoundUtil instance = new SoundUtil();
	public static SoundUtil getInstance(){
		return instance;
	}
	
	private Map<String,AudioClip> soundMap = new HashMap<>();
	
	public void loadResource(String name,String path){
		AudioClip ac = new AudioClip(path);
		ac.setVolume(0.3);
		soundMap.put(name, ac);
	}
	
	public void play(String name){
		if(soundMap.get(name)!=null){
			soundMap.get(name).play();
		}
	}
}
