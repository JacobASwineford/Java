package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeys {
	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public static void main(String[] args) {
		GenerateKeys generatedKeys;
                int N = 1024 ; // could be 2048 - modulus length in bits
		try {
			generatedKeys = new GenerateKeys(N);
			generatedKeys.createKeys();
			generatedKeys.writeToFile("KeyPair/publicKey", generatedKeys.getPublicKey().getEncoded());
                        System.out.println("Private Key ="+generatedKeys.getPublicKey() );
			generatedKeys.writeToFile("KeyPair/privateKey", generatedKeys.getPrivateKey().getEncoded());
                        System.out.println("Private Key ="+generatedKeys.getPrivateKey() );
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}