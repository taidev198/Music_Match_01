package com.sunasterisk.musixmatch.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.sunasterisk.musixmatch.R;

public class RepeatButtonView extends AppCompatImageView {
    private static final int STATE_MAX = 4;
    public static final int STATE_REPEAT_OFF = 0;
    public static final int STATE_REPEAT_ONE = 1;
    public static final int STATE_REPEAT_ALL = 2;
    public static final int STATE_SHUFFLE = 3;
    private int mButtonState;
    private Drawable mDrawableRepeatOff;
    private Drawable mDrawableRepeatOne;
    private Drawable mDrawableRepeatAll;
    private Drawable mDrawableShuffle;

    public RepeatButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RepeatButtonView);
        try {
            mButtonState = typedArray.getInteger(R.styleable.RepeatButtonView_button_state, 0);
            mDrawableRepeatOff = typedArray.getDrawable(R.styleable.RepeatButtonView_src_repeat_off);
            mDrawableRepeatOne = typedArray.getDrawable(R.styleable.RepeatButtonView_src_repeat_one);
            mDrawableRepeatAll = typedArray.getDrawable(R.styleable.RepeatButtonView_src_repeat_all);
            mDrawableShuffle = typedArray.getDrawable(R.styleable.RepeatButtonView_src_shuffle);
        } finally {
            typedArray.recycle();
        }

        switch (mButtonState) {
            case STATE_REPEAT_OFF:
                this.setBackground(mDrawableRepeatOff);
                break;
            case STATE_REPEAT_ONE:
                this.setBackground(mDrawableRepeatOne);
                break;
            case STATE_REPEAT_ALL:
                this.setBackground(mDrawableRepeatAll);
                break;
            case STATE_SHUFFLE:
                this.setBackground(mDrawableShuffle);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean performClick() {
        super.performClick();
        setNextState();
        drawBackground();
        return true;
    }

    @IntDef({STATE_REPEAT_OFF, STATE_REPEAT_ONE, STATE_REPEAT_ALL, STATE_SHUFFLE})
    @interface ButtonState {
    }

    public void setButtonState(@ButtonState int buttonState) {
        mButtonState = buttonState;
        drawBackground();
    }

    private void setNextState() {
        mButtonState++;
        if (mButtonState == STATE_MAX) {
            mButtonState = 0;
        }
    }

    private void drawBackground() {
        switch (mButtonState) {
            case STATE_REPEAT_OFF:
                this.setBackground(mDrawableRepeatOff);
                break;
            case STATE_REPEAT_ONE:
                this.setBackground(mDrawableRepeatOne);
                break;
            case STATE_REPEAT_ALL:
                this.setBackground(mDrawableRepeatAll);
                break;
            case STATE_SHUFFLE:
                this.setBackground(mDrawableShuffle);
                break;
            default:
                break;
        }
    }
}
