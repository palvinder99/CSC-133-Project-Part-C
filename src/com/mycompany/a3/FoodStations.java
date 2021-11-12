package com.mycompany.a3;



import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
/**
 * Food Stations class extends fixed and is responsible for information related to FoodStatios
 * @author PalvinderSingh
 *
 */
public class FoodStations extends Fixed {
	private int capacity;
	private int iShapeX= (int) (getLocation().getX()+getSize()/2);
	private  int iShapeY=(int) (getLocation().getY()+getSize()/2);

	//Constructor of food station with certain partent fields and capacity for its food capacity
	public FoodStations(int size, int color, Point location, int capacity) {
		super(size, color, location);
		this.capacity = capacity;
	}

	//Setter method for capacity
	public void setCapacity(int finalCapacity) {
		capacity= finalCapacity;
		
	}

	//getter method for capacity
	public int getCapacity() {
		return capacity;
	}
	
	//To string method that prints all stats of Food Station objects
	public String toString() {
		return "FoodStation: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ", " + Math.round(getLocation().getY()*10.0)/10.0 + " color= [" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "] " + "size = " + getSize() + " Capacity= " + getCapacity();
		
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		float xPoint= (getLocation().getX() + pCmpRelPrnt.getX());
		float yPoint= (getLocation().getY() + pCmpRelPrnt.getY());
		if(isSelected()) {
			drawSelected( g, pCmpRelPrnt);
		}
		g.setColor(getColor());
		g.fillRect((int)(getLocation().getX()+ pCmpRelPrnt.getX()),(int)(getLocation().getY()+ pCmpRelPrnt.getY()), getSize() , getSize());
		g.setColor(ColorUtil.rgb(0, 100, 0));
		g.drawString(Integer.toString(getCapacity()),(int)(xPoint+getSize()/4),(int)(yPoint+getSize()/4));
		
	}
	
	//Corners of the rectangle for collision detection
	public int getRightCorner() {
		return (int) (getLocation().getX()+getSize()/2);
	}
	 
	public int getLeftCorner() {
		return (int) (getLocation().getX()-getSize()/2);
	}
	
	public int getTopCorner() {
		return (int) (getLocation().getY()+getSize()/2);
	}
	
	public int getBottomCorner() {
		return (int) (getLocation().getY()-getSize()/2);
	}

	//Draw selected used for selectable objects, but was not successfully implemented
	@Override
	public void drawSelected(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int xLoc= (int) (pCmpRelPrnt.getX()+ iShapeX);
		int yLoc= (int) (pCmpRelPrnt.getY()+ iShapeY);
		if(isSelected()) {
			g.fillRect(xLoc, yLoc,getSize(),getSize());
		} else {
			g.drawRect(xLoc,yLoc,getSize(),getSize());
		}
	}
	
	//Draw selected used for selectable objects, but was not successfully implemented
	public void drawSelected(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int xLoc= (int) (pCmpRelPrnt.getX()+ getSize());
		int yLoc= (int) (pCmpRelPrnt.getY()+ getSize());
		if(isSelected()) {
			g.fillRect(xLoc, yLoc,getSize(),getSize());
		} else {
			g.drawRect(xLoc,yLoc,getSize(),getSize());
		}
	} 
	
	//contains used for selectable objects, but was not successfully implemented
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
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


}
