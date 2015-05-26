package com.unisc.gramatica;

import com.unisc.trablinguagensformais.MyDragShadowBuilder;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class Simbolo {

	private String m_Nome;
	private TextView m_Componente;
	private boolean m_IsTerminal = false;
	private boolean m_IsCaracterEspecial = false;
	
	public TextView getComponente() {
		return m_Componente;
	}
	
	public void setComponente(TextView m_Componente) {
		this.m_Componente = m_Componente;
	}

	public String getNome() {
		return m_Nome;
	}

	public void setNome(String m_Nome) {
		this.m_Nome = m_Nome;
	}
	
	public View Novo(Context context, String nome, boolean isTerminal)
	{
		TextView _textView = new TextView(context);
		Typeface _tf = Typeface.createFromAsset(context.getAssets(), "fonts/lettre.ttf");
		
		_textView.setTypeface(_tf);
		_textView.setTag("tag_"+_textView.getText().toString());
		_textView.setText(nome);
		_textView.setTextSize(48);
		
		if (isTerminal)
		{
			_textView.setTextSize(56);
		}
		
		_textView.setOnLongClickListener(getOnLongListener());
		
		this.m_Componente = _textView;
		this.m_Nome = nome;
		this.m_IsTerminal = isTerminal;
		
		return _textView;
	}

	public View NovoEspecial(Context context, String nome)
	{
		TextView _textView = new TextView(context);
		Typeface _tf = Typeface.createFromAsset(context.getAssets(), "fonts/lettre.ttf");
		
		_textView.setTypeface(_tf);
		_textView.setTag("tag_"+_textView.getText().toString());
		_textView.setText(nome);
		_textView.setTextSize(48);
	
		this.m_Componente = _textView;
		this.m_Nome = nome;
		this.m_IsCaracterEspecial = true;
		
		return _textView;
	}

	
	public boolean getIsTerminal() {
		return m_IsTerminal;
	}
	
	public boolean GetIsCaracterEspecial() {
		return m_IsCaracterEspecial;
	}

	public void setIsCaracterEspecial(boolean m_IsCaracterEspecial) {
		this.m_IsCaracterEspecial = m_IsCaracterEspecial;
	}


	public void setIsTerminal(boolean m_IsTerminal) {
		this.m_IsTerminal = m_IsTerminal;
	}

	public static OnLongClickListener getOnLongListener() {
		// TODO Auto-generated method stub
		return new OnLongClickListener() 
		{			
			@Override
			public boolean onLongClick(View arg0) 
			{
				ClipData.Item _clipItem = new ClipData.Item(((TextView)arg0).getText().toString());
				
				ClipData dragData = new ClipData(((TextView)arg0).getText().toString(), new String[]{"text/plain"}, _clipItem);
				
				View.DragShadowBuilder _myShadow = new MyDragShadowBuilder((TextView)arg0);
				
				boolean _result = arg0.startDrag(dragData, _myShadow, null, 0);
				return _result;
			}
		};
	}


	
	
}
