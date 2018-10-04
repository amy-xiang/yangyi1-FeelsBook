package ca.ualberta.yangyi1_feelsbook;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;
import ca.ualberta.yangyi1_feelsbook.EmotionList;
import ca.ualberta.yangyi1_feelsbook.Emotion;

public class EmotionListTest {

    public void testEmotionList(){
        EmotionList emotionList = new EmotionList();
        assertTrue("Empty emotion list", emotionList.size() == 0);
    }


    public void testAddEmotion(){
        EmotionList emotionList = new EmotionList();
        String emotionType = "Joy";
        String comment = "Hello";
        Emotion testEmotion = new Emotion(emotionType, new Date(System.currentTimeMillis()), comment);
        emotionList.addEmotion(testEmotion);
        assertTrue("Emotion list size -- testing add emotion", emotionList.size() == 1);
        assertTrue("Test emotion not contained", emotionList.contains(testEmotion));

    }
    @Test
    public void testDeleteEmotion() {
        EmotionList emotionList = new EmotionList();
        String emotionType = "Joy";
        String comment = "Hello";
        Emotion testEmotion = new Emotion(emotionType, new Date(System.currentTimeMillis()), comment);
        emotionList.addEmotion(testEmotion);
        assertTrue("Emotion list size -- too small", emotionList.size() == 1);
        assertTrue("Test emotion not contained", emotionList.contains(testEmotion));
        emotionList.deleteEmotion(testEmotion);
        assertTrue("Emotion list size too large", emotionList.size() == 0);
        assertFalse("Test emotion still contained", emotionList.contains(testEmotion));
    }


}
