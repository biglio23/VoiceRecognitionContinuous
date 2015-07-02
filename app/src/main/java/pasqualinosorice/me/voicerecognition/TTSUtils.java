package pasqualinosorice.me.voicerecognition;

import android.speech.tts.TextToSpeech;

import java.util.HashMap;

public class TTSUtils {

    public static void sendMessage(final String message) {
        MessageUtils.sendStopMessage();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HashMap<String, String> params = new HashMap<>();
                params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "1");
                AudioUtils.unmute();
                App.engineTTS.speak(message, TextToSpeech.QUEUE_FLUSH, params);
            }
        });
        t.start();
    }

}
