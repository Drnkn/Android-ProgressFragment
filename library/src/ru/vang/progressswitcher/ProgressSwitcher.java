/*
 * Copyright (C) 2013 Zaitsev Dmitry
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.vang.progressswitcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ProgressSwitcher implements Switcher {
    public static final int TYPE_PROGRESS = 0;
    public static final int TYPE_CONTENT = 1;
    public static final int TYPE_EMPTY = 2;
    public static final int TYPE_ERROR = 3;

    private static int sDefaultProgressView = R.layout.progress_view;
    private static int sDefaultEmptyView = R.layout.empty_view;
    private static int sDefaultErrorView = R.layout.error_view;

    static final int DEFAULT_ANIMATION_IN = android.R.anim.fade_in;
    static final int DEFAULT_ANIMATION_OUT = android.R.anim.fade_out;

    private Context mContext;
    private ViewGroup mContentContainer;
    private View mProgressView;
    private View mContentView;
    private View mEmptyView;
    private View mErrorView;
    private View mShownView;
    private int mContentTypeShown = TYPE_PROGRESS;

    private int mAnimationIn = DEFAULT_ANIMATION_IN;
    private int mAnimationOut = DEFAULT_ANIMATION_OUT;

    ProgressSwitcher(final Context context) {
        mContext = context;
    }

    private ProgressSwitcher(final Context context, final View rootView) {
        mContext = context;
        initViews(rootView);
    }

    public static ProgressSwitcher fromRootView(final Context context, final View rootView) {
        return new ProgressSwitcher(context, rootView);
    }

    public static ProgressSwitcher fromContentView(final Context context, final View contentView) {
        if (contentView == null) {
            throw new NullPointerException("Content view can't be null");
        }

        contentView.setId(R.id.content_view);
        final ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent == null) {
            throw new IllegalStateException("Content view wasn't added to layout");
        }
        parent.removeView(contentView);

        final FrameLayout rootView = new FrameLayout(context);
        rootView.setId(R.id.content_container);
        rootView.setLayoutParams(contentView.getLayoutParams());

        final LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(sDefaultProgressView, rootView);
        if (sDefaultEmptyView > 0) {
            inflater.inflate(sDefaultEmptyView, rootView);
        }
        if (sDefaultErrorView > 0) {
            inflater.inflate(sDefaultErrorView, rootView);
        }
        rootView.addView(contentView);
        parent.addView(rootView);

        return new ProgressSwitcher(context, parent);
    }

    public static void setDefaultProgressView(final int layoutId) {
        sDefaultProgressView = layoutId;
    }

    public static void setDefaultEmptyView(final int layoutId) {
        sDefaultEmptyView = layoutId;
    }

    public static void setDefaultErrorView(final int layoutId) {
        sDefaultErrorView = layoutId;
    }

    @Override
    public View getContentView() {
        return mContentView;
    }

    @Override
    public void addContentView(final int layoutResId) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View contentView = layoutInflater.inflate(layoutResId, mContentContainer,
                false);
        addContentView(contentView);
    }

    @Override
    public void addContentView(final View view) {
        ensureContent();
        if (view == null) {
            throw new IllegalArgumentException("Content view can't be null");
        }
        if (mContentView == null) {
            mContentContainer.addView(view);
        } else {
            final int index = mContentContainer.indexOfChild(mContentView);
            // replace content view
            mContentContainer.removeView(mContentView);
            mContentContainer.addView(view, index);
        }
        view.setVisibility(View.GONE);
        mContentView = view;
    }

    // TODO set progress view, error view, empty view in the same way?
    @Override
    public void setContentView(final int contentViewId) {
        ensureContent();
        mContentView = mContentContainer.findViewById(contentViewId);
        if (mContentView == null) {
            throw new IllegalStateException("View with id "
                    + Integer.toHexString(contentViewId) + " wasn't found");
        }
    }

    @Override
    public void setContentView(final View contentView) {
        if (contentView == null) {
            throw new IllegalArgumentException("Content view can't be null");
        }
        ensureContent();
        final int index = mContentContainer.indexOfChild(contentView);
        if (index < 0) {
            throw new IllegalStateException("View " + contentView
                    + " wasn't found in content container");
        }
        mContentView = contentView;
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean animate) {
        if (mProgressView == null) {
            throw new IllegalStateException(
                    "Progress view should be specified in layout");
        }
        setContentShown(TYPE_PROGRESS, animate);
    }

    @Override
    public void showContent() {
        showContent(true);
    }

    @Override
    public void showContent(boolean animate) {
        if (mContentView == null) {
            throw new IllegalStateException("Content view should be initialized");
        }
        setContentShown(TYPE_CONTENT, animate);
    }

    @Override
    public void showEmpty() {
        showEmpty(true);
    }

    @Override
    public void showEmpty(boolean animate) {
        if (mEmptyView == null) {
            throw new IllegalStateException("Empty view should be specified in layout");
        }
        if (mContentView == null) {
            throw new IllegalStateException("Content view must be initialized");
        }

        setContentShown(TYPE_EMPTY, animate);
    }

    @Override
    public void showError() {
        showError(true);
    }

    @Override
    public void showError(boolean animate) {
        if (mErrorView == null) {
            throw new IllegalStateException("Error view should be specified in layout");
        }
        if (mContentView == null) {
            throw new IllegalStateException("Content view must be initialized");
        }

        setContentShown(TYPE_ERROR, animate);
    }

    @Override
    public void setEmptyText(final int resId) {
        setEmptyText(mContext.getString(resId));
    }

    @Override
    public void setEmptyText(final CharSequence text) {
        ensureContent();
        if (mEmptyView == null) {
            throw new IllegalStateException("Empty view should be specified in layout");
        }
        setTextInternal(text, mEmptyView);
    }

    @Override
    public void setEmptyText(final int resId, final int viewId) {
        setEmptyText(mContext.getString(resId), viewId);
    }

    @Override
    public void setEmptyText(final CharSequence text, final int viewId) {
        ensureContent();
        if (mEmptyView == null) {
            throw new IllegalStateException("Empty view should be specified in layout");
        }
        final View emptyTextView = mEmptyView.findViewById(viewId);
        setTextInternal(text, emptyTextView);
    }

    @Override
    public void setErrorText(final int resId) {
        setErrorText(mContext.getString(resId));
    }

    @Override
    public void setErrorText(final CharSequence text) {
        ensureContent();
        if (mErrorView == null) {
            throw new IllegalStateException("Error view should be specified in layout");
        }
        setTextInternal(text, mErrorView);
    }

    @Override
    public void setErrorText(final int resId, final int viewId) {
        setErrorText(mContext.getString(resId), viewId);
    }

    @Override
    public void setErrorText(final CharSequence text, final int viewId) {
        ensureContent();
        if (mErrorView == null) {
            throw new IllegalStateException("Error view should be specified in layout");
        }
        final View errorTextView = mErrorView.findViewById(viewId);
        setTextInternal(text, errorTextView);
    }

    @Override
    public void setOnEmptyViewClickListener(final OnClickListener onClickListener) {
        if (mEmptyView == null) {
            throw new IllegalStateException("Empty view should be provided in layout");
        }

        mEmptyView.setOnClickListener(onClickListener);
    }

    @Override
    public void setOnEmptyViewClickListener(OnClickListener onClickListener, int viewId) {
        if (mEmptyView == null) {
            throw new IllegalStateException("Empty view should be provided in layout");
        }
        setOnClickListenerToView(mEmptyView, onClickListener, viewId);
    }

    @Override
    public void setOnErrorViewClickListener(final OnClickListener onClickListener) {
        if (mErrorView == null) {
            throw new IllegalStateException("Error view should be provided in layout");
        }

        mErrorView.setOnClickListener(onClickListener);
    }

    @Override
    public void setOnErrorViewClickListener(final OnClickListener onClickListener,
                                            final int viewId) {
        if (mErrorView == null) {
            throw new IllegalStateException("Error view should be provided in layout");
        }
        setOnClickListenerToView(mErrorView, onClickListener, viewId);
    }

    @Override
    public boolean isProgressDisplayed() {
        return mContentTypeShown == TYPE_PROGRESS;
    }

    @Override
    public boolean isContentDisplayed() {
        return mContentTypeShown == TYPE_CONTENT;
    }

    @Override
    public boolean isEmptyViewDisplayed() {
        return mContentTypeShown == TYPE_EMPTY;
    }

    @Override
    public boolean isErrorViewDisplayed() {
        return mContentTypeShown == TYPE_ERROR;
    }

    @Override
    public void setCustomAnimation(final int animationIn, final int animationOut) {
        mAnimationIn = animationIn;
        mAnimationOut = animationOut;
    }

    void reset() {
        mContentTypeShown = TYPE_PROGRESS;
        mErrorView = mProgressView = mContentView = mEmptyView = null;
        mContentContainer = null;
    }

    void setRootView(final View rootView) {
        initViews(rootView);
    }

    private void initViews(final View rootView) {
        if (rootView == null) {
            throw new NullPointerException("Root view can't be null");
        }

        final View container = rootView.findViewById(R.id.content_container);
        if (container == null) {
            throw new NullPointerException(
                    "Container with id content_container should be provided in layout");
        }
        if (!(container instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "Content container should be derived from ViewGroup");
        }
        mContentContainer = (ViewGroup) container;
        mProgressView = rootView.findViewById(R.id.progress_view);
        mContentView = rootView.findViewById(R.id.content_view);
        mEmptyView = rootView.findViewById(R.id.empty_view);
        mErrorView = rootView.findViewById(R.id.error_view);
    }

    private void ensureContent() {
        if (mContentView != null) {
            return;
        }
        if (mContentContainer == null) {
            throw new IllegalStateException("Content container not yet set");
        }
        mContentView = mContentContainer.findViewById(R.id.content_view);
        mProgressView = mContentContainer.findViewById(R.id.progress_view);
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
        }
        mEmptyView = mContentContainer.findViewById(R.id.empty_view);
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
        mErrorView = mContentContainer.findViewById(R.id.error_view);
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        // We are starting without a content, so assume we won't
        // have our data right away and start with the progress indicator.
        if (mContentView == null && mProgressView != null) {
            showView(mProgressView, false);
        }
    }

    private void setContentShown(final int type, final boolean animate) {
        ensureContent();
        if (mContentTypeShown == type) {
            return;
        }
        switch (type) {
            case TYPE_PROGRESS:
                showView(mProgressView, animate);
                break;
            case TYPE_CONTENT:
                showView(mContentView, animate);
                break;
            case TYPE_EMPTY:
                showView(mEmptyView, animate);
                break;
            case TYPE_ERROR:
                showView(mErrorView, animate);
                break;
            default:
                throw new IllegalArgumentException("Unknown view type: " + type);
        }
        mContentTypeShown = type;
    }

    private void showView(final View view, final boolean animate) {
        final View shownView = mShownView;

        if (animate) {
            if (shownView != null) {
                shownView.startAnimation(AnimationUtils.loadAnimation(mContext,
                        mAnimationOut));
            }
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    mAnimationIn));
        } else {
            if (shownView != null) {
                shownView.clearAnimation();
            }

            view.clearAnimation();
        }

        if (shownView != null) {
            shownView.setVisibility(View.GONE);
        }
        view.setVisibility(View.VISIBLE);
        mShownView = view;
    }

    private void setTextInternal(final CharSequence text, final View textView) {
        if (textView != null && textView instanceof TextView) {
            ((TextView) textView).setText(text);
        } else {
            throw new IllegalStateException(
                    "Can't be used with a custom view. TextView should be provided.");
        }
    }

    private void setOnClickListenerToView(final View view,
                                          final OnClickListener onClickListener, final int viewId) {
        final View targetView = view.findViewById(viewId);
        if (targetView == null) {
            throw new IllegalArgumentException("View with id "
                    + Integer.toHexString(viewId) + "wasn't found");
        }

        targetView.setOnClickListener(onClickListener);
    }

    public static class Builder {

        private Context mContext;
        private final FrameLayout mRootView;
        private View mContentView;
        private View mProgressView;
        private View mEmptyView;
        private View mErrorView;

        public Builder(final Context context) {
            mContext = context;

            mRootView = new FrameLayout(context);
            mRootView.setId(R.id.content_container);
        }

        public Builder setContentView(final int contentViewResId) {
            final View contentView = inflateViewFromResource(contentViewResId);

            return setContentView(contentView);
        }

        public Builder setContentView(final View contentView) {
            if (contentView == null) {
                throw new NullPointerException("Content view couldn't be null");
            }

            mContentView = contentView;

            return this;
        }

        public Builder setProgressView(final int progressViewResId) {
            final View progressView = inflateViewFromResource(progressViewResId);

            return setProgressView(progressView);
        }

        public Builder setProgressView(final View progressView) {
            if (progressView == null) {
                throw new NullPointerException("Progress view couldn't be null");
            }

            mProgressView = progressView;

            return this;
        }

        public Builder setEmptyView(final int emptyViewResId) {
            final View emptyView = inflateViewFromResource(emptyViewResId);

            return setEmptyView(emptyView);
        }

        public Builder setEmptyView(final View emptyView) {
            if (emptyView == null) {
                throw new NullPointerException("Empty view couldn't be null");
            }

            mEmptyView = emptyView;

            return this;
        }

        public Builder setErrorView(final int errorViewResId) {
            final View errorView = inflateViewFromResource(errorViewResId);

            return setEmptyView(errorView);
        }

        public Builder setErrorView(final View errorView) {
            if (errorView == null) {
                throw new NullPointerException("Error view couldn't be null");
            }

            mEmptyView = errorView;

            return this;
        }

        public ProgressSwitcher build() {
            if (mContentView == null) {
                throw new IllegalArgumentException("Content view wasn't set");
            }

            mRootView.addView(mContentView);
            if (mProgressView != null) {
                mRootView.addView(mProgressView);
            }
            if (mEmptyView != null) {
                mRootView.addView(mEmptyView);
            }
            if (mErrorView != null) {
                mRootView.addView(mErrorView);
            }

            return new ProgressSwitcher(mContext, mRootView);
        }

        private View inflateViewFromResource(final int resId) {
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            return inflater.inflate(resId, mRootView, false);
        }

    }

}
