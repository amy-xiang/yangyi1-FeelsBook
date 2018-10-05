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
        editEmotion = (Emotion) getIntent().getSerializableExtra("editEmotion");
        editEmotionName = (TextView) findViewById(R.id.EditEmotionText);
        editComment = (EditText) findViewById(R.id.EditComment);
        editDate = (EditText) findViewById(R.id.DateTime);
        save = (Button) findViewById(R.id.save_button);
        cancel = (Button) findViewById(R.id.cancel_button);

        editEmotionName.setText(editEmotion.getEmotionType());
        editComment.setText(editEmotion.getComment(), TextView.BufferType.EDITABLE);
        String pastDate = editEmotion.getDate().toString();
        editDate.setText(pastDate, TextView.BufferType.EDITABLE);



    }

    public void onClick(View v){
        if (v == cancel){
            Intent intent = new Intent(EditEmotionActivity.this, HistoryActivity.class);
            EmotionListController.getEmotionList().addEmotion(editEmotion);
            startActivity(intent);
        }

        if (v == save) {
            EmotionListController.getEmotionList().deleteEmotion(editEmotion);
            String newComment = editComment.getText().toString();

            editEmotion.setEmotionType(editEmotionName.getText().toString());


            try {
                editEmotion.setComment(newComment);
            } catch (CommentTooLongException e) {
                Toast.makeText(this, "Comment too long, try again!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }

            Log.d("testing", editEmotion.getComment());

            String stringDate = editDate.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

            Date newDate;
            try{
                newDate = sdf.parse(stringDate);
            } catch (ParseException e){
                Toast.makeText(this, "Wrong date format, try again!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }

            editEmotion.setDate(newDate);
            Log.d("testing", editEmotion.getDate().toString());
            EmotionListController.getEmotionList().addEmotion(editEmotion);

            Intent intent = new Intent(EditEmotionActivity.this, HistoryActivity.class);
            startActivity(intent);


        }

    }

}


