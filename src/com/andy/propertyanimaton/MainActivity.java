package com.andy.propertyanimaton;

import android.os.Bundle;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.PointF;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView mTextView;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView = (TextView) findViewById(R.id.tv_id);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		
		setClickListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * FadeOut、FadeIn
	 */
	public void setClickListeners() {
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mTextView.getAlpha() != 0) {
					// 从当前的alpha值到0,对于作动画的属性，对应view应该具有对应的set方法：view.setAlpha(float f)
					ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mTextView, "alpha", 0f);
					fadeOut.setDuration(3000);
					fadeOut.start();
					
					btn1.setText("FadeIn");
				} else {
					ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mTextView, "alpha", 1f);
					fadeIn.setDuration(3000);
					fadeIn.start();
					
					btn1.setText("FadeOut");
				}
			}
		});
		
		/**
		 * FadeOut、FadeIn Sequentially
		 */
        btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mTextView, "alpha", 0f);
				ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mTextView, "alpha", 1f);
				
				AnimatorSet set = new AnimatorSet();
				set.playSequentially(fadeOut, fadeIn);
				//set.playTogether(items);
				set.setDuration(3000);
				set.start();	
			}
		});
        
        /**
         * FadeOut、FadeIn Builder
         */
        btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mTextView, "alpha", 0f);
				ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mTextView, "alpha", 1f);
				
				AnimatorSet set = new AnimatorSet();
				AnimatorSet.Builder builder = set.play(fadeOut);
				builder.before(fadeIn);// 还有after和with
				//set.play(fadeOut).before(fadeIn);// 还有after和with
				
				set.setDuration(3000);
				set.start();
			}
		});
        
        /**
         * FadeOut、FadeIn XML
         */
        btn4.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.fadein);
        		set.setTarget(mTextView);
        		set.start();	
        	}
        });
        
        /**
         * PropertyValuesHolder
         */
        btn5.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		float h = mTextView.getHeight();
        		float w = mTextView.getWidth();
        		float x = mTextView.getX();
        		float y = mTextView.getY();
        		//Log.e("Animation", "h:" + h + ",w:" + w + ",x:" + x + ",y:" + y);
        		// 把TextView放到右上侧
        		mTextView.setX(w);
        		mTextView.setY(h);
        		
        		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", x);
        		PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", y);
        		
        		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, pvhX, pvhY);
        		animator.setDuration(3000);
        		animator.setInterpolator(new AccelerateDecelerateInterpolator());
        		animator.start();	
        	}
        });
        
        /**
         * ViewPropertyAnimator
         * This class enables automatic and optimized animation of select properties on View objects.
         * If only one or two properties on a View object are being animated, 
         * then using an android.animation.ObjectAnimator is fine; 
         * the property setters called by ObjectAnimator are well equipped to do the right thing to set
         * the property and invalidate the view appropriately. But if several properties are animated simultaneously,
         * or if you just want a more convenient syntax to animate a specific property, then ViewPropertyAnimator might be more well-suited to the task. 
         * This class may provide better performance for several simultaneous animations, 
         * because it will optimize invalidate calls to take place only once for several properties 
         * instead of each animated property independently causing its own invalidation. 
         * Also, the syntax of using this class could be easier to use because 
         * the caller need only tell the View object which property to animate, 
         * and the value to animate either to or by, and this class handles the details of configuring the underlying Animator class and starting it.
         */
        btn6.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		//float h = mTextView.getHeight();
        		//float w = mTextView.getWidth();
        		float x = mTextView.getX();
        		float y = mTextView.getY();
        		
        		// 把TextView放到上边
        		mTextView.setX(0);
        		mTextView.setY(0);
        		//API level 12 
        		ViewPropertyAnimator vpa = mTextView.animate();
        		vpa.x(x);
        		vpa.y(y);
        		
        		vpa.setDuration(3000);
        		vpa.setInterpolator(new AccelerateDecelerateInterpolator()); 
        		// 当UI线程处理到这里时会自动运行动画，不需要显示调用start
        		//vpa.start();
        		
        	}
        });
        
        /**
         * Type Evaluator
         */
        btn7.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		float h = mTextView.getHeight();
        		float w = mTextView.getWidth();
        		float x = mTextView.getX();
        		float y = mTextView.getY();
        		
        		PointF startPoint = new PointF(w, h);
        		PointF endPoint = new PointF(x, y);
        		
        		MyAnimatableView mAnimatableView = new MyAnimatableView(mTextView);
        		
        		// 属性名应写为point或Point
        		ObjectAnimator animator = ObjectAnimator.ofObject(mAnimatableView, "point", new MyPointEvaluator() , startPoint, endPoint);
        		animator.setDuration(3000);
        		animator.start();     		       		
        		
        		/*// 测试属性动画移动一个button后，其可点击位置也随之改变
        		ObjectAnimator animator = ObjectAnimator.ofFloat(btn8, "y", y + 30);
        		animator.setDuration(3000);
        		animator.start();*/
        	}
        });
        
        /**
         * Key Frames
         */
        btn8.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		//float h = mTextView.getHeight();
        		float w = mTextView.getWidth();
        		float x = mTextView.getX();
        		//float y = mTextView.getY();
        		
        		Keyframe kf0 = Keyframe.ofFloat(0.2f,0.8f);
        		Keyframe kf1 = Keyframe.ofFloat(0.5f,0.2f);
        		Keyframe kf2 = Keyframe.ofFloat(0.8f,0.8f);
        		
        		PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofKeyframe("alpha", kf0, kf1, kf2);
        		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", w, x);
        		
        		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, pvhAlpha, pvhX);
        		animator.setDuration(3000);
        		animator.start();
        		
        	}
        });




	}
	
	
	


}
