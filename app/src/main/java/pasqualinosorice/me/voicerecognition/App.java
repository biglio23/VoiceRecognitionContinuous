package pasqualinosorice.me.voicerecognition;

import android.app.Application;
import android.media.AudioManager;
import android.os.Messenger;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

public class App extends Application {

    public static AudioManager audioManager;
    public static String command = "";
    public static TextView commandTV;
    public static IncomingHandler incomingHandler;
    public static boolean listeningCommand = false;
    public static Messenger serverMessenger;
    public static TextView statusTV;
    public static TextToSpeech engineTTS;

}