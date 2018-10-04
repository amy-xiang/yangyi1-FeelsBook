package ca.ualberta.yangyi1_feelsbook;

import android.util.Log;

import java.util.Date;

public class Emotion {
    protected String EmotionType;
    protected Date date;
    protected String comment;
    private static final Integer MAX_CHARS = 100;


    public void setEmotionType(String emotionType) {
        this.EmotionType = emotionType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmotionType() {
        return this.EmotionType;
    }

    public Date getDate() {
        return this.date;
    }


    public void setComment(String comment) throws CommentTooLongException {
        if (comment.length() < MAX_CHARS) {
            this.comment = comment;
        } else {
            throw new CommentTooLongException();

        }
    }

    public String getComment(){
        return this.comment;
    }


}