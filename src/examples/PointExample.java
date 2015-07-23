package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class PointExample {
	private static final float PI=3.1415f;
	private float rotate=0;
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(400, 400));
			Display.setTitle("Points Example");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initgl();
		render();
	}

	private void initgl() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GL11.glOrtho(-200, 200, -200, 200, 400, -400);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}

	private void render() {
		
		
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			float length=50;
			float x;
			float y;
			float z = -50;
			
			//cartisian axis
			glBegin(GL_LINES);
			glColor3f(1, 1, 1);
				glVertex3f(-200, 0, 0);
				glVertex3f(200, 0, 0);
				
				glVertex3f(0, -200, 0);
				glVertex3f(0, 200, 0);
			glEnd();
			
			
			glPushMatrix();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				if(rotate>360)rotate=0;
				rotate+=0.01;
				Display.setTitle("xRotate: "+rotate);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				if(rotate<0)rotate=360;
				rotate-=0.01;
				Display.setTitle("xRotate: "+rotate);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_R)){
				rotate=0;
				Display.setTitle("xRotate: "+rotate);
			}
			
			
			glRotatef(rotate, 0, 1, 0);
			glRotatef(rotate, 1.0f, 0f, 0.0f);
			
			
			glBegin(GL_POINTS);
			glColor3f(0, 1, 0);
				
				for (float i=0;i<(2*PI)*4;i+=0.1){
					y=(float) (length*Math.sin(i));
					x=(float) (length*Math.cos(i));
					
					z+=0.5;
					glVertex3f(x, y, z);					
				}
			glEnd();
			
			
			glPopMatrix();
			Display.update();
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {
		PointExample pe = new PointExample();
		pe.start();
	}
	
	
}
