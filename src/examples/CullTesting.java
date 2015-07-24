package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class CullTesting {
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
		//glFrontFace(GL_CW);

		
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
				xrotate+=0.03;
			}			
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				xrotate-=0.03;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				yrotate+=0.03;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				yrotate-=0.03;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_R)){
				xrotate=0; yrotate=0;
			}

			//glFrontFace(GL_CW);
			//glCullFace(GL_CW);
			glEnable(GL_DEPTH_TEST);
			glPolygonMode(GL_BACK, GL_LINE);
			//glPolygonMode(GL_BACK, GL_FILL);
			while(Keyboard.next()){
				if(Keyboard.getEventKeyState()){
					if(Keyboard.getEventKey()==Keyboard.KEY_Q)
						glEnable(GL_CULL_FACE);
					if(Keyboard.getEventKey()==Keyboard.KEY_W)
						glDisable(GL_CULL_FACE);
				}
			}
			
			
			//glEnable(GL_CULL_FACE);
			
			glPushMatrix();
			glRotatef(xrotate, 0, 1, 0);
			glRotatef(yrotate, 1, 0, 0);
			
			
			glBegin(GL_QUADS);
				glColor3f(0.7f, 0, 0);
				glVertex2f(0,0);
				glVertex2f(50, 0);
				glVertex2f(50, 50);
				glVertex2f(0, 50);

				glColor3f(03f, 0, 0);
				glVertex3f(0, 0, 0);
				glVertex3f(0, 50, 0);
				glVertex3f(0, 50, 50);
				glVertex3f(0, 0, 50);
				
				glColor3f(0f, 03f, 0);
				glVertex3f(0, 0, 50);
				glVertex3f(0, 50, 50);
				glVertex3f(50, 50, 50);
				glVertex3f(50, 0, 50);
			glEnd();
			
				
			glPopMatrix();
			
			Display.update();
			
			
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {
		CullTesting display=new CullTesting();
		display.start();
	}
}
