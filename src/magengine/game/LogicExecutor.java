package magengine.game;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogicExecutor {

	private static LogicExecutor instance;

	public static LogicExecutor getLogicExecutor() {
		if (instance == null) {
			instance = new LogicExecutor();
		}
		return instance;
	}

	public static long gameTime() {
		return (long) instance.curTime;
	}

	public static void clear() {
		instance = null;
	}

	private LogicExecutor() {
	}

	private ExecutorService executor = Executors.newFixedThreadPool(4);

	private double curTime = 0;
	private ArrayList<GameTask> taskList = new ArrayList<>();

	public void update(double deltaTime) {
		curTime += deltaTime;
		// System.out.println(curTime);

		synchronized (taskList) {
			taskList.forEach((task)->{
				if(task.delay<curTime){
//					System.out.println("task executor delay"+task.delay+" curTime="+curTime);
					executor.submit(task.task);
					task.finished=true;
				}
			});
			for(int i=0,j=taskList.size();i<j;i++){
				if(taskList.get(i).finished){
					taskList.remove(i);
					i--;
					j--;
				}
			}
		}
	}

	public void schedule(Runnable task, long delay) {
		synchronized (taskList) {
			taskList.add(new GameTask(task,(long) (delay+curTime)));
		}
	}

	public void schedule(Runnable task, long delay, TimeUnit timeUnit) {
		if (!timeUnit.equals(TimeUnit.MILLISECONDS))
			throw new RuntimeException("LogicExecutor:请使用TimeUnit.MILLISECONDS");
		schedule(task, delay);
	}

	public void shutdownNow() {
		this.executor.shutdownNow();
		synchronized (taskList) {
			taskList.clear();
		}
	}
}
