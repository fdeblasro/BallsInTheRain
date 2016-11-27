package Agario;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Lluvia {
	
	private int x;
	private int y;
	private int speed;
	private Rectangle rect;
	private Rectangle ballRect;
	private Game game;
	
	public void paint(Graphics2D g){
		g.fillPolygon(new int[] {this.x, this.x+6, this.x+3}, new int[] {this.y, this.y,this.y+10}, 3);
	}
	
	public Lluvia(Game game, Rectangle ball,int x, int y){
		this.x=x;
		this.y=y;
		this.speed=2;
		this.ballRect=ball;
		this.game=game;
		this.rect = new Rectangle(this.x,this.y,4,8);
	}
	
	public void move(){
		this.x+=-1;
		this.y+=this.speed;
		this.rect.x+=-1;
		this.rect.y+=this.speed;
		this.checkCollision(this.ballRect);
	}
	
	public void checkCollision(Rectangle ballRect){
		if (this.rect.intersects(ballRect)){
			this.game.gameOver();
		}
	}
	
	public int getY(){
		return this.y;
	}
	
	
}