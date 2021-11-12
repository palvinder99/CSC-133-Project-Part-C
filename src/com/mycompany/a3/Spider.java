package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * Spider class extends moveable
 * Responsible for data related to spider
 * @author PalvinderSingh
 *
 */
public class Spider extends Moveable {
	//Constructor method of Spider
		public Spider(int size, int color, Point location, int heading, int speed) {
			super(size, color, location, heading, speed);
			
		}
		
		//Empty setter method of color
		public void setColor(int color){}
		
		//To String method for printing stats of Spider Object
		public String toString() {
			return "Spider: loc= " + Math.round(getLocation().getX()*10.0)/10.0 + ", " + Math.round(getLocation().getY()*10.0)/10.0 + " color= [" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "] " + "size = " + getSize() + " heading= " + getHeading() + " speed= " + getSpeed();	
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
			g.setColor(getColor());
			g.drawPolygon(x,y,nPoints);
		}
		
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

		
}
