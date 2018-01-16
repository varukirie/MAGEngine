package magengine.enemy;

import java.util.ArrayList;

import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.LogicExecutor;
/**
 * 提供给Boss使用的Enemy
 * 循环播放弹幕
 * @author MegaAstronic
 *
 */
public abstract class ALoopDanmukuEnemy extends APolygonEnemy {

	public ALoopDanmukuEnemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public ALoopDanmukuEnemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public ALoopDanmukuEnemy(double x, double y) {
		super(x, y);
	}
	private LogicExecutor exec = LogicExecutor.getLogicExecutor();
	/**
	 * 第一次执行danmuku前的延时
	 */
	private long danmukuStartDelay = 500;
	
	private ArrayList<DanmukuAndDuration> danmukuList = new ArrayList<>();


	/**
	 * 增加弹幕，参数为 弹幕与弹幕开始到结束所使用的时间 该方法应该在add之前调用，否则可能会出错
	 * 
	 * @param danmuku
	 *            弹幕
	 * @param DURATION
	 *            弹幕开始到结束所使用的时间
	 */
	public ALoopDanmukuEnemy addDanmuku(ADanmuku danmuku, long duration) {
		danmukuList.add(new DanmukuAndDuration(danmuku, duration));
		return this;
	}

	@Override
	public void initWhenAdd() {
		danmukuList.forEach(x -> {
			x.danmuku.setSourceElement(this);
		});
		exec.schedule(()->{
			doNextDmk();
		}, danmukuStartDelay);
		if(movePoints!=null){
			doNextMove();
		}
	}
	private int currentDanmukuIndex=-1;
	private void doNextDmk(){
		if(danmukuList.size()!=0){
			currentDanmukuIndex=(currentDanmukuIndex+1)%danmukuList.size();
			if(currentDanmukuIndex<danmukuList.size()&& !this.deleted){
				danmukuList.get(currentDanmukuIndex).danmuku.executeDanmuku();
				exec.schedule(()->{
					doNextDmk();
				}, danmukuList.get(currentDanmukuIndex).duration);
			}
		}
	}
	private class DanmukuAndDuration {
		public ADanmuku danmuku = null;
		public long duration = 5000;

		public DanmukuAndDuration(ADanmuku danmuku, long duration) {
			this.danmuku = danmuku;
			this.duration = duration;
		}
	}
	public long getDanmukuStartDelay() {
		return danmukuStartDelay;
	}
/**
 * 设置敌人从出场到发射第一个弹幕的间隔
 * @param danmukuStartDelay
 */
	public ALoopDanmukuEnemy setDanmukuStartDelay(long danmukuStartDelay) {
		this.danmukuStartDelay = danmukuStartDelay;
		return this;
	}
	
	
	private double[][] movePoints=null;
	private long moveInterval = 100;
	private long moveDuration = 3000;
	/**
	 * 循环运动
	 * @param points checkpoints格式{{x1,x2,x3...},{y1,y2,y3...}}
	 * @return
	 */
	public ALoopDanmukuEnemy setMoveLoop(double[][] points,long moveDuration,long moveInterval){
		this.movePoints=points;
		this.moveDuration=moveDuration;
		this.moveInterval=moveInterval;
		return this;
	}
	private int moveMark = 0;
	private void doNextMove(){
		if(!getDeleted()){
			QuickDanmuku.getQuickDanmuku().moveTo(this, moveDuration, movePoints[0][moveMark], movePoints[1][moveMark],()->{
				exec.schedule(()->{
					doNextMove();
				}, moveInterval);
			});
			moveMark=(moveMark+1)%movePoints[0].length;
		}
	}
	
}
