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
	private double cameraX,cameraY;
	
	
	public Scene(ArrayList<GameObject> entities, String backgroundPath) {
		this.background = new Background(0,backgroundPath);
		this.entities = entities;
		//addEntity(5,100);
		addEntity(500,500,"block1");
		addEntity(200,20,"block2");
		addEntity(5,200,"block3");
		addEntity(100,22,"block4");
		addEntity(player = new PlayerCharacter(100,100,"player"));
		cameraX=0;
		cameraY=0;
		
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
			e.draw(cameraX,cameraY,c,g);
		}
		//player.getGameObject().draw(cameraX,cameraY,c,g);
		
	}
	public void updatePositions() {
		for(int i =0; i<entities.size(); i++) {
			entities.get(i).move(entities.get(i).xVelocity,entities.get(i).yVelocity);
		}
		
		if(player.getGameObject().y<600) {
			player.getGameObject().yVelocity+=.1;
		}else {
			player.getGameObject().y=600;
		}
		cameraY=player.getGameObject().y/2;
		cameraX=player.getGameObject().x/2;
	}
}
