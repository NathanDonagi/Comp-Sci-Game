package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Scene {
	private Background background;
	private ArrayList<GameObject> entities;
	private PlayerCharacter player;
	
	
	public Scene(ArrayList<GameObject> entities, String backgroundPath) {
		this.background = new Background(0,backgroundPath);
		this.entities = entities;
		//addEntity(5,100);
		//addEntity(50,100);
		//addEntity(200,100);
		//addEntity(5,200);
		addEntity(100,22,"block");
		addEntity(player = new PlayerCharacter(100,100,"player"));
		
	}
	
	private void addEntity(PlayerCharacter playerCharacter) {
		entities.add(playerCharacter.getGameObject());
		
	}

	public PlayerCharacter getPlayer() {
		return this.player;
	}
	
	public void checkCollisions() {
		for(int i =0; i<entities.size(); i++) {
			for(int j=(i+1); j<entities.size(); j++) {
				entities.get(i).checkCollision(entities.get(j));
			}
		}
	}
	public Background getBackground() {
		return background;
	} 
	public void addEntity(int x, int y,String name) {
		entities.add(new GameObject(x,y,name));
	}
	
	public void draw(Component c, Graphics g) {
		background.draw(c,g);
		for(GameObject e: entities) {
			e.draw(c,g);
		}
		player.getGameObject().draw(c,g);
		
	}
	public void updatePositions() {
		for(int i =0; i<entities.size(); i++) {
			for(int j=(i+1); j<entities.size(); j++) {
				entities.get(i).y+=entities.get(i).yVelocity;
				entities.get(i).x+=entities.get(i).xVelocity;
			}
		}
		
		if(player.getGameObject().y<200) {
			System.out.println(player.getGameObject().yVelocity);
			player.getGameObject().yVelocity+=.1;
		}else {
			System.out.println(false);
			player.getGameObject().y=200;
		}
		;
	}
}
