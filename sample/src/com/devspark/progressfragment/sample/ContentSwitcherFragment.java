package com.devspark.progressfragment.sample;

import com.devspark.progressfragment.ContentSwitcher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ContentSwitcherFragment extends Fragment {
	private View mContentView;
	private Handler mHandler;
	private ContentSwitcher mContentSwitcher;
	private Runnable mShowContentRunnable = new Runnable() {

		@Override
		public void run() {
			mContentSwitcher.setContentEmpty(true);
			mContentSwitcher.setContentShown(true);
		}

	};

	public static ContentSwitcherFragment newInstance() {
		final ContentSwitcherFragment fragment = new ContentSwitcherFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.view_content, null);
		return inflater.inflate(R.layout.fragment_progress, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContentSwitcher = new ContentSwitcher(getActivity());
		mContentSwitcher.setRootView(getView());
		// Setup content view
		mContentSwitcher.setContentView(mContentView);
		// Setup error message
		mContentSwitcher.setErrorText(R.string.error);
		obtainData();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mHandler.removeCallbacks(mShowContentRunnable);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.refresh, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			obtainData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void obtainData() {
		// Show indeterminate progress
		mContentSwitcher.setContentShown(false);

		mHandler = new Handler();
		mHandler.postDelayed(mShowContentRunnable, 3000);
	}
}
