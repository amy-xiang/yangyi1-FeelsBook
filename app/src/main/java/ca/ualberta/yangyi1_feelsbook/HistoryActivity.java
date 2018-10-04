package ca.ualberta.yangyi1_feelsbook;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = (ListView) findViewById(R.id.EmotionListView);
        Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
        final ArrayList<Emotion> emotionArrayList = new ArrayList<Emotion>(emotions);
        final ArrayAdapter<Emotion> emotionArrayAdapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1, emotionArrayList);
        listView.setAdapter(emotionArrayAdapter);

        EmotionListController.getEmotionList().addListener(new Listener() {
            @Override
            public void update() {
                emotionArrayList.clear();
                Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
                emotionArrayList.addAll(emotions);
                emotionArrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(HistoryActivity.this, "Delete" + emotionArrayList.get(position).toString(), Toast.LENGTH_SHORT).show();
                Emotion emotion = emotionArrayList.get(position);
                EmotionListController.getEmotionList().deleteEmotion(emotion);
                return false;
            }
        });
    }

}
