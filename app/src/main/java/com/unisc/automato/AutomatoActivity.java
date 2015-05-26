package com.unisc.automato;

import java.util.ArrayList;

import com.unisc.trablinguagensformais.R;
import com.unisc.trablinguagensformais.R.layout;
import com.unisc.trablinguagensformais.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AutomatoActivity extends Activity 
{	
	private RelativeLayout m_MainContainer = null;
	private Automato m_Automato = null;
	private EditText m_EditTextSentenca;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_automato);
		
		m_MainContainer = (RelativeLayout)this.findViewById(R.automato.main_container);
		m_MainContainer.setOnTouchListener(getContainerTouchLinetener());
		LinearLayout m_containerNodos = (LinearLayout)this.findViewById(R.automato.nodos);
		
		m_Automato = new Automato(m_MainContainer, m_containerNodos);
		
		m_EditTextSentenca = (EditText)this.findViewById(R.automato.edittext_sentenca);
		m_EditTextSentenca.setOnKeyListener(null);
		
		ImageButton _buttonDetermine = (ImageButton)this.findViewById(R.automato.button_determin);
		_buttonDetermine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				m_Automato.Determinizar();
			}
		});

		ImageButton _buttonVoz = (ImageButton)this.findViewById(R.automato.button_voz);
		_buttonVoz.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View arg0) 
			{
				getTextFromSpeech();
			}
		});
	}

	private int qrCount = 1;
	private OnTouchListener getContainerTouchLinetener() 
	{
		return new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						
						TextView _textV = new TextView(AutomatoActivity.this);
						_textV.setText(String.valueOf(qrCount));
						_textV.setTextSize(20);
						
						RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
						params.leftMargin = (int) event.getX();
						params.topMargin = (int)event.getY();
						
						m_Automato.NovoNodo(params);

						qrCount++;
						
						break;
						
					case MotionEvent.ACTION_MOVE:
						break;
				}
				
				return false;
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_automato, menu);
		return true;
	}
	
	private String getTextFromSpeech()
	{
		 Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pt-BR");

         try {
             startActivityForResult(intent, 1);
         } catch (ActivityNotFoundException a) {
             Toast t = Toast.makeText(getApplicationContext(),
                     "Opps! Your device doesn't support Speech to Text",
                     Toast.LENGTH_SHORT);
             t.show();
         }		
	
		return "";
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) 
		{
	        case 1: {
	            if (resultCode == RESULT_OK && null != data) {
	 
	                ArrayList<String> text = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	 
	                m_EditTextSentenca.setText(text.get(0));;
	            }
	            break;
	        }
		 }

	}
}
