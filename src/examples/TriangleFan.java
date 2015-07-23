package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class TriangleFan {
	private static final double PI= 3.1415;
	static boolean flagDebth=true;
	
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(400, 400));
			Display.setTitle("Triangle Fan Example");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		initGL();
		render();

	}
	
	public void initGL(){
		glMatrixMode(GL_PROJECTION);
		
		glShadeModel(GL_FLAT);
		glFrontFace(GL_CW);

		
		glLoadIdentity();
		glOrtho(-200, 200, -200, 200, 200, -200);
		glMatrixMode(GL_MODELVIEW);
		
	}
	
	public void render(){
	
		float xrotate = 0;
		float yrotate = 0;
		
		
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
			
					
			
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				if(xrotate>360)xrotate=0;
				xrotate+=0.01;
				Display.setTitle("xRotate: "+xrotate);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				if(xrotate<0)xrotate=360;
				xrotate-=0.01;
				Display.setTitle("xRotate: "+xrotate);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				yrotate+=0.01;
				Display.setTitle("yRotate: "+yrotate);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				yrotate-=0.01;
				Display.setTitle("yRotate: "+xrotate);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_R)){
				xrotate=0;
				yrotate=0;
				Display.setTitle("xRotate: "+xrotate);
			}
			

			while(Keyboard.next()){
				if(Keyboard.getEventKeyState()){
					if(Keyboard.getEventKey()==Keyboard.KEY_D)
						glEnable(GL_DEPTH_TEST);
					if(Keyboard.getEventKey()==Keyboard.KEY_F)
						glDisable(GL_DEPTH_TEST);
					
					if(Keyboard.getEventKey()==Keyboard.KEY_L)
						glPolygonMode(GL_BACK, GL_LINE);
					if(Keyboard.getEventKey()==Keyboard.KEY_K)
						glPolygonMode(GL_BACK, GL_FILL);
					
					if(Keyboard.getEventKey()==Keyboard.KEY_C)
						glEnable(GL_CULL_FACE);
					if(Keyboard.getEventKey()==Keyboard.KEY_V)
						glDisable(GL_CULL_FACE);
				}
			}
			
			
			//glEnable(GL_CULL_FACE);
			
			glPushMatrix();
			glRotatef(xrotate, 0, 1, 0);
			glRotatef(yrotate, 1, 0, 0);
			
			glRectf(100, 100, 110, 110);
			glRectf(100, -100, 120, -120);
			
			int counter=0;
			glBegin(GL_TRIANGLE_FAN);
				glVertex3f(0, 0, 75);	
			for(float i=0;i<(2*PI);i+=(PI/8.0)){
				counter++;
				if(counter%2==0) glColor3f(0, 1, 0);
				else glColor3f(0, 0,1);
		
				float x=(float) (50*Math.cos(i));
				float y=(float) (50*Math.sin(i));
				glVertex2f(x, y);	
			}
			glEnd();
			
			glFrontFace(GL_CCW);
			int counter1=0;
			//base cvc
			glBegin(GL_TRIANGLE_FAN);
				glVertex2f(0, 0);	
			for(float i=0;i<(2*PI);i+=(PI/8.0)){
				counter1++;
				if(counter1%2==0) glColor3f(0, 1, 1);
				else glColor3f(1, 0,1);
		
				float x=(float) (50*Math.cos(i));
				float y=(float) (50*Math.sin(i));
				glVertex2f(x, y);	
			}
			glEnd();
			
			glPopMatrix();
			
			Display.update();
			
			
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {
		TriangleFan display=new TriangleFan();
		display.start();
	}
}
