package Agario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

// Pincho -> width=20 height=35;

public class Estructura {
	private final int W = 800;
	private final int H = 500;
	private ArrayList<Rectangle> rectangulos;
	private ArrayList<Monedas> monedas;
	private ArrayList<Pincho> pinchos;
	private Game game;
	private Ball ball;
	public int width;
	public int x;
	
	public Estructura(Game game, Ball ball){
		this.rectangulos = new ArrayList<Rectangle>();
		this.monedas = new ArrayList<Monedas>();
		this.pinchos = new ArrayList<Pincho>();
		this.game=game;
		this.ball=ball;
		this.x=800;
		this.width=0;
	}
	
	public void transform1(){
		this.width=700;
		for (int i=0;i<3;++i){
			this.rectangulos.add(new Rectangle(this.W+(i*80),this.H-((i+1)*20),500-(i*160),20));
		}
		for (int i=0;i<6;++i){
			for (Rectangle r : rectangulos){
				this.pinchos.add(new Pincho(game,this.ball.rect,r.x-35,r.y,1,"izquierda"));
				this.pinchos.add(new Pincho(game,this.ball.rect,r.x+r.width,r.y,1,"derecha"));
			}
		}
		this.pinchos.add(new Pincho(game,this.ball.rect,1000,500-60-35,1,"arriba"));
		this.pinchos.add(new Pincho(game,this.ball.rect,1100,500-60-35,1,"arriba"));
	}
	
	public void transform2(){
		this.width=600;
		for(int i=0;i<30;i++){
			this.pinchos.add(new Pincho(game , this.ball.rect , this.W+(i*20) , this.H-35 ,1, "arriba" ));
		}
		for (int i=0;i<4;i++){
			this.rectangulos.add(new Rectangle(this.W+(i*150), 400-(i*50), 40, 12));
		}
	}
	
	public void transform3(){
		this.width=525;
		this.rectangulos.add(new Rectangle(this.W,this.H-180,this.width,12));
		for (int i=0;i<15;i++){
			if (i%2==0){
				this.pinchos.add(new Pincho(game , this.ball.rect , this.W+(i*35) , this.H-35 ,1, "arriba" ));
			}
			else this.pinchos.add(new Pincho(game , this.ball.rect , this.W+(i*35) , this.H-180+12 ,1, "abajo" ));
		}
		this.pinchos.add(new Pincho(game , this.ball.rect , this.W, this.H-180-35 ,1, "arriba" ));
		this.pinchos.add(new Pincho(game , this.ball.rect , this.W+this.width-20, this.H-180-35 ,1, "arriba" ));
	}
	
	public void transform4(){
		this.width = 30;
		this.rectangulos.add(new Rectangle(this.W,this.H-160,this.width,12));
		this.pinchos.add(new Pincho(game , this.ball.rect , this.W+5, this.H-160-35 ,1, "arriba" ));
		this.pinchos.add(new Pincho(game , this.ball.rect , this.W+5, this.H-160+12 ,1, "abajo" ));
	}
	
	public void paint(Graphics2D g){
		for (Rectangle r : rectangulos){
			g.fillRect(r.x, r.y, r.width, r.height);
		}
		for (Pincho p : this.pinchos){
			p.paint(g);
		}
	}
	
	public void move(){
		this.checkCollision();
		for (Rectangle r : rectangulos){
			r.x-=1;
		}
		this.x--;
		for (Pincho p : this.pinchos){
			p.move();
		}
	}
	
	public void checkCollision(){
		for (Rectangle r : rectangulos){
			if (r.intersects(this.ball.rect)){
				this.ball.changeYDirection();
				this.ball.setDoubleJump(true);
			}
		}
	}
	
	public void transform(int x){
		if (x==1) this.transform1();
		if (x==2) this.transform2();
		if (x==3) this.transform3();
		if (x==4) this.transform4();
	}
}
