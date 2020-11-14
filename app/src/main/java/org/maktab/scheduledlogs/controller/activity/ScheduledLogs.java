package org.maktab.scheduledlogs.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab.scheduledlogs.controller.fragment.ScheduledLogsFragment;

public class ScheduledLogs extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, ScheduledLogs.class);
    }

    @Override
    public Fragment createFragment() {
        return ScheduledLogsFragment.newInstance();
    }

}