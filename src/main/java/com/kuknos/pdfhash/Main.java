package com.kuknos.pdfhash;

import java.util.Base64;

import com.kuknos.kgen.seededrsa.KRSAKeyPair;
import com.kuknos.kgen.seededrsa.RSAKey;
import com.kuknos.kgen.seededrsa.persianmnemonics.Wallet;

public class Main {
    public static void main( String[] args ) throws Exception{


    String mnemonic = new String(Wallet.generate12WordMnemonic()) ;

    System.out.println(mnemonic);


    //generate RSA from mnemonics  ---- sign and verify
    RSAKey rsakey = new RSAKey(mnemonic);
    KRSAKeyPair keypair = rsakey.generate(2048);
    String data = "data for sign";
    byte[] signature = keypair.sign(data.getBytes());

    System.out.println(Base64.getEncoder().encodeToString(keypair.getEncodedPrivateKey()));
    System.out.println("");
    System.out.println(Base64.getEncoder().encodeToString(keypair.getEncodedPublicKey()));
    System.out.println("");
    System.out.println("sig : "+Base64.getEncoder().encodeToString(signature));
    System.out.println("");
    System.out.println("verify : "+keypair.verify(signature, data.getBytes()));
    System.out.println("");



    //generate Ed25519 from mnemonics  ---- sign and verify
    org.stellar.sdk.KeyPair ed25519keyPair = Wallet.createKeyPair(mnemonic.toCharArray(), null, 0);
    System.out.println(ed25519keyPair.getAccountId());
    byte[] ed25519Signature = ed25519keyPair.sign(data.getBytes());

    System.out.println("ed25519Sig : "+Base64.getEncoder().encodeToString(ed25519Signature));
    System.out.println("");
    System.out.println("verify ed25519Signature: "+ed25519keyPair.verify(data.getBytes(),ed25519Signature));

    }
    
    
}



