package com.example.hai.tts;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextToSpeech tts;
    Set<Voice> set;
    Set<String> features;
    Logger log = Logger.getAnonymousLogger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        features = new HashSet<>();
        features.add("networkTimeoutMs");
        features.add("networkRetriesCount");
        */
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.ENGLISH);
                    //tts.setPitch(0.30f);
                    //tts.setSpeechRate(0.8f);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        //Voice voice = new Voice("cs-network", Locale.CANADA, Voice.QUALITY_VERY_LOW, Voice.LATENCY_HIGH, true, features);
                        //tts.setVoice(voice);

                        //Get list of voices available in engine: list size was 120
                        set = tts.getVoices();
                        int size = set.size();
                        log.info("size: " + size);
                        if(!set.isEmpty())
                            for(Object object: set){
                                String element = object.toString();
                                Log.d("List of voices", element);
                            }
                        else {
                            Log.d("available voices none", "No available voices");
                        }
                    }
                }
            }
        });


        final CharSequence cs = "Hello";
        final CharSequence cs2 = "Morning";
        final CharSequence cs3 = "Test";
        final String text = cs.toString();


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    tts.speak(cs,TextToSpeech.QUEUE_ADD, null, "1");
                    tts.playSilentUtterance(1500, TextToSpeech.QUEUE_ADD, null);
                    tts.setSpeechRate(0.8f);
                    tts.speak(cs2, TextToSpeech.QUEUE_ADD, null, "2");
                    tts.setSpeechRate(1.3f);
                    tts.speak(cs3, TextToSpeech.QUEUE_ADD, null, "2");
                } else{
                    tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    //Release resources
    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

}
