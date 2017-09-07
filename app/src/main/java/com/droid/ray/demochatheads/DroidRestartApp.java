package com.droid.ray.demochatheads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Robson on 03/05/2017.
 */

public class DroidRestartApp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (DroidPreferences.GetInteger(context, "show") == 1) {
            DroidCommon.stopStartService(context, DroidCommon.AtivarBotaoFlutuante(context));
        }
    }
}
