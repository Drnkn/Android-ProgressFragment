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

package com.devspark.progressfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The implementation of the fragment to display content. Based on
 * {@link android.support.v4.app.ListFragment}. If you are waiting for the
 * initial data, you'll can displaying during this time an indeterminate
 * progress indicator.
 * 
 * @author Evgeny Shishkin
 */
public class ProgressFragment extends Fragment {

	private ContentSwitcher mContentSwitcher;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContentSwitcher = new ContentSwitcher(getActivity());
	}

	/**
	 * Provide default implementation to return a simple view. Subclasses can
	 * override to replace with their own layout. If doing so, the returned view
	 * hierarchy <em>must</em> have a progress container whose id is
	 * {@link R.id#progress_container R.id.progress_container}, content
	 * container whose id is {@link R.id#content_container
	 * R.id.content_container} and can optionally have a sibling view id
	 * {@link android.R.id#empty android.R.id.empty} that is to be shown when
	 * the content is empty.
	 * <p/>
	 * <p>
	 * If you are overriding this method with your own custom content, consider
	 * including the standard layout {@link R.layout#fragment_progress} in your
	 * layout file, so that you continue to retain all of the standard behavior
	 * of ProgressFragment. In particular, this is currently the only way to
	 * have the built-in indeterminant progress state be shown.
	 */
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_progress, container, false);
	}

	/**
	 * Attach to view once the view hierarchy has been created.
	 */
	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mContentSwitcher.setRootView(view);
	}

	/**
	 * Detach from view.
	 */
	@Override
	public void onDestroyView() {
		mContentSwitcher.reset();
		super.onDestroyView();
	}

	/**
	 * Return content view or null if the content view has not been initialized.
	 * 
	 * @return content view or null
	 * @see #setContentView(android.view.View)
	 * @see #setContentView(int)
	 */
	public View getContentView() {
		return mContentSwitcher.getContentView();
	}

	/**
	 * Add the content content from a layout resource.
	 * 
	 * @param layoutResId
	 *            Resource ID to be inflated.
	 * @see #setContentView(android.view.View)
	 * @see #getContentView()
	 */
	public void addContentView(final int layoutResId) {
		mContentSwitcher.addContentView(layoutResId);
	}

	/**
	 * Add the content view to an explicit view. If the content view was
	 * installed earlier, the content will be replaced with a new view.
	 * 
	 * @param view
	 *            The desired content to display. Value can't be null.
	 * @see #setContentView(int)
	 * @see #getContentView()
	 */
	public void addContentView(final View view) {
		mContentSwitcher.addContentView(view);
	}

	public void setContentView(final int contentViewId) {
		mContentSwitcher.setContentView(contentViewId);
	}

	public void setContentView(final View contentView) {
		mContentSwitcher.setContentView(contentView);
	}

	/**
	 * The default content for a ProgressFragment has a TextView that can be
	 * shown when the content is empty {@link #setContentEmpty(boolean)}. If you
	 * would like to have it shown, call this method to supply the text it
	 * should use.
	 * 
	 * @param resId
	 *            Identification of string from a resources
	 * @see #setEmptyText(CharSequence)
	 */
	public void setEmptyText(final int resId) {
		mContentSwitcher.setEmptyText(resId);
	}

	/**
	 * The default content for a ProgressFragment has a TextView that can be
	 * shown when the content is empty {@link #setContentEmpty(boolean)}. If you
	 * would like to have it shown, call this method to supply the text it
	 * should use.
	 * 
	 * @param text
	 *            Text for empty view
	 * @see #setEmptyText(int)
	 */
	public void setEmptyText(final CharSequence text) {
		mContentSwitcher.setEmptyText(text);
	}

	public void setErrorText(final int resId) {
		mContentSwitcher.setErrorText(resId);
	}

	public void setErrorText(final CharSequence text) {
		mContentSwitcher.setErrorText(text);
	}

	/**
	 * Control whether the content is being displayed. You can make it not
	 * displayed if you are waiting for the initial data to show in it. During
	 * this time an indeterminant progress indicator will be shown instead.
	 * 
	 * @param shown
	 *            If true, the content view is shown; if false, the progress
	 *            indicator. The initial value is true.
	 * @see #setContentShownNoAnimation(boolean)
	 */
	public void setContentShown(final boolean shown) {
		mContentSwitcher.setContentShown(shown);
	}

	/**
	 * Like {@link #setContentShown(boolean)}, but no animation is used when
	 * transitioning from the previous state.
	 * 
	 * @param shown
	 *            If true, the content view is shown; if false, the progress
	 *            indicator. The initial value is true.
	 * @see #setContentShown(boolean)
	 */
	public void setContentShownNoAnimation(final boolean shown) {
		mContentSwitcher.setContentShownNoAnimation(shown);
	}

	/**
	 * Returns true if content is empty. The default content is not empty.
	 * 
	 * @return true if content is null or empty
	 * @see #setContentEmpty(boolean)
	 */
	public boolean isContentEmpty() {
		return mContentSwitcher.isContentEmpty();
	}

	/**
	 * If the content is empty, then set true otherwise false. The default
	 * content is not empty. You can't call this method if the content view has
	 * not been initialized before {@link #setContentView(android.view.View)}
	 * and content view not null.
	 * 
	 * @param isEmpty
	 *            true if content is empty else false
	 * @see #isContentEmpty()
	 */
	public void setContentEmpty(final boolean isEmpty) {
		mContentSwitcher.setContentEmpty(isEmpty);
	}

	public void setErrorOccured(final boolean error) {
		mContentSwitcher.setErrorOccured(error);
	}

	public boolean isErrorOccured() {
		return mContentSwitcher.isErrorOccured();
	}
}
