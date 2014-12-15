package com.example.spherebreak.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FastRenderView extends SurfaceView implements Runnable
{
	Game game;
	Bitmap frameBuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;
	
	public FastRenderView(Game _game, Bitmap _buffer)
	{
		super(_game);
		
		this.game = _game;
		this.frameBuffer = _buffer;
		this.holder = getHolder();
	}
	
	public void Resume()
	{
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void run()
	{
		Rect dst = new Rect();
		long startTime = System.nanoTime();
		while(running)
		{
			if(!holder.getSurface().isValid())
				continue;
			
			float dt = (System.nanoTime() - startTime)/100000000.0f;
			startTime = System.nanoTime();
			
			game.GetCurrentScreen().update(dt);
			game.GetCurrentScreen().present(dt);
			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dst);
			canvas.drawBitmap(frameBuffer, null, dst, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void pause()
	{
		running = false;
		while(true)
		{
			try
			{
				renderThread.join();
				break;
			}
			catch(InterruptedException _e)
			{
			
			}
		}
	}
}
