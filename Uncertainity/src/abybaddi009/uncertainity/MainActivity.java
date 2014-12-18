package abybaddi009.uncertainity;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Button answer;
	TextView tv;
	int number;
	Random r;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = (Button) findViewById(R.id.button1);
        tv = (TextView) findViewById(R.id.tv1);
        
        answer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				r = new Random();
				number = r.nextInt(2);
				switch(number) {
					case 0:
						tv.setTextColor(Color.RED);
						tv.setText("Don't do it! Don't do it! Don't do it!");
						break;
					case 1:
						tv.setTextColor(Color.GREEN);
						tv.setText("Do it! Do it! Do it!");
						break;
					default:
						tv.setText("This should not happen!!!");
						break;
				}
			}
		});
        
        tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv.setTextColor(Color.BLACK);
				tv.setText("This is just a stupid app. Whenever you are in any situation which requires a yes or no answer but are unsure then use this app.");
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
