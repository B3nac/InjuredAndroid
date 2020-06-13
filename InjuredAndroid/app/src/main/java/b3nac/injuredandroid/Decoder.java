package b3nac.injuredandroid;

import android.util.Base64;

/**
 * Created by B3nac on 12/4/2018.
 */

public class Decoder {
    private byte [] data = Base64.decode("NF9vdmVyZG9uZV9vbWVsZXRz", Base64.DEFAULT);

    public byte[] getData() {
        return data;
    }
}
