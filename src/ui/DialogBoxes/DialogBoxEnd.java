package ui.DialogBoxes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DialogBoxEnd {

    private static JDialog d;

    public DialogBoxEnd() {
        JFrame f = new JFrame();
        d = new JDialog(f, "Fin de jeu", true);
        d.setSize(800, 400);
        d.setLocationRelativeTo(null);
        d.setResizable(false);
        d.setLayout(new GridBagLayout());

        JLabel endGameLabel = new JLabel("Vous avez gagné !");
        d.add(endGameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10, 0, 10, 0), 0, 0));

        JButton quitButton = new JButton("Quitter le jeu");
        quitButton.addActionListener(e -> {
            // Fermer la boîte de dialogue
            d.dispose();
            // Fermer l'application
            System.exit(0);
        });

        // Press enter to quit the game
        JLabel pressEnterLabel = new JLabel("<PRESS ENTER TO QUIT THE GAME>");
        d.add(pressEnterLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 100), 0, 0));

        d.add(quitButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10, 0, 10, 0), 0, 0));

        // Close JDialog when pressing key "ENTER"
        int condition = JPanel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = ((JPanel) d.getContentPane()).getInputMap(condition);
        ActionMap actionMap = ((JPanel) d.getContentPane()).getActionMap();
        String enter = "enter";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        actionMap.put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer la boîte de dialogue
                d.dispose();
                // Fermer l'application
                System.exit(0);
            }
        });

        d.pack();
        d.setVisible(true);
    }

    public void showDialog() {
        d.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DialogBoxEnd::new);
    }
}
