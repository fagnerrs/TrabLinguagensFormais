package com.unisc.trablinguagensformais;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera.Size;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.TextView;

public class MyDragShadowBuilder extends View.DragShadowBuilder 
{
	private static Drawable m_Shadow = null;
	private Paint m_Paint = null;
	private TextView m_CurrentTextView = null;
	
	public MyDragShadowBuilder(TextView textView) 
	{
		super(textView);
		
		m_CurrentTextView = textView;
		
		m_Shadow = new ColorDrawable(Color.LTGRAY);
		m_Paint = new Paint();
		m_Paint.setColor(Color.BLUE);
	}
	
	@Override
	public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) 
	{
		int _width;
		int _height;
		
		_width = getView().getWidth() + (getView().getWidth() / 2) ;
		_height = getView().getHeight() + (getView().getHeight() / 2)  ;
		
		m_Shadow.setBounds(0, 0, _width, _height);
		
		shadowSize.set(_width, _height);
		
		shadowTouchPoint.set(_width / 2, _height / 2);
	}
		
	@Override
	public void onDrawShadow(Canvas canvas) 
	{
		
		canvas.drawText("A", 0, 0, m_Paint);
		
		m_Shadow.draw(canvas);
		m_CurrentTextView.draw(canvas);
	}
}
