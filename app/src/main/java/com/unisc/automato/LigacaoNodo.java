package com.unisc.automato;

import java.util.ArrayList;

public class LigacaoNodo 
{
	private Nodo m_Nodo = null;
	private String m_Palavra = "";
	
	public String getPalavra() {
		return m_Palavra;
	}
	public void setPalavra(String m_Palavra) {
		this.m_Palavra = m_Palavra;
	}
	public Nodo getNodo() {
		return m_Nodo;
	}
	public void setNodo(Nodo m_NodoDestino) {
		this.m_Nodo = m_NodoDestino;
	}
	
	
	

}
