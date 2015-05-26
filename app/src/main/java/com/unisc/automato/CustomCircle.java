package com.unisc.automato;



import com.unisc.trablinguagensformais.R;

import android.content.Context;
import android.view.*;

public class CustomCircle extends ViewGroup
{
	

	public CustomCircle(Context context) {
		super(context);
		
		LayoutInflater.from(context).inflate(R.automato_circle.relativeContainer, this, true);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

}
