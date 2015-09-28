package GUI;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import MenschAergerDichNicht.Figur;

public class SpielfeldCanvas extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int height = 600;
	private static final int width = 600;
	
	private static final int[] xPositionen = {
			546, 546, 494, 494, // Spieler 4
			66, 66, 13, 13,		// Spieler 3
			548, 548, 494, 494,	// Spieler 2
			66, 66, 13, 13		// Spieler 1
		};
	private static final int[] yPositionen = {
			546, 494, 546, 494,	// Spieler 4
			546, 494, 546, 494,	// Spieler 3
			66, 13, 66, 13,		// Spieler 2
			66, 13, 66, 13		// Spieler 1
		};
	
	public void paint(Graphics g) {
		// Hintergrund laden
		Image image = new ImageIcon(this.getClass().getResource("pachisi-4er-blanco.jpg")).getImage();
		g.drawImage(image, 0, 0, height, width, this);
		g.finalize();
	}
	
	public void DrawSpielfigur(Graphics g, Figur figur) {
		int position = figur.getPosition() + 16;
		g.setColor(figur.getColor());
		g.fillOval(xPositionen[position], yPositionen[position], 40, 40);
	}
}
