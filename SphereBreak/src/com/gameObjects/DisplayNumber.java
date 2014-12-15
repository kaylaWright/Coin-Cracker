package com.gameObjects;

import com.example.spherebreak.framework.Game;
import com.example.spherebreak.framework.Graphics;
import com.sphereBreak.Assets;

public class DisplayNumber 
{
	public int numValue;
	public final static int numWidth = 20;
	public int xPos, yPos;
	
	//10 = /, 11 = .
	public DisplayNumber(int _val, int _x, int _y)
	{
		numValue = _val;
		
		xPos = _x;
		yPos = _y;
	}
	
	public void Render(Game _game) 
	{
		Graphics g = _game.GetGraphics();
		///			/pixmap, 		int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight)
		g.DrawPixmap(Assets.numbers, xPos, yPos, (numWidth * (numValue)), 0, numWidth, 23);
	}
}
