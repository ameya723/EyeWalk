package ub.eyewalk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	public SurfaceHolder mHolder;
	public DrawingThread mThread;
	
	public DrawingSurfaceView(Context c) {
		super(c);
		init();
	}
	
	public DrawingSurfaceView(Context c, AttributeSet as) {
		super(c, as);
		init();
	}
	
	public void init() {
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
	}
	
	public void move() {
		mThread.move();
	}
	
	public void draw(float r) {
		mThread.setRotation(r);
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		mThread = new DrawingThread(this);
		mThread.start();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		mThread.waitForExit();
		mThread = null;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		mThread.onWindowResize(w, h);
	}
	
	public MapObject checkCollisions() {	
		return mThread.checkCollisions();
	}
	
	public MapData getMapData() {
		return mThread.getMapData();
	}
	
}