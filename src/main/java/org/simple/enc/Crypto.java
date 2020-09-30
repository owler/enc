package org.simple.enc;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Crypto implements StringEncryptor {
    private final String password;
    private StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    public Crypto(String password) {
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        this.password = password;
        encryptor.setPassword(password);
    }

    @Override
    public String encrypt(String s) {
        return encryptor.encrypt(s);
    }

    @Override
    public String decrypt(String s) {
        if (s.startsWith("ENC(") && s.endsWith(")"))
            try {
                return encryptor.decrypt(s.substring(4, s.length() - 1));
            } catch (Exception e) {
                throw new RuntimeException("Ensure correct encryption key [" + password + "] was provided.", e);
            }
        else
            return s;
    }

}
