package indi.megaastronic.control;

import indi.megaastronic.element.Ball;
import indi.megaastronic.element.Player;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.util.ElementUtils;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
		/*旧写法 样例
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				// System.out.println("tri");
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

	@SuppressWarnings("incomplete-switch")
	public void pressHandle(KeyEvent e) {

		switch (e.getCode()) {
		case W:
			player.setVelocityY(-PLAYER_V);
			pressed[0] = true;
			break;
		case D:
			player.setVelocityX(PLAYER_V);
			pressed[1] = true;
			break;
		case S:
			player.setVelocityY(PLAYER_V);
			pressed[2] = true;
			break;
		case A:
			player.setVelocityX(-PLAYER_V);
			pressed[3] = true;
			break;
		case SPACE:
			MoveHandler.timeSpeed = 0.01 ;//MoveHandler.DEFAULT_TIME_SPEED
			break;
		}
	}
	// 顺序是上、右、下、左
	@SuppressWarnings("incomplete-switch")
	public void releaseHandle(KeyEvent e) {
		switch (e.getCode()) {
		case W:
			if (pressed[2] == false)
				player.setVelocityY(0);
			else
				player.setVelocityY(PLAYER_V);
			pressed[0] = false;
			break;
		case D:
			if (pressed[3] != true)
				player.setVelocityX(0);
			else
				player.setVelocityX(-PLAYER_V);

			pressed[1] = false;
			break;
		case S:
			if (pressed[0] != true)
				player.setVelocityY(0);
			else
				player.setVelocityY(-PLAYER_V);

			pressed[2] = false;
			break;
		case A:
			if (pressed[1] != true)
				player.setVelocityX(0);
			else
				player.setVelocityX(PLAYER_V);
			pressed[3] = false;
			break;
		case SPACE:
			MoveHandler.timeSpeed = MoveHandler.DEFAULT_TIME_SPEED;
		}
	}

	private void playerShootToMouse() {
		Ball ball = new Ball(player.getX(), player.getY());
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
