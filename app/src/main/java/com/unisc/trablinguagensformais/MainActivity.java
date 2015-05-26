package com.unisc.trablinguagensformais;

import java.util.ArrayList;

import com.unisc.automato.AutomatoActivity;
import com.unisc.gramatica.*;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;


public class MainActivity extends Activity  {

	private Gramatica m_Gramatica = null;
	private AlertDialog m_AlertDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewGroup _NaoTerminais = (LinearLayout)this.findViewById(R.act_main.containerNaoTerminais);
		ViewGroup _Terminais = (LinearLayout)this.findViewById(R.act_main.containerTerminais);
		ViewGroup _Producoes = (ViewGroup)this.findViewById(R.act_main.cont_producoes);

		TextView _gramatica_title = (TextView) this.findViewById(R.act_main.gramatica_title);;
		TextView _gramatica = (TextView) this.findViewById(R.act_main.gramatica);;
		TextView _separador = (TextView) this.findViewById(R.act_main.separador);;
		
		SetTextViewProperties(new TextView[] {_gramatica_title, _gramatica, _separador} );
		
		_separador.setOnLongClickListener(Simbolo.getOnLongListener());
		
		ImageButton _buttonAutomatos = (ImageButton)this.findViewById(R.act_main.button_automatos);
		_buttonAutomatos.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) {
				Intent _intentAutomatos = new Intent(MainActivity.this, AutomatoActivity.class);
				startActivity(_intentAutomatos);				
			}
		});

		// Botão Adicionar produções
		ImageButton _buttonAddProducao = (ImageButton)this.findViewById(R.act_main.button_add_producao);
		_buttonAddProducao.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				callAddTerminaisNaoTerminais();
			}
		});
		
		m_Gramatica = new Gramatica(MainActivity.this, _Terminais, _NaoTerminais, _Producoes);

		m_Gramatica.AddNaoTerminal("S");
//		m_Gramatica.AddNaoTerminal("A");
//		m_Gramatica.AddNaoTerminal("B");
//		
//		m_Gramatica.AddTerminal("a");
//		m_Gramatica.AddTerminal("b");
	}
	
	private void closeDialog()
	{
		m_AlertDialog.dismiss();
	}
	
	private void callAddTerminaisNaoTerminais()
	{
		
			AlertDialog.Builder _builder;
	        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
	        
	        View layout = inflater.inflate(R.layout.activity_dialog_nova_producao, null);
	        ImageButton _btnConfirmar = (ImageButton)layout.findViewById(R.nova_prod.botao_confirmar);
	        ImageButton _btnCancelar = (ImageButton)layout.findViewById(R.nova_prod.botao_cancelar);
	        
			TextView _textNaoTerminal = (TextView)layout.findViewById(R.nova_prod.text_nao_terminal);
			TextView _textTerminal = (TextView)layout.findViewById(R.nova_prod.text_terminal);

			final EditText _editNaoTerminal = (EditText)layout.findViewById(R.nova_prod.edittext_nao_terminal);
			final EditText _editTerminal = (EditText)layout.findViewById(R.nova_prod.edittext_terminal);

	        
	        _btnConfirmar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) 
				{
					if (!_editNaoTerminal.getText().toString().equalsIgnoreCase(""))
					{
						m_Gramatica.AddNaoTerminal(_editNaoTerminal.getText().toString());
					}
					
					if (!_editTerminal.getText().toString().equalsIgnoreCase(""))
					{
						m_Gramatica.AddTerminal(_editTerminal.getText().toString());
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

	        _builder = new AlertDialog.Builder(this);
	        _builder.setView(layout);
	        m_AlertDialog = _builder.create();
	        m_AlertDialog.show();		
		
		
	}

	private void SetTextViewProperties(TextView[] args)
	{
		Typeface _tf = Typeface.createFromAsset(getAssets(), "fonts/lettre.ttf");

		for (TextView _text : args)
		{
			_text.setTypeface(_tf);
			_text.setTag("tag_"+_text.getText().toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		 switch (requestCode) {
	        case 1: {
	            if (resultCode == RESULT_OK && null != data) {
	 
	                ArrayList<String> text = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	 
	                Toast.makeText(this, text.get(0), 15000).show();
	            }
	            break;
	        }
		 }
	
	}
	
}