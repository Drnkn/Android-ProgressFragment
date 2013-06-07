package com.devspark.progressfragment.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.devspark.progressfragment.ContentSwitcher;

public class PrepareSwitcher extends Fragment {
	private Handler mHandler;
	private ContentSwitcher mContentSwitcher;
	private Runnable mShowContentRunnable = new Runnable() {

		@Override
		public void run() {		
			mContentSwitcher.setContentShown(true);
		}

	};

	public static PrepareSwitcher newInstance() {
		final PrepareSwitcher fragment = new PrepareSwitcher();
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
		return inflater.inflate(R.layout.fragment_prepared_content, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContentSwitcher = ContentSwitcher.prepare(getActivity(), PrepareSwitcher.this);
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