package pasqualinosorice.me.voicerecognition;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

public class Utils {

    public static void showDialog(final Activity activity, String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setTitle(title);

        alert.setMessage(message);
        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                activity.startActivity(new Intent(
                        android.provider.Settings.ACTION_SETTINGS));
                dialog.dismiss();
                activity.finish();
            }
        });
        alert.show();
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
