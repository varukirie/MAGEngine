package magengine.util;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundUtil {

	private static SoundUtil instance = new SoundUtil();
	public static SoundUtil getInstance(){
		return instance;
	}
	
	private Map<String,MediaPlayer> soundMap = new HashMap<>();
	
	public void loadResource(String name,String path){
		MediaPlayer mp ;
		Media media = new Media(BGMUtil.class.getResource(path).toString());
		mp = new MediaPlayer(media);
		mp.setCycleCount(1);
		mp.setOnEndOfMedia(()->{
			mp.stop();
		});
		soundMap.put(name, mp);
	}
	
	public void play(String name){
		if(soundMap.get(name)!=null){
			soundMap.get(name).play();
		}
	}
}
