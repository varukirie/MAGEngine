package indi.megaastronic.chapter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;
/**
 * 此类负责装载游戏关卡
 * 此类不可实例化
 * @author Astronic
 *
 */
public class ChapterLoader {
	private static MyCanvas staticCanvas =null;
	private static ElementUtils mEU = null;
	private ChapterLoader(){
		
	}
	
	private static ScheduledExecutorService scheduleES=Executors.newScheduledThreadPool(2);
	
	public static void init(MyCanvas staticCanvas,ElementUtils mEU){
		ChapterLoader.staticCanvas = staticCanvas ;
		ChapterLoader.mEU = mEU;
		
	}
	public static void loadChapter(AChapter c){
		c.design(scheduleES,staticCanvas,mEU);
	}
	
	public static void loadChapter(String chapterName) throws Exception{
		Class<AChapter> cls = (Class<AChapter>) Class.forName(chapterName);
		cls.newInstance().design(scheduleES,staticCanvas,mEU);
	}

	public static ScheduledExecutorService getScheduledExecutorService() {
		return scheduleES;
	}

	
	

}
