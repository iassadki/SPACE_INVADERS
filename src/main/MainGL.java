package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import formes.Cube;
import formes.GraphicalObject;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

public class MainGL extends GLCanvas implements GLEventListener {

    private ArrayList<GraphicalObject> objects3D;

    public static void main(String[] args) {
        GLCanvas canvas = new MainGL();
        canvas.setPreferredSize(new Dimension(800, 600));
        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setTitle("OpenGL #1");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Animator animator = new Animator(canvas);
        animator.start();
    }

    public MainGL() {
        this.addGLEventListener(this);
        this.objects3D = new ArrayList<>();
        createCubes();
    }

    private void createCubes() {
        float spacing = 2.0f; // Adjust the spacing between cubes
        for (int i = 0; i < 8; i++) {
            float x = i * spacing;
            //float y = 0.0f;
            //float z = -15.0f;
            //float size = 2.0f;
            Cube cube = new Cube(x, 4.0f, -15.0f, 0, 0, 0, 0.5f, 1.0f, 1.0f, 1.0f);
            objects3D.add(cube);
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        for (GraphicalObject obj : objects3D) {
            gl.glPushMatrix();
            obj.display(gl);
            gl.glPopMatrix();
        }
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(45.0, (float) width / height, 0.1, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
