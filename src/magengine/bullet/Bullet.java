package magengine.bullet;

import java.util.concurrent.atomic.AtomicInteger;

import magengine.element.BaseElement;
import magengine.element.Initializable;
import magengine.game.LogicExecutor;

/**
 * 
 * @author MegaAstronic
 *
 */
public abstract class Bullet extends BaseElement implements Initializable {

	private long launcherTime = 0;
	/**
	 * 代表子弹的状态，用于 弹幕制作时功能扩展。
	 */
	

	public Bullet(double x, double y) {
		super(x, y);
	}

	public Bullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate[0], VCoodinate[1]);
	}

	public Bullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
	}

	public Bullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate[0], ACoodinate[1]);
	}

	public Bullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate[0], VCoodinate[1], ax, ay);
	}

	public Bullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
	}

	public long getLauncherTime() {
		return launcherTime;
	}

	public void setLauncherTime(long launcherTime) {
		this.launcherTime = launcherTime;
	}

	@Override
	public void initWhenAdd() {
		setLauncherTime(LogicExecutor.gameTime());
	}

}
