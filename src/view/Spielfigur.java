package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import model.Figur;

public class Spielfigur extends JComponent implements MouseListener{

	private Figur f;
	private Ellipse2D oval;
	public Spielfigur(Figur f, int x, int y) {
		
		this.f = f;
		oval = new Ellipse2D.Double(x, y, 40, 40);
	}
	
	public Ellipse2D getGrafischeSpielfigur() {
		return oval;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub		
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