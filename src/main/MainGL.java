package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import formes.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

public class MainGL extends GLCanvas
        implements GLEventListener
{
    private ArrayList<GraphicalObject> objects3D;
    private Cube bigCube;
    private Cube smallCube;
    private float angle;

    public static void main(String[] args)
    {
        GLCanvas canvas = new MainGL();
        canvas.setPreferredSize(new Dimension(800, 600));
        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setTitle("OpenGL #1");
        frame.pack();
        frame.setVisible(true);
        Animator animator = new Animator(canvas);
        animator.start();
    }

    public MainGL() {
        this.addGLEventListener(this);
        this.objects3D = new ArrayList<GraphicalObject>();
        this.angle = 0.0f;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();
        // Draw objects ???
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, -15.0f);
        this.bigCube.rotate(0f, 0f, 0f);
        this.bigCube.display(gl);
        gl.glRotatef(this.angle, 0, 0, 0);
        gl.glTranslatef(0.0f, 4.0f, 0.0f);
        this.smallCube.rotate(0f, 0f, 0f);
        this.smallCube.display(gl);
        gl.glPopMatrix();
        this.angle += 0.025f;
        //for (GraphicalObject obj: objects3D)
        //	obj.display(gl);

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        // Color background
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        // Initialize all graphical objects
        this.bigCube = new Cube(0, 0, 0, 0, 0, 0, 2, 0, 0, 0);
        this.smallCube = new Cube(0, 0, 0, 0, 0, 0, 0.5f, 0, 0, 0);
        //this.objects3D.add(new Square(0f, 0f, -40f, 0f, 0f, 0f, 4f, 1.0f, 0.0f, 0.0f));
        //this.objects3D.add(new Square(0f, 0f, -10f, 0f, 0f, 0f, 0.25f, 0.0f, 1.0f, 0.0f));
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
