package com.duc.student.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Duc Pham Le
 * @category read and write file
 */
public class ReadAndWriteFile {
	/**
	 * <b><i>checkEmpty</i></b>
	 * <p>
	 * public static boolean checkEmpty({@link String} filename)
	 * <p>
	 * This method is used to check if the file exists / has data or not.
	 * 
	 * @param filename - name of the file that stores data.
	 *                 <p>
	 * @return true if the file exists or has data, false if otherwise.
	 *         <p>
	 */
	public static boolean checkEmpty(String filename) {
		File file = new File(filename);
		if (!file.exists() || file.length() <= 1)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @author Duc Pham Le
	 * @category Write
	 */
	private class WriteFile {
		// method nay giup write tung line mot
		/**
		 * <b><i>writeFile</i></b>
		 * <p>
		 * private void writeFile({@link String} str, {@link String} filename)
		 * <p>
		 * This method is used to write into the file one line.
		 * 
		 * @param str      - string that is written into the file.
		 *                 <p>
		 * @param filename - name of the file that stores data.
		 *                 <p>
		 * @exception IOException - when there are interrupted inputs or outputs.
		 */
		private void writeFile(String str, String filename) {
			File file = new File(filename);
			try {
				FileWriter temp = new FileWriter(file, true);
				BufferedWriter buff = new BufferedWriter(temp);
				if (!file.exists()) {
					file.createNewFile();
				}
				buff.write(str);
				buff.newLine();
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * <b><i>writeListSize</i></b>
		 * <p>
		 * private void writeListSize({@link String} str, {@link String} filename)
		 * <p>
		 * This method is used to store arraylist size after shutting down the program.
		 * 
		 * @param str      - string that is written into the file.
		 *                 <p>
		 * @param filename - name of the file that stores data.
		 *                 <p>
		 * @exception IOException - when there are interrupted inputs or outputs.
		 */
		// method nay se luu lai arraylist size moi khi tat ctrinh vao 1 file rieng
		private void writeListSize(String str, String filename) {
			File file = new File(filename);
			try {
				FileWriter temp = new FileWriter(file);
				BufferedWriter buff = new BufferedWriter(temp);
				if (!file.exists()) {
					file.createNewFile();
				}
				buff.write(str);
				buff.newLine();
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author Duc Pham Le
	 * @category Read.
	 */
	private class ReadFile {
		// method nay cho phep doc 1 dong trong file
		@SuppressWarnings("unused")
		/**
		 * <b><i>readFile</i></b>
		 * <p>
		 * private void readFile({@link String} filename)
		 * <p>
		 * This method is used to read one line of the file.
		 * 
		 * @param filename - name of the file that stores data.
		 *                 <p>
		 * @exception IOException - when there are interrupted inputs or outputs.
		 */
		private boolean readFile(String filename) {
			File file = new File(filename);
			if (!file.exists() || file.length() == 0) {
				return false;
			} else {
				try {
					BufferedReader buff = new BufferedReader(new FileReader(filename));
					buff.readLine();
					buff.close();
				} catch (IOException e) {
					return false;
				}
				return true;
			}
		}

		/*
		 * method nay giup return buffered reader object. viec nay nham phan biet 2
		 * class controller va class read & write. class controller k can import cac
		 * class read & write ma chi? de? xu li ctrinh
		 */
		/**
		 * <b><i>getFileReader</i></b>
		 * <p>
		 * private BufferedReader getFileReader({@link String} filename)
		 * <p>
		 * This method is used to return the {@link BufferedReader} object.
		 * 
		 * @param filename - name of the file that stores data.
		 * @return {@link BufferedReader} object.
		 */
		private BufferedReader getFileReader(String filename) {
			File file = new File(filename);
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
			} catch (FileNotFoundException e1) {
			}
			BufferedReader reader = new BufferedReader(fileReader);
			return reader;
		}
	}

	/*
	 * Nhung methods ben duoi dc su? dung de goi nhung methods ben trong cac inner
	 * classes ben tren.
	 */
	public void callWriteFile(String str, String filename) {
		WriteFile temp = new WriteFile();
		temp.writeFile(str, filename);
	}

	public void callwriteListSize(String str, String filename) {
		WriteFile temp = new WriteFile();
		temp.writeListSize(str, filename);
	}

	public String callFileReader(String filename) {
		ReadFile temp = new ReadFile();
		String str = null;
		try {
			str = temp.getFileReader(filename).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	// method nay giup print khoang trang' => xoa content cua file
	/**
	 * <b><i>deleteContent</i></b>
	 * <p>
	 * public void deleteContent({@link String} filename)
	 * <p>
	 * This method is used to delete all the data stored in a file.
	 * 
	 * @param filename - name of the file that is deleted.
	 * @exception FileNotFoundException - if that file does not exist.
	 */
	public void deleteContent(String filename) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.close();
	}
}
