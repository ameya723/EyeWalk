package ub.eyewalk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import android.os.CountDownTimer;

public class Intro extends Activity {

	ProgressDialog dialog;	//the dialog box for the progress bar
	LinearLayout layout;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        //for the background image(this can be commented out infact)
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        //bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bitmapDrawable.setGravity(Gravity.FILL_HORIZONTAL + Gravity.FILL_VERTICAL);
        layout = new LinearLayout(this);
        layout.setBackgroundDrawable(bitmapDrawable);
        
        setContentView(layout);
        
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage("Loading Map...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setProgress(0);
      
        dialog.setMax(100);	//setting the maximum value of progress bar to 100
        dialog.show();		//display the progrss bar
        
        ProgressTimer p = new ProgressTimer(4000, 400);
        p.start();
    }
	
	@Override
	public void onActivityResult(int code, int result, Intent i) {
		this.finish();
	}
	
	private class ProgressTimer extends CountDownTimer {
		
		public ProgressTimer(long duration, long tick) { super(duration, tick); }
		
		@Override
		public void onFinish() { 
			dialog.dismiss();	//once the progres bar touches 100, suspend the progress bar
      	   	Intent i = new Intent(layout.getContext(), EyeWalkActivity.class);
        	startActivityForResult(i, 0); 
		}

		@Override
		public void onTick(long millisUntilFinished) {
			dialog.incrementProgressBy(10);	//increment progress bar by values of 10
		}
	}
	
}
