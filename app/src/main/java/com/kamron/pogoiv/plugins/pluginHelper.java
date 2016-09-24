package com.kamron.pogoiv.plugins;

import com.kamron.pogoiv.logic.IVScanResult;

import java.util.ArrayList;


import lombok.Getter;

/**
 * Created by NightMadness on 9/24/2016.
 */

@Getter
public class PluginHelper {
    private static ArrayList<GoIVPlugin> plugins = new ArrayList<GoIVPlugin>();
    private static boolean isDoesHaveNeedForCandyOCR = false;

    public static void addPlugin(GoIVPlugin plug) {
        if (!isDoesHaveNeedForCandyOCR && plug.isDoesHaveNeedForCandyOCR()) {
            isDoesHaveNeedForCandyOCR = true;
        }
        plugins.add(plug);
    }

    public static ArrayList<GoIVPlugin> getListOfPlugins(GoIVPlugin plug) {
        return plugins;
    }

    public static void populateAdvancedInformation(IVScanResult ivScanResult) {
        for (GoIVPlugin item : plugins) {
            item.populateAdvancedInformation(ivScanResult);
        }
    }
}
