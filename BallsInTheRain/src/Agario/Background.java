package Agario;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Background {
	
	private int x;
	private int y;
	private int speed;
	
	public void paint(Graphics2D g){
		g.fillRect(this.x,this.y,1,3);
	}
	
	public Background(int x, int y, int speed){
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	
	public void move(){
		this.y+=this.speed;
	}
	
	public int getY(){
		return this.y;
	}
	
}
	
	