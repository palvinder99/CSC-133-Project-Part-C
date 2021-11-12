package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
/**
 * IDrawable interface used to draw different shapes of objects
 * @author PalvinderSingh
 *
 */
public interface IDrawable {
	public void draw(Graphics g,Point pCmpRelPrnt);
}
