package ub.eyewalk;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class MapObject { // everything is now bounded by a rectangle
	public static final int WALL = 0, DOOR = 1, RESTROOM = 2, STAIRS = 3, EMERGENCY = 4, WATERFOUNTAIN = 8, ELEVATOR = 9, BREAKERS = 99;
	
	private float x, y, width, height;
	private int type;
	public String text;
	private FloatBuffer squareBuff;
	private float[] rgba;
	public Vector<BoundingBox> bbox;
	public double distanceTo;	
	
	public class BoundingBox {
		public float width, height;
		public float x, y;
		public FloatBuffer squareBuff;
		
		public BoundingBox(float x, float y, float w, float h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
			
			float[] square = new float[] {
					this.x-1, this.y-1, .0f,
					this.x+1, this.y-1, .0f,
					this.x-1, this.y+1, .0f,
					this.x+1, this.y+1, .0f
			};
			
			ByteBuffer bb = ByteBuffer.allocateDirect(square.length*4);
			
			bb.order(ByteOrder.nativeOrder());
			squareBuff = bb.asFloatBuffer();
			squareBuff.put(square);
			squareBuff.position(0);	
		}
		public void draw(GL10 gl) {
			Random r = new Random();
			gl.glPushMatrix();
			gl.glTranslatef(this.x, this.y, 0);
			gl.glColor4f(r.nextFloat(), r.nextFloat(), r.nextFloat(), .5f);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, squareBuff);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);//GL_TRIANGLE_FAN, 0, 10);//TRIANGLE_STRIP, 0, 4);
			gl.glPopMatrix();
		}
	}
	
	public MapObject(float x, float y, float w, float h, float bw, float bh, int t) {
		bbox = new Vector<BoundingBox> ();
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.type = t;
		if (this.type == WALL) {
			bbox.add(new BoundingBox(x, y, w, h));
		} else {
			bbox.add(new BoundingBox(x, y, bw, bh));
		}
		this.rgba = new float[4];
		init();
	}

	public MapObject(float x, float y, float w, float h, float bw, float bh, int t, String speakThis) {
		bbox = new Vector<BoundingBox> ();
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		this.type = t;
		if (this.type == WALL) {
			bbox.add(new BoundingBox(x, y, w, h));
		} else {
			bbox.add(new BoundingBox(x, y, bw, bh));
		}
		
		this.rgba = new float[4];
		init();
		this.text = this.text.concat(speakThis);
		
	}
	public void init() {

		resolveType();
		
		// create bounding boxes
		
		float bHeight = bbox.elementAt(0).height;
		float lastW = bbox.elementAt(0).width;
		double ratio = lastW/bHeight;
		
		if (ratio >= 2) { // if width greater than height
			int lastRound = 1;
			//float lastX = x;
			float offsetX = lastW/2;
		
			while (ratio >= 2) {
				ratio /= 2;
				lastW /= 2;
				offsetX /= 2;
				for (int i = 1; i <= Math.pow(2,lastRound)-1; i++) {
					bbox.add(new BoundingBox(x - offsetX*i, y, lastW, bHeight));
				}
				//lastX = x;
				for (int i = 1; i <= Math.pow(2,lastRound)-1; i++) {
					bbox.add(new BoundingBox(x + offsetX*i, y, lastW, bHeight));
				}
				lastRound++;
			
			}
		} else  {
			ratio = bHeight/lastW;
			
			int lastRound = 1;
			//float lastY = y;
			float offsetY = bHeight/2;
		
			while (ratio >= 2) {
				ratio /= 2;
				bHeight /= 2;
				offsetY /= 2;
				for (int i = 1; i <= Math.pow(2,lastRound)-1; i++) {
					bbox.add(new BoundingBox(x, y - offsetY*i, lastW, bHeight));
				}
				//lastY = y;
				for (int i = 1; i <= Math.pow(2,lastRound)-1; i++) {
					bbox.add(new BoundingBox(x, y + offsetY*i, lastW, bHeight));
				}
				lastRound++;
			
			}
		}
		
		// create mesh
		float[] square = new float[] {
				this.x-this.width, this.y-this.height, .0f,
				this.x+this.width, this.y-this.height, .0f,
				this.x-this.width, this.y+this.height, .0f,
				this.x+this.width, this.y+this.height, .0f
		};
		
		ByteBuffer bb = ByteBuffer.allocateDirect(square.length*4);
		
		bb.order(ByteOrder.nativeOrder());
		squareBuff = bb.asFloatBuffer();
		squareBuff.put(square);
		squareBuff.position(0);	
	}
	
	private void resolveType() {
		switch (this.type){
		case WALL: // wall. gray.
			rgba[0] = .5f;
			rgba[1] = .5f;
			rgba[2] = .5f;
			rgba[3] = 1;		
			text = new String("Wall. ");
			break;
		case DOOR: // door green
			rgba[0] = 0f;
			rgba[1] = .9f;
			rgba[2] = .2f;
			rgba[3] = 1;
			text = new String("Door. ");
			break;
		case STAIRS: // stairs green
			rgba[0] = 0f;
			rgba[1] = .8f;
			rgba[2] = .3f;
			rgba[3] = 1;
			text = new String("Stairs. ");
			break;
		case EMERGENCY: // fire red. "Emergency Equipment"
			rgba[0] = 1f;
			rgba[1] = 0f;
			rgba[2] = 0f;
			rgba[3] = 1;
			text = new String("Emergency Equipment. ");
			break;
		case RESTROOM: // rest room purple?
			rgba[0] = .7f;
			rgba[1] = 0f;
			rgba[2] = .7f;
			rgba[3] = 1;			
			text = new String("Restroom. ");
			break;
		case WATERFOUNTAIN: // water fountain. tealish
			rgba[0] = 0f;
			rgba[1] = .6f;
			rgba[2] = .9f;
			rgba[3] = 1;			
			text = new String("Water Fountain. ");
			break;
		case ELEVATOR: // elevators
			rgba[0] = 0f;
			rgba[1] = .5f;
			rgba[2] = .5f;
			rgba[3] = 1;
			text = new String("Elevators. ");
			break;
		case BREAKERS: // breakers blue
			rgba[0] = 0f;
			rgba[1] = .2f;
			rgba[2] = 1;
			rgba[3] = 1;
			text = new String("Breaker Box. ");
			break;
		}
	}
	public void draw(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(this.x, this.y, 0);
		gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, squareBuff);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);//GL_TRIANGLE_FAN, 0, 10);//TRIANGLE_STRIP, 0, 4);
		gl.glPopMatrix();
	}

	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		resolveType();
	}
	
}
