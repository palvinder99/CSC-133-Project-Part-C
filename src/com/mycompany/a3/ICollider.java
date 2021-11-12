package com.mycompany.a3;
/**
 * ICollider Interface used to collision detection and accurate responses to collisions
 * @author PalvinderSingh
 *
 */
public interface ICollider {
	//applies appropriate detection algorithm
	public boolean collidesWith(ICollider otherObject);
	
	//apply appropriate response algorithm
	public void handleCollision(ICollider otherObject);
	//Corners for rectangle that help with detecting collisions
	public int getRightCorner();
	public int getLeftCorner();
	public int getTopCorner();
	public int getBottomCorner();
	
	
}
