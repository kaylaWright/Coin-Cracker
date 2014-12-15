package com.example.spherebreak.framework;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.example.spherebreak.framework.iGame;
import com.example.spherebreak.framework.Screen;
import com.sphereBreak.R;

public abstract class Game extends Activity implements iGame
{
	FastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	public Game()
	{
		super();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle _savedInstance)
	{
		super.onCreate(_savedInstance);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new FastRenderView(this, frameBuffer);
		graphics = new Graphics(getAssets(), frameBuffer);
		fileIO = new FileIO(getAssets());
		audio = new Audio(this);
		input = new Input(this, renderView, scaleX, scaleY);
		screen = GetStartScreen();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.Resume();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		
		if(isFinishing())
			screen.dispose();
	}
	
	public Input GetInput()
	{
		return input;
	}
	
	public FileIO GetFileIO()
	{
		return fileIO;
	}
	
	public Graphics GetGraphics()
	{
		return graphics;
	}
	
	public Audio GetAudio()
	{
		return audio;
	}
	
	public void SetScreen(Screen _screen)
	{
		if(_screen == null)
			throw new IllegalArgumentException("Screen must not be null.");
		
		this.screen.pause();
		this.screen.dispose();
		_screen.resume();
		_screen.update(0);
		this.screen = _screen;
	}
	
	public Screen GetCurrentScreen()
	{
		return screen;
	}
}
