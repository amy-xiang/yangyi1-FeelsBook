package ca.ualberta.yangyi1_feelsbook;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
public class EmotionTest {
    @Test
    public void testEmotion(){
        String EmotionType = "Joy";
        String comment = "Hello";
        Emotion emotion = new Emotion(EmotionType, new Date(System.currentTimeMillis()), comment);
        assertTrue("EmotionType not Equal", EmotionType.equals(emotion.getEmotionType()));
    }
}
