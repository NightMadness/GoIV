package com.kamron.pogoiv.plugins.PokeSpam;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kamron.pogoiv.R;
import com.kamron.pogoiv.logic.IVScanResult;
import com.kamron.pogoiv.plugins.GoIVPlugin;

/**
 * Created by NightMadness on 9/24/2016.
 */

public class PokeSpamPlugin extends GoIVPlugin {

    public PokeSpamPlugin(Context mainContext) {
        super(mainContext,true,true,true,true);
    }

//    public void test() {
//       if (mainContext.getString(R.string.pokespamformatedmessage) == "") {
//       }
//    }

    @Override
    public void populateAdvancedInformation(IVScanResult ivScanResult) {
    }

//    /**
//     * setAndCalculatePokeSpamText sets PokeSpamtext and makes it visible.
//     * @param ivScanResult IVScanResult object that contains the scan results, mainly needed to get candEvolutionCost
//     *                     varible
//     */
//    private void setAndCalculatePokeSpamText(IVScanResult ivScanResult) {
//        if (pokemonCandy.isPresent()
//                && ivScanResult.pokemon != null &&  ivScanResult.pokemon.candyEvolutionCost > 0) {
//            PokeSpam pokeSpamCalculator = new PokeSpam(pokemonCandy.get(),ivScanResult.pokemon.candyEvolutionCost);
//
//            String text = maintContext.getString(R.string.pokespamformatedmessage,
//                    pokeSpamCalculator.getTotalEvolvable(),pokeSpamCalculator.getEvolveRows(),
//                    pokeSpamCalculator.getEvolveExtra());
//            exResPokeSpam.setText(text);
//            pokeSpamView.setVisibility(View.VISIBLE);
//        } else {
//            exResPokeSpam.setText("");
//            pokeSpamView.setVisibility(View.GONE);
//        }
//    }

}
