package com.unisc.automato;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class NodeDragEventListener implements View.OnDragListener 
{
	private INodeEvents m_NodeEvents = null;
	
	public NodeDragEventListener(INodeEvents nodeEvents) 
	{
		m_NodeEvents = nodeEvents;
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
					//view.setBackgroundColor(Color.argb(125, 0, 0, 0));
					
					view.invalidate();
					
					return true;
				}
				else
				{
					return false;
				}
				
			case DragEvent.ACTION_DRAG_ENTERED:

				//view.setBackgroundColor(Color.GREEN);
				
				view.invalidate();
				
				return true;
			
			case DragEvent.ACTION_DRAG_LOCATION:
				return true;
				
			case DragEvent.ACTION_DRAG_EXITED:
				
				//view.setBackgroundColor(Color.argb(125, 0, 0, 0));
				
				view.invalidate();
				
				return true;
				
			case DragEvent.ACTION_DROP:
				
				ClipData.Item _item = event.getClipData().getItemAt(0);
				
				String _nodoOrigem = _item.getText().toString();
				String _nodoDestino = view.getTag().toString();
				
				m_NodeEvents.ConectarNodos(_nodoOrigem, _nodoDestino);
				
				//view.setBackgroundColor(Color.GREEN);
				
				view.invalidate();
		
				return true;
				
		     case DragEvent.ACTION_DRAG_ENDED:

                 // Turns off any color tinting
                 //view.setBackgroundColor(Color.TRANSPARENT);

                 // Invalidates the view to force a redraw
                 view.invalidate();


                 // returns true; the value is ignored.
                 return true;				
		}
		
		
		// TODO Auto-generated method stub
		return false;
	}

}