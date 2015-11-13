package net.odiak.animationtest;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/*
 * Viewをフェードイン/フェードアウトして表示/非表示を切り替える。
 */
public class FadeAnimationActivity extends BaseAnimationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mView.getVisibility() == View.VISIBLE) {
                    Animation out = AnimationUtils.loadAnimation(FadeAnimationActivity.this, android.R.anim.fade_out);
                    mView.startAnimation(out);
                    mView.setVisibility(View.INVISIBLE);
                } else {
                    Animation in = AnimationUtils.loadAnimation(FadeAnimationActivity.this, android.R.anim.fade_in);
                    mView.startAnimation(in);
                    mView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
