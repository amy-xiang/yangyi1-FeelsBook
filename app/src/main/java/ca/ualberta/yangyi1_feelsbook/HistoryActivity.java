package ca.ualberta.yangyi1_feelsbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initializes the Emotion List manager that loads the saved data from previous sessions
        EmotionListManager.initManager(this.getApplicationContext());

        // Set up the ListView
        ListView listView = (ListView) findViewById(R.id.EmotionListView);
        Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
        final ArrayList<Emotion> emotionArrayList = new ArrayList<Emotion>(emotions);

        // Sorts the ListView based on dates
        // taken from: https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
        // Author: Domchi, Date: 4th Oct, 2018
        Collections.sort(emotionArrayList, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion o1, Emotion o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        final ArrayAdapter<Emotion> emotionArrayAdapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1, emotionArrayList);

        // Displays List View
        listView.setAdapter(emotionArrayAdapter);

        // Adds a listener interface to the emotion list so that everytime the emotion list updates, the ListView updates
        EmotionListController.getEmotionList().addListener(new Listener() {
            @Override
            public void update() {
                emotionArrayList.clear();
                Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
                emotionArrayList.addAll(emotions);
                emotionArrayAdapter.notifyDataSetChanged();
            }
        });

        // Open edit and delete pop-up when an item in the ListView is longclicked
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Builds the pop-up
                AlertDialog.Builder adb = new AlertDialog.Builder(HistoryActivity.this);
                String alertMessage = emotionArrayList.get(position).toString() + "\n'" + emotionArrayList.get(position).getComment() + "'";
                adb.setMessage(alertMessage);
                adb.setCancelable(true);
                final int FinalPosition = position;

                // Implements cancel button in pop-up
                adb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                // implements delete button in pop-up
                adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete the emotion at that position in the list view
                        Emotion emotion = emotionArrayList.get(FinalPosition);
                        EmotionListController.getEmotionList().deleteEmotion(emotion);
                    }
                });

                adb.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Passes the emotion at the current position to the edit activity through an intent and opens the edit activity
                        Intent intent = new Intent(HistoryActivity.this, EditEmotionActivity.class);
                        intent.putExtra("editEmotion", emotionArrayList.get(FinalPosition));
                        startActivity(intent);

                        // Deletes the emotion at the current position as passing through an emotion through the intent creates a new emotion
                        // with the same specifications (basically creates a duplicate)
                        EmotionListController.getEmotionList().deleteEmotion(emotionArrayList.get(FinalPosition));





                    }
                });

                // show the pop-up
                adb.show();

                return false;
            }
        });
    }

}
