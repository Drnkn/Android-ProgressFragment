package ru.vang.progressswitcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class ProgressWidget extends FrameLayout implements Switcher {

    private ProgressSwitcher mProgressSwitcher;
    private int mProgressViewResId;
    private int mEmptyViewResId;
    private int mErrorViewResId;

    private ProgressWidget(final Context context) {
        super(context);
    }

    public ProgressWidget(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWidget(final Context context, final AttributeSet attrs,
            final int defStyle) {
        super(context, attrs, defStyle);

        mProgressSwitcher = new ProgressSwitcher(context);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressWidget, 0, 0);
        if (typedArray == null) {
            return;
        }
        try {
            mProgressViewResId = typedArray.getResourceId(
                    R.styleable.ProgressWidget_progressViewLayout,
                    R.layout.progress_view);
            mErrorViewResId = typedArray.getResourceId(
                    R.styleable.ProgressWidget_errorViewLayout,
                    R.layout.error_view);
            mEmptyViewResId = typedArray.getResourceId(
                    R.styleable.ProgressWidget_emptyViewLayout,
                    R.layout.error_view);

            final int animationIn = typedArray.getResourceId(R.styleable.ProgressWidget_animationIn,
                    ProgressSwitcher.DEFAULT_ANIMATION_IN);
            final int animationOut = typedArray
                    .getResourceId(R.styleable.ProgressWidget_animationOut,
                            ProgressSwitcher.DEFAULT_ANIMATION_OUT);
            mProgressSwitcher.setCustomAnimation(animationIn, animationOut);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() == 0) {
            throw new IllegalStateException("Content child must be provided");
        }
        if (getChildCount() > 2) {
            throw new IllegalStateException(ProgressWidget.class.getSimpleName()
                    + " supports only one content child");
        }

        final View content = getChildAt(0);
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(mProgressViewResId, this);
        inflater.inflate(mEmptyViewResId, this);
        inflater.inflate(mErrorViewResId, this);

        mProgressSwitcher.setContentContainer(this);
        mProgressSwitcher.setContentView(content);
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
        throw new UnsupportedOperationException("Content view must be set through xml");
    }

    @Override
    public void setContentView(final View contentView) {
        throw new UnsupportedOperationException("Content view must be set through xml");
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
    public void setOnEmptyViewClickListener(final OnClickListener onClickListener,
            final int viewId) {
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
