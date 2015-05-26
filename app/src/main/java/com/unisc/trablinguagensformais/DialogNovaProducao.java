package com.unisc.trablinguagensformais;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class DialogNovaProducao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_nova_producao);
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dialog_nova_producao, menu);
		return true;
	}
	
	
	private void SetTextViewProperties(TextView[] args)
	{
		Typeface _tf = Typeface.createFromAsset(getAssets(), "fonts/lettre.ttf");

		for (TextView _text : args)
		{
			_text.setTypeface(_tf);
			_text.setTextSize(20);
			_text.setTag("tag_"+_text.getText().toString());
		}
	}


}
