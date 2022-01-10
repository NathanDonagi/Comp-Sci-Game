package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Scene {
	private Background background;
	private ArrayList<GameObject> blocks;
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	private PlayerCharacter player;
	private double cameraX,cameraY;
	private boolean previous;
	
	public Scene(ArrayList<GameObject> blocks, ArrayList<Enemy> enemies, String backgroundPath) {
		this.background = new Background(0,backgroundPath);
		this.blocks = blocks;
		this.enemies=enemies;
		this.previous=true;
		this.player = new PlayerCharacter(100,100,"player");
		this.projectiles=new ArrayList <Projectile>();
		cameraX=player.getGameObject().x-600;
		cameraY=0;
	}

	public PlayerCharacter getPlayer() {
		return this.player;
	}
	
	public Background getBackground() {
		return background;
	} 
	public void addEntity(int x, int y,String name) {
		blocks.add(new GameObject(x,y,name));
	}
	
	public void draw(Component c, Graphics g) {
		background.draw(c,g);
		for(GameObject e: blocks) {
			e.draw(cameraX,cameraY,c,g);
		}
		for(Enemy e: enemies) {
			e.getGameObject().draw(cameraX,cameraY,c,g);
		}
		for(Projectile e: projectiles) {
			e.getGameObject().draw(cameraX,cameraY,c,g);
		}
		player.getGameObject().draw(cameraX,cameraY,c,g);
		//player.getGameObject().draw(cameraX,cameraY,c,g);
		
	}
	public void updatePositions(ArrayList<String>listOflastPresses,Game game) {
		//System.out.println(player.gravity);
		//System.out.println(player.getGameObject().x + ", "+ player.getGameObject().y+ ", "+ player.getGameObject().xVelocity+", "+player.getGameObject().yVelocity);
		for(Enemy e: enemies) {
			e.updatePosition();
			//System.out.println(e.getGameObject().x);
		}
		
		int pr = 0;
		while(pr< projectiles.size()) {
			if(projectiles.get(pr).updatePosition()) {
				pr+=1;
			}else {
				projectiles.remove(pr);
			}
		}
			
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
				player.getGameObject().xVelocity+=10;
				player.canJump=true;
				player.gravity=false;
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="left" &&  listOflastPresses.get(listOflastPresses.size()-3)=="right"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="left") {
				player.getGameObject().xVelocity-=10;
				player.canJump=true;
				player.gravity=false;
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
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="right" &&  listOflastPresses.get(listOflastPresses.size()-3)=="up"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="down") {
				projectiles.add(new Projectile(player.getGameObject().x, player.getGameObject().y, player.getGameObject().x+300 ,player.getGameObject().y,5,"projectile"));
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="left" &&  listOflastPresses.get(listOflastPresses.size()-3)=="up"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="down") {
				projectiles.add(new Projectile(player.getGameObject().x, player.getGameObject().y, player.getGameObject().x-300 ,player.getGameObject().y,5,"projectile"));
				listOflastPresses.clear();
			}
		}
		
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="up" &&  listOflastPresses.get(listOflastPresses.size()-3)=="left"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="left") {
				projectiles.add(new Projectile(player.getGameObject().x, player.getGameObject().y-1000, player.getGameObject().x ,player.getGameObject().y+200,10,"projectile"));
				listOflastPresses.clear();
			}
		}
		if(listOflastPresses.size()>3) {
			if(listOflastPresses.get(listOflastPresses.size()-1)=="down" && listOflastPresses.get(listOflastPresses.size()-2)=="up" &&  listOflastPresses.get(listOflastPresses.size()-3)=="right"  &&  listOflastPresses.get(listOflastPresses.size()-4)=="right") {
				projectiles.add(new Projectile(player.getGameObject().x, player.getGameObject().y-1000, player.getGameObject().x ,player.getGameObject().y+200,10,"projectile"));
				listOflastPresses.clear();
			}
		}


		player.getGameObject().x += player.getGameObject().xVelocity;
		for(GameObject e: blocks){
			if(e.name!="player") {
				if(player.getGameObject().collide(e)[0]!=0)
					player.gravity=true;
			player.getGameObject().x-=player.getGameObject().collide(e)[0]+.01*Math.signum(player.getGameObject().collide(e)[0]);
		}
		}
		
		if(player.gravity) {
			player.getGameObject().y += player.getGameObject().yVelocity;
			for(GameObject e: blocks){
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
		}
		
		for(Enemy e: enemies) {
			if(player.getGameObject().collide(e.getGameObject())[1]<0) {
				this.player=new PlayerCharacter(100,100,"player");
				game.player=this.player;
			}
		}
		
		for(Projectile p: projectiles) {
			int er=0;
			while(er<enemies.size()) {
				if(enemies.get(er).getGameObject().collide(p.getGameObject())[1]<0) {
					enemies.remove(er);
					break;
				}else {
					er+=1;
				}
			}

		}
		
		
		
		if(player.getGameObject().y<300) {
			if(player.getGameObject().yVelocity<20 && !player.touchingGround && player.gravity) {
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
		
		if(player.getGameObject().x>1800 && player.getGameObject().y>250) {
			game.setScene(game.scene2);
		}
	}
}
