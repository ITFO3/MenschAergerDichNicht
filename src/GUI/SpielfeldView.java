package GUI;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class SpielfeldView extends Frame implements Observer{
	private ViewModel model;
	private Canvas canvas;
	
	public SpielfeldView  (int h, int v) {
		super("Mensch aergere dich nicht");
		this.model = model;
		Panel panel = new Panel();
		add("North", panel);
		canvas = new SpielfeldCanvas();
		canvas.setSize(500, 500);
		add("Center", canvas);
		setSize(600, 600);
		setLocation(h, v);
		setVisible(true);
//		model.addObserver(this);
//		panel.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
//		model.setPosition();
		canvas.paint(g);
		super.paint(g);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
