package com.andy.propertyanimaton;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

public class MyPointEvaluator implements TypeEvaluator<PointF>{

	@Override
	public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
		// TODO Auto-generated method stub
		Log.e("Animation", "fraction:" + fraction);
		PointF startPoint = (PointF) startValue;
		PointF endPoint = (PointF) endValue;
		
		return new PointF(startPoint.x + fraction * (endPoint.x - startPoint.x),
				startPoint.y + fraction * (endPoint.y - startPoint.y));
	}

	

}
