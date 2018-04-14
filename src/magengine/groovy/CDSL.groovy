package magengine.groovy;

import magengine.chapter.util.QuickDanmuku
import magengine.control.PlayerControlHandler
import magengine.element.BaseElement
import magengine.enemy.AEnemy
import magengine.game.GameSession
import magengine.game.LogicExecutor
import magengine.ui.SceneManager
import magengine.util.BGMUtil
import magengine.util.DI
import magengine.util.ElementUtils

import static magengine.groovy.ClosureLambdaConverter.*;

import javafx.application.Platform

/**
 * 关卡
 * @author Astronic
 *
 */
public class CDSL{
	
	LogicExecutor exec 
	QuickDanmuku quick 
	ElementUtils mEU 
	Random ran 
	
	public CDSL(){
		exec = LogicExecutor.getLogicExecutor();
		quick = QuickDanmuku.getQuickDanmuku();
		mEU = ((ElementUtils) DI.di().get("mEU"));
		Random ran = new Random(magengine.util.C.SEED);
		
	}
	
	void BGMLoad(String filePath){
		BGMUtil.loadResource(new File(filePath));
	}
	
	void BGMClasspathLoad(String originPath){
		BGMUtil.loadResource(originPath);
	}
	
	void BGMPlay(){
		BGMUtil.play();
	}
	
	AEnemy enemy(Closure c,String className){
		Class<?> cls = Class.forName(className);
		AEnemy em = cls.newInstance(new Double(0),new Double(0))
		c.delegate = em
		c.call(em);
		return em;
	}
	void load(BaseElement be){
		mEU.add(be.toString(),be);
	}
	/**
	 * 通关
	 */
	void clearChapter(){
		PlayerControlHandler.getInstance().doBomb();
		Platform.runLater($Run({
			SceneManager.getInstance().shakeInScene();
		}));
		LogicExecutor.getLogicExecutor().schedule($Run({
			Platform.runLater($Run({
				SceneManager.getInstance().loadClearScene();
				GameSession.closeGameSession();
			}));
		}), 2000);
	}
	public static void chapter(Closure c){
		c.delegate = new CDSL();
		c.call();
	}
}