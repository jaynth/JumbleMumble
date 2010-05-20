package jaynth.android.jumblemumble;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class TimerThread extends Thread implements Runnable 
{
	
	private Handler mainThreadHandler;
	private Bundle data;
	private Message msg;
	public boolean killthread = false;

	
	TimerThread(Handler mainHandler)
	{
		this.mainThreadHandler = mainHandler;
	}

	public void run() 
	
	{
	
			while (LevelGen.time >=0)
				{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(LevelGen.time!=0)
				{
					LevelGen.time--;
				}
				if(LevelGen.time >= 0)
					{
					msg = mainThreadHandler.obtainMessage();
					mainThreadHandler.sendMessage(msg);
					}
				if(GameScreen.threadSTATUS == false)
				{
					break;
				}
				}					
			try 
				{
								
					this.finalize();
				} 
			catch (Throwable e) 
				{
				
				e.printStackTrace();
				}
		
			
				
				}
		
	
	}


