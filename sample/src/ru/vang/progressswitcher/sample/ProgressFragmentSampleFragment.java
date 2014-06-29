package ru.vang.progressswitcher.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProgressFragmentSampleFragment extends BaseProgressSwitcherFragment {

    private View mContentView;

    public static ProgressFragmentSampleFragment newInstance() {
        final ProgressFragmentSampleFragment fragment = new ProgressFragmentSampleFragment();

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.view_content, null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addContentView(mContentView);
        setOnErrorViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }, R.id.retry);
    }

}
