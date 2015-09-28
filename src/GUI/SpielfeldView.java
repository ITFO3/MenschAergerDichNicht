package GUI;

import java.awt.Graphics;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import MenschAergerDichNicht.Figur;
import MenschAergerDichNicht.Spielfeld;

public class SpielfeldView extends JFrame implements Observer {
	
	private Spielfeld model;
	private SpielfeldCanvas canvas;
	
	public SpielfeldView  (Spielfeld model, int h, int v) {
		// Titel setzen
		super("Mensch aerger dich nicht");
		
		// Model speichern
		this.model = model;
		
		Panel panel = new Panel();
		add("North", panel);
		canvas = new SpielfeldCanvas();
		canvas.setSize(100, 100);
		getContentPane().add(canvas);
		
		setSize(800, 800);
		setLocation(h, v);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		model.addObserver(this);
	}
	
	public void paint(Graphics g) {
		canvas.repaint();
		
		for (Figur figur : model.getSpielfiguren().values()) {
			canvas.DrawSpielfigur(g, figur);
		}
		
		super.paint(g);
	}

	@Override
	public void update(Observable arg0, Object object) {
		repaint();
	}
}
