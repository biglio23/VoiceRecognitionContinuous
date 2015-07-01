package pasqualinosorice.me.voicerecognition;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

public class IncomingHandler extends Handler {
    public static final int MSG_RECOGNIZER_CANCEL = 2;
    public static final int MSG_RECOGNIZER_START_LISTENING = 1;
    public boolean isListening;
    private WeakReference<RecoService> mtarget;

    IncomingHandler(RecoService paramRecoService) {
        this.mtarget = new WeakReference<>(paramRecoService);
        this.isListening = false;
    }

    public void handleMessage(Message message) {
        RecoService target = this.mtarget.get();
        switch (message.what) {
            case MSG_RECOGNIZER_START_LISTENING:
                if (!isListening) {
                    target.speechRecognizer.startListening(target.speechRecognizerIntent);
                    isListening = true;
                    Log.d("LINO", "message start listening");
                } else {
                    Log.d("LINO", "message dropped");
                }
                break;
            case MSG_RECOGNIZER_CANCEL:
                target.speechRecognizer.cancel();
                isListening = false;
                Log.d("LINO", "message canceled recognizer");
                break;
        }
    }
}