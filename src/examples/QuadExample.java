package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
public class QuadExample {

	private static int WINDOW_WIDTH=800;
	private static int WINDOW_HEIGHT=600;
	
	public void start(){
		try{
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH,WINDOW_HEIGHT));
			Display.setTitle("Quad Example");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		init();
		render();
	}
	
	public void init(){
		//glViewport(0, 300, 400, 300);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-WINDOW_WIDTH/2, WINDOW_WIDTH/2, -WINDOW_HEIGHT/2, WINDOW_HEIGHT/2, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void render(){
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
			glColor3f(0.5f, 0.5f, 1.0f);
					
			glBegin(GL_QUADS);
				glVertex2f(-50, -50);
				glVertex2f(-50+100, -50);
				glVertex2f(-50+100, -50+100);
				glVertex2f(-50, -50+100);
			glEnd();
			
			Display.update();
		}
		Display.destroy();
	}	
	
	public static void main(String[] args) {
		QuadExample qe=new QuadExample();
		qe.start();
	}
}
