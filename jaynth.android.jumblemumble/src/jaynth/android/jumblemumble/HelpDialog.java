/**
 * 
 */
package jaynth.android.jumblemumble;

/**
 * @author jaynth
 *
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.content.DialogInterface;



public class HelpDialog extends Activity implements DialogInterface.OnClickListener{

	 @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

    Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Jumble Mumble Help");
    builder.setMessage(R.string.help_text);
    builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
        	finish();
            return;
        } }); 
    builder.show(); 
    
  
	
}

	 public void onClick(DialogInterface dialog, int which) {
     	finish();
         return;
     }

}
