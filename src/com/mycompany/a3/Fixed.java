package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;



/**
 * Abstract class Fixed that extends GameObjects
 */
public abstract class Fixed extends GameObjects implements ISelectable{
	//Constructor for fixed with extended field from GameObjects
	private boolean selected;
	
	public Fixed(int size, int color, Point location) {
		super(size, color, location);
	}

	public void setSelected(boolean b) {
		selected=b;
	}
	
	public boolean isSelected() {
		return selected;
	}
	//setter method for location
	
	public void setLocation(Point location) {
		if(selected) {
			super.setLocation(location);
		}
	}

	public abstract boolean contains(Point pPtrRelPrnt,Point pCmpRelPrnt);
	public abstract void draw(Graphics g,Point pCmpRelPrnt);
	
	
	
/*	public boolean contains(Point pPtrRelPrnt,Point pCmpRelPrnt) {
		int px= (int) pPtrRelPrnt.getX();
		int py= (int) pCmpRelPrnt.getY();
		int xLoc= (int) (pCmpRelPrnt.getX()+ iShapeX);
		int yLoc= (int) (pCmpRelPrnt.getY()+ iShapeY);
		if((px>=xLoc) && (px<=xLoc) && (py>=yLoc) && (py <=yLoc)) {
			return true;
		}  else {
			return false;
		}
	}
		
	public void draw(Graphics g,Point pCmpRelPrnt) {
		int xLoc= (int) (pCmpRelPrnt.getX()+ iShapeX);
		int yLoc= (int) (pCmpRelPrnt.getY()+ iShapeY);
		if(isSelected()) {
			g.fillRect(xLoc, yLoc, xLoc+size, yLoc+size);
		} else {
			g.drawRect(xLoc,yLoc,xLoc+size,yLoc+size);
		}
	} */
}
