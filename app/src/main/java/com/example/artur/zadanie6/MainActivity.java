package com.example.artur.zadanie6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {



    private Button btnWyjscie;
    private Button btnPlaySound;
    private MediaPlayer mediaPlayer;
    static final private int buttonWyjscie = 1;
    static final private int buttonPlaySound = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWyjscie = (Button) findViewById(R.id.btnWyjscie);
        btnPlaySound = (Button) findViewById(R.id.btnPlaySound);

        initButtons();
    }

    private void initButtons() {
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnWyjscie:
                        showDialog(buttonWyjscie);
                        break;
                    case R.id.btnPlaySound:
                        showDialog(buttonPlaySound);
                        break;
                    default:
                        break;
                }
            }
        };

        btnWyjscie.setOnClickListener(listener);
        btnPlaySound.setOnClickListener(listener);
    }

    private Dialog createDialogWyjscie() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjscie");
        dialogBuilder.setMessage("Czy napewno?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Wróć do mnie jeszcze...");
                MainActivity.this.finish();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Anulowałeś wyjście");
            }
        });
        return dialogBuilder.create();
    }

    private Dialog createAlertDialogWithList() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Zeus - Hipotermia", "Dżem - Wehikuł czasu"};
        dialogBuilder.setTitle("Muzyka");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                switch(position){
                    case 0:
                        playZEUS();
                        showToast("Dobre rapsy");
                        break;
                    case 1:
                        playDZEM();
                        showToast("Polski rock też spoko");
                        break;
                }

            }
        });
        return dialogBuilder.create();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case buttonWyjscie:
                return createDialogWyjscie();
            case buttonPlaySound:
                return createAlertDialogWithList();
            default:
                return null;
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

    public void playDZEM(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.wehikul);
        mediaPlayer.start();
    }

    public void playZEUS(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.hipotermia);
        mediaPlayer.start();
    }

    public void stopSound(View view) {
        mediaPlayer.stop();
    }

}
