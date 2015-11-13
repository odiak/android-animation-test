package net.odiak.animationtest;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/*
 * ViewGroup に、要素が追加や削除されてレイアウトが変更された時のアニメーションを設定する。
 */
public class AnimatedViewGroupActivity extends AppCompatActivity {

    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_list_view);

        mContainer = (LinearLayout) findViewById(R.id.container);

        LayoutTransition transition = new LayoutTransition();
        mContainer.setLayoutTransition(transition);

        // View が追加された時のアニメーションを設定する
        Animator appearAnim = ObjectAnimator.ofFloat(null, "rotationX", 90f, 0f)
                .setDuration(transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING, appearAnim);

        // View が削除された時のアニメーションを設定する
        Animator disappearAnim = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f)
                .setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearAnim);

        // View が削除された時の、他の View のアニメーションを設定する

        PropertyValuesHolder pvhSlide = PropertyValuesHolder.ofFloat("y", 0, 1);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f, 1f);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f, 1f);

        Animator changingDisappearingAnim = ObjectAnimator.ofPropertyValuesHolder(this,
                pvhSlide, pvhScaleY, pvhScaleX);
        changingDisappearingAnim.setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingDisappearingAnim);

    }

    /*
     * ボタンがクリックされた時に呼ばれる
     */
    public void onAddButtonClick (View v) {
        Button button = new Button(this);
        button.setText("click to remove");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainer.removeView(v);
            }
        });

        mContainer.addView(button, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
