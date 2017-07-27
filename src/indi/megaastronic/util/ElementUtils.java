package indi.megaastronic.util;

import java.util.Map;

import indi.megaastronic.element.Moveable;
import indi.megaastronic.element.Paintable;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.MyCanvas;

public class ElementUtils {
	private MoveHandler mh = null;
	private MyCanvas myCanvas =null;
	public ElementUtils(MoveHandler mh, MyCanvas myCanvas) {
		super();
		this.mh = mh;
		this.myCanvas = myCanvas;
	}

	
	public void add(String key,Object value){
		if(mh!=null)
			if(value instanceof Moveable)
				mh.getWantMoveMap().put(key, (Moveable) value);
		if(myCanvas!=null)
			if(value instanceof Paintable)
				myCanvas.getWantPaintMap().put(key, (Paintable) value);
	}
	
	public void removeMove(String key){
		mh.getWantMoveMap().remove(key);
	}
	public void removePaint(String key){
		myCanvas.getWantPaintMap().remove(key);
	}
	public void removeBoth(String key){
		removeMove(key);
		removePaint(key);
	}
	
	public Map<String, Moveable> getWantMoveMap(){
		return mh.getWantMoveMap();
	}
	public Map<String,Paintable> getWantPaintMap(){
		return myCanvas.getWantPaintMap();
	}
	
}
