package com.gameObjects;

import com.example.spherebreak.framework.Game;
import com.example.spherebreak.framework.Graphics;
import com.sphereBreak.Assets;

public class SelectionCoin extends Coins
{
	public SelectionCoin(int _x, int _y)
	{
		super(_x, _y);
	}
	
	@Override
	public void Render(Game _game)
	{
		Graphics g = _game.GetGraphics();
		////int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight)
		if(!isSelected)
			g.DrawPixmap(Assets.selectionCoins, xPos, yPos, (coinSize * (coinValue - 1)), 0, coinSize, coinSize);
		else
			g.DrawPixmap(Assets.selectionCoins, xPos, yPos, (coinSize * (coinValue - 1)), coinSize, coinSize, coinSize);
	}
}
