package formes;

import com.jogamp.opengl.GL2;

public class Missile extends GraphicalObject {

    private float moveSpeedMissile = 1f; // Ajustez la vitesse de déplacement
    private float y; // Nouvelle variable pour la position en y

    public Missile(float pX, float pY, float pZ,
                   float angX, float angY, float angZ,
                   float scale,
                   float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
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

    public void move(float speed) {
        // Move the missile upwards (along the y-axis)
        //translateY(0.0f, speed, 0.0f);
        y += speed; // Mise à jour de la position en x
        //translateY(speed);
    }

    public void translateY(float v, float speed, float v1) {
    }

    @Override
    public void display(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(getX(), getY(), getZ());
        display_normalized(gl);
        gl.glPopMatrix();
    }
}
