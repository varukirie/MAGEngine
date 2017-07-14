package application;
	
import indi.megaastronic.chapter.Chapter1;
import indi.megaastronic.chapter.ChapterLoader;
import indi.megaastronic.control.PlayerControlHandler;
import indi.megaastronic.element.Ball;
import indi.megaastronic.element.Player;
import indi.megaastronic.element.DisplayTime;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.ui.SceneManager;
import indi.megaastronic.util.ElementUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	public static final boolean DEBUG = true;

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.startGame(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
