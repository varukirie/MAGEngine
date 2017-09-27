package magengine.control;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import magengine.bullet.DefaultBullet;
import magengine.element.impl.Player;
import magengine.paint.MoveHandler;
import magengine.util.ElementUtils;

/**
 * 此类负责绑定玩家操作事件
 * 
 * @author MegaAstronic
 *
 */
public class PlayerControlHandler {
	
	private static double mouseX=0;
	private static double mouseY=0;
	
	private static PlayerControlHandler pch = null;
	
	// 速度V
	public static double DEFAULT_BALL_V = 4;
	public static double BALL_V = DEFAULT_BALL_V;
	public static double PLAYER_V = 1;//用于设置按键导致的player移动速度
	private Player player = null;
	private ElementUtils elementUtils = null;
	
	public static PlayerControlHandler getPlayerControlHandler(ElementUtils eu, Player player){
		if(pch==null){
			if(eu==null||player==null)
				throw new RuntimeException("未接收到Player或者ElementUtils");
			pch=new PlayerControlHandler(eu, player);
		}
		return pch;
	}


	private PlayerControlHandler(ElementUtils eu, Player player) {
		this.elementUtils = eu;
		this.player = player;
	}

	public void bindEvent(Scene scene) {
		//函数式编程写法
		scene.setOnKeyPressed((KeyEvent e) -> {
			pressHandle(e);
		});
		/*等同的旧写法 样例
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				pressHandle(e);
			}
		});
		*/
		scene.setOnKeyReleased((KeyEvent e)->{
			releaseHandle(e);
		});
		scene.setOnMouseMoved((MouseEvent e)->{
			PlayerControlHandler.mouseX=e.getX();
			PlayerControlHandler.mouseY=e.getY();
			// System.out.println("mouse x="+mouseX+" mouseY="+mouseY);
		});
		scene.setOnMouseClicked((MouseEvent e)->{
			playerShootToMouse();
		});

	}

	// 顺序是上、右、下、左
	public boolean[] pressed = new boolean[] { false, false, false, false };
	private int ballCount = 0;
	private void changeV(){
		double vx =0;
		double vy =0;
		if(pressed[0]){
			vy-=PLAYER_V;
		}
		if(pressed[2]){
			vy+=PLAYER_V;
		}
		if(pressed[1]){
			vx+=PLAYER_V;
		}
		if(pressed[3]){
			vx-=PLAYER_V;
		}
		if(vx!=0&&vy!=0){
			vx*=0.7071;
			vy*=0.7071;
		}
		player.setVelocityX(vx);
		player.setVelocityY(vy);
	}

	@SuppressWarnings("incomplete-switch")
	public void pressHandle(KeyEvent e) {

		switch (e.getCode()) {
		case W:
			pressed[0] = true;
			break;
		case D:
			pressed[1] = true;
			break;
		case S:
			pressed[2] = true;
			break;
		case A:
			pressed[3] = true;
			break;
		case SPACE:
			MoveHandler.timeSpeed = MoveHandler.DEFAULT_TIME_SPEED*0.3 ;//MoveHandler.DEFAULT_TIME_SPEED
			break;
		}
		changeV();
	}
	// 顺序是上、右、下、左
	@SuppressWarnings("incomplete-switch")
	public void releaseHandle(KeyEvent e) {
		switch (e.getCode()) {
		case W:
			pressed[0] = false;
			break;
		case D:
			pressed[1] = false;
			break;
		case S:
			pressed[2] = false;
			break;
		case A:
			pressed[3] = false;
			break;
		case SPACE:
			MoveHandler.timeSpeed = MoveHandler.DEFAULT_TIME_SPEED;
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
	public static double getMouseX(){
		return mouseX;
	}
	public static double getMouseY(){
		return mouseY;
	}
}
