package ca.ualberta.yangyi1_feelsbook;

import java.util.Map;

public class EmotionListController {
    private static EmotionList emotionList = null;

    public static EmotionList getEmotionList(){
        if (emotionList == null) {
            emotionList = new EmotionList();
        }
        return emotionList;
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
