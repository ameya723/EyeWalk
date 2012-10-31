package ub.eyewalk;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGL11;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLU;

class DrawingThread extends Thread {
	public static final int stepSize = 40;
	public static final float zoomFactor = 400;

	private MapData md = new MapData();
	private boolean stop;
	private int w, h;
	private float xPos = 0, yPos = 0;
	private boolean changed = true;
	private DrawingSurfaceView surfaceView;

	private float rotY = 0;
	
	DrawingThread(DrawingSurfaceView dsv) {
		super();
		surfaceView = dsv;
		stop = false;
		w = 0;
		h = 0;
	}public void move() {
		xPos -= stepSize * Math.cos(rotY);
		yPos += stepSize * Math.sin(rotY);		
	}
	public void setRotation(float y1) {
		if (Math.abs(y1 - rotY) > .05) { // limit fluctuations
			rotY  = y1;
		}
	}
	
	@Override
	public void run() {
		EGL10 egl = (EGL10) EGLContext.getEGL();
		EGLDisplay dpy = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
		int[] version = new int[2];
		egl.eglInitialize(dpy, version);
		int[] configSpec = {
				EGL10.EGL_RED_SIZE, 	5,
				EGL10.EGL_GREEN_SIZE,	6,
				EGL10.EGL_BLUE_SIZE,	5,
				EGL10.EGL_DEPTH_SIZE,	16,
				EGL10.EGL_NONE
		};
		EGLConfig[] configs = new EGLConfig[1];
		int[] num_config = new int[1];
		egl.eglChooseConfig(dpy, configSpec, configs, 1, num_config);
		EGLConfig config = configs[0];
		EGLContext context = egl.eglCreateContext(dpy, config, EGL10.EGL_NO_CONTEXT, null);
		EGLSurface surface = null;
		GL10 gl = null;
		while (!stop) {
			int W, H;
			boolean updated;
			synchronized(this) {
				updated = this.changed;
				W = this.w;
				H = this.h;
				this.changed = false;
			}
			if (updated) {
				if (surface != null) {
					egl.eglMakeCurrent(dpy, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
					egl.eglDestroySurface(dpy, surface);
				}
				surface = egl.eglCreateWindowSurface(dpy, config, surfaceView.mHolder, null);
				egl.eglMakeCurrent(dpy, surface, surface, context);
				gl = (GL10) context.getGL();
				gl.glDisable(GL10.GL_DITHER);
				gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
				gl.glClearColor(1,1,1,1);
				gl.glEnable(GL10.GL_CULL_FACE);
				gl.glShadeModel(GL10.GL_SMOOTH);
				gl.glEnable(GL10.GL_DEPTH_TEST);
				
				gl.glViewport(0, 0, W, H);				
				float ratio = (float) W/H;
				gl.glMatrixMode(GL10.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
			}
			drawFrame(gl);
			egl.eglSwapBuffers(dpy, surface);
			if (egl.eglGetError() == EGL11.EGL_CONTEXT_LOST){
				Context c = surfaceView.getContext();
				if (c instanceof Activity) {
					((Activity) c).finish();
				}
			}
		}
		egl.eglMakeCurrent(dpy, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
		egl.eglDestroySurface(dpy, surface);
		egl.eglDestroyContext(dpy, context);
		egl.eglTerminate(dpy);
	}
	public void onWindowResize(int w, int h) {
		synchronized (this) {
			this.w = w;
			this.h = h;
			this.changed = true;
		}
	}
	public void waitForExit() {
		this.stop = true;
		try {
			join();
		} catch (InterruptedException ex) {
		}
	}
	private void drawFrame(GL10 gl) {
		
		Random r = new Random();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		float[] square = new float[] {
				-6f, -18f, .0f,
				6f, -18f, .0f,
				-6f, 18f, .0f,
				6f, 18f, .0f,
				
				18f, 18f, 0f,
				0f, 36f, 0f,
				-18f, 18f, 0f
		};
		FloatBuffer squareBuff;
		ByteBuffer bb = ByteBuffer.allocateDirect(square.length*4);
		
		bb.order(ByteOrder.nativeOrder());
		squareBuff = bb.asFloatBuffer();
		squareBuff.put(square);
		squareBuff.position(0);
			
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		float ratio = (float) this.w/this.h;
		
		GLU.gluOrtho2D(gl, -ratio*zoomFactor, ratio*zoomFactor, -zoomFactor, zoomFactor); // this is like a transform. woah. scale.
			 
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glColor4f(.9f, .8f, 0f, 1);			
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, squareBuff);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 7);//GL_TRIANGLE_FAN, 0, 10);//TRIANGLE_STRIP, 0, 4);
		
		gl.glRotatef(rotY*180/3.14f + 90, 0, 0, 1);
		gl.glTranslatef(xPos, yPos, 0);
		
		for (MapObject obj: md.objectList) {
			//for (MapObject.BoundingBox b: obj.bbox) {
			//	b.draw(gl);
			//}
			obj.draw(gl);			
		}		
	}
	
	public MapData getMapData() {
		return md;
	}
	
	public MapObject checkCollisions() {
		// Input xPos, yPos -- current position
		// Input rotation -- current angle 
		double distanceToClosest = 9999999;
		MapObject closest = null;
		
		double angleToObject;
		double angleBetween;
		double distanceToObject;
		
		float x = -1*this.xPos/2; 
		float y = -1*this.yPos/2;
		float rotation = (float) (rotY *180/3.14f);
		rotation *= -1; // inversion to "normalize" axes
		
		if (rotation < 0) {
			rotation += 360; // now bring into [0, 360)
		}
		
		for (MapObject obj : md.objectList) { // For each object on our map
			for (MapObject.BoundingBox b: obj.bbox) { // For each of its bounding boxes,
				distanceToObject = Math.sqrt(Math.pow(x - b.x, 2) + Math.pow(y - b.y, 2));
				
				if (distanceToObject < obj.distanceTo || obj.distanceTo <= 0) {
					obj.distanceTo = distanceToObject; // track shortest distance to the object
				}
				
				angleToObject = 0;
				if (b.x < x) { // if the object is in Q3 or Q2
					angleToObject += 180;				
				}
				angleToObject +=  (Math.atan((b.y - y)/(b.x - x)) * 180/3.14); // compute the angle to the object
				   															       // and convert to degrees
				if ( angleToObject < 0) { // keep within [0, 360)
					angleToObject += 360;
				}
				
				angleBetween = Math.abs(angleToObject - rotation);
				
				if (angleBetween > 180) {
					angleBetween = 360 - angleBetween;
				}
					
				if (angleBetween > 0 && angleBetween < 90) { // if there's a collision, it will be here
					angleBetween *= (3.14/180); // convert to radians
					rotation *= (3.14/180);
					double intersectionArm = Math.sin(angleBetween) * distanceToObject;
					
					if (angleBetween == 0) { // head on collision
						if (intersectionArm < distanceToClosest) { //distanceToObject < distanceToClosest) {
							closest = obj;
							distanceToClosest = intersectionArm;//distanceToObject;
						}
					} else {
						double newDistance = distanceToObject/Math.cos(angleBetween); // we want the closest point along our path to the object
						double newX = x + newDistance * Math.cos(rotation);
						double newY = y + newDistance * Math.sin(rotation);
									
						if (newX < b.x + b.width && newX > b.x - b.width) {
							if (newY < b.y + b.height && newY > b.y - b.height) {
							
								if (intersectionArm < distanceToClosest) { //distanceToObject < distanceToClosest) {
									closest = obj;
									distanceToClosest = intersectionArm;//distanceToObject;
								}
							}
						}
					}
					rotation *= (180/3.14);
				}					
			}
		}	
		return closest;
	}		
}