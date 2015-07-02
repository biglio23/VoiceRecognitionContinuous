package pasqualinosorice.me.voicerecognition;

import java.util.List;

public class RecognitionUtils {

    public static final String HOTWORD_END = "fatto";
    public static final String HOTWORD_START = "Ciao telefono";

    public static boolean isEndHotWord(List<String> strings) {
        for (String s : strings) {
            if (s.equalsIgnoreCase(HOTWORD_END)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStartHotWord(List<String> strings) {
        for (String s : strings) {
            if (s.equalsIgnoreCase(HOTWORD_START)) {
                return true;
            }
        }
        return false;
    }
}