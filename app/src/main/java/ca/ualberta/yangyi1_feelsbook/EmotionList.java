package ca.ualberta.yangyi1_feelsbook;

import java.util.ArrayList;
import java.util.Collection;

public class EmotionList {
    protected ArrayList<Emotion> emotionList;
    protected ArrayList<Listener> listeners;

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

    }

    public int size(){
        return emotionList.size();
    }

    public boolean contains(Emotion emotion){
        return emotionList.contains(emotion);
    }



}
