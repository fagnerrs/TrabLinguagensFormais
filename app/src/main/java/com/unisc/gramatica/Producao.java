package com.unisc.gramatica;

import java.util.ArrayList;

import com.unisc.trablinguagensformais.MyDragEventListener;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Producao implements IDragMetodos
{
	private Simbolo m_SimboloNaoTerminal;	
	private ViewGroup m_Container = null;
	private ArrayList<BlocoRegular> m_BlocosRecugalres = null;
	
	public ArrayList<BlocoRegular> getBlocosRecugalres() {
		return m_BlocosRecugalres;
	}
	
	public void setBlocosRecugalres(ArrayList<BlocoRegular> blocosRecugalres) {
		this.m_BlocosRecugalres = m_BlocosRecugalres;
	}
	
	public ViewGroup getContainer() {
		return m_Container;
	}
	
	public void setContainer(LinearLayout m_Container) {
		this.m_Container = m_Container;
	}
	
	public Simbolo getSimboloNaoTerminal() {
		return m_SimboloNaoTerminal;
	}
	
	public void setSimboloNaoTerminal(Simbolo m_SimboloNaoTerminal) {
		this.m_SimboloNaoTerminal = m_SimboloNaoTerminal;
	} 
	
	public ViewGroup Novo(String nomeNaoTerminal, Context context)
	{
		m_Container = new LinearLayout(context);
		((LinearLayout)m_Container).setOrientation(LinearLayout.HORIZONTAL);
		
		Simbolo _simNaoTerminal = new Simbolo();
		_simNaoTerminal.NovoEspecial(context, nomeNaoTerminal + " => ");
		
		m_Container.addView(_simNaoTerminal.getComponente());
		this.m_SimboloNaoTerminal = _simNaoTerminal;
		
		m_Container.setOnDragListener(new MyDragEventListener(this));
		
		return m_Container;
	}

	@Override
	public void Message(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ValidaGramaticaRegular(String naoTermina, String terminal) {
		// TODO Auto-generated method stub
		
	}
	
}
