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
	private boolean previous;
	
	public Scene(ArrayList<GameObject> entities, String backgroundPath) {
		this.background = new Background(0,backgroundPath);
		this.entities = entities;
		this.previous=true;
		//addEntity(5,100);
		addEntity(-2000,300,100000,40,"block");
		addEntity(50,200,"block");
		addEntity(100,125,"block");
		addEntity(200,100,"block");
		addEntity(500,75,"block");
		addEntity(550,25,"block");
		addEntity(850,50,"block");
		addEntity(950,-25,150,40,"block");
		addEntity(1150,-275,100,40, "block");
		addEntity(1750,-275,100,40, "block");
		addEntity(1810,-275,40,700, "block");
		addEntity(-1400,-275,40,700, "block");
		addEntity(2025,-11070,40,11500, "block");
		addEntity(player = new PlayerCharacter(100,100,"player"));
		cameraX=player.getGameObject().x-600;
		cameraY=0;
		
	}
	
	private void addEntity(int i, int j, int k, int l, String string) {
		entities.add(new GameObject(i,j,k,l,string));
		
	}

	private void addEntity(PlayerCharacter playerCharacter) {
		entities.add(playerCharacter.getGameObject());
		
	}

	public PlayerCharacter getPlayer() {
		return this.player;
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
	public void updatePositions(ArrayList<String>listOflastPresses) {
		//System.out.println(player.getGameObject().x + ", "+ player.getGameObject().y+ ", "+ player.getGameObject().xVelocity+", "+player.getGameObject().yVelocity);
		player.touchingGround=false;
		//System.out.println(listOflastPresses+"");
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="up" &&  listOflastPresses.get(listOflastPresses.size()-3)=="right"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="left") {
				player.getGameObject().yVelocity=-8;
				player.canJump=true;
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="up" &&  listOflastPresses.get(listOflastPresses.size()-3)=="left"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="right") {
				player.getGameObject().yVelocity=-8;
				player.canJump=true;
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="right" &&  listOflastPresses.get(listOflastPresses.size()-3)=="left"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="right") {
				player.getGameObject().xVelocity+=7;
				player.canJump=true;
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="left" &&  listOflastPresses.get(listOflastPresses.size()-3)=="right"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="left") {
				player.getGameObject().xVelocity-=7;
				player.canJump=true;
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>2) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="up" &&  listOflastPresses.get(listOflastPresses.size()-3)=="up") {
				player.touchingGround=false;
				player.getGameObject().y-=1500;
				listOflastPresses.clear();
			}
		}


		player.getGameObject().x += player.getGameObject().xVelocity;
		for(GameObject e: entities){
			if(e.name!="player") {
			player.getGameObject().x-=player.getGameObject().collide(e)[0]+.01*Math.signum(player.getGameObject().collide(e)[0]);
		}
		}
		
		player.getGameObject().y += player.getGameObject().yVelocity;
		for(GameObject e: entities){
			if(e.name!="player") {
			if(player.getGameObject().collide(e)[1]>0){
				player.touchingGround=true;
			}else if(player.getGameObject().collide(e)[1]<0){
				player.getGameObject().yVelocity=0;
			}
				//System.out.println(e.name + player.getGameObject().collide(e)[1]+1*Math.signum(player.getGameObject().collide(e)[1]));
				player.getGameObject().y-=player.getGameObject().collide(e)[1]+.01*Math.signum(player.getGameObject().collide(e)[1]);
		}
		}
		
		
		if(player.getGameObject().y<300) {
			if(player.getGameObject().yVelocity<20 && !player.touchingGround) {
				player.getGameObject().yVelocity+=.1;
			}
		}else {
			player.getGameObject().y=300;
			player.getGameObject().yVelocity=0;
			player.touchingGround=true;
		}
		//cameraY=player.getGameObject().y;
		if(player.getGameObject().x<1300 && player.getGameObject().x>-800) {
			 cameraX=player.getGameObject().x-600;
		}
		if(player.getGameObject().y<200) {
			 cameraY=player.getGameObject().y-200;
		}
		if(player.touchingGround) {
			player.canJump=true;
		}
		if(previous && !player.touchingGround && player.getGameObject().yVelocity>-1) {
			player.getGameObject().yVelocity=-1;
		}
		previous=player.touchingGround;
	}
}
