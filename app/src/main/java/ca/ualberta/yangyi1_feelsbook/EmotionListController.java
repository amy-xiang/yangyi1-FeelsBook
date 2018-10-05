package ca.ualberta.yangyi1_feelsbook;

import java.io.IOException;
import java.util.Map;

public class EmotionListController {
    // initiates the emotion list
    private static EmotionList emotionList = null;


    public static EmotionList getEmotionList(){
        // creates a new emotion list and if present, loads the emotion list from file
        if (emotionList == null) {
            try {
                emotionList = EmotionListManager.getManager().loadEmotionList();
                emotionList.addListener(new Listener() {
                    @Override
                    public void update() {
                        saveEmotionList();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("could not deserialize EmotionList from EmotionListManager");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("could not deserialize EmotionList from EmotionListManager");
            }
        }
        return emotionList;
    }

    // saves the current emotion list to file
    public static void saveEmotionList(){
        try {
            EmotionListManager.getManager().saveEmotionList(getEmotionList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("could not deserialize EmotionList from EmotionListManager");

        }
    }

    //adds emotion to emotion list
    public void addToEmotionList(Emotion emotion){
        getEmotionList().addEmotion(emotion);
    }

    // returns size of the emotion list
    public int getSize(){
        return getEmotionList().size();
    }

    // gets the emotion counter
    public static Map getEmotionsCounter(){
        return getEmotionList().getEmotionCounter();
    }



}
