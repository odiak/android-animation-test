package net.odiak.animationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
 * アニメーションを使うアクティビティのための基底クラス。
 * ボタンと塗りつぶされたViewを表示して、それらのViewをメンバ変数に代入する。
 */
public abstract class BaseAnimationActivity extends AppCompatActivity {
    protected Button mButton;
    protected View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mButton = (Button) findViewById(R.id.button);
        mView = findViewById(R.id.view);
    }
}
