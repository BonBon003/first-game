package com.BonBon003.calliope.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected float accX, accY;
	protected boolean falling = true;
	protected boolean jumping = false;
	
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public GameObject(float x, float y, ID id) {
		
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setAccX(float accX) {
		this.accX = accX;
	}
	public void setAccY(float accY) {
		this.accY = accY;
	}
	public float getAccX() {
		return accX;
	}
	public float getAccY() {
		return accY;
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
