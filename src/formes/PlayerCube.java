package formes;

import com.jogamp.opengl.GL2;

public class PlayerCube extends Cube {

    private float moveSpeed = 0.1f; // Ajustez la vitesse de déplacement
    private float x; // Nouvelle variable pour la position en x

    public PlayerCube(float pX, float pY, float pZ, float angX, float angY, float angZ, float scale, float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
        this.x = pX; // Initialiser la position en x
    }

    public void translateX(float speed) {
        x += speed; // Mise à jour de la position en x
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void display_normalized(GL2 gl) {
        gl.glPushMatrix();
        float z = 0;
        float y = 0;
        gl.glTranslatef(x, y, z);
        super.display_normalized(gl); // Appeler la méthode display_normalized de la classe parente
        gl.glPopMatrix();
    }
}
