package pasqualinosorice.me.voicerecognition;

import android.media.AudioManager;
import android.util.Log;

public class AudioUtils {

    public static void mute() {
        if (App.audioManager != null) {
            Log.d("LINO", "MUTO");
            App.audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }
    }

    public static void unmute() {
        if (App.audioManager != null) {
            Log.d("LINO", "NON MUTO");
            App.audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
    }

}