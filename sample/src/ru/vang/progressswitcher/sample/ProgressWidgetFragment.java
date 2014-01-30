package ru.vang.progressswitcher.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vang.progressswitcher.ProgressWidget;

public class ProgressWidgetFragment extends Fragment implements View.OnClickListener {

    private ProgressWidget mProgressWidget;

    public static ProgressWidgetFragment newInstance() {
        final ProgressWidgetFragment fragment = new ProgressWidgetFragment();

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_progress_widget,
                container, false);

        mProgressWidget = (ProgressWidget) view.findViewById(R.id.progress_widget);
        mProgressWidget.setEmptyText("Empty :\\");
        mProgressWidget.setErrorText("Error :(");
        view.findViewById(R.id.content).setOnClickListener(this);
        view.findViewById(R.id.progress).setOnClickListener(this);
        view.findViewById(R.id.empty).setOnClickListener(this);
        view.findViewById(R.id.error).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(final View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.progress:
                mProgressWidget.showProgress();
                break;
            case R.id.content:
                mProgressWidget.showContent();
                break;
            case R.id.empty:
                mProgressWidget.showEmpty();
                break;
            case R.id.error:
                mProgressWidget.showError();
                break;
            default:
                break;
        }
    }

}

