package com.sphereBreak;

import com.example.spherebreak.framework.Game;
import com.example.spherebreak.framework.Graphics;
import com.example.spherebreak.framework.Screen;
import com.example.spherebreak.framework.inGraphics.PixmapFormat;

public class LoadingScreen extends Screen
{
	public LoadingScreen(Game _game)
	{
		super(_game);
	}

	@Override
	public void update(float _dt) 
	{
		Graphics g = game.GetGraphics();
		
		Assets.background = g.NewPixmap("background.jpg", PixmapFormat.RGB565);
		//Assets.logo = g.NewPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.centerCoins = g.NewPixmap("CoinsCenter.png", PixmapFormat.ARGB4444);
		Assets.entryCoins = g.NewPixmap("CoinsEntry.png", PixmapFormat.ARGB4444);
		Assets.selectionCoins = g.NewPixmap("CoinsSelectable.png", PixmapFormat.ARGB4444);
		Assets.guiElements = g.NewPixmap("GUI.png", PixmapFormat.ARGB4444);
		Assets.numbers = g.NewPixmap("Numbers.png", PixmapFormat.ARGB4444);
		Assets.guiScore = g.NewPixmap("ScoreGUI.png", PixmapFormat.ARGB4444);
		
		Assets.mainMenu = g.NewPixmap("MainMenu.png", PixmapFormat.ARGB4444);
		Assets.difficultySelection = g.NewPixmap("DifficultySelection.png", PixmapFormat.ARGB4444);
		Assets.pauseMenu = g.NewPixmap("PauseMenu.png", PixmapFormat.ARGB4444);
		Assets.winMenu = g.NewPixmap("WinMenu.png", PixmapFormat.ARGB4444);
		Assets.lossMenu = g.NewPixmap("LoseMenu.png", PixmapFormat.ARGB4444);
		Assets.instructions = g.NewPixmap("Instructions.png", PixmapFormat.ARGB4444);

		Assets.backgroundMusic = game.GetAudio().CreateNewMusic("_deadcaveMUS.wav", true);
		
		Assets.coinSFX = game.GetAudio().CreateNewSound("_coinSFX.wav");
		Assets.menuSFX = game.GetAudio().CreateNewSound("_menuButtonSFX.wav");
		
		Settings.Load(game.GetFileIO());
		game.SetScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float _dt) 
	{
	}

	@Override
	public void pause() 
	{
	}

	@Override
	public void resume() 
	{
	}

	@Override
	public void dispose() 
	{
	}
	

}
