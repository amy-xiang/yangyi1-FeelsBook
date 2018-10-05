package ca.ualberta.yangyi1_feelsbook;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EmotionList implements Serializable {
    protected ArrayList<Emotion> emotionList = null;
    protected transient ArrayList<Listener> listeners = null;
    private static final Map<String, Integer> emotionCounter = new HashMap<String, Integer>();

    public EmotionList(){
        emotionList = new ArrayList<Emotion>();
        listeners = new ArrayList<Listener>();

    }

    public Collection<Emotion> getEmotions(){
        return emotionList;
    }

    public void addEmotion(Emotion emotion){
        emotionList.add(emotion);
        notifyListeners();

        String emotionName = emotion.getEmotionType();
        if (emotionCounter.get(emotionName) == null){
            emotionCounter.put(emotionName, 1);
        } else {
            int count = emotionCounter.get(emotionName);
            count++;
            emotionCounter.put(emotionName, count);

        }


    }

    private ArrayList<Listener> getListeners(){
        if (listeners == null){
            listeners = new ArrayList<Listener>();
        }

        return listeners;
    }

    private void notifyListeners() {
        for (Listener listener: getListeners()) {
            listener.update();
        }
    }



    public void addListener(Listener l){
        getListeners().add(l);

    }

    public void deleteListener(Listener l){
        getListeners().remove(l);
    }

    public void deleteEmotion(Emotion emotion){
        emotionList.remove(emotion);
        notifyListeners();

        String emotionName = emotion.getEmotionType();
        if (emotionCounter.get(emotionName) != null) {
            int count = emotionCounter.get(emotionName);
            count--;
            emotionCounter.put(emotionName, count);
        }



    }

    public int size(){
        return emotionList.size();
    }

    public boolean contains(Emotion emotion){
        return emotionList.contains(emotion);
    }

    public Map getEmotionCounter(){
        return emotionCounter;

    }


}
