package GUI;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class SpielfeldCanvas extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int height = 600;
	private static final int width = 600;
	
	public void paint(Graphics g) {
		Image image = Toolkit.getDefaultToolkit().getImage("J:/pachisi-4er-blanco.jpg");
		g.drawImage(image, 0, 0, this);
	}
	
}
