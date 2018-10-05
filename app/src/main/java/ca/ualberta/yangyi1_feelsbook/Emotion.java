package ca.ualberta.yangyi1_feelsbook;

import android.util.Log;

import java.io.Serializable;
import java.util.Date;

public class Emotion implements Serializable {
    // global variables
    protected String EmotionType;
    protected Date date;
    protected String comment;
    private static final Integer MAX_CHARS = 100;

    // sets emotion name
    public void setEmotionType(String emotionType) {
        this.EmotionType = emotionType;
    }
    // sets emotion date
    public void setDate(Date date) {
        this.date = date;
    }
    // gets emotion name
    public String getEmotionType() {
        return this.EmotionType;
    }
    // gets emotion name
    public Date getDate() {
        return this.date;
    }
    // gets string of date
    public String getStringDate(){
        return this.date.toString();
    }

    // setting the comment and checking if under 100 chars
    public void setComment(String comment) throws CommentTooLongException {
        if (comment.length() < MAX_CHARS) {
            this.comment = comment;
        } else {
            throw new CommentTooLongException();

        }
    }
    // get emotion comment
    public String getComment(){
        return this.comment;
    }
    // method for listview to display emotion name and date
    public String toString(){
        String listView = getEmotionType() + "\n" + getStringDate();
        return listView;
    }


}