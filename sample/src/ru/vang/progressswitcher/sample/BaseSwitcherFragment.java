package ru.vang.progressswitcher.sample;

import android.support.v4.app.Fragment;

import ru.vang.progressswitcher.Switcher;

public abstract class BaseSwitcherFragment extends Fragment implements ProgressActivity.OnSwitchListener {

    protected Switcher mProgressSwitcher;

    public void setProgressSwitcher(final Switcher switcher) {
        mProgressSwitcher = switcher;
    }

    @Override
    public void showProgress() {
        mProgressSwitcher.showProgress();
    }

    @Override
    public void showContent() {
        mProgressSwitcher.showContent();
    }

    @Override
    public void showEmpty() {
        mProgressSwitcher.showEmpty();
    }

    @Override
    public void showError() {
        mProgressSwitcher.showError();
    }
}
