package indi.megaastronic.util;

import indi.megaastronic.MyCanvas;
import indi.megaastronic.paint.MoveHandler;
import indi.megaastronic.paint.Moveable;
import indi.megaastronic.paint.Paintable;

public class ElementUtils {
	private MoveHandler mh = null;
	private MyCanvas myCanvas =null;
	public ElementUtils(MoveHandler mh, MyCanvas myCanvas) {
		super();
		this.mh = mh;
		this.myCanvas = myCanvas;
	}
	
	public void addWantMoveAndPaint(String key,Object value){
		mh.getWantMoveMap().put(key, (Moveable) value);
		myCanvas.getWantPaintMap().put(key, (Paintable) value);
	}
	
}
