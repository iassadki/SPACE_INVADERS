package formes;

import com.jogamp.opengl.GL2;

public class PlayerCube extends Cube {

    public PlayerCube(float pX, float pY, float pZ, float angX, float angY, float angZ, float scale, float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
    }

    public void display_normalized(GL2 gl) {
        super.display_normalized(gl);
    }

    public void move(float speed) {
        // Déplacer le missile vers le haut (dans l'axe y)
        translateY(speed);
    }

    private void translateY(float speed) {
    }
}
