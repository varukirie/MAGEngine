package magengine.game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LogicExecutor {

	private static LogicExecutor instance = new LogicExecutor();

	public static LogicExecutor getLogicExecutor() {
		if (instance == null) {
			instance = new LogicExecutor();
		}
		return instance;
	}

	public static long gameTime() {
		return (long) instance.curTime;
	}
	
	public static double deltaTime(){
		return instance.curDeltaTime;
	}

	public static void clear() {
		instance = null;
	}

	private LogicExecutor() {
	}

	private double curTime = 0;
	private ArrayList<GameTask> taskList = new ArrayList<>();
	private volatile double curDeltaTime=0;
	
	public void update(double deltaTime) {
		this.curDeltaTime=deltaTime;
		curTime += deltaTime;
		for (int i = 0, j = taskList.size(); i < j; i++) {
			if (taskList.get(i).delay < curTime) {
				taskList.get(i).task.run();
				taskList.set(i, taskList.get(j - 1));
				taskList.remove(j - 1);
				i--;
				j--;
			}
		}
	}

	/**
	 * 计划任务 如果你需要同步操作，那么可以将delay填0 ，task会在下一次update执行
	 * 
	 * @param task
	 * @param delay
	 */
	public void schedule(Runnable task, long delay) {
		taskList.add(new GameTask(task, (long) (delay + curTime)));
	}

	public void schedule(Runnable task, long delay, TimeUnit timeUnit) {
		if (!timeUnit.equals(TimeUnit.MILLISECONDS))
			throw new IllegalArgumentException("LogicExecutor:请使用TimeUnit.MILLISECONDS");
		schedule(task, delay);
	}

	public void shutdownNow() {
		taskList.clear();
	}

	private class GameTask {
		public GameTask(Runnable task, long delay) {
			super();
			this.task = task;
			this.delay = delay;
		}

		Runnable task;
		long delay;
	}

	public int getTaskCount() {
		return taskList.size();
	}
}
