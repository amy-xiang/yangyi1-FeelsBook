package ca.ualberta.yangyi1_feelsbook;

import java.util.ArrayList;
import java.util.Collection;

public class EmotionList {
    protected ArrayList<Emotion> emotionList;

    public EmotionList(){
        emotionList = new ArrayList<Emotion>();

    }
    public Collection<Emotion> getEmotions(){
        return emotionList;
    }

    public void addEmotion(Emotion emotion){
        emotionList.add(emotion);

    }

    public void deleteEmotion(Emotion emotion){
        emotionList.remove(emotion);

    }



}
