package ru.vang.progressswitcher.sample;

import ru.vang.progressswitcher.ProgressFragment;

public abstract class BaseProgressSwitcherFragment extends ProgressFragment implements ProgressActivity.OnSwitchListener {

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    public void showEmpty() {
        super.showEmpty();
    }

    @Override
    public void showError() {
        super.showError();
    }
}
