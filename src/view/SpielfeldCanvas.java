package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import model.Figur;
import model.Spielfeld;

/**
 * Das Canvas auf dem JFrame
 */
public class SpielfeldCanvas extends Canvas implements Observer, MouseListener {
	private static final int height = 600;
	private static final int width = 600;
	int click_X_Position = 0;
	int click_Y_Position = 0;
	List<Figur> moeglichkeiten;
	static Spielfeld model;
	private static SpielfeldCanvas instanz;

	/**
	 * Das Canvas auf dem JFrame
	 * 
	 * @param model
	 *            Das Model von dem Spielfeld
	 */
	public SpielfeldCanvas(Spielfeld model) {
		this.model = model;
		this.model.addObserver(this);
		addMouseListener(this);
	}

	/**
	 * Array fuer die X-Positionen der Figuren
	 */
	private static final int[] xPositionen = { 
			546, 546, 494, 494, // Startfelder Spieler 4
			66, 66, 13, 13, // Startfelder Spieler 3
			548, 548, 494, 494, // Startfelder Spieler 2
			66, 66, 13, 13, // Startfelder Spieler 1
			-50, // Nullfeld
			226, 226, 226, 226, 226, // 1-5
			173, 120, 67, 14, // 6-9
			14, // 10
			14, 67, 120, 173, 226, // 11-15
			226, 226, 226, 226, // 16-19
			279, // 20
			332, 332, 332, 332, 332, // 21-25
			385, 438, 491, 544, // 26-29
			544, // 30
			544, 491, 438, 385, 332, // 31-35
			332, 332, 332, 332, // 36-39
			279, // 40
			279, 279, 279, 279, // Endfelder Spieler 1
			67, 120, 173, 226,  // Endfelder Spieler 2
			279, 279, 279, 279, // Endfelder Spieler 3
			491, 438, 385, 332, // Endfelder Spieler 4
	};
	/**
	 * Array fuer die Y-Positionen der Figuren
	 */
	private static final int[] yPositionen = { 
			546, 494, 546, 494, // Startfelder  Spieler 4
			546, 494, 546, 494, // Startfelder Spieler 3
			66, 13, 66, 13, // Startfelder Spieler 2
			66, 13, 66, 13, // Startfelder Spieler 1
			-50, // Nullfeld
			546, 493, 440, 387, 334, // 1-5
			334, 334, 334, 334, // 6-9
			281, // 10
			228, 228, 228, 228, 228, // 11-15
			175, 122, 69, 16, // 16-19
			16, // 20
			16, 69, 122, 175, 228, // 21-25
			228, 228, 228, 228, // 26-29
			281, // 30
			334, 334, 334, 334, 334, // 31-35
			387, 440, 493, 546, // 36-39
			546, // 40
			493, 440, 387, 334, // Endfelder Spieler 1
			281, 281, 281, 281, // Endfelder Spieler 2
			69, 122, 175, 228, // Endfelder Spieler 3
			281, 281, 281, 281, // Endfelder Spieler 4
	};

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Hintergrund laden
//		Image image = new ImageIcon(this.getClass().getResource(
//				"pachisi-4er-blanco.jpg")).getImage();
		Image image = new ImageIcon("J:/MenschAergerDichNicht/src/view/pachisi-4er-blanco.jpg").getImage();
		g.drawImage(image, 0, 0, height, width, this);

		// Spielfiguren zeichnen
		for (Figur figur : model.getSpielfiguren().values()) {
			DrawSpielfigur(g, figur);
		}
		
		highlightSpielfiguren(g, model.getMoeglichkeiten());
		g.finalize();
		//getSelectedFigur();
	}

	/**
	 * Zeichnet die uebergebene Spielfigur
	 * 
	 * @param g
	 * @param figur
	 */
	public void DrawSpielfigur(Graphics g, Figur figur) {
		int position = figur.getPosition() + 16;
//		Spielfigur oberflaechenFigur = new Spielfigur(figur, xPositionen[position], yPositionen[position]);
//		
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.fill(oberflaechenFigur.getGrafischeSpielfigur());
		
		g.setColor(figur.getColor());
		g.fillOval(xPositionen[position], yPositionen[position], 40, 40);
	}

	public void highlightSpielfiguren(Graphics g, List<Figur> figuren) {
		moeglichkeiten = new ArrayList<Figur>();
		moeglichkeiten.addAll(figuren);
		for (Figur figur : figuren) {
			int position = figur.getPosition() + 16;
			g.setColor(Color.GREEN);
			g.fillOval(xPositionen[position]+8, yPositionen[position]+8, 25, 25);
//			g.setColor(Color.red);
//			g.fillOval(xPositionen[position], yPositionen[position], 55, 55);
//			g.setColor(Color.black);
//			g.fillOval(xPositionen[position], yPositionen[position], 50, 50);
//			g.setColor(figur.getColor());
//			g.fillOval(xPositionen[position], yPositionen[position], 40, 40);
		}
	}
	
	
	
	public void getSelectedFigur() {
		for(Figur f : moeglichkeiten) {
			int position = f.getPosition();
		int figPosition = yPositionen[position];
		
		}
	}
	

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	/**
	 * Gibt die aktuelle Instanz des Spielfeldes zurueck
	 * 
	 * @return
	 */
	public static SpielfeldCanvas getInstanz() {
		if (instanz == null) {
			instanz = new SpielfeldCanvas(model);
		}

		return instanz;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("X: "+arg0.getX()+ " Y: "+arg0.getY());
		System.out.println("Obj: "+arg0.getComponent());
		click_X_Position = arg0.getX();
		click_Y_Position = arg0.getY();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
