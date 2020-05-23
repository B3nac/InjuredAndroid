package b3nac.injuredandroid;

import android.util.Base64;

public class Hide {
    private static byte[] encKey = Base64.decode("Q2FwdHVyM1RoMXM=", 0);
    private static byte[] encKeyTwo = Base64.decode("e0NhcHR1cjNUaDFzVG9vfQ==", 0);
    private static String remoteUrl = "9EEADi^^:?;FC652?5C@:5]7:C632D6:@]4@>^DB=:E6];D@?";

    static byte[] getKey() {
        return encKey;
    }

    static byte[] getAnotherKey() {
        return encKeyTwo;
    }

    static String getRemoteUrl() {
        return remoteUrl;
    }
}
