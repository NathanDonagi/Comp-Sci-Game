package main;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;

public class GameObject {
	protected String name;
	protected Hitbox hitbox;
	public double x,y,width,height;
	private ImageResource image;
	public double xVelocity;
	public double yVelocity;
	
	public GameObject(double x, double y, double width, double height, ImageResource image, String name) {
		this(x,y,width,height,name);
	}
	
	public GameObject(double x, double y, double width, double height, String name) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.xVelocity=0;
		this.yVelocity=0;
		this.image=image;
		this.width=width;
		this.height=height;
		this.hitbox= new Hitbox(new double[]{0,0,width,height});
	}
	
	public GameObject(double x, double y, String name) {
		this.name=name;
		this.hitbox= new Hitbox(new double[]{0,0,40,40});
		this.x=x;
		this.y=y;
		this.width=40;
		this.height=40;
	}

	public double[] collide(GameObject otherObject) {
		return hitbox.calcCollision(this,otherObject);
	}
	
	public void updateVelocity(double x, double y) {
		xVelocity+=x;
		yVelocity+=y;
	}
	
	public Hitbox getHitBox() {
		return hitbox;
	}

	public void move(double x, double y) {
		this.x+=x;
		this.y+=y;
	}
	
	public void setPos(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public void glide(int x, int y) {
		//later problem
	}

	public void draw(double cameraX, double cameraY,Component c, Graphics g) {
		int relativeX=(int)x-(int)cameraX;
		int relativeY=(int)y-(int)cameraY;
		g.fillRect((int)relativeX, (int)relativeY, (int)width, (int)height);
	}

}
