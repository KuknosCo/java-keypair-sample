
## Install
  ``` kotlin
     <dependencies>


    <dependency>
      <groupId>${project.groupId}</groupId>
      <artifactId>mybcp-v1</artifactId>
      <version>1.71</version>
      <scope>system</scope>
      <systemPath>${project.basedir}/src/main/resources/mybcp-v1.jar</systemPath>
      </dependency>

      <dependency>
        <groupId>${project.groupId}</groupId>
        <artifactId>stellar</artifactId>
        <version>0.37.2</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/src/main/resources/stellar.jar</systemPath>
        </dependency>

        <dependency>
          <groupId>${project.groupId}</groupId>
          <artifactId>kgen</artifactId>
          <version>1.71</version>
          <scope>system</scope>
          <systemPath>${project.basedir}/src/main/resources/kgen.jar</systemPath>
          </dependency>
    


  </dependencies>
  
  ```
  
  
 ## Sample Code
  ``` kotlin
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
  ```