package pasqualinosorice.me.voicerecognition;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (App.listeningCommand) {
            TTSUtils.sendMessage("Non ho capito");
        } else {
            MessageUtils.sendStopMessage();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MessageUtils.sendStartMessage();
                }
            }).start();
        }
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
            TTSUtils.sendMessage("Ciao, cosa posso fare per te");
        } else if (!RecognitionUtils.isEndHotWord(matches)) {
            if (App.listeningCommand) {
                Log.d("LINO", matches.get(0));
                List<String> strings = Arrays.asList(matches.get(0).split(" "));
                Log.d("LINO", strings.toString());
                String command;
                if (strings.contains(RecognitionUtils.HOTWORD_END)) {
                    int index = strings.indexOf(RecognitionUtils.HOTWORD_END);
                    command = TextUtils.join(" ", strings.subList(0, index));
                    App.command = App.command + " " + command;
                    sendCommand();
                    return;
                } else {
                    command = matches.get(0);
                }
                App.command = App.command + " " + command;
                TTSUtils.sendMessage("OK");
            } else {
                MessageUtils.sendStartMessage();
            }
        } else if (App.listeningCommand) {
            sendCommand();
        }
    }

    public void onRmsChanged(float paramFloat) {
    }

    private void sendCommand() {
        App.listeningCommand = false;
        MessageUtils.sendStopMessage();
        App.commandTV.setText(App.command);
        App.command = "";
        App.statusTV.setText("Pronuncia " + RecognitionUtils.HOTWORD_START);
        App.statusTV.setTextColor(Color.BLUE);
        TTSUtils.sendMessage("Comando eseguito");
    }
}