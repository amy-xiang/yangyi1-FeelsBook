package ca.ualberta.yangyi1_feelsbook;

import java.io.IOException;
import java.util.Map;

public class EmotionListController {
    private static EmotionList emotionList = null;


    public static EmotionList getEmotionList(){
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

    public static void saveEmotionList(){
        try {
            EmotionListManager.getManager().saveEmotionList(getEmotionList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("could not deserialize EmotionList from EmotionListManager");

        }
    }

    public void addToEmotionList(Emotion emotion){
        getEmotionList().addEmotion(emotion);
    }

    public int getSize(){
        return getEmotionList().size();
    }

    public static Map getEmotionsCounter(){
        return getEmotionList().getEmotionCounter();
    }



}
