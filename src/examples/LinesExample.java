package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class LinesExample {
	private static final float PI=3.1415f;
	

	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(400, 400));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initGL();
		renderGL();
	}

	private void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-200, 200, -200, 200, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
	}

	private void renderGL() {

		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT);
			glColor3f(1, 1, 1);
			
			glLineWidth(3f);//increase the line width
			glPushMatrix();
			
			glBegin(GL_LINES);
				for(float i=0;i<PI;i+=PI/20){
					float x1=(float) (70*Math.cos(i));
					float y1=(float) (70*Math.sin(i));
					
					float x2=(float) (70*Math.cos(i+PI));
					float y2=(float) (70*Math.sin(i+PI));
					
					glVertex2f(x1, y1);
					glVertex2f(x2, y2);
				}
				
			glEnd();
			glPopMatrix();
			
			
			Display.update();
		}
		Display.destroy();
		
	}
	
	public static void main(String[] args) {
		LinesExample le=new LinesExample();
		le.start();
	}
	
}
