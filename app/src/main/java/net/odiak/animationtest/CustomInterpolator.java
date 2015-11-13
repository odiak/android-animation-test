package net.odiak.animationtest;

import android.view.animation.BaseInterpolator;

public class CustomInterpolator extends BaseInterpolator {
    @Override
    public float getInterpolation(float input) {
        return (float) Math.pow(input, 3.0);
    }
}
