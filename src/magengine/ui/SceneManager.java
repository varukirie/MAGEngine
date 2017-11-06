package magengine.ui;

import org.ietf.jgss.GSSContext;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
		startGame(false, false);
	}

	public void startGame(boolean mulplay, boolean mulplayServer) {
		primaryStage.setResizable(false);
		GameSession session = GameSession.startGameSession();
		session.setMulplay(mulplay);
		session.setMulplayServer(mulplayServer);
		session.loadGameScene();
		session.loadChapter(new TestChapter());
		session.setFailureEvent(() -> {
			GameSession.closeGameSession();
			loadSceneTest();
		});
	}

	public void loadSceneTest() {
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(200, 300);
		canvas.getGraphicsContext2D().fillText("Failure!  push\"r\" to reset", 10, 100);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, 200, 300);
		scene.setOnKeyReleased((e) -> {
			if (KeyCode.R.equals(e.getCode())) {
				SceneManager.getInstance().startGame();
			}
		});
		primaryStage.setScene(scene);
	}

	public void loadMulplaySelectScene() {

		TextField ipTextField = new TextField("127.0.0.1");
		Button singlebtn = new Button("Single");
		Button serverbtn = new Button("multiplay-Server");
		Button clientbtn = new Button("multiplay-Client");
		Button setIPbtn = new Button("set remote IP");
		final StringProperty ipProps = new SimpleStringProperty();
		ipProps.bind(ipTextField.textProperty());
		singlebtn.setOnAction((e)->{
			startGame();
		});
		serverbtn.setOnAction((e)->{
			startGame(true, true);
		});
		clientbtn.setOnAction((e)->{
			GameSession.remoteHost=ipProps.get();
			startGame(true, false);
		});
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("ip:"), 0, 2);
		grid.add(ipTextField, 1, 2);
		grid.add(singlebtn, 3, 0);
		grid.add(serverbtn, 3, 1);
		grid.add(clientbtn, 3, 2);
		grid.add(setIPbtn, 2, 2);
		
		StackPane root = new StackPane();
		root.getChildren().add(grid);
		Scene scene = new Scene(root, 600, 300);
		primaryStage.setTitle("MAGEngine");
		primaryStage.setScene(scene);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
