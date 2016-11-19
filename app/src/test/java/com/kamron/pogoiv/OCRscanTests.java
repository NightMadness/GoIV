package com.kamron.pogoiv;

import android.graphics.BitmapFactory;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.kamron.pogoiv.logic.IVCombination;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by NightMadness on 11/18/2016.
 */

public class OCRscanTests {


    @Test

    public void testImage1() {

        String dataPath = "/storage/emulated/0/Android/data/com.kamron.pogoiv.nointernet/files";
        //String dataPath = "C:\\Users\\ofirp_000\\Downloads\\android\\goIv\\GoIV\\app\\src\\main\\assets";
        int widthPixels = 1440;
        int heightPixels = 2560;
        String nidoFemale = "Nidoran♀";
        String nidoMale = "Nidoran♂";
        boolean isPokeSpamEnabled = true;

        OcrHelper x = OcrHelper.init(dataPath,  widthPixels,  heightPixels,  nidoFemale,  nidoMale,
        true);
        Assert.assertTrue(true);
//        BitmapFactory.decodeResource(getResources(), R);
//        x.scanPokemon(GoIVTestImages/test1.png)
    }

}
