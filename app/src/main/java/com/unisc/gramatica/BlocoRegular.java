package com.unisc.gramatica;

public class BlocoRegular 
{
	private Simbolo m_NaoTerminal = null;
	private Simbolo m_Terminal = null;
	
	public Simbolo getNaoTerminal() {
		return m_NaoTerminal;
	}
	
	public void setNaoTerminal(Simbolo m_NaoTerminal) {
		this.m_NaoTerminal = m_NaoTerminal;
	}
}
