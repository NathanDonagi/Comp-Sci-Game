package main;

import java.io.IOException;

// Class: PlayerCharacter
// Written by: Woodland Wanderer Dev Team
// Date: 1/20/2022
// Description: This class contains the implementation for a player.
public class PlayerCharacter {

	// instance variables
	protected GameObject object;
	protected int jumpCounter;
	protected boolean state;
	public boolean touchingGround,canJump;
	public boolean gravity;

	// packed constructor
	public PlayerCharacter(double x, double y, String name) throws IOException{
		gravity = true;
		touchingGround = true;
		canJump = true;
		this.object = new GameObject(x, y - 100, 52, 70, "images/", new String[]{"Jump","Walk", "still","flippedJump","flippedWalk", "flippedstill"}, new int[]{5, 4, 1, 5, 4, 1}, new int[]{1, 1, 1, 1, 1, 1}, 12, name);
		jumpCounter = -1;
	}

	// updates movement
	public void move(String movement) {
		if(movement == "left") {
			gravity = true;
			object.flipped = true;
			if(object.xVelocity > -3)
				object.xVelocity = -3;
		}
		if(movement == "right") {
			gravity = true;
			object.flipped = false;
			if(object.xVelocity < 3)
				object.xVelocity = 3;
		}
		if(movement == "up") {
			gravity = true;
			if(canJump) {
				if(object.yVelocity < -5);
				object.yVelocity = -5;
				canJump = false;
			}
		}
		if(movement == "down") {

		}
	}

	// calculates friction
	public void friction(String movement) {
		if(movement == "left") {
			object.xVelocity = 0;
		}
		if(movement == "right") {
			object.xVelocity = 0;
		}
		if(movement == "up") {

		}
		if(movement == "down") {

		}
	}

	// getter
	public GameObject getGameObject() {
		return object;
	}
}