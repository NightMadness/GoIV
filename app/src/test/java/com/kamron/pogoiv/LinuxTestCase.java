package com.kamron.pogoiv;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by NightMadness on 11/20/2016.
 */

//this test only runs on linux
public class LinuxTestCase extends TestCase {

    private final String dataPath = "/storage/emulated/0/Android/data/com.kamron.pogoiv.nointernet/files";
    private final String nidoFemale = "Nidoran♀";
    private final String nidoMale = "Nidoran♂";
    private final boolean isPokeSpamEnabled = true;

    @Test
    public void testcase(){
        int heightPixels = 2560;
        int widthPixels = 1440;
        OcrHelper.init(dataPath, widthPixels, heightPixels, nidoFemale, nidoMale,
                true);
        assertTrue(true);

    }


}
