package main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GameObject {
	protected String name;
	protected Hitbox hitbox;
	public double x,y,width,height;
	public String currentAnimation;
	private Hashtable<String, ArrayList<ImageIcon>> animations;
	private Hashtable<String, Integer> times;
	private int SCALE;
	protected double frame;
	public double xVelocity;
	public double yVelocity;
	public boolean flipped;
	
	public GameObject(double x, double y, double width, double height, String imagePath, String[] images, int[] lengths, int[] times, int scale,  String name) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.xVelocity=0;
		this.yVelocity=0;
		this.animations=new Hashtable<>();
		this.times = new Hashtable<>();
		this.frame=0;
		this.SCALE= scale;
		for(int i=0; i<images.length; i++) {
			ArrayList<ImageIcon> imagesToStore = new ArrayList<>();
			for(int j=1; j<=lengths[i];j++) {
				ClassLoader cldr = this.getClass().getClassLoader();
				String newImagePath; 
				newImagePath = "main/" +imagePath + images[i] + (j) + ".png";
				URL imageURL = cldr.getResource(newImagePath);	
				//System.out.println(newImagePath);
				ImageIcon image = new ImageIcon(newImagePath);	
				//System.out.println(image);
				image = new ImageIcon(imageURL);
				Image scaled = image.getImage().getScaledInstance(image.getIconWidth() / SCALE, 
						image.getIconHeight() / SCALE, image.getImage().SCALE_SMOOTH);
				image = new ImageIcon(scaled);
				imagesToStore.add(image);
			}
			animations.put(images[i], imagesToStore);
			this.times.put(images[i],times[i]);
		}
		
		
		this.currentAnimation=images[0];
		this.width=width;
		this.height=height;
		this.hitbox= new Hitbox(new double[]{0,0,width,height});
		flipped=false;
	}
	
	public GameObject(double x, double y, double width, double height, String name) {
		this.name=name;
		this.hitbox= new Hitbox(new double[]{0,0,width,height});
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.animations=null;
	}

	
	public GameObject(double x, double y, String name) {
		this(x,y,25,25,"images/", new String[]{"grass"}, new int[]{1},new int[]{1},1, name);
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
		if(this.animations!=null) {
			if(flipped) {
				frame+=.05*times.get("flipped" + currentAnimation);
				frame%=animations.get("flipped" + currentAnimation).size();
				animations.get("flipped" + currentAnimation).get((int)frame).paintIcon(c, g, (int)relativeX, (int)relativeY);
			}else {
				frame+=.05*times.get(currentAnimation);
				frame%=animations.get(currentAnimation).size();
				animations.get(currentAnimation).get((int)frame).paintIcon(c, g, (int)relativeX, (int)relativeY);
			}
			//g.drawRect((int)relativeX, (int)relativeY, (int)width, (int)height);
		}else {
			g.setColor(new Color(53,26,22));
			g.fillRect((int)relativeX, (int)relativeY, (int)width, (int)height);
		}
	}

}
