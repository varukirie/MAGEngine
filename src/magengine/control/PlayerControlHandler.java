package magengine.control;

import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.element.BaseElement;
import magengine.element.impl.BombCircleArea;
import magengine.element.impl.CircleArea;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.ui.SceneManager;
import magengine.util.CollisionTeam;
import magengine.util.DI;
import magengine.util.ElementUtils;

import static java.lang.Math.*;
/**
 * 此类负责绑定玩家操作事件
 * 
 * @author MegaAstronic
 *
 */
public class PlayerControlHandler {

	private static double mouseX = 0;
	private static double mouseY = 0;

	private static PlayerControlHandler pch = null;
	// 速度V
	public static double DEFAULT_BALL_V = 4;
	public static double BALL_V = DEFAULT_BALL_V;
	public static double PLAYER_V = 200;// 用于设置按键导致的player移动速度
	private Player player = null;
	private ElementUtils elementUtils = null;

	public static void clear() {
		pch = null;
	}

	public static PlayerControlHandler getPlayerControlHandler(ElementUtils eu, Player player) {
		if (pch == null) {
			if (eu == null || player == null)
				throw new RuntimeException("未接收到Player或者ElementUtils");
			pch = new PlayerControlHandler(eu, player);
		}
		return pch;
	}

	public static PlayerControlHandler getExistedPlayerControlHandler() {
		if (pch == null)
			throw new RuntimeException("未生成PCH");
		return pch;
	}

	private PlayerControlHandler(ElementUtils eu, Player player) {
		this.elementUtils = eu;
		this.player = player;
	}

	public void bindEvent(Scene scene) {
		// 函数式编程写法
		scene.setOnKeyPressed((KeyEvent e) -> {
			pressHandle(e);
		});
		/*
		 * 等同的旧写法 样例 scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 * 
		 * @Override public void handle(KeyEvent e) { pressHandle(e); } });
		 */
		scene.setOnKeyReleased((KeyEvent e) -> {
			releaseHandle(e);
		});
		scene.setOnMouseMoved((MouseEvent e) -> {
			PlayerControlHandler.mouseX = e.getX();
			PlayerControlHandler.mouseY = e.getY();
			// System.out.println("mouse x="+mouseX+" mouseY="+mouseY);
		});
		// scene.setOnMouseClicked((MouseEvent e)->{
		// playerShootToMouse();
		// });

	}

	// 顺序是上、右、下、左
	public boolean[] pressed = new boolean[] { false, false, false, false };
	private int ballCount = 0;

	private void changeV() {
		double vx = 0;
		double vy = 0;
		if (pressed[0]) {
			vy -= PLAYER_V;
		}
		if (pressed[2]) {
			vy += PLAYER_V;
		}
		if (pressed[1]) {
			vx += PLAYER_V;
		}
		if (pressed[3]) {
			vx -= PLAYER_V;
		}
		if (vx != 0 && vy != 0) {
			vx *= 0.7071;
			vy *= 0.7071;
		}
		player.setVelocityX(vx);
		player.setVelocityY(vy);
	}

	@SuppressWarnings("incomplete-switch")
	public void pressHandle(KeyEvent e) {

		switch (e.getCode()) {
		case UP:
			pressed[0] = true;
			break;
		case RIGHT:
			pressed[1] = true;
			break;
		case DOWN:
			pressed[2] = true;
			break;
		case LEFT:
			pressed[3] = true;
			break;
		case X:
			Player.getPlayer1().isShooting = true;
			break;
		}
		changeV();
	}

	// 顺序是上、右、下、左
	private boolean boomIntervalEnable = true;

	@SuppressWarnings("incomplete-switch")
	public void releaseHandle(KeyEvent e) {
		switch (e.getCode()) {
		case UP:
			pressed[0] = false;
			break;
		case RIGHT:
			pressed[1] = false;
			break;
		case DOWN:
			pressed[2] = false;
			break;
		case LEFT:
			pressed[3] = false;
			break;
		case X:
			Player.getPlayer1().isShooting = false;
			break;
		case ENTER:
			GameSession.closeGameSession();
			SceneManager.getInstance().loadFailureScene();
			break;
		case C:
			//TODO 不线程安全
			if (GameSession.getGameSession().haveBomb()) {
				if(boomIntervalEnable){
					if(GameSession.getGameSession().useBomb()){
						doBomb();
						boomIntervalEnable=false;
						LogicExecutor.getLogicExecutor().schedule(()->{
							boomIntervalEnable=true;
						}, 1000);
					}
				}
			}
			break;
		case ESCAPE:
			MoveHandler.setDeltaTimeFactor(0);
			SceneManager.getInstance().loadPauseScene();
			break;
		}
		changeV();
	}

	private void playerShootToMouse() {
		DefaultBullet ball = new DefaultBullet(player.getX(), player.getY());
		double dx = PlayerControlHandler.mouseX - player.getX();
		double dy = PlayerControlHandler.mouseY - player.getY();
		double s = Math.sqrt(dx * dx + dy * dy);
		ball.setVelocityX(dx * BALL_V / s);
		ball.setVelocityY(dy * BALL_V / s);
		elementUtils.add("ball" + ballCount++, ball);

	}

	public static double getMouseX() {
		return mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}
	
	
	public static boolean boomEffectSetted = false;
	private void doBomb(){
		double x  = Player.getPlayer1().getX();
		double y = Player.getPlayer1().getY();
		double r = 175;
//		double x=MyCanvas.CANVAS_WIDTH/2;
//		double y=MyCanvas.CANVAS_HEIGHT;
		BombCircleArea ca = new BombCircleArea(x, y, r);
		ca.setAccY(-255);
		ca.setOnCollisionEvent((m)->{
			if(m instanceof BaseElement){
				if(m.getTeam().equals(CollisionTeam.ENEMY_BULLET)){
					((BaseElement) m).setWantBeRemoved(true);
				}
			}
		});
		ca.setOnPaintEvent((gc)->{
			gc.setFill(Color.rgb(245, 245, 245,0.3));
//			gc.setStroke(Color.rgb(245, 245, 90,0.7));
			gc.setStroke(Color.BLACK);
			gc.fillOval(ca.getX()-r, ca.getY()-r, r*2, r*2);
			double angleDelta = sin(LogicExecutor.gameTime()/500.0)*1;
			
			{
				double angle=0+angleDelta;
				double px=ca.getX();
				double py=ca.getY();
				double lx=ca.getX()-r;
				double ly=ca.getY();
				gc.moveTo(lx, ly);
				gc.beginPath();
				for(int i=0;i<4;i++){
					double tx=px-sin(angle)*r;
					double ty=py-cos(angle)*r;
					gc.lineTo(tx,ty);
					lx=tx;
					ly=ty;
					angle+=PI/3*2;
				}
				gc.closePath();
				gc.stroke();
			}
			{
				double angle=PI+angleDelta;
				double px=ca.getX();
				double py=ca.getY();
				double lx=ca.getX()+r;
				double ly=ca.getY();
				gc.moveTo(lx, ly);
				gc.beginPath();
				for(int i=0;i<4;i++){
					double tx=px-sin(angle)*r;
					double ty=py-cos(angle)*r;
					gc.lineTo(tx,ty);
					lx=tx;
					ly=ty;
					angle+=PI/3*2;
				}
				gc.closePath();
				gc.stroke();
			}
		});
		ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
		mEU.add("bombCircleArea", ca);
	}
}
