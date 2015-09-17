package GUI;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class SpielfeldView extends JFrame implements Observer{
	private ViewModel model;
	private Canvas canvas;
	
	public SpielfeldView  (int h, int v) {
		super("Mensch aerger dich nicht");
		this.model = model;
		Panel panel = new Panel();
		add("North", panel);
		canvas = new SpielfeldCanvas();
		canvas.setSize(100, 100);
		getContentPane().add(canvas);
//		add("Center", canvas);
		setSize(800, 800);
		setLocation(h, v);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//		model.addObserver(this);

		
	}
	
	public void paint(Graphics g) {
//		model.setPosition();
		canvas.repaint();
		super.paint(g);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
