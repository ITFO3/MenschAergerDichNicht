package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import model.Spielfeld;

public class SpielfeldView extends JFrame {

    private Spielfeld model;
    private SpielfeldCanvas canvas;

    /**
     * Das Hauptfenster des Spiels
     *
     * @param model	Das Model von dem Spielfeld
     * @param h		Die X-Startposition
     * @param v		Die Y-Startposition
     */
    public SpielfeldView (Spielfeld model, int h, int v) {
        // Titel setzen
        super("Mensch aerger dich nicht");

        // Model speichern
        this.model = Spielfeld.getInstance();

        canvas = new SpielfeldCanvas();
        canvas.setSize(100, 100);
        getContentPane().add(canvas);

        setSize(800, 800);
        setLocation(h, v);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        canvas.repaint();
        super.paint(g);
    }
}
