package com.mycompany.a3;




import com.codename1.charts.models.Point;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * Flag class extend fixed and is responsible for all information related to flag
 * @author PalvinderSingh
 *
 */
public class Flags extends Fixed {
	private int sequenceNumber;
	private int iShapeX=(int) (getLocation().getX()+getSize());
	private  int iShapeY=(int) (getLocation().getY()+getSize());
	//Constructor method for specific flag fields and extended field from GameObjects
	public Flags(int size, int color, Point location, int sequenceNumber) {
		super(size, color, location);
		this.sequenceNumber = sequenceNumber;

	}

	//Getter method for sequence number
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	//Empty body setter method of color, so it does not override the original set value for flags
	//public void setColor(int color){}
	
	//Returns the location same as the original set location of flags
	public Point getLocation() {

		return super.getLocation();
	}
	
	//To String  method that display all flag objects with its stats
	public String toString() {
		return "Flag: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ", " + Math.round(getLocation().getY()*10.0)/10.0 + " color= [" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "] " + "size = " + getSize() + " SeqNum= " + getSequenceNumber();
		
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		Point tripoint= new Point(pCmpRelPrnt.getX(),pCmpRelPrnt.getY());
		double initialPoint= 0;
		double finalPoint= 0;
		double midPointx= 0;
		double finalPointy=0;
		double initialPointy=0;
		initialPoint= (getLocation().getX()+tripoint.getX());
		finalPoint= (getLocation().getX()+tripoint.getX()+ getSize());
		midPointx= (initialPoint+finalPoint)/2;
		initialPointy=(getLocation().getY()) + tripoint.getY();
		finalPointy=initialPointy + getSize();
		int[] x= {(int) initialPoint,(int) finalPoint,(int) midPointx};
		int[] y= {(int) initialPointy,(int) initialPointy, (int) finalPointy};
		int nPoints= 3;
		if(isSelected()) {
			g.drawPolygon(x,y,nPoints);
		}
		g.setColor(getColor());
		g.fillPolygon(x,y,nPoints);
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(getSequenceNumber()),(int) midPointx-getSize()/8, (int)initialPointy+getSize()/4);
				
	}

	//Corners of rectangle of collision detection 
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
	

	//Drawselected for selectable, but wasnot able successfully implement it 
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

	//Contains for selectable, but was not able successfully implement it 
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

	@Override
	public void drawSelected(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
}
	
	
	

	

	
	

	
	
	

