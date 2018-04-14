package magengine.util;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BGMUtil {
	
	private static MediaPlayer mp;
	private BGMUtil(){
		
	}
	public static void loadResource(String path){
		Media media = new Media(BGMUtil.class.getResource(path).toString());
		mp = new MediaPlayer(media);
		mp.setVolume(0.3);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
	}
	public static void loadResource(File filePath){
		Media media = new Media(filePath.toURI().toString());
		mp = new MediaPlayer(media);
		mp.setVolume(0.3);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
	}
	public static void play(){
		mp.play();
	}
	
	public static void pause(){
		mp.pause();
	}
	public static void stop(){
		if(mp!=null) mp.stop();
	}
}
