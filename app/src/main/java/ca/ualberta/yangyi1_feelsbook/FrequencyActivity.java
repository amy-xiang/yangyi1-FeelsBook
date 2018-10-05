package ca.ualberta.yangyi1_feelsbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class FrequencyActivity extends AppCompatActivity {
    TextView Joy;
    TextView Love;
    TextView Surprise;
    TextView Anger;
    TextView Sad;
    TextView Fear;
    ArrayList<TextView> emotions;
    ArrayList<String> emotionText;
    Map emotionMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmotionListManager.initManager(this.getApplicationContext());

        setContentView(R.layout.activity_frequency);
        Joy = (TextView) findViewById(R.id.Joy_Counter);
        Love = (TextView) findViewById(R.id.Love_Counter);
        Surprise = (TextView) findViewById(R.id.Surprise_Counter);
        Anger = (TextView) findViewById(R.id.Anger_Counter);
        Sad = (TextView) findViewById(R.id.Sad_Counter);
        Fear = (TextView) findViewById(R.id.fear_Counter);

        emotions = new ArrayList<TextView>();
        emotionText = new ArrayList<String>();

        emotions.add(Joy);
        emotions.add(Love);
        emotions.add(Surprise);
        emotions.add(Anger);
        emotions.add(Sad);
        emotions.add(Fear);
        emotionText.add("Joy");
        emotionText.add("Love");
        emotionText.add("Surprise");
        emotionText.add("Anger");
        emotionText.add("Sadness");
        emotionText.add("Fear");


        emotionMap = EmotionListController.getEmotionsCounter();

        int i;
        for (i = 0; i < 6; i++){
            if (emotionMap.get(emotionText.get(i)) == null) {
                emotions.get(i).setText(String.valueOf(0));
            } else {
                String emotionName = emotionText.get(i);
                emotions.get(i).setText(String.valueOf((int) emotionMap.get(emotionName)));
            }
        }


    }

}
