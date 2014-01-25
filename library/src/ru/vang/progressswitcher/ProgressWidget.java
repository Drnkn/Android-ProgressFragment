package ru.vang.progressswitcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class ProgressWidget extends FrameLayout implements Switcher {

    private ProgressSwitcher mProgressSwitcher;

    private ProgressWidget(final Context context) {
        super(context);
    }

    public ProgressWidget(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWidget(final Context context, final AttributeSet attrs,
                          final int defStyle) {
        super(context, attrs, defStyle);

        setId(R.id.content_container);
        mProgressSwitcher = new ProgressSwitcher(context);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressWidget, 0, 0);
        if (typedArray == null) {
            return;
        }
        try {
            final LayoutInflater inflater = LayoutInflater.from(context);

            final FrameLayout rootView = new FrameLayout(context);
            rootView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            rootView.setId(R.id.content_container);

            final int progressViewLayoutId = typedArray.getResourceId(
                    R.styleable.ProgressWidget_progressViewLayout,
                    R.layout.progress_view);
            inflater.inflate(progressViewLayoutId, rootView);

            final int errorViewLayoutId = typedArray.getResourceId(
                    R.styleable.ProgressWidget_errorViewLayout,
                    R.layout.error_view);
            inflater.inflate(errorViewLayoutId, rootView);

            final int emptyViewLayoutId = typedArray.getResourceId(
                    R.styleable.ProgressWidget_emptyViewLayout,
                    R.layout.error_view);
            inflater.inflate(emptyViewLayoutId, rootView);

            addView(rootView);

            final int animationIn = typedArray.getResourceId(R.styleable.ProgressWidget_animationIn, ProgressSwitcher.DEFAULT_ANIMATION_IN);
            final int animationOut = typedArray.getResourceId(R.styleable.ProgressWidget_animationOut, ProgressSwitcher.DEFAULT_ANIMATION_OUT);
            mProgressSwitcher.setCustomAnimation(animationIn, animationOut);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mProgressSwitcher.setRootView(this);
    }

    @Override
    public View getContentView() {
        return mProgressSwitcher.getContentView();
    }

    @Override
    public void addContentView(final int layoutResId) {
        mProgressSwitcher.addContentView(layoutResId);
    }

    @Override
    public void addContentView(final View view) {
        mProgressSwitcher.addContentView(view);
    }

    @Override
    public void setContentView(final int contentViewId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setContentView(final View contentView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEmptyText(final int resId) {
        mProgressSwitcher.setEmptyText(resId);
    }

    @Override
    public void setEmptyText(final CharSequence text) {
        mProgressSwitcher.setEmptyText(text);
    }

    @Override
    public void setEmptyText(final int resId, final int viewId) {
        mProgressSwitcher.setEmptyText(resId, viewId);

    }

    @Override
    public void setEmptyText(final CharSequence text, final int viewId) {
        mProgressSwitcher.setEmptyText(text, viewId);
    }

    @Override
    public void setErrorText(final int resId) {
        mProgressSwitcher.setErrorText(resId);
    }

    @Override
    public void setErrorText(final CharSequence text) {
        mProgressSwitcher.setErrorText(text);
    }

    @Override
    public void setErrorText(int resId, int layoutResId) {
        mProgressSwitcher.setErrorText(resId, layoutResId);
    }

    @Override
    public void setErrorText(CharSequence text, int layoutResId) {
        mProgressSwitcher.setErrorText(text, layoutResId);
    }

    @Override
    public void setCustomAnimation(final int animationIn, final int animationOut) {
        mProgressSwitcher.setCustomAnimation(animationIn, animationOut);
    }

    @Override
    public void setOnEmptyViewClickListener(final OnClickListener onClickListener) {
        mProgressSwitcher.setOnEmptyViewClickListener(onClickListener);

    }

    @Override
    public void setOnEmptyViewClickListener(final OnClickListener onClickListener, final int viewId) {
        mProgressSwitcher.setOnEmptyViewClickListener(onClickListener, viewId);
    }

    @Override
    public void setOnErrorViewClickListener(final OnClickListener onClickListener) {
        mProgressSwitcher.setOnErrorViewClickListener(onClickListener);
    }

    @Override
    public void setOnErrorViewClickListener(final OnClickListener onClickListener,
                                            final int resId) {
        mProgressSwitcher.setOnErrorViewClickListener(onClickListener, resId);
    }

    @Override
    public void showContent() {
        mProgressSwitcher.showContent();
    }

    @Override
    public void showContent(final boolean animate) {
        mProgressSwitcher.showContent(animate);
    }

    @Override
    public void showProgress() {
        mProgressSwitcher.showProgress();
    }

    @Override
    public void showProgress(final boolean animate) {
        mProgressSwitcher.showProgress(animate);
    }

    @Override
    public void showEmpty() {
        mProgressSwitcher.showEmpty();
    }

    @Override
    public void showEmpty(boolean animate) {
        mProgressSwitcher.showEmpty(animate);
    }

    @Override
    public void showError() {
        mProgressSwitcher.showError();
    }

    @Override
    public void showError(final boolean animate) {
        mProgressSwitcher.showError(animate);
    }

    @Override
    public boolean isProgressDisplayed() {
        return mProgressSwitcher.isProgressDisplayed();
    }

    @Override
    public boolean isContentDisplayed() {
        return mProgressSwitcher.isContentDisplayed();
    }

    @Override
    public boolean isEmptyViewDisplayed() {
        return mProgressSwitcher.isEmptyViewDisplayed();
    }

    @Override
    public boolean isErrorViewDisplayed() {
        return mProgressSwitcher.isErrorViewDisplayed();
    }
}
