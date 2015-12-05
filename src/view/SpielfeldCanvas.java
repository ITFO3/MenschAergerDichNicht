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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import controller.server.ServerController;
import model.Figur;
import model.Spielfeld;

/**
 * Das Canvas auf dem JFrame
 */
public class SpielfeldCanvas extends Canvas implements Observer, MouseListener{
	private static final int height = 600;
	private static final int width = 600;
	int click_X_Position = 0;
	int click_Y_Position = 0;
	List<Figur> moeglichkeiten;
	static Spielfeld model;
	private static SpielfeldCanvas instanz;
	private static HashMap<Integer, int[]> feldZuKoordinate;
	private static HashMap<int[], Integer> koordinateZuFeld;

	/**
	 * Das Canvas auf dem JFrame
	 * 
	 * @param model
	 *            Das Model von dem Spielfeld
	 */
	public SpielfeldCanvas(Spielfeld model) {
		this.model = model;
		this.model.addObserver(this);
		this.initializeFZK();
		this.initializeKZF();
		this.addMouseListener(this);
	}

	private void initializeFZK(){
		feldZuKoordinate = new HashMap<Integer, int[]>();
		feldZuKoordinate.put(1, new int[]{226,546});
		feldZuKoordinate.put(2, new int[]{226,493});
		feldZuKoordinate.put(3, new int[]{226,440});
		feldZuKoordinate.put(4, new int[]{226,387});
		feldZuKoordinate.put(5, new int[]{226,334});
		feldZuKoordinate.put(6, new int[]{173,334});
		feldZuKoordinate.put(7, new int[]{120,334});
		feldZuKoordinate.put(8, new int[]{67,334});
		feldZuKoordinate.put(9, new int[]{14,334});
		feldZuKoordinate.put(10, new int[]{14,281});
		feldZuKoordinate.put(11, new int[]{14,228});
		feldZuKoordinate.put(12, new int[]{67,228});
		feldZuKoordinate.put(13, new int[]{120,228});
		feldZuKoordinate.put(14, new int[]{173,228});
		feldZuKoordinate.put(15, new int[]{226,228});
		feldZuKoordinate.put(16, new int[]{226,175});
		feldZuKoordinate.put(17, new int[]{226,122});
		feldZuKoordinate.put(18, new int[]{226,69});
		feldZuKoordinate.put(19, new int[]{226,16});
		feldZuKoordinate.put(20, new int[]{279,16});
		feldZuKoordinate.put(21, new int[]{332,16});
		feldZuKoordinate.put(22, new int[]{332,69});
		feldZuKoordinate.put(23, new int[]{332,122});
		feldZuKoordinate.put(24, new int[]{332,175});
		feldZuKoordinate.put(25, new int[]{332,228});
		feldZuKoordinate.put(26, new int[]{385,228});
		feldZuKoordinate.put(27, new int[]{438,228});
		feldZuKoordinate.put(28, new int[]{491,228});
		feldZuKoordinate.put(29, new int[]{544,228});
		feldZuKoordinate.put(30, new int[]{544,281});
		feldZuKoordinate.put(31, new int[]{544,334});
		feldZuKoordinate.put(32, new int[]{491,334});
		feldZuKoordinate.put(33, new int[]{438,334});
		feldZuKoordinate.put(34, new int[]{385,334});
		feldZuKoordinate.put(35, new int[]{332,334});
		feldZuKoordinate.put(36, new int[]{332,387});
		feldZuKoordinate.put(37, new int[]{332,440});
		feldZuKoordinate.put(38, new int[]{332,493});
		feldZuKoordinate.put(39, new int[]{332,546});
		feldZuKoordinate.put(40, new int[]{279,546});
		
		// Homefields
		feldZuKoordinate.put(-1, new int[]{66,66});
		feldZuKoordinate.put(-2, new int[]{66,13});
		feldZuKoordinate.put(-3, new int[]{13,66});
		feldZuKoordinate.put(-4, new int[]{13,13});
		feldZuKoordinate.put(-5, new int[]{548,66});
		feldZuKoordinate.put(-6, new int[]{548,13});
		feldZuKoordinate.put(-7, new int[]{494,66});
		feldZuKoordinate.put(-8, new int[]{494,13});
		feldZuKoordinate.put(-9, new int[]{66,546});
		feldZuKoordinate.put(-10, new int[]{66,494});
		feldZuKoordinate.put(-11, new int[]{13,546});
		feldZuKoordinate.put(-12, new int[]{13,494});
		feldZuKoordinate.put(-13, new int[]{546,546});
		feldZuKoordinate.put(-14, new int[]{546,494});
		feldZuKoordinate.put(-15, new int[]{494,546});
		feldZuKoordinate.put(-16, new int[]{494,494});
		
		// Nullfield
		// feldZuKoordinate.put(-100, new int[]{-50,-50});
	}
	
