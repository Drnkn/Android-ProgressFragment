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

package ru.vang.progressswitcher.sample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * @author Evgeny Shishkin
 */
public class ProgressActivity extends FragmentActivity implements View.OnClickListener {

    public static final String EXTRA_TITLE = "ru.vang.progressfragment.sample.extras.EXTRA_TITLE";

    public static final String EXTRA_FRAGMENT
            = "ru.vang.progressfragment.sample.extras.EXTRA_FRAGMENT";

    public static final int FRAGMENT_PROGRESS_FRAGMENT = 0;

    public static final int FRAGMENT_SWITCHER = 1;

    public static final int FRAGMENT_WIDGET = 2;

    public static final int FRAGMENT_CUSTOM_LAYOUTS = 3;

    private static final String TAG_FRAGMENT = "fragment_progress";

    public interface OnSwitchListener {

        public void showProgress();

        public void showContent();

        public void showEmpty();

        public void showError();

    }

    private Fragment mFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra(EXTRA_TITLE));
        setContentView(R.layout.actvitiy_progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            new ActionBarHelper().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.action_progress).setOnClickListener(this);
        findViewById(R.id.action_content).setOnClickListener(this);
        findViewById(R.id.action_empty).setOnClickListener(this);
        findViewById(R.id.action_error).setOnClickListener(this);

        if (savedInstanceState == null) {
            int fragmentId = getIntent().getIntExtra(EXTRA_FRAGMENT, FRAGMENT_PROGRESS_FRAGMENT);
            switch (fragmentId) {
                case FRAGMENT_PROGRESS_FRAGMENT:
                    mFragment = ProgressFragmentSampleFragment.newInstance();
                    break;
                case FRAGMENT_SWITCHER:
                    mFragment = ProgressSwitcherFragment.newInstance();
                    break;
                case FRAGMENT_WIDGET:
                    mFragment = ProgressWidgetFragment.newInstance();
                    break;
                case FRAGMENT_CUSTOM_LAYOUTS:
                    mFragment = CustomLayoutsFragment.newInstance();
                    break;
                default:
                    throw new IllegalArgumentException("Incorrect index: " + fragmentId);

            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mFragment, TAG_FRAGMENT).commit();
        } else {
            mFragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(final View view) {
        final int id = view.getId();
        final OnSwitchListener localFragment = (OnSwitchListener) mFragment;
        switch (id) {
            case R.id.action_progress:
                localFragment.showProgress();
                break;
            case R.id.action_content:
                localFragment.showContent();
                break;
            case R.id.action_empty:
                localFragment.showEmpty();
                break;
            case R.id.action_error:
                localFragment.showError();
                break;
        }
    }

    class ActionBarHelper {

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
            getActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }
}
