package indi.megaastronic.util;

import java.util.concurrent.ConcurrentHashMap;

import indi.megaastronic.element.BaseElement;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;
import javafx.application.Platform;
import javafx.scene.effect.Effect;
import javafx.scene.layout.StackPane;

public class MyCanvasSwitcher {
	private MoveHandler mh=null;
	private ConcurrentHashMap<Class<?>, MyCanvas> fmyCanvasMap=new ConcurrentHashMap<>();
	private ConcurrentHashMap<Class<?>, MyCanvas> smyCanvasMap=new ConcurrentHashMap<>();
	private StackPane root=null;

	
	public MyCanvasSwitcher(StackPane root,MoveHandler mh){
		this.root=root;
		this.mh=mh;
	}
	
	public void addElement(String key,BaseElement e){
		Class<?> cls = e.getClass();
		MyCanvas canvas= fmyCanvasMap.get(cls);
		if(canvas==null){
			createCanvas(cls);
			canvas= fmyCanvasMap.get(cls);
		}
		canvas.getWantPaintMap().put(key, e);
	}
	
	public void removeElement(String key){
		Class<?> cls=mh.getWantMoveMap().get(key).getClass();
		fmyCanvasMap.get(cls).getWantPaintMap().remove(key);
	}
	private int switchCount = 0;
	public void repaint(){
		if(switchCount==0){
			fmyCanvasMap.forEach((cls,canvas)->{
				canvas.repaint();
			});
			fmyCanvasMap.forEach((cls,canvas)->{
				canvas.setVisible(true);
			});
			smyCanvasMap.forEach((cls,canvas)->{
				canvas.setVisible(false);
			});
		}else{
			smyCanvasMap.forEach((cls,canvas)->{
				canvas.repaint();
			});
			smyCanvasMap.forEach((cls,canvas)->{
				canvas.setVisible(true);
			});
			fmyCanvasMap.forEach((cls,canvas)->{
				canvas.setVisible(false);
			});
		}
		switchCount=(switchCount+1)%2;
	}
	
	public void setEffect(Class<?> cls,Effect effect){
		if(fmyCanvasMap.get(cls)==null) createCanvas(cls);
		fmyCanvasMap.get(cls).setEffect(effect);
		smyCanvasMap.get(cls).setEffect(effect);
	}
	
	private void createCanvas(Class<?> cls){
		MyCanvas canvas = new MyCanvas();
		MyCanvas scanvas = new MyCanvas(canvas.getWantPaintMap());
		fmyCanvasMap.put(cls, canvas);
		smyCanvasMap.put(cls, scanvas);
		Platform.runLater(()->{
			root.getChildren().add(canvas);
			root.getChildren().add(scanvas);
		});
	}
	
	
	
	

}
