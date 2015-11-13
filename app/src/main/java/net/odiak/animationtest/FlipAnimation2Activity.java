package net.odiak.animationtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

/*
 * FlipAnimationActivity とほぼ同様。
 *
 * ただし、こちらでは XML で定義したアニメーションを実行する。
 */
public class FlipAnimation2Activity extends BaseAnimationActivity {

    boolean isHeads;
    Animation shrink;
    Animation grow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView.setBackgroundColor(Color.RED);

        shrink = AnimationUtils.loadAnimation(this, R.anim.shrink);
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isHeads) {
                    isHeads = false;
                    mView.setBackgroundColor(Color.RED);
                } else {
                    isHeads = true;
                    mView.setBackgroundColor(Color.BLUE);
                }
                mView.startAnimation(grow);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        grow = AnimationUtils.loadAnimation(this, R.anim.grow);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.startAnimation(shrink);
            }
        });
    }
}
