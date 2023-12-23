package formes;

import com.jogamp.opengl.GL2;

public class PlayerCube extends Cube {

    private float moveSpeed = 1f; // Movement speed of the player
    private float x; // Variable for the position in y

    public PlayerCube(float pX, float pY, float pZ, float angX, float angY, float angZ, float scale, float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
        this.x = pX; // Initialize the position in x
    }

    public void translateX(float speed) {
        x += speed; // Update the position in x
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getX() {
    	return x;
    }

    @Override
    public void display_normalized(GL2 gl) {
        gl.glPushMatrix();
        float z = 0;
        float y = 0;
        gl.glTranslatef(x, y, z); // Translate the player cube to the right position
        super.display_normalized(gl); // Call the display_normalized method of the parent class
        gl.glPopMatrix();
    }
}
