package magengine.game;

public class GameTask {
	
	public GameTask(Runnable task, long delay) {
		super();
		this.task = task;
		this.delay = delay;
	}
	Runnable task ;
	long delay;
	boolean finished=false;
}
