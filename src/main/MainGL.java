package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import formes.Cube;
import formes.EnnemyCube;
import formes.GraphicalObject;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import formes.PlayerCube;

public class MainGL extends GLCanvas implements GLEventListener {

    private Cube cube;
    private ArrayList<GraphicalObject> objects3D; // Liste des objets 3D, cubes ennemis, à afficher

    public static void main(String[] args) {
        GLCanvas canvas = new MainGL();
        canvas.setPreferredSize(new Dimension(800, 600));
        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setTitle("SPACE INVADERS");
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
        createPlayerCube();
    }

    private void createCubes() {
        float spacing = 2.0f; // Adjust the spacing between cubes
        float initialCubeX = -5.0f; // Value for the x coordinate of the first cube
        float initialCubeY = 1.0f; // Value for the y coordinate of the first cube

        // Boucle pour créer des lignes de 5 cubes de haut en bas
        for (int i = 0; i < 3; i++) {
            float y = initialCubeY + i * spacing;

            // Boucle pour créer des colonnes de 8 cubes de gauche à droite
            for (int j = 0; j < 6; j++) {
                float x = initialCubeX + j * spacing;
                objects3D.add(new EnnemyCube(x, y, -15.0f, 0, 0, 0, 0.5f, 1.0f, 1.0f, 1.0f)); // Ajout du cube crée à la liste objects3D
            }
        }
    }

    private void createPlayerCube() {
        objects3D.add(new PlayerCube(0.0f, -5.0f, -15.0f, 0, 0, 0, 0.5f, 1.0f, 1.0f, 1.0f));
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
