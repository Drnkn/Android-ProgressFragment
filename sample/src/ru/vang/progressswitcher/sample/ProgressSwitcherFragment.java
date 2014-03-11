package ru.vang.progressswitcher.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vang.progressswitcher.ProgressSwitcher;

public class ProgressSwitcherFragment extends BaseSwitcherFragment {

    private View mContentView;

    public static ProgressSwitcherFragment newInstance() {
        final ProgressSwitcherFragment fragment = new ProgressSwitcherFragment();

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.view_content, container, false);

        return mContentView;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ProgressSwitcher progressSwitcher = ProgressSwitcher
                .fromContentView(getActivity(), mContentView);
        setProgressSwitcher(progressSwitcher);
    }

}
