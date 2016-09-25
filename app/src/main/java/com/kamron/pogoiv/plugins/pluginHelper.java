package com.kamron.pogoiv.plugins;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.widget.LinearLayout;

import com.kamron.pogoiv.Pokefly;
import com.kamron.pogoiv.logic.IVScanResult;

import java.util.ArrayList;


import javax.annotation.OverridingMethodsMustInvokeSuper;

import lombok.Getter;

/**
 * Created by NightMadness on 9/24/2016.
 */

@Getter
public class PluginHelper {
    private static ArrayList<GoIVPlugin> plugins = new ArrayList<GoIVPlugin>();
    private static boolean isDoesHaveNeedForCandyOCR = false;

    public static void addPlugin(GoIVPlugin plug) {
        if (!isDoesHaveNeedForCandyOCR && plug.isNeedScanForCandy()) {
            isDoesHaveNeedForCandyOCR = true;
        }
        plugins.add(plug);
    }

    public static ArrayList<GoIVPlugin> getListOfPlugins(GoIVPlugin plug) {
        return plugins;
    }

    public static void populateAdvancedInformation(IVScanResult ivScanResult, Pokefly pokefly) {
        for (GoIVPlugin item : plugins) {
            item.populateAdvancedInformation(ivScanResult, pokefly);
        }
    }

    public static void generateDialogInputAndChangeVisibility(LinearLayout llPluginDialogContent, Context rcvContext) {
        for (GoIVPlugin item : plugins) {
            item.generateDialogInputAndChangeVisibility(llPluginDialogContent, rcvContext);
        }
    }

    @OverridingMethodsMustInvokeSuper
    public static void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox, Context rcvContext) {
        for (GoIVPlugin item : plugins) {
            //if all are true we can disable llPluginExpendedResultBox
            item.generateExpendedResultBoxAndChangeVisibility(llPluginExpendedResultBox, rcvContext);
        }
    }

    @OverridingMethodsMustInvokeSuper
    public static void addSettingsDialog(PreferenceScreen preferences, Context prfContext) {
        //if all are true we can disable llPluginExpendedResultBox
        for (GoIVPlugin item : plugins) {
            item.addSettingsDialog(preferences, prfContext);
        }
    }

}
