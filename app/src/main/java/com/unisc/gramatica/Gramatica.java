package com.unisc.gramatica;

import java.util.ArrayList;
import android.content.Context;
import android.view.ViewGroup;

import com.unisc.trablinguagensformais.MainActivity;

public class Gramatica 
{
	private Context m_Context = null;	
	private ArrayList<Simbolo> m_Simbolos = null;
	
	private ViewGroup m_Terminais = null;
	private ViewGroup m_NaoTerminais = null;
	private ViewGroup m_Producoes = null;
	
	public Gramatica(Context context,ViewGroup terminais, ViewGroup naoTerminais, ViewGroup producoes) 
	{
		setSimbolos(new ArrayList<Simbolo>());
		
		m_Context= context;
		m_NaoTerminais = naoTerminais;
		m_Producoes = producoes;
		m_Terminais = terminais;
	}

	public ArrayList<Simbolo> getSimbolos() {
		return m_Simbolos;
	}

	public void setSimbolos(ArrayList<Simbolo> m_Simbolos) {
		this.m_Simbolos = m_Simbolos;
	}
	
	public void AddTerminal(String nome)
	{
		Simbolo _newSimbol = new Simbolo();

		if (m_Terminais.getChildCount() > 0)
		{
			m_Terminais.addView(_newSimbol.NovoEspecial(m_Context, ","));
		}
		
		_newSimbol = new Simbolo();
		_newSimbol.Novo(m_Context, nome, true);		m_Terminais.addView(_newSimbol.getComponente());				
	}
	
	public void AddNaoTerminal(String nome)
	{
		Simbolo _newSimbol = new Simbolo();
		
		if (m_NaoTerminais.getChildCount() > 0)
		{
			m_NaoTerminais.addView(_newSimbol.NovoEspecial(m_Context, ","));
		}
		
		_newSimbol = new Simbolo();
		_newSimbol.Novo(m_Context, nome, false);
		m_NaoTerminais.addView(_newSimbol.getComponente());
				
		Producao _novaProducao = new Producao();
		_novaProducao.Novo(m_Producoes.getChildCount() == 0?"P: "+ _newSimbol.getNome():_newSimbol.getNome(), m_Context);
		m_Producoes.addView(_novaProducao.getContainer());
	}
}
