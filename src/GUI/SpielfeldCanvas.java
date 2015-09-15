package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpielfeldCanvas extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int height = 600;
	private static final int width = 600;
	
	public void paint(Graphics g) {
		Image image = Toolkit.getDefaultToolkit().getImage("J:/pachisi-4er-blanco.jpg");
		g.drawImage(image, 0, 0,height,width, this);
		g.finalize();
//		g.drawOval(10, 10, 30, 30);
		fillHomeFieldsP1(g);
		fillHomeFieldsP2(g);
	}
	
	public void fillHomeFieldsP1(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(13, 13, 40, 40);
		g.fillOval(13, 66, 40, 40);
		g.fillOval(66, 13, 40, 40);
		g.fillOval(66, 66, 40, 40);
	}
	
	public void fillHomeFieldsP2(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(494, 13, 40, 40);
		g.fillOval(494, 66, 40, 40);
		g.fillOval(548, 13, 40, 41);
		g.fillOval(548, 66, 40, 41);
	}
	
}
