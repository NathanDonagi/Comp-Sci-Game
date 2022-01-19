package main;
// Class: Sprite
// Written by: Mr. Swope
// Date: 1/27/2020
// Description: This class implements an Item.  This Item will be drawn onto a graphics panel. 
// 
// If you modify this class you should add comments that describe when and how you modified the class.  

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Projectile {

	// movement variables
	protected GameObject object;
	protected ImageResource imageResource;
	protected double[]movementCoords = new double[2];
	protected double speed;
	
	public Projectile(double xStart, double yStart, double xEnd, double yEnd, double speed, String name){
		this.object = new GameObject(xStart,yStart,20,20,"images/", new String[]{"dirt"}, new int[]{1},new int[]{1},1, name);
		movementCoords = new double[]{xEnd,yEnd};
		this.speed=speed;
	}
	public boolean updatePosition() {
		double magnitude = Math.sqrt((movementCoords[0]-object.x)*(movementCoords[0]-object.x)+(movementCoords[1]-object.y)*(movementCoords[1]-object.y));
		if(magnitude>2) {
			object.x+=speed*(movementCoords[0]-object.x)/(magnitude);
			object.y+=speed*(movementCoords[1]-object.y)/magnitude;
			return true;
		}else {
			return false;
		}
	}
	public GameObject getGameObject() {
		return object;
	}
}
	
	
	
