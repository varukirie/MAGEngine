package indi.megaastronic.chapter;

import java.util.Timer;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class ChapterLoader {
	private static MyCanvas staticCanvas =null;
	private static ElementUtils mEU = null;
	private ChapterLoader(){
		
	}
	
	private static Timer timer = new Timer();
	
	public static void init(MyCanvas staticCanvas,ElementUtils mEU){
		ChapterLoader.staticCanvas = staticCanvas ;
		ChapterLoader.mEU = mEU;
		
	}
	public static void loadChapter(AChapter c){
		c.design(timer,staticCanvas,mEU);
	}

	public static Timer getTimer() {
		return timer;
	}

	public static void setTimer(Timer timer) {
		ChapterLoader.timer = timer;
	}
	
	

}
