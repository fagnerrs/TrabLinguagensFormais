package com.unisc.trablinguagensformais;

import com.unisc.gramatica.IDragMetodos;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.*; 
import android.view.View.OnLongClickListener;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MyDragEventListener implements View.OnDragListener
{
	private IDragMetodos m_Dragmetodos = null;

	public MyDragEventListener(IDragMetodos dragMetodos) 
	{
		m_Dragmetodos = dragMetodos;
	}
	
	@Override
	public boolean onDrag(View view, DragEvent event) 
	{
		final int _action = event.getAction();
		
		switch (_action)
		{
			case DragEvent.ACTION_DRAG_STARTED:
				
				if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
				{
					view.setBackgroundColor(Color.argb(125, 0, 0, 0));
					
					view.invalidate();
					
					return true;
				}
				else
				{
					return false;
				}
				
			case DragEvent.ACTION_DRAG_ENTERED:

				view.setBackgroundColor(Color.GREEN);
				
				view.invalidate();
				
				return true;
			
			case DragEvent.ACTION_DRAG_LOCATION:
				return true;
				
			case DragEvent.ACTION_DRAG_EXITED:
				
				view.setBackgroundColor(Color.argb(125, 0, 0, 0));
				
				view.invalidate();
				
				return true;
				
			case DragEvent.ACTION_DROP:
				
				ClipData.Item _item = event.getClipData().getItemAt(0);
				
				TextView _text = novoTextView(view.getContext(), _item.getText().toString().trim());
				_text.setOnLongClickListener(getOnLongClick((ViewGroup)view));
				
				((ViewGroup)view).addView(_text);
				
				view.setBackgroundColor(Color.TRANSPARENT);
				
				view.invalidate();
		
				return true;
				
		     case DragEvent.ACTION_DRAG_ENDED:

                 // Turns off any color tinting
                 view.setBackgroundColor(Color.TRANSPARENT);

                 // Invalidates the view to force a redraw
                 view.invalidate();


                 // returns true; the value is ignored.
                 return true;				
		}
		
		
		// TODO Auto-generated method stub
		return false;
	}


	private TextView novoTextView(Context context, String nome)
	{
		TextView _textView = new TextView(context);
		Typeface _tf = Typeface.createFromAsset(context.getAssets(), "fonts/lettre.ttf");
		
		_textView.setTypeface(_tf);
		_textView.setTag("tag_"+_textView.getText().toString());
		_textView.setText(nome);
		_textView.setTextSize(48);
		
		return _textView;
	}
	
	private OnLongClickListener getOnLongClick(final View view)
	{
		return new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) 
			{
				ViewGroup _container= (ViewGroup)v.getParent();
				
				
				_container.removeView(v);
				
				return false;
			}
		};
	}
}
