package jaynth.android.jumblemumble;



public class LevelGen {
	
	public static int time = 59;
	public static int score_factor = 10;
	
	LevelGen(int difficulty)
	{
		this.setLevel(difficulty);
				
	}
	
	public void setLevel(int difficulty)

	{
		if (difficulty == 0)
		{
			LevelGen.time = 59;
			LevelGen.score_factor = 10;
		}
		else if(difficulty == 1)
		{
			LevelGen.time = 30;
			LevelGen.score_factor = 20;
		}
		else if(difficulty == 2)
		{
			LevelGen.time = 15;
			LevelGen.score_factor = 30;
		}
		else
		{
			LevelGen.time = -1;
		}
	

	}
}
