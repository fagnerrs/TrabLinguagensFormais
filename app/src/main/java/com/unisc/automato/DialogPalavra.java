package com.unisc.automato;

import com.unisc.trablinguagensformais.R;
import com.unisc.trablinguagensformais.R.layout;
import com.unisc.trablinguagensformais.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class DialogPalavra extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_palavra);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dialog_palavra, menu);
		return true;
	}
	
	public void Confirmar(View view)
	{
		this.finish();
	}
	
	public void Cancelar(View view)
	{
		this.finish();
	}
	
}
