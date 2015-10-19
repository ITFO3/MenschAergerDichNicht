package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import menschaergerdichnicht.Figur;
import menschaergerdichnicht.Spielfeld;

/**
 * Das Canvas auf dem JFrame
 */
public class SpielfeldCanvas extends Canvas implements Observer {
	private static final int height = 600;
	private static final int width = 600;
	
	private Spielfeld model;
	
	/**
	 * Das Canvas auf dem JFrame
	 * 
	 * @param model Das Model von dem Spielfeld
	 */
	public SpielfeldCanvas(Spielfeld model) {
		this.model = model;
		this.model.addObserver(this);
	}
	
	/**
	 * Array fuer die X-Positionen der Figuren
	 */
	private static final int[] xPositionen = {
			546, 546, 494, 494, 		// Startfelder Spieler 4
			66, 66, 13, 13,				// Startfelder Spieler 3
			548, 548, 494, 494,			// Startfelder Spieler 2
			66, 66, 13, 13,				// Startfelder Spieler 1
			-50,						// Nullfeld
			226, 226, 226, 226, 226,	// 1-5
			173, 120, 67, 14,			// 6-9
			14, 						// 10
			14, 67, 120, 173, 226,		// 11-15
			226, 226, 226, 226,			// 16-19
			279,						// 20
			332, 332, 332, 332, 332,	// 21-25		
			385, 438, 491, 544,			// 26-29
			544,						// 30
			544, 491, 438, 385, 332,	// 31-35
			332, 332, 332, 332,			// 36-39
			279							// 40
		};
	/**
	 * Array fuer die Y-Positionen der Figuren
	 */
	private static final int[] yPositionen = {
			546, 494, 546, 494,			// Startfelder Spieler 4
			546, 494, 546, 494,			// Startfelder Spieler 3
			66, 13, 66, 13,				// Startfelder Spieler 2
			66, 13, 66, 13,				// Startfelder Spieler 1
			-50,						// Nullfeld
			546, 493, 440, 387, 334,	// 1-5		
			334, 334, 334, 334,			// 6-9
			281, 						// 10
			228, 228, 228, 228, 228,	// 11-15
			175, 122, 69, 16,			// 16-19
			16,							// 20
			16, 69, 122, 175, 228,		// 21-25
			228, 228, 228, 228,			// 26-29
			281,						// 30
			334, 334, 334, 334, 334,	// 31-35
			387, 440, 493, 546,			// 36-39
			546							// 40
		};
	
	public void paint(Graphics g) {
		// Hintergrund laden
		Image image = new ImageIcon(this.getClass().getResource("pachisi-4er-blanco.jpg")).getImage();
		g.drawImage(image, 0, 0, height, width, this);
		
		// Spielfiguren zeichnen
		for (Figur figur : model.getSpielfiguren().values()) {
			DrawSpielfigur(g, figur);
		}
		
		g.finalize();
	}
	
	/**
	 * Zeichnet die uebergebene Spielfigur
	 * @param g
	 * @param figur
	 */
	public void DrawSpielfigur(Graphics g, Figur figur) {
		int position = figur.getPosition() + 16;
		g.setColor(figur.getColor());
		g.fillOval(xPositionen[position], yPositionen[position], 40, 40);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
