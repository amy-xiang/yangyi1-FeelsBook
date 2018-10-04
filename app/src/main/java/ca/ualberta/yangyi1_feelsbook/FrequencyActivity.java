package ca.ualberta.yangyi1_feelsbook;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class FrequencyActivity extends Activity {
    TextView Joy = (TextView) findViewById(R.id.joy_Counter);
    TextView Love = (TextView) findViewById(R.id.love_Counter);
    TextView Surprise = (TextView) findViewById(R.id.surprise_Counter);
    TextView Anger  = (TextView) findViewById(R.id.anger_Counter);
    TextView Sad = (TextView) findViewById(R.id.sad_Counter);
    TextView Fear = (TextView) findViewById(R.id.fear_Counter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Map emotionMap = EmotionListController.getEmotionsCounter();

    }

    public void emotionCounter(View view){

    }

}
