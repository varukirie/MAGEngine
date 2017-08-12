package indi.megaastronic.chapter.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import application.Main;
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
		long start = System.currentTimeMillis();
		c.design(scheduleES,staticCanvas,mEU);
		long end = System.currentTimeMillis();
		System.out.println("装载关卡使用 "+(end-start)+" 毫秒");
	}
	
	public static void loadChapter(String chapterName) throws Exception{
		Class<AChapter> cls = (Class<AChapter>) Class.forName(chapterName);
		loadChapter(cls.newInstance());
	}

	public static ScheduledExecutorService getScheduledExecutorService() {
		return scheduleES;
	}

	
	

}
