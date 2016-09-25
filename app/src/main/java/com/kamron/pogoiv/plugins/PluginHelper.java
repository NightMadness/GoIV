package com.kamron.pogoiv.plugins;

import android.content.Context;
import android.preference.PreferenceScreen;
import android.widget.LinearLayout;

import com.kamron.pogoiv.Pokefly;
import com.kamron.pogoiv.logic.IVScanResult;

import java.util.ArrayList;


import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Created by NightMadness on 9/24/2016.
 */


public class PluginHelper {
    private static ArrayList<GoIVPlugin> plugins = new ArrayList<GoIVPlugin>();


    public static boolean isNeedScanForCandy() {
        for (GoIVPlugin item : plugins) {
            if (item.isEnabled() && item.isNeedScanForCandy()) {
                return true;
            }
        }
        return false;
    }

    public static void addPlugin(GoIVPlugin plug) {
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

    /**
     * This method will generate input on the main Dialog Screen.
     */
    public static void generateDialogInputAndChangeVisibility(LinearLayout llPluginDialogContent) {
        for (GoIVPlugin item : plugins) {
            item.generateDialogInputAndChangeVisibility(llPluginDialogContent);
        }
    }

    /**
     * This method will generate input on the main ExpendedResultBox.
     */
    @OverridingMethodsMustInvokeSuper
    public static void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox) {
        for (GoIVPlugin item : plugins) {
            //if no method we can disable llPluginExpendedResultBox
            item.generateExpendedResultBoxAndChangeVisibility(llPluginExpendedResultBox);
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
