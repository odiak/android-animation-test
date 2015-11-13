package net.odiak.animationtest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/*
 * 裏返るようなアニメーションでViewの色を変える。
 *
 * ScaleAnimation の shrink と grow を定義し、 shrink が終了後に View の色を変えて、
 * grow を実行する。
 */
public class FlipAnimationActivity extends BaseAnimationActivity {

    // 現在の View の状態; 表か裏か
    private boolean isHeads;

    private ScaleAnimation shrink;
    private ScaleAnimation grow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView.setBackgroundColor(Color.RED);

        // 幅を 100% -> 0% にするアニメーション
        // 基点は View の中心
        shrink = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(1000);
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            /*
             * 縮小するアニメーションが終了したら、状態を切り替えて View の色を変更し、
             * 拡大するアニメーションを実行する
             */
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

        // 自作のInterpolatorを使ってみる
        shrink.setInterpolator(new CustomInterpolator());

        // 幅を 0% -> 100% にするアニメーション
        // 基点は View の中心
        grow = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        grow.setDuration(150);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.startAnimation(shrink);
            }
        });
    }
}
