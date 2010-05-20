package jaynth.android.jumblemumble;

import java.io.*;
import java.util.Random;

public class WordsGenerator{
	
	public String[][] words =  new String[50][3] ;
	public static int wordCount = 0;
	public int list_length = 0;
	
	
	WordsGenerator(InputStream file)
	{
		try {
			int i = 0;
		    BufferedReader reader = new BufferedReader( new InputStreamReader(file));
		    String line  = null;
		    String[] temp = new String[2];
		    words = new String[50][3];
		    while( ( line = reader.readLine() ) != null) 
		    	{
		    	temp = line.split(" - ");
		    	words[i][0] = temp[0];
		    	words[i][1] = temp[1];
		    	words[i][2] = "0";
		    	
		    	list_length++;
		    	
		    	//System.out.println("Word:" + words[i][0].toString());
		    	//System.out.println("Meaning:" + words[i][1].toString());
		    	//System.out.println("used/unused:" + words[i][2].toString());
			    i++;
				}
		    
			} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String[] nextWord()
	{
		
		
		System.out.println("Function called");
		String jword = null; 
		int count = 0;
		String answer[] = new String[4];
		Random gen = new Random();
		int randomword_index = gen.nextInt(list_length-1);
		System.out.println("randomword_index :" + Integer.toString(randomword_index));
		System.out.println(this.words[randomword_index][2].contains("1"));
		System.out.println("randomword_index outside while:" + Integer.toString(randomword_index));
		while(this.words[randomword_index][2].contains("1"))
		{
			System.out.println("randomword_index inside while:" + Integer.toString(randomword_index));
			count++;
			randomword_index = gen.nextInt(list_length-1);
			if (count == list_length-1)
				break;
			
		}
		if(count!= list_length)
		{
		jword = this.words[randomword_index][0];
		System.out.println(jword);
		jword = this.jumble(jword);
		answer[0] = jword;
		answer[1] = this.words[randomword_index][0];
		answer[2] = this.words[randomword_index][1];
		answer[3] = this.words[randomword_index][2];
		words[randomword_index][2] = "1";
		System.out.println(answer[0]+answer[1]+answer[2]);
		return answer;
		}
		else
			System.out.println("Null returned");
			return null;

	}
	 
	public String jumble(String word)
	{
		Random gen = new Random();
		int len = word.length()-1;
		//System.out.println("Word " + word);
		//System.out.println("Length " + Integer.toString(len));
		int c = 0, other = 0;
		char temp;
		char[] jumble = word.toCharArray();
		//System.out.println(jumble);
		for (c=0;c <= len;c++)
		{
		  //swap two chars
		  temp = jumble[c];
		  other = gen.nextInt(len-1);
		  //System.out.println(Integer.toHexString(other));
		  jumble[c] = jumble[other];
		  jumble[other] = temp;
		  //System.out.println(jumble);
		}
		
		String jumbled_word = new String(jumble);
		//System.out.println(jumbled_word);
		return jumbled_word;
	}
	 

	
	
	
	
}