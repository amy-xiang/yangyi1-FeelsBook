package ca.ualberta.yangyi1_feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditEmotionActivity extends AppCompatActivity {
    // global variables
    Emotion editEmotion;
    TextView editEmotionName;
    EditText editComment;
    EditText editDate;
    Button save;
    Button cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emotion);

        // Initializes the Emotion List manager that loads the saved data from previous sessions
        EmotionListManager.initManager(this.getApplicationContext());

        // Gets the emotion passed through the intent
        editEmotion = (Emotion) getIntent().getSerializableExtra("editEmotion");

        // Gets UI references
        editEmotionName = (TextView) findViewById(R.id.EditEmotionText);
        editComment = (EditText) findViewById(R.id.EditComment);
        editDate = (EditText) findViewById(R.id.DateTime);
        save = (Button) findViewById(R.id.save_button);
        cancel = (Button) findViewById(R.id.cancel_button);

        // Gets emotion attributes and displays it on the UI
        editEmotionName.setText(editEmotion.getEmotionType());
        editComment.setText(editEmotion.getComment(), TextView.BufferType.EDITABLE);
        String pastDate = editEmotion.getDate().toString();
        editDate.setText(pastDate, TextView.BufferType.EDITABLE);



    }

    // Deals with the save and the cancel button when clicked
    public void onClick(View v){
        if (v == cancel){
            // returns back to the History Activity when canceled
            Intent intent = new Intent(EditEmotionActivity.this, HistoryActivity.class);

            // Adds the emotion for edit to the emotion list as the original emotion was deleted
            EmotionListController.getEmotionList().addEmotion(editEmotion);
            startActivity(intent);
        }

        // saves the changes to the editEmotion attributes and adds it back into the Emotion List
        if (v == save) {
            EmotionListController.getEmotionList().deleteEmotion(editEmotion);
            String newComment = editComment.getText().toString();

            // Sets emotion name
            editEmotion.setEmotionType(editEmotionName.getText().toString());

            // Tests if comment is under 100 chars and sets
            try {
                editEmotion.setComment(newComment);
            } catch (CommentTooLongException e) {
                Toast.makeText(this, "Comment too long, try again!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }

            // Tests if date is entered correctly and sets
            String stringDate = editDate.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

            // parsing date format
            // taken from: https://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
            // Author: user370305, buxik - Date: 3rd oct, 2018
            Date newDate;
            try{
                newDate = sdf.parse(stringDate);
            } catch (ParseException e){
                Toast.makeText(this, "Wrong date format, try again!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }

            editEmotion.setDate(newDate);

            // Adds emotion back into emotion list
            EmotionListController.getEmotionList().addEmotion(editEmotion);

            // returns to history activity
            Intent intent = new Intent(EditEmotionActivity.this, HistoryActivity.class);
            startActivity(intent);


        }

    }

}


