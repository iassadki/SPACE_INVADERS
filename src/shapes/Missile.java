package shapes;

import com.jogamp.opengl.GL2;

public class Missile extends GraphicalObject {

    private float moveSpeedMissile = 1f; // Speed of the missile
    private float y; // Variable for the position in y

    public Missile(float pX, float pY, float pZ,
                   float angX, float angY, float angZ,
                   float scale,
                   float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
        this.y = pY; // Initialize the y position of the missile
    }

    @Override
    public void display_normalized(GL2 gl) {
        // Implement the missile drawing logic here
        gl.glColor3f(0.8f, 0.8f, 0.8f);

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-0.05f, 0.0f, 0.0f);
        gl.glVertex3f(0.05f, 0.0f, 0.0f);
        gl.glVertex3f(0.05f, 0.2f, 0.0f);
        gl.glVertex3f(-0.05f, 0.2f, 0.0f);
        gl.glEnd();
    }

    public float getY() {
        return y;
    }

    public float getMoveSpeedMissile() {
        return moveSpeedMissile;
    }

    // Method for moving the missile, from the bottom to the top of the screen, with a given speed
    public void move(float speed) {
        // Déplacer le missile vers le haut (le long de l'axe Y)
        translateY(speed, 0.0f, 0.0f);
    }

    public void translateY(float speed, float v, float v1) {
        y += speed;
    }

    @Override
    public void display(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(getX(), getY(), getZ());
        display_normalized(gl);
        gl.glPopMatrix();
    }
}
