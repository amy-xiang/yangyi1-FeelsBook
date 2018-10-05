package ca.ualberta.yangyi1_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmotionListManager {
    Context context;
    static final String preffile = "EmotionList";
    static final String elKey = "emotionList";
    static private EmotionListManager emotionListManager = null;

    public static void initManager(Context context){
        if (emotionListManager == null) {
            if (context == null) {
                throw new RuntimeException("Missing context for emotionListManager!");

            }
            emotionListManager = new EmotionListManager(context);
        }
    }

    public static EmotionListManager getManager(){
        if (emotionListManager == null) {
            throw new RuntimeException("Did not initialize manager");

        }


        return emotionListManager;
    }

    public EmotionListManager(Context context){
        this.context = context;
    }
    public EmotionList loadEmotionList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(preffile, context.MODE_PRIVATE);
        String EmotionListData = settings.getString(elKey, "");
        if (EmotionListData.equals("")) {
            return new EmotionList();
        } else {
            return EmotionListFromString(EmotionListData);
        }

    }

    public static EmotionList EmotionListFromString(String emotionListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(emotionListData, Base64.DEFAULT));
        ObjectInputStream ois = new ObjectInputStream(bi);
        return (EmotionList) ois.readObject();

    }

    public static String emotionListToString(EmotionList emotionList) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bo);
        oos.writeObject(emotionList);
        oos.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public void saveEmotionList(EmotionList emotionList) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(preffile, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(elKey, emotionListToString(emotionList));
        editor.commit();


    }




}
