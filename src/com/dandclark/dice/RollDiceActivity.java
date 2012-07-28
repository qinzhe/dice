package com.dandclark.dice;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RollDiceActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*RelativeLayout relativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);*/
		
		LinearLayout linearLayout = new LinearLayout(this);
		LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setGravity(Gravity.CENTER);
		
		/*rollText = new TextView(this);
		rollText.setTextSize(40);
		rollText.setText(Integer.toString(rollDie()));
		rollText.setId(1); // @todo[DDC] Is there a preferred way of choosing IDs?
		RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		textLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		//rollText.setLayoutParams(textLayoutParams);
		relativeLayout.addView(rollText, textLayoutParams);*/
		
		rollImage = new ImageView(this);
		rollImage.setId(1);
		rollImage.setImageResource(getImageFromRoll(getRollNumber()));
		LinearLayout.LayoutParams rollImageLayoutParams = new LinearLayout.LayoutParams(
				200, 200);
				//LinearLayout.LayoutParams.WRAP_CONTENT,
				///LinearLayout.LayoutParams.WRAP_CONTENT);
		linearLayout.addView(rollImage, rollImageLayoutParams);
		
		// Insert some empty space between the die and the roll button
		View space = new View(this);
		LinearLayout.LayoutParams spaceLayoutParams
				= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 60);
		linearLayout.addView(space, spaceLayoutParams);
		
		button = new Button(this);
		button.setText("Roll");
		button.setOnClickListener(clickListener);
		LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		button.setLayoutParams(buttonLayoutParams);
		button.setVisibility(View.INVISIBLE);
		linearLayout.addView(button);
		
		setContentView(linearLayout, linearLayoutParams);
		
		performRoll();
	}
	
	private void performRoll() {
		// Hide the button while we perform an animation
		button.setVisibility(View.INVISIBLE);
        Handler handler = new Handler();
        final int NUM_CHANGES = 5;
        final int DELAY_AMOUNT_MILLIS = 200;
        // Spawn a series of handlers to perform a roll animation
        for(int i=0; i < NUM_CHANGES; i++) {
	        handler.postDelayed(
	        		new Runnable() { 
	        			public void run() {
	        				rollImage.setImageResource(getImageFromRoll(getRollNumber()));
	        			}
	        		},
	        		DELAY_AMOUNT_MILLIS * i);
		}
		
		// Put the button back after the roll animation has finished
		handler.postDelayed(
				new Runnable() {
					public void run() {
						button.setVisibility(View.VISIBLE);
						}
					},
					DELAY_AMOUNT_MILLIS * NUM_CHANGES);
    }
    
	/** Given an integer roll result from 1 to 6, return the corresponding image pointer from R. */
	private int getImageFromRoll(int rollResult) {
		switch(rollResult) {
		case 1:
			return R.drawable.dice1md;
		case 2:
			return R.drawable.dice2md;
		case 3:
			return R.drawable.dice3md;
		case 4:
			return R.drawable.dice4md;
		case 5:
			return R.drawable.dice5md;
		case 6:
			return R.drawable.dice6md;
		default:
			throw new RuntimeException("Invalid roll result");
		}
	}
	
    private int getRollNumber() {
    	Random random = new Random(System.currentTimeMillis());
    	return random.nextInt(6) + 1;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
    	public void onClick(View view) {
    		performRoll();
    	}
    };
    
    //private TextView rollText = null;
    private Button button = null;
    private ImageView rollImage = null;
    
}
