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

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    public static EmotionListController emotionListController = new EmotionListController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void openHistory(View view) {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);

    }

    public void onClick(View view) {
        Button clickedButton = (Button) view;

        EditText bodyText = (EditText) findViewById(R.id.Comment);
        String comment = bodyText.getText().toString();
        String emotionName = clickedButton.getText().toString();
        Emotion emotion = new Emotion();

        while (true){
            try {
                emotion.setComment(comment);
                break;
            } catch (CommentTooLongException e) {
                Toast.makeText(this, "Comment too long, try again!", Toast.LENGTH_SHORT).show();
                bodyText.getText().clear();
                e.printStackTrace();
                return;
            }
        }
        emotion.setEmotionType(emotionName);
        emotion.setDate(new Date(System.currentTimeMillis()));
        emotionListController.addToEmotionList(emotion);
        bodyText.getText().clear();
        Toast.makeText(this, "Emotion Recorded", Toast.LENGTH_SHORT).show();
//        Log.d("testing", emotion.getEmotionType());
//        Log.d("testing", emotion.getComment());
//        Log.d("testing", String.valueOf(emotionListController.getSize()));










    }


}




