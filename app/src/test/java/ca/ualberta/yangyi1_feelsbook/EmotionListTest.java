package ca.ualberta.yangyi1_feelsbook;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import ca.ualberta.yangyi1_feelsbook.EmotionList;
import ca.ualberta.yangyi1_feelsbook.Emotion;

public class EmotionListTest {

    public void testEmotionList(){
        EmotionList emotionList = new EmotionList();
        Collection<Emotion> emotions = emotionList.getEmotions();
        assertTrue("Empty emotion list", emotions.size() == 0);
    }


    public void testAddEmotion(){
        EmotionList emotionList = new EmotionList();
        String emotionType = "Joy";
        Emotion testEmotion = new Emotion(emotionType);
        emotionList.addEmotion(testEmotion);
        Collection<Emotion> emotions = emotionList.getEmotions();
        assertTrue("Emotion list size -- testing add emotion", emotions.size() == 1);
        assertTrue("Test emotion not contained", emotions.contains(testEmotion));

    }
    @Test
    public void testDeleteEmotion() {
        EmotionList emotionList = new EmotionList();
        String emotionType = "Joy";
        Emotion testEmotion = new Emotion(emotionType);
        emotionList.addEmotion(testEmotion);
        Collection<Emotion> emotions = emotionList.getEmotions();
        assertTrue("Emotion list size -- too small", emotions.size() == 1);
        assertTrue("Test emotion not contained", emotions.contains(testEmotion));
        emotionList.deleteEmotion(testEmotion);
        emotions = emotionList.getEmotions();
        assertTrue("Emotion list size too large", emotions.size() == 0);
        assertFalse("Test emotion still contained", emotions.contains(testEmotion));
    }
}
