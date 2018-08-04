/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;

	// Number of turns 
	public static final int NTURNS = 3;

	public void run() {
		setTitle("CS 106A Breakout");
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		addMouseListeners();
		lives = NTURNS;
		while(true) {
			setUp();
			waitForClick();
			checkForCollisions();
			remove(paddle);
			if(lives == 0) {
				gameWonOver(Color.RED, "Game Over! Click to restart");
			} else if(bricksNumber == 0) {
				gameWonOver(Color.GREEN, "YOU WIN! Click to restart");
			}
		}
	}
	
	private void setUp() {
		setBricks();
		setPaddle();
		setUpBall();
		setScore();
	}
	
	private void setScore() {
		result = new GLabel("Total score: " + score + " Lives: " +lives);
		result.setFont("SansSerif-20");
		add(result, (getWidth() - result.getWidth()) /2, result.getAscent());
	}
	
	private void setBricks() {
		double x = (getWidth() - BRICK_SEP * (NBRICK_COLUMNS - 1) - 
					BRICK_WIDTH * NBRICK_COLUMNS) /2;
		for(int i = 0; i < NBRICK_COLUMNS; i++) {
			for(int j = 0; j < NBRICK_ROWS; j++) {
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(colorBricks(i));
				add(brick, x + BRICK_WIDTH * j + BRICK_SEP * j, 
						BRICK_Y_OFFSET + BRICK_SEP * i + BRICK_HEIGHT * i);
			}
		}
	}
	
	private Color colorBricks(int i) {
		if(i == 0 || i == 1) {
			return Color.RED;
		} else if(i == 2 || i == 3) {
			return Color.ORANGE;
		} else if(i == 4 || i == 5) {
			return Color.YELLOW;
		} else if(i == 6 || i == 7) {
			return Color.GREEN;
		} else return Color.CYAN;
	}
	
	private void setPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, (getWidth() - PADDLE_WIDTH)/2,
				(getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT));
	}
	
	public void mouseMoved(MouseEvent e) {
		if(e.getX() > getWidth() - PADDLE_WIDTH) {
			paddle.setX(getWidth() - PADDLE_WIDTH);
		} else if(e.getX() < 0) {
			paddle.setX(0);
		} else paddle.setX(e.getX());
	}
	
	private void setUpBall() {
		ball = new GOval(BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball, (getWidth() - BALL_RADIUS)/2 - BALL_RADIUS,
				(getHeight() - BALL_RADIUS)/2 - BALL_RADIUS);
	}
	
	private void checkForCollisions() {
		collisionsWithPaddle = 0;
		bricksNumber = NBRICK_COLUMNS* NBRICK_ROWS;
		vx = rgen.nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = VELOCITY_Y;
		while(bricksNumber != 0) {
			moveBall(vx, vy);
			if(ball.getY() > getHeight()) {
				gameLost();
				break;
			}
			if(ball.getY() < 0) {
				vy = -vy;
			} else if (ball.getX() + BALL_RADIUS*2 > getWidth() || ball.getX() < 0) {
				vx = -vx;
			}
			GObject collider = getCollidingObject();
			if(collider == paddle && ball.getY() + BALL_RADIUS*2 < paddle.getY()
						&& ball.getX() > paddle.getX()) {
				collisionsWithPaddle++;
				updateSpeed();
				if(vy > 0) vy = -vy;
				if(vx < 0) vx = -1 * vx;
			} else if(collider == paddle && ball.getY() + BALL_RADIUS*2 < paddle.getY()
						&& ball.getX() < paddle.getX()) {
				collisionsWithPaddle++;
				if(vy > 0) vy = -vy;
				if(vx > 0) vx = -1 * vx;
				updateSpeed();
			} else if(collider == paddle && ball.getX() < paddle.getX() + PADDLE_WIDTH/2) {
				collisionsWithPaddle++;
				if(vy > 0) vy = -vy;
				if(vx > 0) vx =-1 * vx;
				updateSpeed();
			} else if(collider == paddle && ball.getX() > paddle.getX() + PADDLE_WIDTH/2) {
				collisionsWithPaddle++;
				if(vy > 0) vy = -vy;
				if(vx < 0) vx =-1 * vx;
				updateSpeed();
			} else if (collider == paddle) {
				collisionsWithPaddle++;
				if(vy > 0) vy = -vy;
				updateSpeed();
			} else if (collider != null && collider != result) {
				keepScore(collider);
				remove(collider);
				bounceClip.play();
				bricksNumber--;
				vy = -vy;
				}
			}
		}
	
	private void moveBall(double vx, double vy) {
		ball.move(vx, vy);
		pause(DELAY/2);
	}
	
	private GObject getCollidingObject() {
		if(getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY()); 
		} else if (getElementAt(ball.getX() + BALL_RADIUS*2, 
				ball.getY()) != null) {
			return getElementAt(ball.getX() + BALL_RADIUS*2, 
					ball.getY());
		} else if (getElementAt(ball.getX(), 
				ball.getY() + BALL_RADIUS*2) != null) {
			return getElementAt(ball.getX(), 
					ball.getY() + BALL_RADIUS*2) ;
		} else if (getElementAt(ball.getX() + BALL_RADIUS*2, 
				ball.getY() + BALL_RADIUS*2) != null) {
			return getElementAt(ball.getX() + BALL_RADIUS*2, 
					ball.getY() + BALL_RADIUS*2);
		} else return null;
			
	}
	
	private void gameLost() {
		GLabel lose = new GLabel("You Lose");
		lose.setFont("SansSerif-bold-30");
		lose.setColor(Color.RED);
		add(lose, (getWidth() - lose.getWidth()) /2, 
				(getHeight() - lose.getAscent()) /2);
		lives--;
		vx = 0; vy = 0; collisionsWithPaddle = 0;
		pause(DELAY*60);
		removeAll();
	}
	
	private void gameWonOver(Color color, String string) {
		removeAll();   
		GLabel gameover = new GLabel(string);
		gameover.setFont("SansSerif-bold-25");
		gameover.setColor(color);
		add(gameover, (getWidth() - gameover.getWidth()) /2, 
				(getHeight() - gameover.getAscent()) /2 );
		waitForClick();
		score = 0;
		vx = 0; vy = 0; collisionsWithPaddle = 0;
		lives = NTURNS;
		remove(gameover);
	}
	
	private void keepScore(GObject collider) {
		if(collider.getColor() == Color.CYAN) {
			score += 100;
		} else if(collider.getColor() == Color.GREEN) {
			score += 200;
		} else if(collider.getColor() == Color.YELLOW) {
			score += 300;
		} else if (collider.getColor() == Color.ORANGE) {
			score += 400;
		} else if (collider.getColor() == Color.RED) {
			score += 500;
		}
		result.setLabel("Total score: " + score + " Lives: " +lives);
	}
	
	private void updateSpeed() {
		if(collisionsWithPaddle % 2 == 0) vx +=0.05;
	}
	
	private GRect paddle;
	private int bricksNumber, lives, score, collisionsWithPaddle;
	private GLabel result;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	private double vx, vy;
}