/*
 * Copyright (C) 2013 Evgeny Shishkin
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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * The implementation of the fragment to display content. Based on
 * {@link android.support.v4.app.ListFragment}. If you are waiting for the
 * initial data, you'll can displaying during this time an indeterminate
 * progress indicator.
 * 
 * @author Evgeny Shishkin
 */
public class ProgressFragment extends Fragment implements Switcher {

	private ProgressSwitcher mProgressSwitcher;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mProgressSwitcher = new ProgressSwitcher(getActivity());
	}

	/**
	 * Provide default implementation to return a simple view. Subclasses can
	 * override to replace with their own layout. If doing so, the returned view
	 * hierarchy <em>must</em> have a progress container whose id is
	 * {@link ru.vang.progressswitcher.R.id#progress_container
	 * R.id.progress_container}, content container whose id is
	 * {@link ru.vang.progressswitcher.R.id#content_container
	 * R.id.content_container} and can optionally have a sibling view id
	 * {@link android.R.id#empty android.R.id.empty} that is to be shown when
	 * the content is empty.
	 * <p/>
	 * <p>
	 * If you are overriding this method with your own custom content, consider
	 * including the standard layout
	 * {@link ru.vang.progressswitcher.R.layout#switcher_layout} in your
	 * layout file, so that you continue to retain all of the standard behavior
	 * of ProgressFragment. In particular, this is currently the only way to
	 * have the built-in indeterminant progress state be shown.
	 */
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.switcher_layout, container, false);
	}

	/**
	 * Attach to view once the view hierarchy has been created.
	 */
	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mProgressSwitcher.setRootView(view);
	}

	/**
	 * Detach from view.
	 */
	@Override
	public void onDestroyView() {
		mProgressSwitcher.reset();
		super.onDestroyView();
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
		mProgressSwitcher.setContentView(contentViewId);
	}

	@Override
	public void setContentView(final View contentView) {
		mProgressSwitcher.setContentView(contentView);
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
	public void showEmpty(final boolean animate) {
		mProgressSwitcher.showEmpty();
	}

	@Override
	public void showError() {
		mProgressSwitcher.showError();
	}

	@Override
	public void showError(final boolean animate) {
		mProgressSwitcher.showEmpty(animate);
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
	public void setErrorText(final int resId, final int viewId) {
		mProgressSwitcher.setErrorText(resId, viewId);
	}

	@Override
	public void setErrorText(final CharSequence text, final int viewId) {
		mProgressSwitcher.setErrorText(text, viewId);
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
			final int viewId) {
		mProgressSwitcher.setOnErrorViewClickListener(onClickListener);
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

	@Override
	public void setCustomAnimation(final int animationIn, final int animationOut) {
		mProgressSwitcher.setCustomAnimation(animationIn, animationOut);
	}
}
