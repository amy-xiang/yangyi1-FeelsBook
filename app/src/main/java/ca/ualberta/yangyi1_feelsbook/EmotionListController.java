package ca.ualberta.yangyi1_feelsbook;

public class EmotionListController {
    //Lazy Singleton
    private static EmotionList emotionList = null;

    static public EmotionList getEmotionList(){

        if(emotionList==null){
            EmotionList emotionList = new EmotionList();
        }
        return emotionList;
    }

    public void addEmotion(Emotion emotion){
        getEmotionList().addEmotion(emotion);

    }

}
