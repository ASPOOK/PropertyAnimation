package com.andy.propertyanimaton;

import android.graphics.PointF;
import android.view.View;

public class MyAnimatableView {
	
	PointF curPoint = null;
	View mView = null;
	
	public MyAnimatableView (View v) {
		curPoint = new PointF(v.getX(), v.getY());
		mView = v;
	}
	
	public PointF getPoint() {
		return curPoint;
	}
	
	// ��set��������������Ҫ����Ҫ������������һ�£�setPointF����Ч
	public void setPoint(PointF p) {
		curPoint = p;
		mView.setX(p.x);
		mView.setY(p.y);
	}

}
