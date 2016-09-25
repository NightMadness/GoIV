package com.kamron.pogoiv.plugins.pokespam;

import android.content.Context;
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

    //FIX ME!!!
    @Override
    public void generateDialogInputAndChangeVisibility(LinearLayout llPluginDialogContent) {
        super.generateDialogInputAndChangeVisibility(llPluginDialogContent);
        if (GoIVSettings.getInstance(mainContext).isPokeSpamEnabled()) {
            if (pokeSpamDialogView == null) {
                LayoutInflater inflater = LayoutInflater.from(mainContext);
                pokeSpamDialogView = (LinearLayout) inflater.inflate(R.layout.pokespamdalog, null, false);
                super.addTextViewMap("etCandy", (TextView) pokeSpamDialogView.getChildAt(0));
                super.addTextViewMap("candlyLabel", (TextView) pokeSpamDialogView.getChildAt(1));
                llPluginDialogContent.addView(pokeSpamDialogView);
                llPluginDialogContent.setVisibility(View.VISIBLE);
            } else {
                pokeSpamDialogView.setVisibility(View.VISIBLE);
            }
        } else {
            pokeSpamDialogView.setVisibility(View.GONE);
        }

    }

    @Override
    public void generateExpendedResultBoxAndChangeVisibility(LinearLayout llPluginExpendedResultBox) {
        super.generateExpendedResultBoxAndChangeVisibility(llPluginExpendedResultBox);
        if (GoIVSettings.getInstance(mainContext).isPokeSpamEnabled()) {
            if (pokeSpamExtendedResultsBoxView == null) {
                LayoutInflater inflater = LayoutInflater.from(mainContext);
                pokeSpamExtendedResultsBoxView = (LinearLayout) inflater.inflate(R.layout.pokespamexpenededresults,
                        null, false);
                super.addTextViewMap(displayLabelForSpamTextView, (TextView) pokeSpamExtendedResultsBoxView.getChildAt(2));
                llPluginExpendedResultBox.addView(pokeSpamExtendedResultsBoxView);
                llPluginExpendedResultBox.setVisibility(View.VISIBLE);
            } else {
                pokeSpamExtendedResultsBoxView.setVisibility(View.VISIBLE);
            }
        } else {
            pokeSpamExtendedResultsBoxView.setVisibility(View.GONE);
        }
    }

    //change this, pokefly is not a reliable reference
    @Override
    public void populateAdvancedInformation(IVScanResult ivScanResult, Pokefly pokefly) {
        setAndCalculatePokeSpamText(ivScanResult, pokefly.getPokemonCandy());
    }

    /**
     * setAndCalculatePokeSpamText sets PokeSpamtext and makes it visible.
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

}
