package com.adityawebapps.adityabloodconnect;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ImageView imageView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        imageView = (ImageView) findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splashscreen_animation);



        imageView.setAnimation(animation);
        animation.setDuration(3000);



        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(),DrawerActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
