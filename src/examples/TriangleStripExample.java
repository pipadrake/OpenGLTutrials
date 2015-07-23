package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class TriangleStripExample {
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Display Example");
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
		glLoadIdentity();
		glOrtho(-400, 400, -300, 300, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
	}
	
	public void render(){
		
		while(!Display.isCloseRequested()){
			
			
			glBegin(GL_TRIANGLE_STRIP);
			
			glEnd();
			
			Display.update();
		}
		Display.destroy();
	}
	
	public static void main(String[] args) {
		TriangleStripExample display=new TriangleStripExample();
		display.start();
	}
}
