/*MIT License

        Copyright (c) 2018 Amy Xiang

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.*/

package ca.ualberta.yangyi1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    // Initiates a new Controller that regulates the Emotion List
    public static EmotionListController emotionListController = new EmotionListController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes the Emotion List manager that loads the saved data from previous sessions
        EmotionListManager.initManager(this.getApplicationContext());


    }
    // Sends an intent to open the History activity that lists the previously clicked emotions when the History button is clicked
    public void openHistory(View view) {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);

    }
    // Sends an intent to open the Frequency activity that displays how many times an emotion is clicked when the Frequency button is clicked
    public void openFrequency(View view){
        Intent intent = new Intent(MainActivity.this, FrequencyActivity.class);
        startActivity(intent);


    }

    // Method that adds the emotions
    public void onClick(View view) {
        // Gets the button that is clicked
        Button clickedButton = (Button) view;

        // Gets the optional comment
        EditText bodyText = (EditText) findViewById(R.id.Comment);
        String comment = bodyText.getText().toString();
        String emotionName = clickedButton.getText().toString();

        // Creates a new emotion
        Emotion emotion = new Emotion();

        // Tests to see if the comment is under 100 characters
        try {
            emotion.setComment(comment);
        } catch (CommentTooLongException e) {
            Toast.makeText(this, "Comment too long, try again!", Toast.LENGTH_SHORT).show();
            bodyText.getText().clear();
            e.printStackTrace();
            return;
        }

        // Sets the type of emotion and the current date
        emotion.setEmotionType(emotionName);
        emotion.setDate(new Date(System.currentTimeMillis()));

        // Adds the emotion to the emotion list through the controller
        emotionListController.addToEmotionList(emotion);

        // Clears the comment box
        bodyText.getText().clear();

        // Sends a message to confirm emotion being recorded
        Toast.makeText(this, "Emotion Recorded", Toast.LENGTH_SHORT).show();

    }






}




