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
    // global variables

    // initializes emotion list
    protected ArrayList<Emotion> emotionList = null;

    //initializes listeners
    protected transient ArrayList<Listener> listeners = null;

    //initializes emotion counter
    private static final Map<String, Integer> emotionCounter = new HashMap<String, Integer>();

    // creates new emotion list
    public EmotionList(){
        emotionList = new ArrayList<Emotion>();
        listeners = new ArrayList<Listener>();

    }

    // gets the emotion list
    public Collection<Emotion> getEmotions(){
        return emotionList;
    }

    // add an emotion, notify all listeners and increment the emotion counter
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

    // gets the list of listeners
    private ArrayList<Listener> getListeners(){
        if (listeners == null){
            listeners = new ArrayList<Listener>();
        }

        return listeners;
    }

    // notifies all the listeners
    private void notifyListeners() {
        for (Listener listener: getListeners()) {
            listener.update();
        }
    }


    // add a listener
    public void addListener(Listener l){
        getListeners().add(l);

    }

    // delete a listener
    public void deleteListener(Listener l){
        getListeners().remove(l);
    }

    // deletes emotion, notifies the listeners and decrement the emotion counter
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

    // returns the emotion list size
    public int size(){
        return emotionList.size();
    }

    // checks to see if an emotion is contained in the emotion list
    public boolean contains(Emotion emotion){
        return emotionList.contains(emotion);
    }

    // gets emotion counter
    public Map getEmotionCounter(){
        return emotionCounter;

    }


}
