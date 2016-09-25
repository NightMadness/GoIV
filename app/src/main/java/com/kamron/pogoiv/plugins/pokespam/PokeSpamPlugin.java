package com.kamron.pogoiv.plugins.pokespam;

import android.content.Context;
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
        super(mainContext, true);
    }

    private LinearLayout pokeSpamDialogView;
    private LinearLayout pokeSpamExtendedResultsBoxView;
    private final String displayLabelForSpamTextView = "displayLabelForSpamTextView";
    public static final String pokeSpamEnabledKey = "pokeSpamEnabledKey";


    @Override
    public void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox) {
        boolean pokeSpamEnabled = GoIVSettings.getInstance(mainContext).isSettingEnabled(pokeSpamEnabledKey);
        if (pokeSpamExtendedResultsBoxView == null && pokeSpamEnabled) {
            LayoutInflater inflater = LayoutInflater.from(mainContext);
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
        }
    }

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
        if (GoIVSettings.getInstance(mainContext).isSettingEnabled(pokeSpamEnabledKey)
                && pokemonCandy.isPresent() && ivScanResult.pokemon != null
                && ivScanResult.pokemon.candyEvolutionCost > 0) {
            PokeSpam pokeSpamCalculator = new PokeSpam(pokemonCandy.get(), ivScanResult.pokemon.candyEvolutionCost);

            String text = mainContext.getString(R.string.pokespam_formated_message,
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
        SwitchPreference pokeSpamEnabled = new SwitchPreference(prfContext);

        pokeSpamEnabled.setKey(pokeSpamEnabledKey);
        pokeSpamEnabled.setTitle(prfContext.getString(R.string.PokeSpam_setting_title));
        pokeSpamEnabled.setSummary(prfContext.getString(R.string.PokeSpam_setting_summary));
        pokeSpamEnabled.setDefaultValue(true);
        preferences.addPreference(pokeSpamEnabled);
    }

    public boolean isEnabled() {
        return GoIVSettings.getInstance(mainContext).isSettingEnabled(pokeSpamEnabledKey);
    }


}
