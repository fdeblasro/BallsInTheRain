package Agario;

import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	private static Random random = new Random();
	private Ball ball = new Ball(this,10,300);
	public static Rectangle rect = new Rectangle(0, 500, 1300, 15);
	public ArrayList<Pincho> pinchos = new ArrayList<Pincho>();
	private ArrayList<Lluvia> lluvias = new ArrayList<Lluvia>();
	private ArrayList<Background> bck = new ArrayList<Background>();
	public ArrayList<Monedas> monedas = new ArrayList<Monedas>();
	public ArrayList<Estructura> estructuras = new ArrayList<Estructura>();
	private int score=0;
	private int aux;
	private int auxx;
	

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP){
					if (ball.getDoubleJump()){
						ball.changeYDirection();
						ball.setDoubleJump(false);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT){
					ball.changeXDirection(-1);					
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					ball.changeXDirection(1);					
				}
			}
		});
		setFocusable(true);
	}

	private void move() {
		ball.move();
		this.moveLluvia();
		this.movePinchos();
		this.moveBackground();
		this.moveMonedas();
		this.moveEstructura();
		this.aux--;
	}
	
	public void scoreUP(){
		this.score++;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Verdana", Font.BOLD, 20));
		g2d.fillRect(0, 500, 5000, 15);
		Color aux= g2d.getColor();
		if (this.score>30) g2d.setColor(Color.RED);
		else g2d.setColor(Color.BLUE);
		this.paintBackground(g2d);
		this.paintMonedas(g2d);
		g2d.setColor(aux);
		ball.paint(g2d);
		this.paintEstructura(g2d);
		this.paintLluvia(g2d);
		this.paintPinchos(g2d);
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(this, "YOU LOST!",
				"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public void randomSpawn(){
		if (!this.estructuras.isEmpty()){
			if (this.random.nextInt(90-(this.score/3))==2 && this.estructuras.get(this.estructuras.size()-1).x<800-this.estructuras.get(this.estructuras.size()-1).width-50){
				this.pinchos.add(new Pincho(this,ball.rect,800,500-35,1));
			}
		}
		else {
			if (this.random.nextInt(90-(this.score/3))==2){
				this.pinchos.add(new Pincho(this,ball.rect,800,500-35,(this.score/30)+1));
			}
		}
		if (this.random.nextInt(30)==2 && true){
			this.lluvias.add(new Lluvia(this,ball.rect,random.nextInt(800)+200,0));
		}
		if (this.random.nextInt(4)==1){
			this.bck.add(new Background(random.nextInt(800),0,random.nextInt(3)+1));
		}
		if (this.random.nextInt(400)==4){
			this.monedas.add(new Monedas (this,ball.rect,800,random.nextInt(100)+350,(this.score/30)+1));
		}
		if (this.random.nextInt(1000)==2 && this.aux<800-this.auxx-50){
			Estructura aux = new Estructura(this,this.ball);
			aux.transform(random.nextInt(4)+1);
			this.estructuras.add(aux);
			this.aux=800;
			this.auxx=aux.width;
		}
	}
	
	public void clean(){
		boolean finished=false;
		while (!finished){
			for (Pincho p : this.pinchos){
				if (p.getX()<-30){
					this.pinchos.remove(this.pinchos.indexOf(p));
					this.score++;
					break;
				}
			}
			finished = true;
		}
		finished= false;
		while (!finished){
			for (Lluvia p : this.lluvias){
				if (p.getY()>560){
					this.lluvias.remove(this.lluvias.indexOf(p));
					break;
				}
			}
			finished = true;
		}
		finished= false;
		while (!finished){
			for (Background p : this.bck){
				if (p.getY()>560){
					this.bck.remove(this.bck.indexOf(p));
					break;
				}
			}
			finished = true;
		}
		finished= false;
		while (!finished){
			for (Monedas p : this.monedas){
				if (p.rect.intersects(ball.rect)){
					this.monedas.remove(this.monedas.indexOf(p));
					break;
				}
			}
			finished = true;
		}
		finished= false;
		while (!finished){
			for (Estructura p : this.estructuras){
				if (p.x+p.width<0){
					this.estructuras.remove(p);
					break;
				}
			}
			finished = true;
		}

	}
	
	public void checkSpeed(){
		if (!pinchos.isEmpty()){
			Pincho p = pinchos.get(0);
			if ((this.score/30)+1> p.getSpeed()){
				for (Pincho f : pinchos){
					f.setSpeed(f.getSpeed()+1);
				}
			}
		}
		if (!monedas.isEmpty()){
			Monedas p = monedas.get(0);
			if ((this.score/30)+1> p.getSpeed()){
				for (Monedas f : monedas){
					f.setSpeed(f.getSpeed()+1);
				}
			}
		}
	}

	public void moveMonedas(){
		if (!monedas.isEmpty()){
			for (Monedas p : this.monedas){
				p.move();
			}
		}
	}
	
	public void moveLluvia(){
		if (!lluvias.isEmpty()){
			for (Lluvia p : this.lluvias){
				p.move();
			}
		}
	}
	
	public void movePinchos(){
		if (!pinchos.isEmpty()){
			for (Pincho p : this.pinchos){
				p.move();
			}
		}
	}
	
	public void moveBackground(){
		if (!bck.isEmpty()){
			for (Background p : this.bck){
				p.move();
			}
		}
	}
	
	public void moveEstructura(){
		for(Estructura e : this.estructuras){
			e.move();
		}
	}
	
	public void paintLluvia(Graphics2D g2d){
		if (!lluvias.isEmpty()){
			for (Lluvia ll : this.lluvias){
				ll.paint(g2d);
			}
		}
	}
	
	public void paintPinchos(Graphics2D g2d){
		if (!pinchos.isEmpty()){
			for (Pincho p : this.pinchos){
				p.paint(g2d);
			}
		}
	}
	
	public void paintBackground(Graphics2D g2d){
		if (!bck.isEmpty()){
			for (Background ll : this.bck){
				ll.paint(g2d);
			}
		}
	}
	
	public void paintMonedas(Graphics2D g2d){
		if (!monedas.isEmpty()){
			for (Monedas ll : this.monedas){
				ll.paint(g2d);
			}
		}
	}

	public void paintEstructura(Graphics2D g2d){
		for (Estructura e : this.estructuras){
			e.paint(g2d);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		random.setSeed(System.currentTimeMillis());
		frame.add(game);
		frame.setSize(800, 550);
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.randomSpawn();
			game.checkSpeed();
			game.move();
			game.clean();
			game.repaint();
			Thread.sleep(7);
		}
	}
}