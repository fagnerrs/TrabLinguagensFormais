package com.unisc.automato;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;

public class Nodo 
{
	private ArrayList<LigacaoNodo> m_Ligacoes = null;
	private boolean m_IsFinal = false;
	private boolean m_IsInicial = false;
	private String m_Nome = "";
	private ArrayList<Nodo> m_NodoComposto = null;
	private boolean m_IsComposto = false;
	
	public ArrayList<LigacaoNodo> getLigacoes()
	{
		return m_Ligacoes;
	}
	
	public Nodo() 
	{
		m_Ligacoes = new ArrayList<LigacaoNodo>();
		m_NodoComposto = new ArrayList<Nodo>();
	}
	
	public void SetNodoComposto(ArrayList<Nodo> nodos)
	{
		m_NodoComposto = nodos;
	}
	
	private ACircle m_ACircle = null;

	public ACircle getACircle() {
		return m_ACircle;
	}

	public void setACircle(ACircle m_ACircle) {
		this.m_ACircle = m_ACircle;
	}
	
	public Nodo Novo(Context context, String nome)
	{
		m_ACircle = new ACircle(context, nome);
		m_ACircle.setTag(nome);
		m_Nome = nome;
		
		return  this;
	}
	
	public String ConectarNodo(Nodo nodo, String palavra)
	{
		String _result = "";
		
		if (!verificaPalavaNodo(nodo, palavra))
		{
			_result = "Leitura deste nodos já resulta nesta palavra. Ligação interrompida.";
		}
		else
		{
			LigacaoNodo _ligNodo = new LigacaoNodo();
			_ligNodo.setNodo(nodo);
			_ligNodo.setPalavra(palavra);
			
			m_Ligacoes.add(_ligNodo);
		}
		
		return _result;
	}
	
	private boolean verificaPalavaNodo(Nodo nodoDestino, String palavra)
	{
		boolean _result = true;
		
		for (LigacaoNodo _connNodo: m_Ligacoes)
		{
			
			if (_connNodo.getNodo().getNome().equalsIgnoreCase(nodoDestino.getNome())
					&& _connNodo.getPalavra().equalsIgnoreCase(palavra))
			{
				_result = false;
				break;
			}
		}
		
		return _result;
	}

	public String getNome() {
		return m_Nome;
	}

	public void setNome(String m_Nome) {
		this.m_Nome = m_Nome;
	}
	
	
	public ArrayList<Nodo> RetornaNodoComposto()
	{
		ArrayList<Nodo> _result = new ArrayList<Nodo>();
		HashMap<String, ArrayList<Nodo>> _palavraNodos = new HashMap<String, ArrayList<Nodo>>();
		ArrayList<String> _palavras = new ArrayList<String>();
		
		for (LigacaoNodo _conn : m_Ligacoes)
		{
			if (!_palavraNodos.containsKey(_conn.getPalavra()))
			{
				ArrayList<Nodo> _nodos = new ArrayList<Nodo>();
				_nodos.add(_conn.getNodo());
				
				_palavraNodos.put(_conn.getPalavra(), _nodos);
				_palavras.add(_conn.getPalavra());
			}
			else
			{
				_palavraNodos.get(_conn.getPalavra()).add(_conn.getNodo());
			}			
		}
				
		for (String _palavra : _palavras)
		{
			ArrayList<Nodo> _nodosDaPalavra = _palavraNodos.get(_palavra);
			
			if (_nodosDaPalavra != null && _nodosDaPalavra.size() > 1)
			{
				_result.add(novoComposto(_nodosDaPalavra));
			}
		}
		
		return _result;
	}

	public boolean isIsComposto() {
		return m_IsComposto;
	}

	public void setIsComposto(boolean m_IsComposto) {
		this.m_IsComposto = m_IsComposto;
	}
	
	
	private Nodo novoComposto(ArrayList<Nodo> nodos)
	{		
		Nodo _result = new Nodo();
		
		for (Nodo _nodo : nodos)
		{
			_result.setNome(_result.getNome() + _nodo.getNome());
		}
		
		_result.SetNodoComposto(nodos);
		_result.setIsComposto(true);
		
		return _result;
	}
	
	
}
