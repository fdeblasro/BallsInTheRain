package Agario;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Pincho {
	
	private int x;
	private int y;
	private int speed;
	private String direccion;
	public Rectangle rect;
	private Rectangle ballRect;
	private Game game;
	
	public void paint(Graphics2D g){
		if (this.direccion.equals("arriba")){
			g.fillPolygon(new int[] {this.x, this.x+20, this.x+10}, new int[] {this.y+35, this.y+35,this.y}, 3);
		}
		else if (this.direccion.equals("derecha")){
			g.fillPolygon(new int[] {this.x, this.x+35, this.x}, new int[] {this.y, this.y+10,this.y+20}, 3);
		}
		else if (this.direccion.equals("izquierda")){
			g.fillPolygon(new int[] {this.x+35, this.x, this.x+35}, new int[] {this.y, this.y+10,this.y+20}, 3);
		}
		else g.fillPolygon(new int[] {this.x, this.x+10, this.x+20}, new int[] {this.y, this.y+35,this.y}, 3);
	}
	
	public Pincho(Game game, Rectangle ball,int x, int y, int speed){
		this(game,ball,x,y,speed,"arriba");
	}
	
	public Pincho(Game game, Rectangle ball,int x, int y, int speed, String direccion){
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.direccion=direccion;
		this.ballRect=ball;
		this.game=game;
		if (this.direccion.equals("arriba")) this.rect = new Rectangle(this.x+4,this.y+2,12,29);
		else if (this.direccion.equals("derecha")) this.rect = new Rectangle(this.x+4,this.y+3,29,12);
		else if (this.direccion.equals("izquierda")) this.rect = new Rectangle(this.x+4,this.y+4,29,12);
		else this.rect = new Rectangle(this.x+4,this.y+2,12,29);
	}
	
	public void move(){
		this.x+=-1;
		this.rect.x+=-1;
		this.checkCollision(this.ballRect);
	}
	
	public void checkCollision(Rectangle ballRect){
		if (this.rect.intersects(ballRect)){
			this.game.gameOver();
		}
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
}
