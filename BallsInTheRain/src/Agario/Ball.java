package Agario;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {
	private int x;
	private int y;
	private Game game;
	private int speed;
	public Rectangle rect;
	private int gravity;
	private boolean doubleJump;
	private int xDirection;
	private Ellipse2D.Double elli;
	
	private int yPos;
	
	public Ball(Game game) {
		this(game,0,0);
	}
	
	public Ball(Game game, int x, int y){
		this.game = game;
		this.x=x;
		this.y=y;
		this.speed=1;
		this.gravity = 0;
		this.yPos = 0;
		this.doubleJump=false;
		this.xDirection=1;
		this.rect = new Rectangle(this.x+2,this.y+2,23,23);
	}
	
	public void move(){
		this.performMovement();
		this.moveHitbox();
		this.checkBounce();
		if (this.x<-2) this.game.gameOver();
	}
	
	public void paint(Graphics2D g) {
		g.fillOval(x, y, 30-this.gravity, 30+this.gravity);
	}
	
	public void changeYDirection(){
		this.gravity = -5;
		this.yPos=0;
	}
	public void changeYDirection(int x){
		this.gravity = -x;
	}
	
	public void changeXDirection(int x){
		if (x==-1 && this.xDirection==1) this.xDirection=-1;
		else if (this.xDirection==-1 && x==1) this.xDirection=1;
	}
	
	public void setSpeed(int x){
		this.speed=x;
	}
	
	public boolean getDoubleJump(){
		return this.doubleJump;
	}
	
	public void moveHitbox(){
		this.rect.x=this.x+2;
		this.rect.y=this.y+2;
	}
	
	public void checkBounce(){
		if (this.rect.intersects(Game.rect)){
			this.y=Game.rect.y-25;
			this.changeYDirection();
			this.setDoubleJump(true);
		}
	}
	
	public void setDoubleJump(boolean a){
		this.doubleJump=a;
	}
	
	public void performMovement(){
		this.x+=this.speed*this.xDirection;
		this.y+=(this.speed+this.gravity);
		if (this.yPos>=10){
			this.yPos=0;
			this.gravity++;
			if (this.gravity<0){
				this.y=this.y-this.gravity;
			}
		}
		else this.yPos++;
	}

}
