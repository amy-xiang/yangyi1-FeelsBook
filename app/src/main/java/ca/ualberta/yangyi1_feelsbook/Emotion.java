package ca.ualberta.yangyi1_feelsbook;

import java.util.Date;

public class Emotion {
    protected String EmotionType;
    protected Date date;

    public Emotion(String EmotionType){
        this.EmotionType = EmotionType;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getEmotionType(){
        return this.EmotionType;
    }

    public Date getDate(){
        return this.date;
    }
}