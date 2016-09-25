package com.kamron.pogoiv.plugins;

import android.content.Context;
import android.preference.PreferenceScreen;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kamron.pogoiv.Pokefly;
import com.kamron.pogoiv.logic.IVScanResult;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by NightMadness on 9/24/2016.
 */

public abstract class GoIVPlugin {

    public GoIVPlugin(Context mainContext, boolean isNeedScanForCandy) {
        this.mainContext = mainContext;
        this.isNeedScanForCandy = isNeedScanForCandy;
    }

    protected Context mainContext;

    private boolean isNeedScanForCandy = false;

    protected static Map<String, TextView> pluginTextViewMap = new HashMap<String, TextView>();

    public void addTextViewMap(String ViewName, TextView vm)    {
        pluginTextViewMap.put(ViewName,vm);
    }

    public boolean isNeedScanForCandy() {
        return isNeedScanForCandy;
    }

    public void populateAdvancedInformation(IVScanResult ivScanResult, Pokefly pokefly) {
    }

    public void generateDialogInputAndChangeVisibility(LinearLayout llPluginDialogContent) {
    }

    public void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox) {
    }

    public void addSettingsDialog(PreferenceScreen preferences, Context rcvContext) {
    }

    public boolean isEnabled() {
        return false;
    }

}
