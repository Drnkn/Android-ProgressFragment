package ru.vang.progressswitcher.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vang.progressswitcher.ProgressWidget;

public class CustomLayoutsFragment extends BaseSwitcherFragment {

    public static CustomLayoutsFragment newInstance() {
        final CustomLayoutsFragment fragment = new CustomLayoutsFragment();

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_custom_layouts, container, false);
        final ProgressWidget progressWidget = (ProgressWidget) view.findViewById(R.id.progress_widget);
        setProgressSwitcher(progressWidget);

        return view;
    }

}
