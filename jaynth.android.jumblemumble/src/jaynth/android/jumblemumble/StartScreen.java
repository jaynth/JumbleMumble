package jaynth.android.jumblemumble;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.content.Intent;

import jaynth.android.jumblemumble.OptionScreen;


public class StartScreen extends Activity implements OnClickListener {
	
	private Button options_button;
	private Button help_button;
	private Button start_button;
	private Button btnQuit;
	
	public static int difficulty_level=0;
	public static boolean sound=true;
	public static int Option_load =0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_screen);
		
		this.options_button = (Button)findViewById(R.id.btnOptions);
		this.help_button = (Button)findViewById(R.id.btnHelp);
		this.start_button = (Button)findViewById(R.id.btnStartGame);
		this.btnQuit = (Button)findViewById(R.id.btnQuit);
		
		this.options_button.setOnClickListener(this);
		this.help_button.setOnClickListener(this);
		this.start_button.setOnClickListener(this);
		this.btnQuit.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		Button btnClicked = (Button)v;
		if (btnClicked == this.options_button)
		{
			Option_load++;
			Intent i = new Intent(this,OptionScreen.class);
			startActivity(i);
		}
		else if (btnClicked == this.help_button)
		{
			Intent i = new Intent(this,HelpDialog.class);
			startActivity(i);
		}
		else if (btnClicked == this.start_button)
		{
			Intent i = new Intent(this,GameScreen.class);
			startActivity(i);
		}
		else if (btnClicked == this.btnQuit)
		{
			finish();
		}
	}
	
	
	@Override
	public void onResume()
	{
		super.onResume();
		if(sound)
		{
			
		}
				
	}
	
}
	
	
	
	

