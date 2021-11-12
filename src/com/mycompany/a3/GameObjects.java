package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Graphics;

/**
 * Game Object class is reponsible for data related to object's size,color,location
 * @author PalvinderSingh
 *
 */
public abstract class GameObjects implements IDrawable, ICollider {
	protected int size;
	private int color;
	private Point location;
	
	
	
	protected GameWorld o= new GameWorld();
//	private boolean isSelected;
	

	//Contructor of GameObjects
	public GameObjects(int size, int color, Point location) {
		this.size=size;
		this.color=color;
		this.location=location;
	}

	public void setGameWorld(GameWorld o) {
		this.o=o;
	}
	
	//getter method for location
	public Point getLocation() {
		return location;
	}
	
	//Setter method for color
	public void setColor(int color) {
		this.color = color;
	}
	
	//setter method for location
	public void setLocation(Point location) {
		this.location = location;
	}

	//getter method color
	public int getColor() {
		return color;
	}

	//getter method of size
	public int getSize() {
		return size;
	}

//	public abstract void draw(Graphics g,Point pCmpRelPrnt);
	
	//To string method 
	public String toString() {
		return "Location:" + getLocation().getX() + ", " + getLocation().getY();
	}
	
	@Override
	public boolean collidesWith(ICollider otherObject) {
		if(getRightCorner()<= otherObject.getLeftCorner() || getLeftCorner() >= otherObject.getRightCorner()) {
			return false;
		}
		
		if(otherObject.getTopCorner() <=getBottomCorner() || getTopCorner() <=otherObject.getBottomCorner()) {
			return false;
		}
		return true;
	}
	
	/*
	 
	public void handleCollision(ICollider otherObject) {
		
		int i,j=0;
		boolean exist = false;
			if(pastCollisions.size()==0) {
				for(i=0 ;i<newCollisions.size();i++) {
					if (newCollisions.elementAt(i) instanceof FoodStations) {
						int num=((FoodStations) newCollisions.elementAt(i)).getCapacity();
						o.foodCollision(num);
						((GameObjects) newCollisions.elementAt(i)).setColor(ColorUtil.rgb(17,115,17));
					}
				}
			} else {
				for (i=0;i<newCollisions.size();i++) {
					for(j=0; j<pastCollisions.size();j++) {
						if((( pastCollisions.elementAt(j)).equals(newCollisions.elementAt(i)))) {
							exist=true;
							//pastCollisions.remove(j);
						}
					}
					if(!exist) {
						int num=((FoodStations) newCollisions.elementAt(i)).getCapacity();
						o.foodCollision(num);
						((GameObjects) newCollisions.elementAt(i)).setColor(ColorUtil.rgb(17,115,17));
						}
					}
				exist=false;
				}
			}
		
	*/		 
	
	
	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof Spider) {
			o.antCollision();
			//ant.setHealthLevel();
		} else if(otherObject instanceof FoodStations) {
			//((FoodStations) otherObject).setColor(ColorUtil.rgb(17,115,17));
			int value= ((FoodStations) otherObject).getCapacity();	
			//ant.settotalLevel(getFoodLevel()+ value);
			o.foodCollision(value);
			((FoodStations) otherObject).setCapacity(0);
			((FoodStations) otherObject).setColor(ColorUtil.rgb(24,107, 11));
		} else if(otherObject instanceof Flags) {
			int seqNum= ((Flags) otherObject).getSequenceNumber();
			o.flagReaching(seqNum);
		}
		
		
	}
}
