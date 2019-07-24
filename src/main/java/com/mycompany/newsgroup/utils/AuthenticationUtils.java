/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author denzel
 */
public class AuthenticationUtils {
    private static final Random RANDOM= new SecureRandom();
    private static final String ALPHABET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS=1000;
    private static final int KEY_LENGTH=256;
    
    public String generateSalt(int length){
        StringBuilder returnValue=new StringBuilder(length);
        for(int i=0;i<length;i++){
        returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    }
        return new String(returnValue);
    }
    
    public String generateSecurePassword(String password, String salt)throws InvalidKeySpecException{
        byte[] securedPassword=hash(password.toCharArray(),salt.getBytes());
        return Base64.getEncoder().encodeToString(securedPassword);
    }
    
    private byte[] hash(char[] password, byte[] salt)throws InvalidKeySpecException{
        PBEKeySpec spec=new PBEKeySpec(password, salt,ITERATIONS,KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try{
             SecretKeyFactory skf=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
             return skf.generateSecret(spec).getEncoded();
        }catch(NoSuchAlgorithmException e){
            throw new AssertionError("Error while hashing a password "+e.getMessage(),e);
        }finally{
            spec.clearPassword();
        }
        
    }
}
