package pasqualinosorice.me.voicerecognition;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

public class RecoService extends Service {
    SpeechRecognizer speechRecognizer;
    Intent speechRecognizerIntent;

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        App.incomingHandler = new IncomingHandler(this);
        App.serverMessenger = new Messenger(App.incomingHandler);
        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        this.speechRecognizer.setRecognitionListener(new SpeechRecognitionListener());
        this.speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        this.speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        this.speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        Log.d("LINO", "Service started");
        MessageUtils.sendStartMessage();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.speechRecognizer != null) {
            this.speechRecognizer.destroy();
        }
        Log.d("LINO", "Service stopped");
    }
}