package jaynth.android.jumblemumble;


import java.io.InputStream;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.IntentService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import jaynth.android.jumblemumble.TimerThread;
import jaynth.android.jumblemumble.OptionScreen;
import jaynth.android.jumblemumble.WordsGenerator;

public class GameScreen extends Activity implements OnClickListener
{
	
	public static final int QUIT_DIALOG = 1;
	public static final int TIMEOVER_DIALOG = 2;
	public static final int INVISBLE = 4;
	public static final int VISIBLE = 0;
	
	public static boolean threadSTATUS = true;
	
	
	
	public static int time_flag = 0;
	public static boolean game_over_flag = true;
	public int score = 0;
	
	private Handler mainThreadHandler;
	public Thread timerThread;
	public String[] currentWord;
	
	public  TextView lblTime ;
	public  Button btnQuit;
	public  Button btnSubmit;
	public  TextView lblScore;
	public  TextView lblWord;
	public  EditText txtAnswer;
	public  TextView lblMeaning;
	public  TextView lblIncorrect;
	public  TextView lblShowAnswer;
	
    
	public LevelGen level;
    private WordsGenerator words;
	    
  			
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
				
					super.onCreate(savedInstanceState);
					setContentView(R.layout.game_screen);
					
					lblTime = (TextView)findViewById(R.id.lblTime);
					btnQuit = (Button)findViewById(R.id.btngQuit);
					btnSubmit = (Button)findViewById(R.id.btnSubmit);
					lblScore = (TextView)findViewById(R.id.lblScore);
					lblWord = (TextView)findViewById(R.id.lblword);
					txtAnswer = (EditText)findViewById(R.id.txtAnswer);
					lblMeaning = (TextView)findViewById(R.id.lblMeaning);
					lblIncorrect = (TextView)findViewById(R.id.lblIncorrect);
					lblShowAnswer = (TextView)findViewById(R.id.lblShowAnswer);
					
					
					this.btnQuit.setOnClickListener(this);
					this.btnSubmit.setOnClickListener(this);
					this.lblShowAnswer.setOnClickListener(this);
					//this.txtAnswer.setOnClickListener(this);
					
					level = new LevelGen(StartScreen.difficulty_level);
					currentWord = new String[3];
					lblTime.setText("Time :" + Integer.toString(LevelGen.time));
					lblIncorrect.setVisibility(INVISBLE);
					lblShowAnswer.setVisibility(INVISBLE);
					
					InputStream wordlist = this.getResources().openRawResource(R.raw.words);
					words = new WordsGenerator(wordlist);
					
					WordsGenerator.wordCount = 0;
						    
				
					Handler mainThreadHandler = new Handler(){
						public void handleMessage(Message m){
							
							Bundle msg = m.getData();
							lblTime.setText("Time :" + Integer.toString(LevelGen.time));
							if(LevelGen.time == 0)
							{							
								showDialog(TIMEOVER_DIALOG);
								
							}
									
									
							}
						
				};
									
					this.timerThread = new Thread(new TimerThread(mainThreadHandler));
					timerThread.start();
					updateCurrentWord();														
	}
	
	public void onClick(View v)
	{
		
		
		if((Button)v == btnQuit)
		{
			this.showDialog(QUIT_DIALOG);
			
		}
		else if ((Button)v == btnSubmit)
		{	
			//timerThread.stop();
			//lblWord.setText(txtAnswr.getText().toString());
			//timerThread.stop();
			// If correct dialog + next word
			if(checkWord(txtAnswer.getText().toString())== true )
			{
				updateScore();
				updateCurrentWord();
			}
			
			else
			{
				lblIncorrect.setVisibility(VISIBLE);
				lblShowAnswer.setVisibility(VISIBLE);
			}
			// If wrong increment incorrect_countertimer
			
			// If incorrect_counter = 3 , game over dialog + finish()
		}
		else if (v == (View)lblShowAnswer)
		{
			
		}
		
			//Throw exception

	}
	
	public void updateCurrentWord()
	{
		GameScreen.threadSTATUS = true;
		currentWord = words.nextWord();
		lblWord.setText(currentWord[0].toLowerCase());
		lblMeaning.setText(currentWord[2]);
		level.setLevel(StartScreen.difficulty_level);
		txtAnswer.setText(null);
		
	}
	
	public boolean checkWord(String userInput)
	
	{
		//lblWord.setText(currentWord[1].toString().toLowerCase());
		//lblTime.setText(userInput);
		
		if(userInput.toLowerCase().equals(currentWord[1].toString().toLowerCase()) == true)
		{
			//lblTime.setText("True");
			return true;
			
		}
		else
		{
			
			return false;
		}
			
	}
	
	
	public void updateScore()
		{
		score = score + LevelGen.score_factor;
		lblScore.setText("Score:"+ Integer.toString(score));
		}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog=null;
	    AlertDialog.Builder builder=null;
	    switch(id) {
	    case GameScreen.TIMEOVER_DIALOG:
	    	builder = new AlertDialog.Builder(this);
	    	builder.setMessage(R.string.timer_dialog)
	    	       .setCancelable(false)
	    	       .setNegativeButton("Try Again",new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	  // timerThread.start();
	    	        	   updateCurrentWord();
	    	        	   dialog.cancel();
	    	        	  }
	    	           })
	    	       .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   GameScreen.threadSTATUS = false;
	    	        	   finish();
	    	               dialog.cancel(); 
	    	           }
	    	       })
	    	       ;
	    	dialog = builder.create();
	    	break;
	    case GameScreen.QUIT_DIALOG:
	    	builder = new AlertDialog.Builder(this);
	    	builder.setMessage(R.string.quit_dialog)
	    	       .setCancelable(true)
	    	       .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   dialog.cancel();
	    	        	  
	    	           }
	    	       })
	    	       .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   LevelGen.time = 0;
	    	        	   GameScreen.threadSTATUS = false;
	    	        	   finish();
	    	               dialog.cancel();
	    	           }
	    	       });
	    	dialog = builder.create();
	    	break;
	    }
	    return dialog;
	}
	//public void onResume()
	//{
	//	updateCurrentWord();
	//}
}