	private void initializeKZF(){
		koordinateZuFeld = new HashMap<int[],Integer>();
		koordinateZuFeld.put(new int[]{226,546},1);
		koordinateZuFeld.put(new int[]{226,493},2);
		koordinateZuFeld.put(new int[]{226,440},3);
		koordinateZuFeld.put(new int[]{226,387},4);
		koordinateZuFeld.put(new int[]{226,334},5);
		koordinateZuFeld.put(new int[]{173,334},6);
		koordinateZuFeld.put(new int[]{120,334},7);
		koordinateZuFeld.put(new int[]{67,334},8);
		koordinateZuFeld.put(new int[]{14,334},9);
		koordinateZuFeld.put(new int[]{14,281},10);
		koordinateZuFeld.put(new int[]{14,228},11);
		koordinateZuFeld.put(new int[]{67,228},12);
		koordinateZuFeld.put(new int[]{120,228},13);
		koordinateZuFeld.put(new int[]{173,228},14);
		koordinateZuFeld.put(new int[]{226,228},15);
		koordinateZuFeld.put(new int[]{226,175},16);
		koordinateZuFeld.put(new int[]{226,122},17);
		koordinateZuFeld.put(new int[]{226,69},18);
		koordinateZuFeld.put(new int[]{226,16},19);
		koordinateZuFeld.put(new int[]{279,16},20);
		koordinateZuFeld.put(new int[]{332,16},21);
		koordinateZuFeld.put(new int[]{332,69},22);
		koordinateZuFeld.put(new int[]{332,122},23);
		koordinateZuFeld.put(new int[]{332,175},24);
		koordinateZuFeld.put(new int[]{332,228},25);
		koordinateZuFeld.put(new int[]{385,228},26);
		koordinateZuFeld.put(new int[]{438,228},27);
		koordinateZuFeld.put(new int[]{491,228},28);
		koordinateZuFeld.put(new int[]{544,228},29);
		koordinateZuFeld.put(new int[]{544,281},30);
		koordinateZuFeld.put(new int[]{544,334},31);
		koordinateZuFeld.put(new int[]{491,334},32);
		koordinateZuFeld.put(new int[]{438,334},33);
		koordinateZuFeld.put(new int[]{385,334},34);
		koordinateZuFeld.put(new int[]{332,334},35);
		koordinateZuFeld.put(new int[]{332,387},36);
		koordinateZuFeld.put(new int[]{332,440},37);
		koordinateZuFeld.put(new int[]{332,493},38);
		koordinateZuFeld.put(new int[]{332,546},39);
		koordinateZuFeld.put(new int[]{279,546},40);
		
		// Homefields
		koordinateZuFeld.put(new int[]{66,66}  ,-1);
		koordinateZuFeld.put(new int[]{66,13}  ,-2);
		koordinateZuFeld.put(new int[]{13,66}  ,-3);
		koordinateZuFeld.put(new int[]{13,13}  ,-4);
		koordinateZuFeld.put(new int[]{548,66} ,-5);
		koordinateZuFeld.put(new int[]{548,13} ,-6);
		koordinateZuFeld.put(new int[]{494,66} ,-7);
		koordinateZuFeld.put(new int[]{494,13} ,-8);
		koordinateZuFeld.put(new int[]{66,546} ,-9);
		koordinateZuFeld.put(new int[]{66,494} ,-10);
		koordinateZuFeld.put(new int[]{13,546} ,-11);
		koordinateZuFeld.put(new int[]{13,494} ,-12);
		koordinateZuFeld.put(new int[]{546,546},-13);
		koordinateZuFeld.put(new int[]{546,494},-14);
		koordinateZuFeld.put(new int[]{494,546},-15);
		koordinateZuFeld.put(new int[]{494,494},-16);
		
		// Nullfield
		// feldZuKoordinate.put(-100, new int[]{-50,-50});
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
		Image image = new ImageIcon(this.getClass().getResource(
				"pachisi-4er-blanco.jpg")).getImage();
//		Image image = new ImageIcon("J:/MenschAergerDichNicht/src/view/pachisi-4er-blanco.jpg").getImage();
		g.drawImage(image, 0, 0, height, width, this);

		// Spielfiguren zeichnen
		for (Figur figur : model.getSpielfiguren().values()) {
			drawSpielfigur(g, figur);
		}

//		highlightSpielfiguren(g, model.getMoeglichkeiten());
		g.finalize();
	}

	/**
	 * Zeichnet die uebergebene Spielfigur
	 * 
	 * @param g
	 * @param figur
	 */	
	public void drawSpielfigur(Graphics g, Figur figur) {
		
		int position = figur.getPosition();
		g.setColor(figur.getColor());
		g.fillOval(feldZuKoordinate.get(position)[0],feldZuKoordinate.get(position)[1], 40, 40);
	}

	public void highlightSpielfiguren(Graphics g, List<Figur> figuren) {
		moeglichkeiten = new ArrayList<Figur>();
		moeglichkeiten.addAll(figuren);
		for (Figur figur : figuren) {
			int position = figur.getPosition() + 16;
			g.setColor(Color.GREEN);
			g.fillOval(feldZuKoordinate.get(position)[0] + 8, feldZuKoordinate.get(position)[1] + 8,
					25, 25);
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
	public void mouseClicked(MouseEvent e) {
		int feld = -100;
		
		Iterator it = feldZuKoordinate.entrySet().iterator();
	    while (it.hasNext() && feld == -100) {
	        Map.Entry pair = (Map.Entry)it.next();
	        int [] currentValue = (int[])pair.getValue();
	        if(e.getX() >= currentValue[0] && e.getY() >= currentValue[1] &&
	        		e.getX() <= currentValue[0]+40 && e.getY() >= currentValue[1] +40) {
	        	feld = (int)pair.getKey();

	        	
	        }
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    		
		Figur f = Spielfeld.getInstanz().sucheFigur(feld);
		if(f != null) {
			ServerController.getInstanz().bewegeFigur(f);
		
		System.out.println(f.getPosition());
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
