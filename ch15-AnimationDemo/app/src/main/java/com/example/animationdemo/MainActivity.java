package com.example.animationdemo;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void animate1(View source) {
        View view = findViewById(R.id.imageView1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                view, "rotationY", 0F, 720.0F);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    public void animate2(View source) {
        final View view = findViewById(R.id.imageView1);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0F,
                7200F);
        valueAnimator.setDuration(15000);

        valueAnimator.addUpdateListener(new
            ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float value = (Float) animation.getAnimatedValue();
                    view.setRotationX(value);
                    if (value < 3600) {
                        view.setTranslationX(value/20);
                        view.setTranslationY(value/20);
                    } else {
                        view.setTranslationX((7200-value)/20);
                        view.setTranslationY((7200-value)/20);
                    }
                }
            });
        valueAnimator.start();
    }
    public void animate3(View source) {
        View view = findViewById(R.id.imageView1);
        ObjectAnimator objectAnimator1 =
                ObjectAnimator.ofFloat(view, "translationY", 0F,
                        300.0F);
        ObjectAnimator objectAnimator2 =
                ObjectAnimator.ofFloat(view, "translationX", 0F,
                        300.0F);
        objectAnimator1.setDuration(2000);
        objectAnimator2.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);

        ObjectAnimator objectAnimator3 =
                ObjectAnimator.ofFloat(view, "rotation", 0F,
                        1440F);
        objectAnimator3.setDuration(4000);
        animatorSet.play(objectAnimator3).after(objectAnimator2);
        animatorSet.start();
    }
}