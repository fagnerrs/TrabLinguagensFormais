package com.unisc.automato;

import org.apache.http.client.CircularRedirectException;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public class ACircle extends View
{
	private Paint m_Paint = null;
	private TextView m_Text = null;
	private String m_Nome = null;
	
	public ACircle(Context context, String nome) {
		super(context);
	
		m_Paint = new Paint();
		m_Nome = nome;		
	}
	
	public String getNome()
	{
		return m_Nome;
	}
	
	private TextView novoTextView(Context context, String nome)
	{
		TextView _textView = new TextView(context);
		Typeface _tf = Typeface.createFromAsset(context.getAssets(), "fonts/lettre.ttf");
		
		_textView.setTypeface(_tf);
		_textView.setText(nome);
		_textView.setTextSize(48);
		
		return _textView;
	}
	
	@Override
	protected void onDraw(Canvas canvas) 
	{		
		super.onDraw(canvas);

		m_Paint.setColor(Color.argb(125, 0, 0, 0));		
		canvas.drawCircle(45, 45, 45, m_Paint);

		m_Paint.setTextSize(30);
		m_Paint.setColor(Color.RED);
		canvas.drawText(m_Nome, 15, 35, m_Paint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		// TODO Auto-generated method stub
		
		super.onSizeChanged(w, h, oldw, oldh);
		
		 // Account for padding
	       float xpad = (float)(getPaddingLeft() + getPaddingRight());
	       float ypad = (float)(getPaddingTop() + getPaddingBottom());
//
//	       // Account for the label
//	       if (mShowText) xpad += mTextWidth;
//
//	       float ww = (float)w - xpad;
//	       float hh = (float)h - ypad;
//
//	       // Figure out how big we can make the pie.
//	       float diameter = Math.min(ww, hh);		
	}
}

