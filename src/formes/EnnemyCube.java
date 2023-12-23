package formes;

import com.jogamp.opengl.GL2;

public class EnnemyCube extends Cube {

    private float y; // Nouvelle variable pour la position en y

    public EnnemyCube(float pX, float pY, float pZ, float angX, float angY, float angZ, float scale, float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
    }

    public void display_normalized(GL2 gl) {
        super.display_normalized(gl);
    }

    public float getY() {
        return y;
    }
}
