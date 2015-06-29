/**
 * 
 */
package ramp.sample.emoji;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author Rama Palaniappan
 * @since 29-Jun-2015
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Let's print this emoji character in multiple ways in Java
		System.out.println("😁");
		
		//This emoji character is called "Grinning face with Similing eyes"
		//It's equivalent unicode characters:
		//unicode = 1F601 
		//UTF-8 == F0 9F 98 81
		//Surrogates == D83D DE01 
		//Source: http://apps.timwhitlock.info/unicode/inspect/hex/1F601
		
		//Also read this tutorial on how these characters are internally stored in a java String
		//here: https://docs.oracle.com/javase/tutorial/i18n/text/terminology.html
		
		byte[] emojiBytes = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x81};
		String emojiAsString = new String(emojiBytes, Charset.forName("UTF-8"));
		System.out.println(emojiAsString);

		
		//Can also be written as
		char[] emojiCharArray = Character.toChars(0x1F601);
		emojiAsString = new String(emojiCharArray);
		System.out.println(emojiAsString);
		
		char[] emojiCharArrayFromString = emojiAsString.toCharArray();
		System.out.println(Arrays.asList(emojiCharArrayFromString));
	}

}
