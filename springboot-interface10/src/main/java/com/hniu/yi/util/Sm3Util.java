package com.hniu.yi.util;

import org.bouncycastle.crypto.digests.SM3Digest;

public class Sm3Util {
    public static byte[] hash(byte[] srcData){
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(srcData,0,srcData.length);
        byte[] bytes = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(bytes,0);
        return bytes;
    }
}
