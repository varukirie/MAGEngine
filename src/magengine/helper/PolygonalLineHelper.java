package magengine.helper;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import magengine.chapter.util.QuickDanmuku;
import magengine.element.Initializable;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.util.DI;
import magengine.util.ElementUtils;
/**
 * 折线路径的Helper
 * @author MegaAstronic
 *
 */
public class PolygonalLineHelper extends Helper implements Initializable{
	/**
	 * 
	 * @param ploygonalLine 坐标集  格式 new double[][]{{x1,x2,x3,x4……},{y1,y2,y3,y4……}}
	 * @param speed helper运动速度
	 */
	public PolygonalLineHelper(double[][] ploygonalLine,double speed){
		super(1, 1);
		setPloygon(ploygonalLine);
		setSpeed(speed);
	}
	
	private double speed=1;
	private double[][] ploygon = null;
	private ScheduledExecutorService ses = (ScheduledExecutorService) DI.di().get("sES");
	private QuickDanmuku quick=QuickDanmuku.getQuickDanmuku();
	private Consumer<PolygonalLineHelper> endEvent = null;
	
	
	@Override
	public void initWhenAdd() {
		this.setX(this.ploygon[0][0]);
		this.setY(this.ploygon[1][0]);
		this.setVelocityX(getSpeed());
		double lastX=this.ploygon[0][0];
		double lastY=this.ploygon[1][0];
		long nextChangeTime=1;

		for(int i=1;i<this.ploygon[0].length;i++){
			int curI=i;
			double s = Math.sqrt((this.ploygon[0][i]-this.ploygon[0][i-1])*(this.ploygon[0][i]-this.ploygon[0][i-1])+
						(this.ploygon[1][i]-this.ploygon[1][i-1])*(this.ploygon[1][i]-this.ploygon[1][i-1]));
			long duration = (long) ((s/this.speed)*(1/MoveHandler.absoluteTimeSpeed)*1000);
//			System.out.println("i="+i+" s="+s+" duration="+duration);
			LogicExecutor.getLogicExecutor().schedule(()->{
				quick.VTo(this, ploygon[0][curI], ploygon[1][curI]);
//				System.out.println("call "+curI+" this.x="+this.getX()+" this.y="+this.getY());
			}, nextChangeTime, TimeUnit.MILLISECONDS);
			nextChangeTime+=duration;
		}
		this.setDuration(nextChangeTime);
		LogicExecutor.getLogicExecutor().schedule(()->{
			if(getEndEvent()!=null){
				getEndEvent().accept(this);
			}
		}, nextChangeTime, TimeUnit.MILLISECONDS);
	}
	public double[][] getPloygon() {
		return ploygon;
	}
	public void setPloygon(double[][] ploygon) {
		//TODO  ploygon验证数据正确性
		if(ploygon.length!=2){
			throw new IllegalArgumentException("PolygonalLineHelper:点集数据不正确");
		}
		if(ploygon[0].length!=ploygon[1].length){
			throw new IllegalArgumentException("PolygonalLineHelper:点集数据不正确");
		}
		this.ploygon = ploygon;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Consumer<PolygonalLineHelper> getEndEvent() {
		return endEvent;
	}
	public void setEndEvent(Consumer<PolygonalLineHelper> endEvent) {
		this.endEvent = endEvent;
	}
	
	
	
	
}
