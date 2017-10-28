package magengine.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogicExecutor {

	private static LogicExecutor instance=new LogicExecutor();

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

	private ExecutorService executor = Executors.newFixedThreadPool(2);

	private double curTime = 0;
	private ArrayList<GameTask> taskList = new ArrayList<>();
	
	public void update(double deltaTime) {
		curTime += deltaTime;
		for (int i = 0, j = taskList.size(); i < j; i++) {
			if (taskList.get(i).delay < curTime) {
				taskList.get(i).task.run();
				taskList.set(i, taskList.get(j-1));
				taskList.remove(j-1);
				i--;
				j--;
			}
		}
	}
/**
 * 计划任务
 * 如果你需要同步操作，那么可以将delay填0 ，task会在下一次update执行
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
		this.executor.shutdownNow();
		synchronized (taskList) {
			taskList.clear();
		}
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
