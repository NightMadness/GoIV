package com.kamron.pogoiv.plugins;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.kamron.pogoiv.GoIVSettings;
import com.kamron.pogoiv.logic.IVScanResult;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * Created by NightMadness on 9/24/2016.
 */

public abstract class GoIVPlugin {

    public GoIVPlugin(Context mainContext,boolean isDoesHaveSettingsDialog, boolean isDoesHaveNeedForCandyOCR, boolean
            isSettingsDialog,
                      boolean isDoesNeedTopopulateAdvancedInformation) {
        this.mainContext = mainContext;
        this.isDoesHaveSettingsDialog = isDoesHaveSettingsDialog;
        this.isDoesHaveNeedForCandyOCR = isDoesHaveNeedForCandyOCR;
        this.isSettingsDialog = isSettingsDialog;
        this.isDoesNeedTopopulateAdvancedInformation = isDoesNeedTopopulateAdvancedInformation;
    }

    protected Context mainContext;
    private boolean isDoesHaveSettingsDialog = false;
    private boolean isDoesHaveNeedForCandyOCR = false;
    private boolean isSettingsDialog = false;
    private boolean isDoesNeedTopopulateAdvancedInformation = false;
    protected static Map<String, TextView> pluginTextViewMap = new HashMap<String, TextView>();

    public void addTextViewMap(String ViewName, TextView vm)    {
        pluginTextViewMap.put(ViewName,vm);
    }

    public boolean isDoesHaveSettingsDialog() {
        return isDoesHaveSettingsDialog;
    }

    public boolean isDoesHaveNeedForCandyOCR() {
        return isDoesHaveNeedForCandyOCR;
    }

    public boolean isSettingsDialog() {
        return isSettingsDialog;
    }

    public void populateAdvancedInformation(IVScanResult ivScanResult) {
    }

}
