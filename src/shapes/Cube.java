package shapes;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class Cube extends GraphicalObject {

    private ArrayList<Square> faces;

    public Cube(float pX, float pY, float pZ,
                float angX, float angY, float angZ,
                float scale,
                float r, float g, float b) {
        super(pX, pY, pZ, angX, angY, angZ, scale, r, g, b);
        faces = new ArrayList<Square>();
        // Front face
        faces.add(new Square(0, 0, 1, 0, 0, 0, 1, 0.8f, 0.2f, 0.2f));
        // Back face
        faces.add(new Square(0, 0, -1, 0, 0, 0, 1, 0.2f, 0.4f, 0.6f));
        // Right face
        faces.add(new Square(1, 0, 0, 0, 90, 0, 1, 0.1f, 0.8f, 0.2f));
        // Left face
        faces.add(new Square(-1, 0, 0, 0, -90, 0, 1, 0.6f, 0.5f, 0.2f));
        // Top face
        faces.add(new Square(0, 1, 0, 90, 0, 0, 1, 0.3f, 0.2f, 0.8f));
        // Left face
        faces.add(new Square(0, -1, 0, 90, 0, 0, 1, 0.3f, 0.8f, 0.7f));
    }

    public void display_normalized(GL2 gl) {
        for (Square face : faces)
            face.display(gl);
    }
}
