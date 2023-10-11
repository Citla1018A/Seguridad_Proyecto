package mx.ulsa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MiLibreriaUtil {

	public static void main(String args[]) {
		System.out.println("Encriptado: " +encriptar("12345"));//sirve el metodo encriptar
	}

	public static String encriptar(String cad) {
		String r = null;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(cad.getBytes());
			byte[] bytes = m.digest();
			/*
			 * los bytes array tiene bytes en formato decimal y se convierten a hexadecimal.
			 */
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			r = s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return r;
	}

}
