package com.gameObjects;

import java.util.Random;

import com.example.spherebreak.framework.Game;

public class Coins 
{
	public int xPos, yPos;
	final public static int coinSize = 43;
	
	protected int coinValue;
	public boolean isSelected;
	private Random rnd;
	
	public Coins(int _x, int _y)
	{
		rnd = new Random();
		SetRandomValue();
		
		xPos = _x;
		yPos = _y;
		
		isSelected = false;
	}
	
	public void Render(Game _game) 
	{ }
	
	public int GetCoinValue()
	{ return coinValue; }
	public void SetCoinValue(int _i)
	{ coinValue = _i; }
	
	public void SetRandomValue()
	{ coinValue = (rnd.nextInt(9) + 1);} 
	public void IncreaseCoinValue()
	{ 
		coinValue++; 
		if(coinValue > 10)
			SetRandomValue();
	}
	
	public void ChangeSelected()
	{ isSelected = !isSelected; }
}
