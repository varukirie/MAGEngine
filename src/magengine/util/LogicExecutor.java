package magengine.util;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogicExecutor {

	
	private static LogicExecutor instance ;
	
	public static LogicExecutor getLogicExecutor(){
		if(instance==null){
			instance=new LogicExecutor();
		}
		return instance;
	}
	
	public static long gameTime(){
		return instance.curTime;
	}
	
	public static void clear(){
		instance=null;
	}
	
	private LogicExecutor(){}
	
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private long curTime = 0;
	private ArrayList<Runnable> taskList = new ArrayList<>();
	private ArrayList<Long> timeList = new ArrayList<>();
	private ArrayList<Integer> removeList = new ArrayList<>();
	
	
	public void update(long deltaTime){
		curTime+=deltaTime;
//		System.out.println(curTime);
		for(int i=0,j=taskList.size();i<j;i++){
			if(timeList.get(i)<curTime){
				executor.submit(taskList.get(i));
				removeList.add(i);
			}
		}
		removeList.forEach((i)->{
			int j=i;
			taskList.remove(j);
			timeList.remove(j);
		});
		removeList.clear();
	}
	
	public void schedule(Runnable task,long delay){
		taskList.add(task);
		timeList.add(delay+curTime);
	}
	
	public void shutdownNow(){
		this.executor.shutdownNow();
		timeList.clear();
		taskList.clear();
	}
}
