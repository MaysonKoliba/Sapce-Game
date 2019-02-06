package SpacePackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class spaceRender extends JPanel implements ActionListener,KeyListener,MouseMotionListener{

	private int shipX = 250;
	private int shipY = 400;
	private int gameLevel = 1;
	private int astrosHit = 0;
	private int score = 0;
	private int astroToggle = 0;
	private int numBalls = 4;
	private boolean gameOver = false;
	private boolean gameWin = false;
	private boolean gameLose = false;
	private boolean mouseDragged = false;
	private boolean gameStart = true;
	private boolean loopBreak = false;
	private gameElements [] balls = new gameElements[4];
	private gameElements [] astros = new gameElements[10];
	private Random random = new Random();
	private Timer timer = new Timer(5,this);
	private Image ship = new ImageIcon("images/ufo2.png").getImage();
	private Image astronaut = new ImageIcon("images/astronaut.png").getImage();
	private Image background = new ImageIcon("images/galaxyBackground.jpg").getImage();
	
	public spaceRender() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		addMouseMotionListener(this);
		
		for(int i = 0; i < 4; i++) {
			gameElements ball = new gameElements();
			balls[i] = ball;
		}
		
		for(int i = 0; i < 10; i++) {
			gameElements astro = new gameElements();
			astros[i] = astro;
		} 
		
		timer.start();
	}
	
    @Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//background
		g.drawImage(background,0,0,1300,900, null);
		
		//Level
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.BOLD,24));
		g.drawString("Level " + gameLevel, 40, 30);
		
		//ball ammo
		g.setColor(Color.YELLOW);
		if(numBalls == 1) {
			g.fillOval(600,10,20,20);
		}
		else if(numBalls == 2) {
			g.fillOval(600,10,20,20);
			g.fillOval(630,10,20,20);
		}
		else if(numBalls == 3) {
			g.fillOval(600,10,20,20);
			g.fillOval(630,10,20,20);
			g.fillOval(660,10,20,20);
		}
		else if(numBalls == 4) {
			g.fillOval(600,10,20,20);
			g.fillOval(630,10,20,20);
			g.fillOval(660,10,20,20);
			g.fillOval(690,10,20,20);
		}
		
		//score
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif",Font.BOLD,24));
		g.drawString("Score: " + score, 1150, 30);
		
		//astronauts
		for(int i = 0; i < 10; i++) {
			if(astros[i].astroDeployed) {
				g.drawImage(astronaut, (int)astros[i].astroPosX, (int)astros[i].astroPosY, 80, 80, null);
			}
		}
		
		//ball
		for(int i = 0; i < 4; i++) {
			if(balls[i].ballFired) {
				
				if(balls[i].ballHits < 2) {
					g.setColor(Color.YELLOW);
					g.fillOval((int)balls[i].ballPosX,(int)balls[i].ballPosY,20,20);
				}
				else if(balls[i].ballHits < 4) {
					g.setColor(new Color(16105758));
					g.fillOval((int)balls[i].ballPosX,(int)balls[i].ballPosY,20,20);
				}
				else if(balls[i].ballHits < 6) {
					g.setColor(new Color(16085790));
					g.fillOval((int)balls[i].ballPosX,(int)balls[i].ballPosY,20,20);
				}
				else if(balls[i].ballHits < 8) {
					g.setColor(new Color(16074014));
					g.fillOval((int)balls[i].ballPosX,(int)balls[i].ballPosY,20,20);
				}
				else if(balls[i].ballHits < 10) {
					g.setColor(Color.RED);
					g.fillOval((int)balls[i].ballPosX,(int)balls[i].ballPosY,20,20);
				}
			}
		}
		
		//ship
		if(mouseDragged) {
			g.drawImage(ship, shipX, shipY, 120, 80, null);
		}
		else {
			g.drawImage(ship, shipX, shipY, 120, 80, null);
		}
		
		//gameWin
		if(gameWin) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif",Font.BOLD,64));
			g.drawString("You Win!", 520, 390);
		}
		
		//gameLose
		if(gameLose) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif",Font.BOLD,64));
			g.drawString("You Lose!", 510, 390);
		}
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!gameOver) {
		
			repaint();
			
			if(gameStart) {
				for(int i = 0; i < gameLevel; i++) {
					astroToggle = random.nextInt(2);
					astros[i].astroDeployed = true;
					if(astroToggle == 0) {
						astros[i].astroPosX = -50;
						astros[i].astroPosY = random.nextInt(700) + 50;
						astros[i].astroDirX = 2;
						astros[i].astroDirY = 0;
						astros[i].astroPassLeftX = astros[i].astroPosX;
						astros[i].astroPassLeft = true;
						astros[i].astroPassRightX = 1400;
						astros[i].astroPassRight = false;
					}
					else if(astroToggle == 1) {
						astros[i].astroPosX = 1400;
						astros[i].astroPosY = random.nextInt(700) + 50;
						astros[i].astroDirX = -2;
						astros[i].astroDirY = 0;
						astros[i].astroPassRightX = astros[i].astroPosX;
						astros[i].astroPassRight = true;
						astros[i].astroPassLeftX = -50;
						astros[i].astroPassLeft = false;
					}
					
					for(int k = 0; k < 10; k++) {
						if(k > 0 && new Rectangle(astros[i].astroPosX,astros[i].astroPosY,100,100).intersects(new Rectangle(astros[k-1].astroPosX,astros[k-1].astroPosY,100,100)) && k-1 != i) {
							if(astroToggle == 0) {
								astros[i].astroPosX = -50;
								astros[i].astroPosY = random.nextInt(700) + 50;
								astros[i].astroDirX = 2;
								astros[i].astroDirY = 0;
								astros[i].astroPassLeftX = astros[i].astroPosX;
								astros[i].astroPassLeft = true;
								astros[i].astroPassRightX = 1400;
								astros[i].astroPassRight = false;
								astroToggle = random.nextInt(2);
								k = 0;
							}
							else if(astroToggle == 1) {
								astros[i].astroPosX = 1400;
								astros[i].astroPosY = random.nextInt(700) + 50;
								astros[i].astroDirX = -2;
								astros[i].astroDirY = 0;
								astros[i].astroPassRight = true;
								astros[i].astroPassLeftX = -50;
								astros[i].astroPassLeft = false;
								astroToggle = random.nextInt(2);
								k = 0;
							}
						}
						else if(new Rectangle(astros[i].astroPosX,astros[i].astroPosY,100,100).intersects(new Rectangle(astros[k].astroPosX,astros[k].astroPosY,100,100)) && k != i) {
							if(astroToggle == 0) {
								astros[i].astroPosX = -50;
								astros[i].astroPosY = random.nextInt(700) + 50;
								astros[i].astroDirX = 2;
								astros[i].astroDirY = 0;
								astros[i].astroPassLeftX = astros[i].astroPosX;
								astros[i].astroPassLeft = true;
								astros[i].astroPassRightX = 1400;
								astros[i].astroPassRight = false;
								astroToggle = random.nextInt(2);
								k = 0;
							}
							else if(astroToggle == 1) {
								astros[i].astroPosX = 1400;
								astros[i].astroPosY = random.nextInt(700) + 50;
								astros[i].astroDirX = -2;
								astros[i].astroDirY = 0;
								astros[i].astroPassRight = true;
								astros[i].astroPassLeftX = -50;
								astros[i].astroPassLeft = false;
								astroToggle = random.nextInt(2);
								k = 0;
							}
							
						} 
					}
				}
			}
			
			for(int i = 0; i < 10; i++) {
				if(new Rectangle(shipX,shipY,100,50).intersects(new Rectangle(astros[i].astroPosX,astros[i].astroPosY,60,70))) {
					gameOver = true;
					gameLose = true;
				}
				for(int k = 0; k < 4; k++) {
					if(new Rectangle(astros[i].astroPosX,astros[i].astroPosY,60,70).intersects(new Rectangle(balls[k].ballPosX,balls[k].ballPosY,15,15))) {
						balls[k].ballFired = false;
						balls[k].ballHits = 0;
						balls[k].ballDirX = 0;
						balls[k].ballDirY = 0;
						balls[k].ballPosX = -100;
						balls[k].ballPosY = -100;
						score += 100;
						numBalls++;
						astros[i].astroDeployed = false;
						astros[i].astroDirX = 0;
						astros[i].astroDirY = 0;
						astros[i].astroBounce = 0;
						astros[i].astroPosX = -300;
						astros[i].astroPosY = -300;
						astros[i].astroHits++;
					}
				}
			}
			
			for(int i = 0; i < 4; i++) {
				if(balls[i].ballFired) {
					if(balls[i].ballPosX < 0) {
						balls[i].ballDirY = 1;
						balls[i].ballDirX = -balls[i].ballDirX;
						if(balls[i].ballHits % 3 == 0) {
							balls[i].ballDirY = -balls[i].ballDirY;
						}
						balls[i].ballHits++;
					}
					else if(balls[i].ballPosX > 1280) {
						balls[i].ballDirY = 1;
						balls[i].ballDirX = -balls[i].ballDirX;
						if(balls[i].ballHits % 3 == 0) {
							balls[i].ballDirY = -balls[i].ballDirY;
						}
						balls[i].ballHits++;
					}
					else if(balls[i].ballPosY > 840) {
						balls[i].ballDirX = 3;
						balls[i].ballDirY = -balls[i].ballDirY;
						if(balls[i].ballHits % 3 == 0) {
							balls[i].ballDirX = -balls[i].ballDirX;
						}
						balls[i].ballHits++;
					}
					else if(balls[i].ballPosY < 0) {
						balls[i].ballDirX = 3;
						balls[i].ballDirY = -balls[i].ballDirY;
						if(balls[i].ballHits % 3 == 0) {
							balls[i].ballDirX = -balls[i].ballDirX;
						}
						balls[i].ballHits++;
					}
					
					balls[i].ballPosX += balls[i].ballDirX;
					balls[i].ballPosY += balls[i].ballDirY;
					
					if(balls[i].ballHits >= 10) {
						balls[i].ballFired = false;
						balls[i].ballHits = 0;
						balls[i].ballDirX = 0;
						balls[i].ballDirY = 0;
						balls[i].ballPosX = -100;
						balls[i].ballPosY = -100;
						numBalls++;
					}
				}
			}
			
			for(int i = 0; i < 10; i++) {
				if(astros[i].astroDeployed) {
					if(astros[i].astroPosX < -10 && (astros[i].astroPassRightX < 1200 || astros[i].astroPassLeftX > 0)) {
						astros[i].astroDirY = 1;
						astros[i].astroDirX = -astros[i].astroDirX;
						if(astros[i].astroBounce % 3 == 0) {
							astros[i].astroDirY = -astros[i].astroDirY;
						}
						astros[i].astroBounce++;
					}
					else if(astros[i].astroPosX > 1210 && (astros[i].astroPassRightX < 1200 || astros[i].astroPassLeftX > 0)) {
						astros[i].astroDirY = 1;
						astros[i].astroDirX = -astros[i].astroDirX;
						if(astros[i].astroBounce % 3 == 0) {
							astros[i].astroDirY = -astros[i].astroDirY;
						}
						astros[i].astroBounce++;
					}
					else if(astros[i].astroPosY > 770 && (astros[i].astroPassRightX < 1200 || astros[i].astroPassLeftX > 0)) {
						astros[i].astroDirX = 2;
						astros[i].astroDirY = -astros[i].astroDirY;
						if(astros[i].astroBounce % 3 == 0) {
							astros[i].astroDirX = -astros[i].astroDirX;
						}
						astros[i].astroBounce++;
					}
					else if(astros[i].astroPosY < -10 && (astros[i].astroPassRightX < 1200 || astros[i].astroPassLeftX > 0)) {
						astros[i].astroDirX = 2;
						astros[i].astroDirY = -astros[i].astroDirY;
						if(astros[i].astroBounce % 3 == 0) {
							astros[i].astroDirX = -astros[i].astroDirX;
						}
						astros[i].astroBounce++;
					}
					else {
						//hello
					}
					
					astros[i].astroPosX += astros[i].astroDirX;
					astros[i].astroPosY += astros[i].astroDirY;
					
					
						if(astros[i].astroPassRight) {
							astros[i].astroPassRightX += astros[i].astroDirX;
							if(astros[i].astroPassRightX < 1200) {
								astros[i].astroPassRight = false;
							}
						}
						
						if(astros[i].astroPassLeft) {
							astros[i].astroPassLeftX += astros[i].astroDirX;
							if(astros[i].astroPassLeftX  > 0) {
								astros[i].astroPassLeft = false;
							}
						}
				}
			}
			
			gameStart = false;
			for(int i = 0; i < gameLevel; i++) {
				if(astros[i].astroHits == 1) {
					astros[i].astroHits++;
					astrosHit++;
				}
				if(astrosHit == gameLevel) {
					if(gameLevel + 1 <= 10) {
						gameLevel++;
						gameStart = true;
					}
					else {
						gameWin = true;
					}
					
					for(int k = 0; k < astrosHit; k++) {
						astros[k].astroHits = 0;
					}
					
					for(int z = 0; z < 4; z++) {
						balls[z].ballFired = false;
						balls[z].ballHits = 0;
						balls[z].ballDirX = 0;
						balls[z].ballDirY = 0;
						balls[z].ballPosX = -100;
						balls[z].ballPosY = -100;
					}
					astrosHit = 0;
					numBalls = 4;
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if(i == KeyEvent.VK_RIGHT) {
			for(int k = 0; k < 4; k++) {
				if(!balls[k].ballFired) {
					balls[k].ballFired = true;
					balls[k].ballPosX = shipX +60;
					balls[k].ballPosY = shipY +40;
					balls[k].ballDirX = 3;
					balls[k].ballDirY = 0;
					numBalls--;
					loopBreak = true;
					if(loopBreak) {
						break;
					}
				}
			} 
			loopBreak = false;
		}
		if(i == KeyEvent.VK_LEFT) {
			for(int k = 0; k < 4; k++) {
				if(!balls[k].ballFired) {
					balls[k].ballFired = true;
					balls[k].ballPosX = shipX +60;
					balls[k].ballPosY = shipY +40;
					balls[k].ballDirX = -3;
					balls[k].ballDirY = 0;
					numBalls--;
					loopBreak = true;
					if(loopBreak) {
						break;
					}
				}
			}
			loopBreak = false;
		}
		if(i == KeyEvent.VK_UP) {
			for(int k = 0; k < 4; k++) {
				if(!balls[k].ballFired) {
					balls[k].ballFired = true;
					balls[k].ballPosX = shipX +60;
					balls[k].ballPosY = shipY +40;
					balls[k].ballDirY = -3;
					balls[k].ballDirX = 0;
					numBalls--;
					loopBreak = true;
					if(loopBreak) {
						break;
					}
				}
			}
			loopBreak = false;
		}
		if(i == KeyEvent.VK_DOWN) {
			for(int k = 0; k < 4; k++) {
				if(!balls[k].ballFired) {
					balls[k].ballFired = true;
					balls[k].ballPosX = shipX +60;
					balls[k].ballPosY = shipY +40;
					balls[k].ballDirY = 3;
					balls[k].ballDirX = 0;
					numBalls--;
					loopBreak = true;
					if(loopBreak) {
						break;
					}
				}
			}
			loopBreak = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		shipX = e.getX()-60;
		shipY = e.getY()-40;
		mouseDragged = true;
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		shipX = e.getX()-60;
		shipY = e.getY()-40;
		mouseDragged = false;
		e.consume();
	}
}