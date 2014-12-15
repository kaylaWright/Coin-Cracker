package com.example.spherebreak.framework;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.example.spherebreak.framework.inGraphics;
import com.example.spherebreak.framework.Pixmap;

public class Graphics implements inGraphics
{
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();
	
	public Graphics(AssetManager _assets, Bitmap _buffer)
	{
		this.assets = _assets;
		this.frameBuffer = _buffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}
	
	public Pixmap NewPixmap(String _name, PixmapFormat _fmt)
	{
		Config config = null;
		if(_fmt == PixmapFormat.RGB565)
			config = Config.RGB_565;
		else if(_fmt == PixmapFormat.ARGB4444)
			config = Config.ARGB_4444;
		else if(_fmt == PixmapFormat.ARGB8888)
			config = Config.ARGB_8888;
		
		Options options = new Options();
		options.inPreferredConfig = config;
		
		InputStream in = null;
		Bitmap bitmap = null;
		try
		{
			in = assets.open(_name);
			bitmap = BitmapFactory.decodeStream(in);
			if(bitmap == null)
			{
				throw new RuntimeException("Couldn't load bitmap from asset.");
			}
		}
		catch (IOException _e)
		{
			throw new RuntimeException("Couldn't load bitmap from asset.");
		}
		finally
		{
			if(in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException _e)
				{
					
				}
			}
		}
		
		if(bitmap.getConfig() == Config.RGB_565)
			_fmt = PixmapFormat.RGB565;
		else if(bitmap.getConfig() == Config.ARGB_4444)
			_fmt = PixmapFormat.ARGB4444;
		else if(bitmap.getConfig() == Config.ARGB_8888)
			_fmt = PixmapFormat.ARGB8888;
		
		return new Pixmap(bitmap, _fmt);
		
	}
	
	public void Clear(int _rgb)
	{
		canvas.drawRGB((_rgb & 0xff0000) >> 16, (_rgb & 0xff00) >> 8, (_rgb & 0xff));
	}
	
	public void DrawPixel(int _x, int _y, int _rgb)
	{
		paint.setColor(_rgb);
		canvas.drawPoint(_x, _y, paint);
	}
	
	public void DrawLine(int _x, int _y, int _x2, int _y2, int _rgb)
	{
		paint.setColor(_rgb);
		canvas.drawLine(_x, _y, _x2, _y2, paint);
	}
	
	public void DrawRect(int _x, int _y, int _width, int _height, int _rgb)
	{
		paint.setColor(_rgb);
		paint.setStyle(Style.FILL);
		canvas.drawRect(_x, _y, (_x + _width - 1), (_y + _height - 1), paint);
	}
	
	public void DrawPixmap(Pixmap _pixmap, int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight)
	{
		srcRect.left = _srcX;
		srcRect.top = _srcY;
		srcRect.right = _srcX + _srcWidth - 1;
		srcRect.bottom = _srcY + _srcHeight - 1;
		
		dstRect.left = _x;
		dstRect.top = _y;
		dstRect.right = _x + _srcWidth - 1;
		dstRect.bottom = _y + _srcHeight - 1;
		
		canvas.drawBitmap(((Pixmap) _pixmap).bitmap, srcRect, dstRect, null);
	}
	
	public void DrawPixmap(Pixmap _pixmap, int _x, int _y)
	{
		canvas.drawBitmap(((Pixmap)_pixmap).bitmap, _x, _y, null);
	}
	
	public int GetWidth()
	{
		return frameBuffer.getWidth();
	}
	
	public int GetHeight()
	{
		return frameBuffer.getHeight();
	}
}
