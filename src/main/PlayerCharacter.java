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
	public boolean touchingGround,canJump;
	public boolean gravity;
	
	public PlayerCharacter(double x, double y,String name){
		gravity=true;
		imageResource = new ImageResource("main/images/robot/", 8, 80);
		touchingGround=true;
		canJump=true;
		this.object = new GameObject(0,0,20,20,imageResource,name);
		jumpCounter = -1;
	}
	public void move(String movement) {
		if(movement=="left") {
			gravity=true;
			//object.move(-3,0);
			if(object.xVelocity>-3)
				object.xVelocity=-3;
		}
		if(movement=="right") {
			gravity=true;
			//object.move(3,0);
			if(object.xVelocity<3)
				object.xVelocity=3;
		}
		if(movement=="up") {
			gravity=true;
			//object.move(0,-4);
			if(canJump) {
				if(object.yVelocity<-5);
					object.yVelocity=-5;
				canJump=false;
			}
		}
		if(movement=="down") {
			//object.move(0,4);
			//object.yVelocity+=2;
		}
	}
	public void friction(String movement) {
		if(movement=="left") {
			//object.move(-3,0);
			object.xVelocity=0;
		}
		if(movement=="right") {
			//object.move(3,0);
			object.xVelocity=0;
		}
		if(movement=="up") {

		}
		if(movement=="down") {
		}
	}
	public GameObject getGameObject() {
		return object;
	}
}
	
	
	
