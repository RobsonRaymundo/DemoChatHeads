package com.droid.ray.demochatheads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Robson on 13/08/2017.
 */

public class DroidRestartService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DroidCommon.stopStartService(context, DroidCommon.AtivarBotaoFlutuante(context));

    }
}
