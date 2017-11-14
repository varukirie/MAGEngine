package magengine.util;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

import application.Main;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import magengine.bullet.Bullet;
import magengine.element.BaseElement;
import magengine.element.DurationManage;
import magengine.element.Initializable;
import magengine.element.Moveable;
import magengine.element.Paintable;
import magengine.element.PolygonCollision;
import magengine.element.impl.Area;
import magengine.enemy.APolygonEnemy;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.launcher.BulletEvent;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;

/**
 * 统一处理元素的运动与绘画
 * 
 * @author Astronic
 *
 */
public class ElementUtils {
	private MoveHandler mh = null;
	private MyCanvas myCanvas = null;
	private MyCanvasSwitcher switcher = null;

	public ElementUtils(MoveHandler mh, MyCanvas myCanvas, StackPane root) {
		super();
		this.mh = mh;
		this.myCanvas = myCanvas;
		this.switcher = new MyCanvasSwitcher(root, mh);
	}

	/**
	 * 让元素可以运动与被绘画 如果元素需要初始化，会在此初始化 {@link Initializable}
	 * 
	 * @param name
	 * @param value
	 */
	public void add(String name, Object value) {
		if (Main.DEBUG_ElementCreate) {
			System.out.println("addElement:" + value);
		}
		if (value instanceof Initializable) {
			((Initializable) value).initWhenAdd();
		}
		if (value instanceof DurationManage) {
			((DurationManage) value).setStartTime(LogicExecutor.gameTime());
		}
		if (value instanceof APolygonEnemy || value instanceof Area) {
			((MoveHandler) DI.di().get("mh")).addCollisionElement(name, (PolygonCollision) value);
		}
		if (mh != null)
			if (value instanceof Moveable)
				mh.getWantMoveMap().put(name, (Moveable) value);
		if (myCanvas != null)
			if (value instanceof Paintable)
				if (value instanceof BaseElement) {
					switcher.addElement(name, (BaseElement) value);
				} else {
					myCanvas.getWantPaintMap().put(name, (Paintable) value);
				}
	}

	public void removeBoth(String key) {

		Object obj = getWantMoveMap().get(key);
		if (Main.DEBUG_ElementCreate) {
			System.out.println("removeElement:" + obj);
		}
		if (obj instanceof BaseElement) {
			switcher.removeElement(key);
		} else {
			myCanvas.getWantPaintMap().remove(key);
		}
		if (obj instanceof BaseElement) {
			((BaseElement) obj).setDeleted(true);

		}
		if (obj instanceof APolygonEnemy || obj instanceof Area) {
			((MoveHandler) DI.di().get("mh")).removeCollisionElement(key);
		}
		mh.getWantMoveMap().remove(key);
		if (obj instanceof BaseElement) {
			Consumer<BaseElement> event = ((BaseElement) obj).getOnRemoveEvent();
			if (event != null)
				event.accept((BaseElement) obj);
		}
	}

	private Map<String, Moveable> getWantMoveMap() {
		return mh.getWantMoveMap();
	}

	private Map<String, Paintable> getWantPaintMap() {
		return myCanvas.getWantPaintMap();
	}

	public MyCanvasSwitcher getSwitcher() {
		return switcher;
	}

	public MoveHandler getMh() {
		return mh;
	}

	public void setMh(MoveHandler mh) {
		this.mh = mh;
	}

	public MyCanvas getMyCanvas() {
		return myCanvas;
	}

	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}

	public void addEventBullet(String name, BulletEvent be, BaseElement bullet) {
		add(name, bullet);
		if (be != null) {
			be.event((LogicExecutor) DI.di().get("logicExecutor"), bullet);
		}
	}

	private Random random = new Random();

	public void addAll(BaseElement... value) {
		for (int i = 0; i < value.length; i++) {
			this.add(random.nextLong() + "", value);
		}
	}
}
