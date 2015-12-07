package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import javax.swing.JLabel;

import model.Figur;
import model.Spielfeld;
import controller.client.network.ClientNetworkService;
import controller.server.ServerController;

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
	private HashMap<Integer, int[]> feldZuKoordinate;
	private static HashMap<int[], Integer> koordinateZuFeld;

	/**
	 * Das Canvas auf dem JFrame
	 * 
	 * @param model Das Model von dem Spielfeld
	 */
	public SpielfeldCanvas(Spielfeld model) {
		SpielfeldCanvas.model = model;
		SpielfeldCanvas.model.addObserver(this);
		this.initializeFZK();
		this.addMouseListener(this);
		instanz = this;
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
		
		// Finishfields
		feldZuKoordinate.put(41, new int[]{279,493});
		feldZuKoordinate.put(42, new int[]{279,440});
		feldZuKoordinate.put(43, new int[]{279,387});
		feldZuKoordinate.put(44, new int[]{279,334});
		feldZuKoordinate.put(45, new int[]{67,281});
		feldZuKoordinate.put(46, new int[]{120,281});
		feldZuKoordinate.put(47, new int[]{173,281});
		feldZuKoordinate.put(48, new int[]{226,281});
		feldZuKoordinate.put(49, new int[]{279,69});
		feldZuKoordinate.put(50, new int[]{279,122});
		feldZuKoordinate.put(51, new int[]{279,175});
		feldZuKoordinate.put(52, new int[]{279,228});
		feldZuKoordinate.put(53, new int[]{491,281});
		feldZuKoordinate.put(54, new int[]{438,281});
		feldZuKoordinate.put(55, new int[]{385,281});
		feldZuKoordinate.put(56, new int[]{332,281});
		
		// Nullfield
		// feldZuKoordinate.put(-100, new int[]{-50,-50});
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Hintergrund laden
		Image image = new ImageIcon(this.getClass().getResource(
				"pachisi-4er-blanco.jpg")).getImage();
//		Image image = new ImageIcon("E:/Installationen/Google Drive/Programmieren/WorkSpace/Java/MenschAergerDichNicht/src/view/pachisi-4er-blanco.jpg").getImage();
		g2d.drawImage(image, 0, 0, height, width, this);
		
		Spielfeld spielFeld = Spielfeld.getInstanz();
		if(spielFeld.getWurfAnzahl() != 0) {
			//TODO evtl verschönern
			g2d.drawString("" + spielFeld.getWurfAnzahl(), 150, 50);
		}
			
		// Spielfiguren zeichnen
		for (Figur figur : model.getSpielfiguren().values()) {
			drawSpielfigur(g, figur);
		}

		highlightSpielfiguren(g, model.getMoeglichkeiten());
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
	        		e.getX() <= currentValue[0]+40 && e.getY() <= currentValue[1] +40) {
	        	feld = (int)pair.getKey();

	        	
	        }
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    		
		Figur f = Spielfeld.getInstanz().sucheFigur(feld);
		if(f != null) {
			Spielfeld.getInstanz().setWurfAnzahl(1);
			//ServerController.getInstanz().bewegeFigur(f); //lokale Version
			ClientNetworkService.getInstance().sendeFigur(f);
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
