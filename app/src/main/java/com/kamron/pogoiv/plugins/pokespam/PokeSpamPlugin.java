package com.kamron.pogoiv.plugins.pokespam;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.base.Optional;
import com.kamron.pogoiv.GoIVSettings;
import com.kamron.pogoiv.Pokefly;
import com.kamron.pogoiv.R;
import com.kamron.pogoiv.logic.IVScanResult;
import com.kamron.pogoiv.plugins.GoIVPlugin;


/**
 * Created by NightMadness on 9/24/2016.
 */

public class PokeSpamPlugin extends GoIVPlugin {

    public PokeSpamPlugin(Context mainContext) {
        super(mainContext, true, true, true, true);
    }

    private LinearLayout pokeSpamDialogView;
    private LinearLayout pokeSpamExtendedResultsBoxView;
    private final String displayLabelForSpamTextView = "displayLabelForSpamTextView";

    //FIX ME!!! remove context if we dont need it
    @Override
    public void generateDialogInputAndChangeVisibility(LinearLayout llPluginDialogContent, Context rcvContext) {
        /*
        super.generateDialogInputAndChangeVisibility(llPluginDialogContent, rcvContext);
        boolean pokeSpamEnabled = GoIVSettings.getInstance(rcvContext).isPokeSpamEnabled();
        if (pokeSpamDialogView == null && pokeSpamEnabled) {

                LayoutInflater inflater = LayoutInflater.from(rcvContext);
                pokeSpamDialogView = (LinearLayout) inflater.inflate(R.layout.pokespamdialog, null, false);
                super.addTextViewMap("etCandy", (TextView) pokeSpamDialogView.getChildAt(0));
                super.addTextViewMap("candlyLabel", (TextView) pokeSpamDialogView.getChildAt(1));
                llPluginDialogContent.addView(pokeSpamDialogView);
                llPluginDialogContent.setVisibility(View.VISIBLE);

        } else if (pokeSpamDialogView != null && pokeSpamEnabled) {
            pokeSpamDialogView.setVisibility(View.VISIBLE);
        } else if (pokeSpamDialogView != null && !pokeSpamEnabled) {
            pokeSpamDialogView.setVisibility(View.GONE);
        } else if (pokeSpamDialogView == null && !pokeSpamEnabled) {
        }
        */
    }

    @Override
    public void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox, Context rcvContext) {
        super.generateExpendedResultBoxAndChangeVisibility(llPluginExpendedResultBox, rcvContext);
        boolean pokeSpamEnabled = GoIVSettings.getInstance(rcvContext).isPokeSpamEnabled();
        if (pokeSpamExtendedResultsBoxView == null && pokeSpamEnabled) {

                LayoutInflater inflater = LayoutInflater.from(rcvContext);
                pokeSpamExtendedResultsBoxView = (LinearLayout) inflater.inflate(R.layout.pokespamexpenededresults,
                        null, false);
                super.addTextViewMap(displayLabelForSpamTextView,
                        (TextView) pokeSpamExtendedResultsBoxView.getChildAt(2));
                llPluginExpendedResultBox.addView(pokeSpamExtendedResultsBoxView);
                llPluginExpendedResultBox.setVisibility(View.VISIBLE);

        } else if (pokeSpamExtendedResultsBoxView != null && pokeSpamEnabled) {
            pokeSpamExtendedResultsBoxView.setVisibility(View.VISIBLE);
        } else if (pokeSpamExtendedResultsBoxView != null && !pokeSpamEnabled) {
            pokeSpamExtendedResultsBoxView.setVisibility(View.GONE);
        } else if (pokeSpamExtendedResultsBoxView == null && !pokeSpamEnabled) {
        }
    }

    //change this, pokefly is not a reliable reference
    @Override
    public void populateAdvancedInformation(IVScanResult ivScanResult, Pokefly pokefly) {
        setAndCalculatePokeSpamText(ivScanResult, pokefly.getPokemonCandy());
    }

    /**
     * setAndCalculatePokeSpamText sets PokeSpamtext and makes it visible.
     *
     * @param ivScanResult IVScanResult object that contains the scan results, mainly needed to get candEvolutionCost
     *                     variable
     * @param pokemonCandy how many pokemon candy we have
     */
    private void setAndCalculatePokeSpamText(IVScanResult ivScanResult, Optional<Integer> pokemonCandy) {
        TextView pokeSpamDisplayLabel = pluginTextViewMap.get(displayLabelForSpamTextView);
        if (pokeSpamExtendedResultsBoxView == null || pokeSpamDisplayLabel == null) {
            return;
        }
        if (GoIVSettings.getInstance(mainContext).isPokeSpamEnabled()
                && pokemonCandy.isPresent() && ivScanResult.pokemon != null
                && ivScanResult.pokemon.candyEvolutionCost > 0) {
            PokeSpam pokeSpamCalculator = new PokeSpam(pokemonCandy.get(), ivScanResult.pokemon.candyEvolutionCost);

            String text = mainContext.getString(R.string.pokespamformatedmessage,
                    pokeSpamCalculator.getTotalEvolvable(), pokeSpamCalculator.getEvolveRows(),
                    pokeSpamCalculator.getEvolveExtra());
            pokeSpamDisplayLabel.setText(text);
            pokeSpamExtendedResultsBoxView.setVisibility(View.VISIBLE);
        } else {
            pokeSpamDisplayLabel.setText("");
            pokeSpamExtendedResultsBoxView.setVisibility(View.GONE);
        }
    }
    public void addSettingsDialog(PreferenceScreen preferences, Context prfContext) {
//        <SwitchPreference
//        android:key="pokeSpamEnabled"
//        android:title="@string/PokeSpam_setting_title"
//        android:summary="@string/PokeSpam_setting_summary"
//        android:defaultValue="true"/>

        SwitchPreference pokeSpamEnabled = new SwitchPreference(prfContext);
        pokeSpamEnabled.setKey(GoIVSettings.POKESPAM_ENABLED);
        pokeSpamEnabled.setTitle(prfContext.getString(R.string.PokeSpam_setting_title));
        pokeSpamEnabled.setSummary(prfContext.getString(R.string.PokeSpam_setting_summary));
        pokeSpamEnabled.setDefaultValue(true);
        //pokeSpamEnabled.setOrder(preferences.getPreferenceCount());
        preferences.addPreference(pokeSpamEnabled);

    }

}
