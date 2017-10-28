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
		primaryStage.setResizable(false);
		GameSession session = GameSession.startGameSession();
		session.loadGameScene();
		session.loadChapter(new TestChapter());
	}

	public void loadSceneTest() {
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(200, 300);
		canvas.getGraphicsContext2D().fillOval(10, 10, 20, 20);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyReleased((e) -> {
			if (KeyCode.ENTER.equals(e.getCode())) {
				SceneManager.getInstance().startGame();
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
