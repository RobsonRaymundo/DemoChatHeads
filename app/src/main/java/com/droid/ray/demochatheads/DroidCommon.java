package com.droid.ray.demochatheads;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Robson on 20/08/2017.
 */



public class DroidCommon {
    public static String TAG = "DemoChatHeads";

    public static boolean AtivarBotaoFlutuante(final Context context) {
        boolean spf = false;
        try {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            spf = sp.getBoolean("spf_botaoFlutuante", true);
        } catch (Exception ex) {
            Log.d(TAG, "AtivarBotaoFlutuante - " + ex.getMessage());
        }
        return spf;
    }

    public static void stopStartService(Context context, boolean start)
    {
        Intent intentService = new Intent(context, DroidHeadService.class);

        try {
            if (!start) {
                context.stopService(intentService);
                DroidHeadService.killService = true;
                Log.d(TAG, "stopService");
            }

        } catch (Exception ex) {
            Log.d(TAG, "stopService - Erro: " + ex.getMessage());
        }
        try {
            if (start) {
                context.startService(intentService);
                Log.d(TAG, "startService");
            }
        } catch (Exception ex) {
            Log.d(TAG, "starService - Erro: " + ex.getMessage());
        }
    }

}
