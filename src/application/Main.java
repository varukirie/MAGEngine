package application;
	
import indi.megaastronic.MyCanvas;
import indi.megaastronic.control.PlayerKBControlHandler;
import indi.megaastronic.element.Player;
import indi.megaastronic.paint.MoveHandler;
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
			StackPane root = new StackPane();
			MyCanvas canvas = new MyCanvas();
			root.getChildren().add(canvas);
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Canvas Demo!");
			primaryStage.show();
			
			//运行 线程MoveHandle
			MoveHandler mh = new MoveHandler(canvas);
			new Thread(mh).start();
			//关闭窗口时关闭所有线程
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					mh.keepRun=false;
				}
			});
			//创建玩家
			Player player = new Player(10,10);
			
			ElementUtils elementUtils = new ElementUtils(mh, canvas);
			//绑定玩家与键盘控制
			PlayerKBControlHandler PKBH= new PlayerKBControlHandler(elementUtils,player);
			PKBH.bindEvent(scene);
			//
			canvas.getWantPaintMap().put("player", player);//让MyCanvas管理player
			mh.getWantMoveMap().put("player", player);//让MoveHandler管理player
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
