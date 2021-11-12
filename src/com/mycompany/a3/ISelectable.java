package com.mycompany.a3;

import com.codename1.ui.Graphics;

import com.codename1.ui.geom.Point;
/**
 * ISelectable used for selectable objects and moving their position, however I was not successfully at having
 * them be fully implements in the program
 * @author PalvinderSingh
 *
 */
public interface ISelectable {
	//Way to mark an object as "selecte" or not
	public void setSelected(boolean b);

	//a way to test whether an object is selected
	public boolean isSelected();
	
	// a way to determine if a pointer is “in” an object
	// pPtrRelPrnt is pointer position relative to the parent origin
	// pCmpRelPrnt is the component position relative to the parent origin
	public boolean contains(com.codename1.charts.models.Point pPtrRelPrnt, com.codename1.charts.models.Point pCmpRelPrnt);
	
	//a Way to "Draw" the object that knows about drawing 
	//different ways depending on "isSelected"
	public void drawSelected(Graphics g, Point pCmpRelPrnt);
}
