package gl;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

public class MainGL_Triangle extends GLCanvas implements GLEventListener {
    
	private float posX, posY, posZ; 
	private float angle;
	private int iter;
	public static void main(String[] args)
	{
		GLCanvas canvas = new MainGL_Triangle();
		canvas.setPreferredSize(new Dimension(800, 600));
		final JFrame frame = new JFrame();
		frame.getContentPane().add(canvas);
		frame.setTitle("OpenGL Triangle");
		frame.pack();
		frame.setVisible(true);
		Animator animator = new Animator(canvas);
		animator.start();
	}
	
	public MainGL_Triangle() { 
		this.addGLEventListener(this);
		this.posX = 0.0f;
		this.posY = 0.0f;
		this.posZ = -10.0f;
		this.iter = 0;
		this.angle = 0.0f;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		this.iter += 1;
		this.angle += 0.1f;
		//this.posX += 0.001f;
		//this.posY += 0.001f;
		//this.posZ += 0.005f;
		//gl.glRotatef(0.05f, 1.0f, 0.0f, 0.0f);
		//gl.glTranslatef(0.0f, 0.0f, -20.0f);
		//gl.glRotatef(10, 0.0f, 1.0f, 0.0f);
		//gl.glRotatef(16.0f, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(this.angle, 1.0f, 1.0f, 1.0f);
		gl.glTranslatef(0.0f, 0.0f, this.posZ);
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glColor3f(0.2f, 0.1f, 0.7f);
			gl.glVertex3f(0.0f, 1.0f, 0.0f);
			gl.glColor3f(0.2f, 0.7f, 0.3f);
			gl.glVertex3f(-1.0f , -1.0f, 0.0f);
			gl.glColor3f(0.7f, 0.1f, 0.3f);
			gl.glVertex3f(1.0f, -1.0f, 0.0f);
		gl.glEnd();
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, 
			int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
		// Set the view area
		gl.glViewport(0, 0, width, height);
		// Setup perspective projection
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU glu = new GLU();
		glu.gluPerspective(45.0, (float)width/height, 
				0.1, 100.0);
		// Enable the model view
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	

}
