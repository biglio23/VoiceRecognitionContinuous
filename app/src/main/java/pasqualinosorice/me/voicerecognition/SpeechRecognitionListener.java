package pasqualinosorice.me.voicerecognition;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;

public class SpeechRecognitionListener implements RecognitionListener {

    private final String TAG = "LINO";

    public void onBeginningOfSpeech() {
        Log.d("LINO", "onBeginingOfSpeech");
    }

    public void onBufferReceived(byte[] paramArrayOfByte) {
    }

    public void onEndOfSpeech() {
        Log.d("LINO", "onEndOfSpeech");
    }

    public void onError(int paramInt) {
        Log.d("LINO", "error = " + paramInt);
        MessageUtils.restartMessage();
    }

    public void onEvent(int paramInt, Bundle paramBundle) {
    }

    public void onPartialResults(Bundle paramBundle) {
    }

    public void onReadyForSpeech(Bundle paramBundle) {
        Log.d(TAG, "onReadyForSpeech");
    }

    public void onResults(Bundle result) {
        ArrayList<String> matches = result.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = matches.get(0);
        Log.d("LINO", "onResults: " + text);
        if (RecognitionUtils.isStartHotWord(matches)) {
            App.listeningCommand = true;
            App.statusTV.setText("Apprendimento comando");
            App.statusTV.setTextColor(Color.RED);
            App.commandTV.setText("");
        } else if (!RecognitionUtils.isEndHotWord(matches)) {
            if (App.listeningCommand) {
                if ((App.command == null) || (App.command.equals(""))) {
                    App.command = matches.get(0);
                } else {
                    App.command = App.command + " " + matches.get(0);
                }
            }
        } else if (App.listeningCommand) {
            App.listeningCommand = false;
            MessageUtils.sendStopMessage();
            App.commandTV.setText(App.command);
            App.command = "";
            App.statusTV.setText("Pronuncia Ciao telefono");
            App.statusTV.setTextColor(Color.BLUE);
        }
        MessageUtils.restartMessage();
    }

    public void onRmsChanged(float paramFloat) {
    }
}