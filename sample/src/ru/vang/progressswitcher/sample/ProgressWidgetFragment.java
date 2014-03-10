package ru.vang.progressswitcher.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vang.progressswitcher.ProgressWidget;
import ru.vang.progressswitcher.Switcher;

public class ProgressWidgetFragment extends BaseSwitcherFragment {

    public static ProgressWidgetFragment newInstance() {
        final ProgressWidgetFragment fragment = new ProgressWidgetFragment();

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_progress_widget, container, false);
        final Switcher switcher = (ProgressWidget) view.findViewById(R.id.progress_widget);
        setProgressSwitcher(switcher);

        return view;
    }
}

