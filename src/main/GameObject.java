package main;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;

public class GameObject {
	protected String name;
	protected Hitbox hitbox;
	public double x, y;
	private ImageResource image;
	public double xVelocity;
	public double yVelocity;
	
	public GameObject(double x, double y, ImageResource image, double[] coords,String name) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.xVelocity=0;
		this.yVelocity=0;
		this.image=image;
		this.hitbox= new Hitbox(coords);
	}
	
	public GameObject(double x, double y,String name) {
		this.name=name;
		this.hitbox= new Hitbox(new double[]{0,0,20,20});
		this.x=x;
		this.y=y;
	}

	public boolean checkCollision(GameObject otherObject) {
		double[] collision = hitbox.calcCollision(this,otherObject);
		System.out.println(Arrays.toString(collision));
		if(collision[0]!=0 && collision[1]!=0) {
			this.collide(collision,true);
			otherObject.collide(collision,false);
			return true;
		}
		return false;
	}
	
	//indicator is 1 if this is the primary collision object, -1 if its the secondary object
	public void collide(double[] collision, boolean indicator) {
		if(indicator) {
			this.x-=collision[0];
			this.y-=collision[1];
		}
	}
	public void updateVelocity(double x, double y) {
		xVelocity+=x;
		yVelocity+=y;
	}
	
	public Hitbox getHitBox() {
		return hitbox;
	}

	public void move(int x, int y) {
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

	public void draw(Component c, Graphics g) {
		g.fillRect((int)x, (int)y, 20, 20);
	}

}
