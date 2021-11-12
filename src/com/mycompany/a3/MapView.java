package com.mycompany.a3;

import java.util.Observable;

import java.util.Observer;
import java.util.Vector;

import com.codename1.charts.models.Point;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

/**
 * MapView is responsible for displaying the map of the game when it is invoked
 * @author PalvinderSingh
 *
 */
public class MapView extends Container implements Observer {
	private GameWorld gw;
	private Vector<GameObjects> obj= new Vector<GameObjects>();
	//Constructor empty due to not fully implementing in GUI
	public MapView(GameWorld  newgw){
		gw=newgw;
	}
	@Override
	//Update method that calls gameworld's displayMap()
	public void update(Observable o, Object arg) {
		if (o instanceof GameWorld) {
			((GameWorld) o).displayMap();
			repaint();
			
		}		
	}

	public void paint(Graphics g) {
		super.paint(g);
		IIterator paintIterator;
		Point pCmpRelPrnt= new Point(getX(),getY());
		paintIterator = gw.getIterator();
		Object curObj;
		while(paintIterator.hasNext()) {
			curObj= paintIterator.next();
			if(curObj !=null) {
				((GameObjects) curObj).draw(g, pCmpRelPrnt);
			}
		}
	}
	
	public void pointerPressed(int x,int y) {
		x=x-getParent().getAbsoluteX();
		y=y-getParent().getAbsoluteY();
		Point pPtrRelPrnt= new Point(x,y);
		Point pCmpRelPrnt= new Point(getX(),getY());
		if(gw.getgamePaused()) {
			gw.pressed(pPtrRelPrnt,pCmpRelPrnt);
		}
		repaint();
	}

}
