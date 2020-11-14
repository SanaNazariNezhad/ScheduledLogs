package org.maktab.scheduledlogs.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.maktab.scheduledlogs.R;
import org.maktab.scheduledlogs.model.Logs;
import org.maktab.scheduledlogs.repository.LogDBRepository;

import java.util.ArrayList;
import java.util.List;

public class ScheduledLogsFragment extends Fragment {

    private LogDBRepository mLogDBRepository;
    private TextView mTextViewLogs;
    private Button mButtonStart, mButtonStop;

    public ScheduledLogsFragment() {
        // Required empty public constructor
    }

    public static ScheduledLogsFragment newInstance() {
        ScheduledLogsFragment fragment = new ScheduledLogsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogDBRepository = LogDBRepository.getInstance(getActivity());
        mLogDBRepository.fetchItemsAsync();
        setLiveDataObservers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduled_logs, container, false);
        findView(view);
        listeners();
        return view;
    }

    private void listeners() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogDBRepository.fetchItemsAsync();
                setLiveDataObservers();
            }
        });
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void findView(View view) {
        mTextViewLogs = view.findViewById(R.id.text_log);
        mButtonStart = view.findViewById(R.id.button_start);
        mButtonStop = view.findViewById(R.id.button_stop);
    }

    private void setLiveDataObservers() {
        mLogDBRepository.getLogItemsLiveData().observe(this, new Observer<List<Logs>>() {
            @Override
            public void onChanged(List<Logs> items) {
//                List<String> result = new ArrayList<>();
                List<Logs> databaseLog = mLogDBRepository.getLogs();
                List<Logs> totalResult = new ArrayList<>();
                totalResult.addAll(databaseLog);
                totalResult.addAll(items);
                String result = "";
                for (int i = 0; i < totalResult.size(); i++) {
                    result = totalResult.get(i).getPrimaryId() + "\t" + totalResult.get(i).getName() + "\t"
                            + totalResult.get(i).getCurrentDate().toString() + "\t"
                            + totalResult.get(i).getTimestamp() + "\n";
                }

                String textMessage = mTextViewLogs.getText().toString() + result;
                mTextViewLogs.setText(textMessage);
            }
        });

    }
}