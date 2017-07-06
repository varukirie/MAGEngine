package indi.megaastronic.control;

import indi.megaastronic.element.Ball;
import indi.megaastronic.element.Player;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.PInfo;
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
	// 速度V
	public static double DEFAULT_BALL_V = 4;
	public static double BALL_V = DEFAULT_BALL_V;
	public static double PLAYER_V = 1;//用于设置按键导致的player移动速度
	private Player player = null;
	private ElementUtils elementUtils = null;


	public PlayerControlHandler(ElementUtils eu, Player player) {
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
			PInfo.setMouseX(e.getX());
			PInfo.setMouseY(e.getY());
			// System.out.println("mouse x="+mouseX+" mouseY="+mouseY);
		});
		scene.setOnMouseClicked((MouseEvent e)->{
			playerShootToMouse();
		});

	}

	// 顺序是上、右、下、左
	private boolean[] pressed = new boolean[] { false, false, false, false };
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
			MoveHandler.speed = MoveHandler.DEFAULT_SPEED / 4;
			break;
		}
	}

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
			MoveHandler.speed = MoveHandler.DEFAULT_SPEED;
		}
	}

	private void playerShootToMouse() {
		Ball ball = new Ball(player.getX(), player.getY());
		double dx = PInfo.getMouseX() - player.getX();
		double dy = PInfo.getMouseY() - player.getY();
		double s = Math.sqrt(dx * dx + dy * dy);
		ball.setVelocityX(dx * BALL_V / s);
		ball.setVelocityY(dy * BALL_V / s);
		elementUtils.addWantMoveAndPaint("ball" + ballCount++, ball);
	}
}
