package ca.ualberta.yangyi1_feelsbook;

import org.junit.Test;

import static org.junit.Assert.*;
public class EmotionTest {
    @Test
    public void testEmotion(){
        String EmotionType = "Joy";
        Emotion emotion = new Emotion(EmotionType);
        assertTrue("EmotionType not Equal", EmotionType.equals(emotion.getEmotionType()));
    }
}
