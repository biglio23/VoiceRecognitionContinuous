package pasqualinosorice.me.voicerecognition;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    boolean isStarted = false;
    @InjectView(R.id.btn_service)
    Button serviceBTN;
    Intent serviceIntent;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        App.commandTV = (TextView) findViewById(R.id.tv_command);
        App.statusTV = (TextView) findViewById(R.id.tv_status);
        App.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ButterKnife.inject(this);
        serviceIntent = new Intent(this, RecoService.class);
        serviceBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!isStarted) {
                    startService(serviceIntent);
                    serviceBTN.setText("Ferma Servizio");
                    App.statusTV.setText("Pronuncia Ciao telefono");
                    App.statusTV.setTextColor(Color.BLUE);
                    isStarted = true;
                    return;
                }
                stopService(serviceIntent);
                serviceBTN.setText("Avvia Servizio");
                App.statusTV.setText("Avvia il servizio con il tasto in alto");
                App.statusTV.setTextColor(Color.BLACK);
                App.commandTV.setText("");
                isStarted = false;
            }
        });
    }

}
