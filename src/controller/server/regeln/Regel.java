package controller.server.regeln;

public abstract class Regel implements IRegel {
	/**
	 * Die naechste Regel, die angewendet werden soll
	 */
	protected IRegel nextRegel;
	/**
	 * Der aktuelle Zustand des Spielfeldes
	 */
	protected Zustand zustand;
	
	/**
	 * 
	 * @param regel 	Die naechste Regel, die angewendet werden soll
	 * @param zustand 	Der aktuelle Zustand des Spielfeldes
	 */
	public void setNextRegel(IRegel regel, Zustand zustand) {
		this.nextRegel = regel;
		this.zustand = zustand;
	}
}
