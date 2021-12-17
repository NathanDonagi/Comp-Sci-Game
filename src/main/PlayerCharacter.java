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

public class PlayerCharacter {

	// movement variables
	protected GameObject object;
	

	protected int jumpCounter;
	protected boolean state;
	protected ImageResource imageResource;
	
	public PlayerCharacter(double x, double y,String name){
		
		imageResource = new ImageResource("main/images/robot/", 8, 80);
		
		this.object = new GameObject(0,0,imageResource, new double[]{0,0,20,20},name);
		jumpCounter = -1;
	}
	public void move(String movement) {
		if(movement=="left") {
			//object.move(-3,0);
			object.xVelocity=-3;
		}
		if(movement=="right") {
			//object.move(3,0);
			object.xVelocity=3;
		}
		if(movement=="up") {
			//object.move(0,-4);
			object.yVelocity=-5;
		}
		if(movement=="down") {
			object.move(0,4);
		}
		
	}
	public GameObject getGameObject() {
		return object;
	}
}
	
	
	
