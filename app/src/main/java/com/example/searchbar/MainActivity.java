package com.example.searchbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText search;
    private int currentHintIndex = 0;
    private final String[] hintArray = {"Search Milk","Search Grocery","Search Snacks","Search Fruits","Search Vegetables","Search Meat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.searchEditText);
//        search.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus) {
//                setRandomHint();
//            }
//        });
        startHintAnimation();
    }

    private void startHintAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            int scrollX = (int) (search.getWidth() * progress);
            search.setPadding(0, 0, 0, 0);
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Animation ended
                currentHintIndex = (currentHintIndex + 1) % hintArray.length;
                search.setHint(hintArray[currentHintIndex]);
                search.setPadding(0, 0, 0, 0);
                startHintAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Animation cancelled
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Animation repeated
            }
        });

        animator.start();
    }

//    private void setRandomHint() {
//        Random random = new Random();
//        int randomIndex = random.nextInt(hintArray.length);
//        String randomHint = hintArray[randomIndex];
//        search.setHint(randomHint);
//    }

}
