package com.unisc.automato;

import java.util.ArrayList;

import com.unisc.trablinguagensformais.MyDragShadowBuilder;
import com.unisc.trablinguagensformais.R;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.sax.StartElementListener;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class Automato implements INodeEvents
{
	private ArrayList<Nodo> m_Nodos = null;
	private ArrayList<String> m_Palavras = null;
	private ViewGroup m_container = null;
	private ViewGroup m_TabelaDeNodos = null;
	
	private Context m_Context = null;
	private String m_NodoName = null;
	
	private Nodo m_NodoTouched = null;

	private String m_NodoOrigem = null;
	private String m_NodoDestino = null;
	
	AlertDialog m_AlertDialog;

	public Automato(ViewGroup viewGroup, ViewGroup tabelaDeNodos) 
	{
		m_container = viewGroup;
		m_Context = tabelaDeNodos.getContext();
		m_Nodos = new ArrayList<Nodo>();
		m_TabelaDeNodos = tabelaDeNodos;
		m_Palavras = new ArrayList<String>();
	}
	
	public ArrayList<Nodo> getNodos() {
		return m_Nodos;
	}

	public void setNodos(ArrayList<Nodo> m_Nodos) {
		this.m_Nodos = m_Nodos;
	}
	
	public void NovoNodo(LayoutParams param)
	{
		Nodo _newNodo = new Nodo();
		
		int _qtdeNodos =  m_Nodos.size();
		
		_qtdeNodos++;
		
		String _nodoNome = "q" + String.valueOf(_qtdeNodos);
		_newNodo.Novo(m_Context, _nodoNome);
		_newNodo.getACircle().setTag(_nodoNome);
		_newNodo.getACircle().setOnDragListener(new NodeDragEventListener(this));
		_newNodo.getACircle().setOnLongClickListener(getOnLongListener());
		//_newNodo.getACircle().setOnTouchListener(getOnTouchListener());
		
		//CustomCircle _cc = new CustomCircle(m_container.getContext());
		
		
		m_container.addView(_newNodo.getACircle(), param);
		m_Nodos.add(_newNodo);
	}

	public View NovoText(Context context, String nome)
	{
		TextView _textView = new TextView(context);
		Typeface _tf = Typeface.createFromAsset(context.getAssets(), "fonts/lettre.ttf");
		
		_textView.setTypeface(_tf);
		_textView.setText(nome);
		_textView.setTextSize(18);
		
		return _textView;
	}
	
	private OnTouchListener getOnTouchListener() {
		return new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) 
			{
				if	(m_NodoTouched == null)
				{
					m_NodoTouched = getNode(view.getTag().toString());
				}
				else
				{
					Nodo _nodeTarget = getNode(view.getTag().toString());
					m_NodoTouched.ConectarNodo(_nodeTarget, "a");
					
					
					String _name = "(" + m_NodoTouched.getNome() + ", a) = " + _nodeTarget.getNome();
					View  _text = NovoText(m_TabelaDeNodos.getContext(), _name);
					m_TabelaDeNodos.addView(_text);
					
					m_NodoTouched = null;
				}
				
				return false;
			}
		};
	}
	
	private Nodo getNode(String nome)
	{
		Nodo _result = null;
		
		for (Nodo _nodo : m_Nodos)
		{
			if ( _nodo.getNome().equalsIgnoreCase(nome))
			{
				_result = _nodo;
				break;
			}
		}
		
		return _result;
	}

	private OnLongClickListener getOnLongListener() {
		// TODO Auto-generated method stub
		return new OnLongClickListener() 
		{			
			@Override
			public boolean onLongClick(View arg0) 
			{
				ClipData.Item _clipItem = new ClipData.Item(((ACircle)arg0).getNome());
				
				ClipData dragData = new ClipData(((ACircle)arg0).getNome(), new String[]{"text/plain"}, _clipItem);
				
				View.DragShadowBuilder _myShadow = new NodeDragShadowBuilder((ACircle)arg0);
				
				boolean _result = arg0.startDrag(dragData, _myShadow, null, 0);
				return _result;
			}
		};
	}
	
	private void escreverDadosEmTela(String descricao)
	{
		View  _text = NovoText(m_TabelaDeNodos.getContext(), descricao);
		
		m_TabelaDeNodos.addView(_text);
	}
	
	private void conectarNodos(String nodoOrigem, String nodoDestino, String palavra) 
	{
		Nodo _nodoOrigem = getNode(nodoOrigem);
		Nodo _nodoDestino = getNode(nodoDestino);
		
		String _response = _nodoOrigem.ConectarNodo(_nodoDestino, palavra);
		
		if (_response == "")
		{
			String _name = "("+nodoOrigem+", "+ palavra + ") = " + nodoDestino;
			escreverDadosEmTela(_name);
			m_Palavras.add(palavra);
		}
		else
		{
			Toast.makeText(m_Context, _response, 25000).show();
		}
	}
	
	@Override
	public void ConectarNodos(String nodoOrigem, String nodoDestino) 
	{
		m_NodoOrigem = nodoOrigem;
		m_NodoDestino = nodoDestino;
		
		callDialogPalavra();
	}

	
	private void closeDialog()
	{
		m_AlertDialog.dismiss();
	}
		
	private void callDialogPalavra()
	{
		AlertDialog.Builder builder;
        LayoutInflater inflater = (LayoutInflater)m_Context.getSystemService(m_Context.LAYOUT_INFLATER_SERVICE);
        
        View layout = inflater.inflate(R.layout.activity_dialog_palavra, null);
        ImageButton _btnConfirmar = (ImageButton)layout.findViewById(R.palavra.botao_confirmar);
        ImageButton _btnCancelar = (ImageButton)layout.findViewById(R.palavra.botao_cancelar);
        final EditText _editPalavra = (EditText)layout.findViewById(R.palavra.edittext_palavra);
        
        _btnConfirmar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				if (!_editPalavra.getText().toString().equalsIgnoreCase(""))
				{
					conectarNodos(m_NodoOrigem, m_NodoDestino, _editPalavra.getText().toString());
				}
				
				closeDialog();
			}
		});        
        _btnCancelar.setOnClickListener(new OnClickListener()
        {			
			@Override
			public void onClick(View arg0) 
			{
				closeDialog();
			}
		});

        builder = new AlertDialog.Builder(m_Context);
        builder.setView(layout);
        m_AlertDialog = builder.create();
        m_AlertDialog.show();		
	}

	public String Determinizar() 
	{
		String _result = "";
		ArrayList<Nodo> _nodosCompostos = new ArrayList<Nodo>();
		
		escreverDadosEmTela("Derminização:");
		
		String _palavras = "";
		for (String _pal : m_Palavras)
		{
			_palavras = _palavras + _pal;
		}
		
		escreverDadosEmTela("Palavras");
		escreverDadosEmTela(_palavras);
		
		boolean _novosdados = false;
		for (Nodo _nodo: m_Nodos)
		{
			// retorna nodos compostos
			ArrayList<Nodo> _responseCompostos = _nodo.RetornaNodoComposto();	
			
			if (existemNodosNovos(_responseCompostos))
			{
				escreverDadosEmTela("Nodos Compostos");
			
				for (Nodo _nodoComp : _responseCompostos)
				{
					_nodosCompostos.add(_nodoComp);				
					m_Nodos.add(_nodoComp);
					escreverDadosEmTela(_nodoComp.getNome());
				}
			}
		}
		
		determinizar(_nodosCompostos);
		
		if (_nodosCompostos.size() == 0)
		{
			escreverDadosEmTela("AFD");
			Toast.makeText(m_TabelaDeNodos.getContext(), "Não há necessidade de determinização !", 25000).show();
		}
		
		return _result;
	}
	
	private ArrayList<Nodo> determinizar(ArrayList<Nodo> nodosCompostos)
	{
		ArrayList<Nodo> _result = new ArrayList<Nodo>();
		
		for (Nodo _nodo : nodosCompostos)
		{
			//for (String _palavra : m_Palavras)
			//{
				ArrayList<Nodo> _nodosCompostos = _nodo.RetornaNodoComposto();
			//}
		}

		//caso n�o tenha retornado nenhum NODO novo FIM, sen�o recurs�o
		
		
		return _result;
	}
	
	private boolean existemNodosNovos(ArrayList<Nodo> nodosCompostos)
	{
		return nodosCompostos.size() > 0;
		
//		boolean _result = false;
//
//		for (Nodo _nodoPrincipal : nodosCompostos)
//		{
//			for (Nodo _nodo : m_Nodos)
//			{
//				if (_nodo.getNome() != _nodoPrincipal.getNome())
//				{
//					_result = true;
//					break;
//				}				
//			}
//			
//			if (_result)
//			{
//				break;
//			}			
//		}
//				
//		return (nodosCompostos.size() > 0);
	}
}
