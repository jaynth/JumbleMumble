package jaynth.android.jumblemumble;

/* This class holds the logic for the user to configure all the game options 
 * such as Difficulty Levels and Sound Enable/Disable 1872 
 * 5895 4100 1089 57657
 */

import android.app.Activity;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;
import android.content.Intent;

import jaynth.android.jumblemumble.StartScreen;
import jaynth.android.jumblemumble.LevelGen;



public class OptionScreen extends Activity implements OnClickListener,RadioGroup.OnCheckedChangeListener,ToggleButton.OnCheckedChangeListener {
	
	/* Declaring Variable for all the necessary views on the layout*/
	private Button btnBack;
	private RadioButton rdbtnEasy;
	private RadioButton rdbtnMedium;
	private RadioButton rdbtnHard;
	private RadioGroup rdgpDifficulty;
	private ToggleButton tglSound;
	
	private LevelGen level;
	
	/*global static reference variables for setting difficulty levels and sound*/
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		/*Set initial difficulty level to Medium*/
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_screen);
		
		/*Referencing the Views defined in the layout*/		
		this.btnBack = (Button)findViewById(R.id.Back);
		this.rdbtnEasy = (RadioButton)findViewById(R.id.rdbtnEasy);
		this.rdbtnMedium = (RadioButton)findViewById(R.id.rdbtnMedium);
		this.rdbtnHard = (RadioButton)findViewById(R.id.rdbtnHard);
		this.rdgpDifficulty = (RadioGroup)findViewById(R.id.rdgpDifficulty);
		this.tglSound = (ToggleButton)findViewById(R.id.tglSound);
		
		
		this.btnBack.setOnClickListener(this);
		this.rdgpDifficulty.setOnCheckedChangeListener(this);
		this.tglSound.setOnCheckedChangeListener(this);
		
		this.rdbtnEasy.setId(0);
		this.rdbtnMedium.setId(1);
		this.rdbtnHard.setId(2);
		
		if(StartScreen.Option_load<2)
		{
			rdgpDifficulty.check(0);
			//level = new LevelGen(0);
			tglSound.setChecked(true);
		}
		else
		{
			rdgpDifficulty.check(StartScreen.difficulty_level);
			//level.setLevel(StartScreen.difficulty_level);
			tglSound.setChecked(StartScreen.sound);
		}
	} 
	
	public void onClick(View v)
	{
		Button btnClicked = (Button)v;
		if (btnClicked == btnBack)
		{
			finish();
		}
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		StartScreen.difficulty_level = checkedId;
		//level.setLevel(StartScreen.difficulty_level);
		
		
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		StartScreen.sound = isChecked;
	}
	
	

}
