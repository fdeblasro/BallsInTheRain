package Agario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Monedas {
	private int x;
	private int y;
	private int speed;
	private Game game;
	public Rectangle rect;
	private Rectangle ballRect;
	
	public Monedas(Game game, Rectangle ball, int x, int y, int speed){
		this.game=game;
		this.ballRect=ball;
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.rect=new Rectangle(this.x+1,this.y+1,6,6);
	}
	
	public void paint(Graphics2D g){
		g.setColor(Color.GRAY);
		g.fillOval(this.x, this.y, 8, 8);
		g.setColor(Color.DARK_GRAY);
		g.drawOval(this.x, this.y, 8, 8);
	}
	
	public void move(){
		this.x-=1;
		this.rect.x-=1;
		this.checkCollision(ballRect);
	}
	
	public void checkCollision(Rectangle ballRect){
		if (this.rect.intersects(ballRect)){
			game.scoreUP();
		}
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
}
