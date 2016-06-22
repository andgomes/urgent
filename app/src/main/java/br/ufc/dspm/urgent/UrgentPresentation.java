package br.ufc.dspm.urgent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class UrgentPresentation extends AppCompatActivity implements Runnable {

    private RelativeLayout logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_presentation);

        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.logo_move);

        logo = (RelativeLayout) findViewById(R.id.logo);
        logo.startAnimation(animation);

    }

    public void run() {

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}