/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Animesh Samanta
 */
public class AESEncryption {
      private static final String ALGORITHM = "AES";
    private static final String KEY = "0123456789abcdef0123456789abcdef";

    public static String encrypt(String value) {
          try {
              Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
              Cipher cipher = Cipher.getInstance(ALGORITHM);
              cipher.init(Cipher.ENCRYPT_MODE, key);
              byte[] encrypted = cipher.doFinal(value.getBytes());
              return Base64.getEncoder().encodeToString(encrypted);
          }
          catch (NoSuchAlgorithmException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (NoSuchPaddingException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (InvalidKeyException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (IllegalBlockSizeException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (BadPaddingException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          return null;
    }

    public static String decrypt(String value) {
          try {
              Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
              Cipher cipher = Cipher.getInstance(ALGORITHM);
              cipher.init(Cipher.DECRYPT_MODE, key);
              byte[] decodedValue = Base64.getDecoder().decode(value);
              byte[] decrypted = cipher.doFinal(decodedValue);
              return new String(decrypted);
          }
          catch (NoSuchAlgorithmException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (NoSuchPaddingException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (InvalidKeyException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (IllegalBlockSizeException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (BadPaddingException ex) {
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch(Exception ex){
              Logger.getLogger(AESEncryption.class.getName()).log(Level.SEVERE, null, ex);
          }
          return null;
    }

    
}
