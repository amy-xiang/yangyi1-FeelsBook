package ca.ualberta.yangyi1_feelsbook;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = (ListView) findViewById(R.id.EmotionListView);
        Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
        ArrayList<Emotion> emotionArrayList = new ArrayList<Emotion>(emotions);
        ArrayAdapter<Emotion> emotionArrayAdapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1, emotionArrayList);
        listView.setAdapter(emotionArrayAdapter);
    }

}
