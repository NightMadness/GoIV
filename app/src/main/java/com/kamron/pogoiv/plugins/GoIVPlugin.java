package com.kamron.pogoiv.plugins;

import android.content.Context;
import android.preference.PreferenceScreen;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kamron.pogoiv.Pokefly;
import com.kamron.pogoiv.logic.IVScanResult;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Created by NightMadness on 9/24/2016.
 */

public abstract class GoIVPlugin {

    public GoIVPlugin(Context mainContext, boolean isNeedSettingsDialog, boolean isNeedScanForCandy, boolean
            isSettingsDialog,
                      boolean isAdvancedInformation) {
        this.mainContext = mainContext;
        this.isNeedSettingsDialog = isNeedSettingsDialog;
        this.isNeedScanForCandy = isNeedScanForCandy;
        this.isSettingsDialog = isSettingsDialog;
        this.isAdvancedInformation = isAdvancedInformation;
    }

    protected Context mainContext;
    private boolean isNeedSettingsDialog = false;
    private boolean isNeedScanForCandy = false;
    private boolean isSettingsDialog = false;
    private boolean isAdvancedInformation = false;
    protected static Map<String, TextView> pluginTextViewMap = new HashMap<String, TextView>();

    public void addTextViewMap(String ViewName, TextView vm)    {
        pluginTextViewMap.put(ViewName,vm);
    }

    public boolean isNeedSettingsDialog() {
        return isNeedSettingsDialog;
    }

    public boolean isNeedScanForCandy() {
        return isNeedScanForCandy;
    }

    public boolean isSettingsDialog() {
        return isSettingsDialog;
    }

    public void populateAdvancedInformation(IVScanResult ivScanResult, Pokefly pokefly) {
    }

    @OverridingMethodsMustInvokeSuper
    public void generateDialogInputAndChangeVisibility(LinearLayout llPluginDialogContent, Context prfContext) {
    }

    @OverridingMethodsMustInvokeSuper
    public void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox, Context rcvContext) {
    }

    public void addSettingsDialog(PreferenceScreen preferences, Context rcvContext) {
    }

}
