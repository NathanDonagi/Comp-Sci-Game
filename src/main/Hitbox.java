package main;

import java.util.Arrays;

public class Hitbox {
	private double[] coordinates;
	
	public Hitbox(double[] coordinates){
		this.coordinates=coordinates;
	}
	
	/*
	The input is in the format x1,y1,x2,y2
	
	the return is the x difference then the y difference, then 1/0 if the object 
	object or the other object is on the right then the 1/0 if the object or the other 
	object is on top
	*/
	
	public double[] getCoords() {
		return coordinates;
	}

	public double[] calcCollision(GameObject gameObject, GameObject otherObject) {
		
		double[] otherCoordinates = otherObject.getHitBox().getCoords();
		double[] otherCoords = {otherCoordinates[0],otherCoordinates[1],otherCoordinates[2],otherCoordinates[3]};
		double[] coords = {coordinates[0],coordinates[1],coordinates[2],coordinates[3]};
		
		coords[0]+=gameObject.x;
		coords[1]+=gameObject.y;
		coords[2]+=gameObject.x;
		coords[3]+=gameObject.y;
		
		otherCoords[0]+=otherObject.x;
		otherCoords[1]+=otherObject.y;
		otherCoords[2]+=otherObject.x;
		otherCoords[3]+=otherObject.y;
		
		//System.out.println(Arrays.toString(coords) + Arrays.toString(otherCoords));
		
		if (coords[2] >= otherCoords[0] && coords[2] <= otherCoords[2]) {
		      if (coords[3] >= otherCoords[1] && coords[3] <= otherCoords[3]) {
		        return new double[]{coords[2]-otherCoords[0],coords[3]-otherCoords[1]};
		      }
		      if (coords[3] >= otherCoords[3] && coords[1] <= otherCoords[1]) {
		        return new double[]{coords[2]-otherCoords[0],coords[3]-otherCoords[1]};
		      }
		      if (coords[1] >= otherCoords[1] && coords[1] <= otherCoords[3]) {
		        return new double[]{coords[2]-otherCoords[0],coords[1]-otherCoords[3]};
		      }
		    }
		    if (coords[0] >= otherCoords[0] && coords[0] <= otherCoords[2]) {
		      if (coords[3] >= otherCoords[1] && coords[3] <= otherCoords[3]) {
		        return new double[]{coords[0]-otherCoords[2],coords[3]-otherCoords[1]};
		      }
		      if (coords[3] >= otherCoords[3] && coords[1] <= otherCoords[1]) {
		        return new double[]{coords[0]-otherCoords[2],coords[3]-otherCoords[1]};
		      }
		      if (coords[1] >= otherCoords[1] && coords[1] <= otherCoords[3]) {
		        return new double[]{coords[0]-otherCoords[2],coords[1]-otherCoords[3]};
		      }
		    }
		    if (coords[0] <= otherCoords[0] && coords[2] >= otherCoords[2]) {
		      if (coords[3] >= otherCoords[1] && coords[3] <= otherCoords[3]) {
		        return new double[]{coords[0]-otherCoords[2],coords[3]-otherCoords[1]};
		      }
		      if (coords[3] >= otherCoords[3] && coords[1] <= otherCoords[1]) {
		        return new double[]{coords[0]-otherCoords[2],coords[3]-otherCoords[1]};
		      }
		      if (coords[1] >= otherCoords[1] && coords[1] <= otherCoords[3]) {
		        return new double[]{coords[0]-otherCoords[2],coords[1]-otherCoords[3]};
		      }
		    }
		

		return new double[]{0,0};
	}
}
