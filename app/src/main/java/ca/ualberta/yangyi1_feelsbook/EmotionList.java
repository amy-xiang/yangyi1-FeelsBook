package ca.ualberta.yangyi1_feelsbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EmotionList {
    protected ArrayList<Emotion> emotionList;
    protected ArrayList<Listener> listeners;
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

    private void notifyListeners() {
        for (Listener listener: listeners) {
            listener.update();
        }
    }

    public void addListener(Listener l){
        listeners.add(l);

    }

    public void deleteListener(Listener l){
        listeners.remove(l);
    }

    public void deleteEmotion(Emotion emotion){
        emotionList.remove(emotion);
        notifyListeners();

        String emotionName = emotion.getEmotionType();
        int count = emotionCounter.get(emotionName);
        count--;
        emotionCounter.put(emotionName, count);


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
