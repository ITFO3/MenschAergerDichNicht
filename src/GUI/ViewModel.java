package GUI;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;



public class ViewModel extends java.util.Observable{
	
	public void setPosition(String figureID, int fieldsToMove) {
		
		
	}
	
	public ArrayList<Object> field;
	public List home;
	
	public void initField() {
		field = new ArrayList<>();
	}
	
}
