package magengine.ui;

import org.ietf.jgss.GSSContext;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import magengine.chapter.ChapterDemo;
import magengine.chapter.TestChapter;
import magengine.chapter.TestChapter1;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.control.PlayerControlHandler;
import magengine.control.PlayerLaunchHandler;
import magengine.element.impl.DisplayMessage;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;

/**
 * 负责Scene切换
 * 
 * @author Astronic
 *
 */
public class SceneManager {
	public static void init(Stage primaryStage) {
		instance.setPrimaryStage(primaryStage);
	}

	public static SceneManager getInstance() {
		return instance;
	}

	private static SceneManager instance = new SceneManager();
	private Stage primaryStage;

	public void startGame() {
		startGame(false,false);
	}
	public void startGame(boolean mulplay,boolean mulplayServer) {
		primaryStage.setResizable(false);
		GameSession session = GameSession.startGameSession();
		session.setMulplay(mulplay);
		session.setMulplayServer(mulplayServer);
		session.loadGameScene();
//		session.loadChapter(new TestChapter());
		session.setFailureEvent(()->{
			GameSession.closeGameSession();
			loadSceneTest();
		});
	}

	public void loadSceneTest() {
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(200, 300);
		canvas.getGraphicsContext2D().fillText("Failure!  push\"r\" to reset", 10, 100);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root,200,300);
		scene.setOnKeyReleased((e) -> {
			if (KeyCode.R.equals(e.getCode())) {
				SceneManager.getInstance().startGame();
			}
		});
		primaryStage.setScene(scene);
	}
	
	public void loadMulplaySelectScene() {
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(200, 300);
//		canvas.getGraphicsContext2D().fillOval(10, 10, 20, 20);
		canvas.getGraphicsContext2D().fillText("press 's' to be server\n press 'c' to be client\n 'n' single", 10, 100);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyReleased((e) -> {
			switch (e.getCode()) {
			case S:
				startGame(true,true);
				break;
			case C:
				startGame(true, false);
				break;
			case N:
				startGame();
				break;
			default:
				break;
			}
		});
		primaryStage.setScene(scene);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
