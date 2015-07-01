package pasqualinosorice.me.voicerecognition;

import android.os.Message;
import android.os.RemoteException;

public class MessageUtils {

    public static void restartMessage() {
        sendStopMessage();
        sendStartMessage();
    }

    public static void sendStartMessage() {
        Message localMessage = Message.obtain(null, 1);
        try {
            App.serverMessenger.send(localMessage);
        } catch (RemoteException localRemoteException) {
            localRemoteException.printStackTrace();
        }
    }

    public static void sendStopMessage() {
        Message localMessage = Message.obtain(null, 2);
        try {
            App.serverMessenger.send(localMessage);
        } catch (RemoteException localRemoteException) {
            localRemoteException.printStackTrace();
        }
    }
}