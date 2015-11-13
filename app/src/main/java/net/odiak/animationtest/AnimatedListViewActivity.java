package net.odiak.animationtest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * ListView のスクロール時に子 View をアニメーションさせる
 */
public class AnimatedListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_list_view);

        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            array.add(String.format("item %d", i + 1));
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        ItemsAdapter adapter = new ItemsAdapter(this);
        adapter.addAll(array);
        listView.setAdapter(adapter);
    }

    private static class ItemsAdapter extends ArrayAdapter<String> {

        private LayoutInflater mLayoutInflater;

        public ItemsAdapter(Context context) {
            super(context, 0);
            mLayoutInflater = LayoutInflater.from(context);
        }

        /*
         * getView で子 View のアニメーションを開始する
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.animated_list_item, parent, false);
            }
            String item = getItem(position);
            ((TextView) convertView).setText(item);

            // ObjectAnimator を使って、拡大しながら、下から上に移動するアニメーション
            Animator animator = ObjectAnimator.ofPropertyValuesHolder(convertView,
                    PropertyValuesHolder.ofFloat("scaleX", 0.85f, 1f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.85f, 1f),
                    PropertyValuesHolder.ofFloat("translationY", 100f, 0f));
            animator.setInterpolator(new CustomInterpolator());
            animator.setDuration(800);
            animator.start();

            return convertView;
        }
    }

    /*
     * 上に凸な曲線の Interpolator
     */
    private static class CustomInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float t) {
            return (float) Math.pow(t - 1f, 3) + 1f;
        }
    }
}
