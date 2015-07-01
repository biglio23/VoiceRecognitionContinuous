package pasqualinosorice.me.voicerecognition;

public class AudioUtils {

    public static void mute() {
        if (App.audioManager != null) {
            App.audioManager.setStreamMute(5, true);
            App.audioManager.setStreamMute(4, true);
            App.audioManager.setStreamMute(3, true);
            App.audioManager.setStreamMute(2, true);
            App.audioManager.setStreamMute(1, true);
        }
    }

    public static void unmute() {
        if (App.audioManager != null) {
            App.audioManager.setStreamMute(5, false);
            App.audioManager.setStreamMute(4, false);
            App.audioManager.setStreamMute(3, false);
            App.audioManager.setStreamMute(2, false);
            App.audioManager.setStreamMute(1, false);
        }
    }

}