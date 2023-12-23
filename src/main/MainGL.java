package main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

import formes.EnnemyCube;
import formes.GraphicalObject;
import formes.Missile;
import formes.PlayerCube;

public class MainGL extends GLCanvas implements GLEventListener, KeyListener {

    private ArrayList<GraphicalObject> objects3D; // List of 3D objects to display
    private PlayerCube playerCube; // Reference to the player cube
    private ArrayList<Missile> missiles; // List of missiles fired by the player

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
        this.addKeyListener(this);
        this.setFocusable(true);
        this.objects3D = new ArrayList<>();
        this.missiles = new ArrayList<>(); // Initialize the missiles list
        createCubes();
        createPlayerCube();
        //this.requestFocusInWindow(); // Request focus for the KeyListener
    }

    private void createCubes() {
        float spacing = 2.0f; // Adjust the spacing between cubes
        float initialCubeX = -5.0f; // Value for the x coordinate of the first cube
        float initialCubeY = 1.0f; // Value for the y coordinate of the first cube

        // First loop to create 3 rows of 8 cubes from bottom to top
        for (int i = 0; i < 3; i++) {
            float y = initialCubeY + i * spacing;

            // Second loop to create 8 cubes from left to right
            for (int j = 0; j < 6; j++) {
                float x = initialCubeX + j * spacing;
                objects3D.add(new EnnemyCube(x, y, -15.0f, 0, 0, 0, 0.5f, 1.0f, 1.0f, 1.0f)); // Addition of the enemy cube in the list of object3D
            }
        }
    }

    private void createPlayerCube() {
        playerCube = new PlayerCube(0.0f, -5.0f, -15.0f, 0, 0, 0, 0.5f, 1.0f, 1.0f, 1.0f); // Addition of the player cube in the list of object3D
        objects3D.add(playerCube);
    }

    private void fireMissile() {
        // Adjust the initial position of the missile to start from the player
        Missile missile = new Missile(playerCube.getX(), playerCube.getY(), playerCube.getZ() - 5.0f, 0, 0, 0, 5.0f, 1.0f, 1.0f, 1.0f);
        missiles.add(missile);
        objects3D.add(missile);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Update the position of missiles and remove them if they go out of bounds
        updateMissiles();

        for (GraphicalObject obj : objects3D) {
            gl.glPushMatrix();
            obj.display(gl);
            gl.glPopMatrix();
        }
    }

    private void updateMissiles() {
        ArrayList<Missile> missilesToRemove = new ArrayList<>();
        ArrayList<GraphicalObject> objectsToRemove = new ArrayList<>();

        for (Missile missile : missiles) {
            missile.move(missile.getMoveSpeedMissile());

            // Check if the missile is out of bounds, and mark it for removal
            if (missile.getY() > 10.0f) {
                missilesToRemove.add(missile);
            } else {
                // Check for collisions with enemy cubes
                boolean missileCollided = false; // Variable to check if the missile collided with a cube

                for (GraphicalObject obj : objects3D) {
                    if (obj instanceof EnnemyCube) {
                        EnnemyCube ennemyCube = (EnnemyCube) obj;
                        float cubeX = ennemyCube.getX();
                        float cubeY = ennemyCube.getY();
                        float missileX = missile.getX();
                        float missileY = missile.getY();

                        // Check if the missile touches the enemy cube
                        if (missileY + 0.5f > cubeY - 0.5f && missileY - 0.5f < cubeY + 0.5f && missileX + 0.5f > cubeX - 0.5f && missileX - 0.5f < cubeX + 0.5f) {
                            System.out.println("MainGL.missileTouched");
                            objectsToRemove.add(obj);
                            missileCollided = true; // Set the flag to true if collision occurred
                            break; // Exit the loop since we only want to remove one cube
                        }
                    }
                }

                // Remove the missile if it collided with a cube
                if (missileCollided) {
                    missilesToRemove.add(missile);
                }
            }
        }

        // Remove collided missiles and cubes
        missiles.removeAll(missilesToRemove);
        objects3D.removeAll(objectsToRemove);

        // Check for game over
        if (objects3D.isEmpty()) {
            System.out.println("Game Over");
            System.exit(0);
        }
    }

    private void removeEnemyCube(float missileX, float missileY) {
        for (int i = 0; i < objects3D.size(); i++) {
            GraphicalObject obj = objects3D.get(i);
            if (obj instanceof EnnemyCube) {
                EnnemyCube ennemyCube = (EnnemyCube) obj;
                float cubeX = ennemyCube.getX();
                float cubeY = ennemyCube.getY();

                // Vérifiez si le missile touche le cube ennemi
                if (missileY > cubeY && missileY < cubeY + 1.0f && missileX > cubeX - 0.5f && missileX < cubeX + 0.5f) {
                    objects3D.remove(i);
                    return; // Sortez de la méthode après avoir supprimé le cube
                }
            }
        }
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        missiles = new ArrayList<>(); // Initialize the missiles list
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        missiles = new ArrayList<>(); // Add this line to initialize the missiles list
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

    @Override
    public void keyTyped(KeyEvent e) {
        // Fire a missile when the space bar is pressed
        if (e.getKeyChar() == ' ') {
            //System.out.println("MainGL.keyTyped");
            fireMissile();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Q) {
            playerCube.translateX(-playerCube.getMoveSpeed()); // Move to the left
            System.out.println("CoorX Left : " + playerCube.getX());
        }

        if (key == KeyEvent.VK_D) {
            playerCube.translateX(playerCube.getMoveSpeed()); // Move to the right
            System.out.println("CoorX Right : " + playerCube.getX());
        }

        if (key == KeyEvent.VK_SPACE) {
            fireMissile();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used for this functionality
    }
}