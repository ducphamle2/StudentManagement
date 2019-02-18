package com.duc.student.io;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckingInput {
	/**
	 * <b><i>inputCheckObject</i></b>
	 * <p>
	 * public static {@link Object} inputCheckObject({@link Object} value,
	 * {@link Scanner} input)
	 * <p>
	 * This method is used to check user input (for {@link StudentDemoV3} only).
	 * 
	 * @param value - type of object that dev wants users to type.
	 * @param input - class instance that allows users to type in.
	 * @return data type that value represents (Integer/String/...)
	 * @exception InputMismatchException    - when users type wrong input data type.
	 *                                      <p>
	 * @exception IndexOutOfBoundsException - when users type wrong index of
	 *                                      elements
	 */
	public static Object inputCheckObject(Object value, Scanner input) {
		while (true) {
			try {

				if (value instanceof Integer) {
					return input.nextInt();
				} else if (value instanceof Double) {
					return input.nextDouble();
				} else if (value instanceof String) {
					return input.nextLine();
				} else if (value instanceof Character) {
					return input.next();
				} else if (value instanceof Boolean) {
					return input.nextBoolean();
				} else if (value instanceof BigDecimal) {
					return input.nextBigDecimal();
				} else if (value instanceof BigInteger) {
					return input.nextBigInteger();
				} else if (value instanceof Byte) {
					return input.nextByte();
				} else if (value instanceof Long) {
					return input.nextLong();
				} else if (value instanceof Float) {
					return input.nextFloat();
				} else if (value instanceof Short) {
					return input.nextShort();
				} else {
					System.err.println("Khong tim thay kieu du lieu");
					return null;
				}
			} catch (InputMismatchException e) {
				String bad_input = input.nextLine();
				if (bad_input.trim().isEmpty()) {
					System.err.println("At least 1 char pls");
				} else {
					System.err.println(bad_input + " is a wrong input.");
				}
				System.err.println("Try again!");
				continue;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("No ID found!! Try again.");
				continue;
			}
		}
	}
	
	/**
	 * <b><i>checkInput</i></b>
	 * <p>
	 * public static {@link Object} checkInput({@link String} str, {@link Object}
	 * value)
	 * <p>
	 * This works better in comparison with
	 * {@link #inputCheckObject(Object, Scanner)} because it first is a string, and
	 * then if the dev guy wants to force users to type in a different data type =>
	 * use value {@link Object} to parse.
	 * 
	 * @param str   - user input.
	 * @param value - parse depending on the dev guy
	 * @return null if input is not valid; str if input is valid.
	 * @exception InputMismatchException - when users type wrong input data type.
	 *                                   <p>
	 * @exception NullPointerException   - when str equals to null.
	 *                                   <p>
	 * @exception NumberFormatException  - when the str has inappropriate format.
	 */
	public static Object checkInput(String str, Object value) {
		try {
			if (value instanceof Integer) {
				Integer.parseInt(str);
			} else if (value instanceof Double) {
				Double.parseDouble(str);
			} else if (value instanceof String) {
				if (checkingNumeric(str))
					return null;
			}
		} catch (InputMismatchException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		} catch (NumberFormatException e) {
			return null;
		}
		if (str.trim().isEmpty()) {
			return null;
		}
		return str;
	}

	/**
	 * <b><i>checkingNumeric</i></b>
	 * <p>
	 * public static boolean checkingNumeric({@link String} str)
	 * <p>
	 * This method is used to check if the string input is fully numeric or not.
	 * 
	 * @param str - user input.
	 * @return false if it is not numeric; true if otherwise.
	 * @exception NumberFormatException - when the str has inappropriate format.
	 */
	public static boolean checkingNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
