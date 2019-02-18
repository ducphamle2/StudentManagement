
/**
 * 
 * @author Duc Pham Le
 * @version 3.3
 */
 
package com.duc.student.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.duc.student.model.StudentModel;
import com.duc.student.io.ReadAndWriteFile;
 
public class StudentController {
	
	public enum SWITCH {
		OFF(0), ON(1);
		int value = 0;
		SWITCH(int value) {
			this.value = value;
		}
		public int getSwitch() {
			return value;
		}
	}

	private ArrayList<StudentModel> studentList = null;
	private static final int ID = 20160000;

	public StudentController() {
		init();
	}

	/**
	 * init(): to initialize instance variables.
	 * 
	 */
	private void init() {
		studentList = new ArrayList<StudentModel>();
	}
	
	public void deleteInfoInFile(String filename) {
		ReadAndWriteFile tool = new ReadAndWriteFile();
		tool.deleteContent(filename);
	}

	/**
	 * <p>
	 * <p>
	 * <b><i>readInfo</i></b>
	 * <p>
	 * public void replaceInfo({@link String} filename, {@link String} filename2)
	 * <p>
	 * This method is used to read info from a file that stores student information,
	 * and add them into the arraylist when the program has been shut down for
	 * further work and adjustment.
	 * <p>
	 * 
	 * @param filename  - file name that the dev guy wants to store data at.
	 *                  <p>
	 * @param filename2 - file name that the dev guy wants to store the arraylist
	 *                  size. This helps retrive how many students that have been
	 *                  added to the program.
	 *                  <p>
	 * @exception IOException - if there are any interrupted inputs or outputs while
	 *                        running {@link BufferedReader} object.
	 * @return <b>true</b> - if the file exists or has data inside, and there is no
	 *         IOException.
	 *         <p>
	 *         <b>false</b> - if the file does not exist or has no data, or there
	 *         are interrupted inputs or outputs.
	 */
	
	public boolean readInfo(String filename) {
		File file = new File(filename);
		if (!file.exists() || file.length() == 0) {
			return false;
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String str = reader.readLine();
				while (str != null) {
					str = reader.readLine();
					if (str == null) 
						break;
					else {
						SWITCH programSwitch = SWITCH.ON;
						String[] temp = str.substring(1, str.length() - 2).split("\\s+\\|");
						StudentModel student = new StudentModel();
						student.setId(Integer.parseInt(temp[0]));
						student.setName(temp[1]);
						student.setAge(Integer.parseInt(temp[2]));
						student.setAddress(temp[3]);
						student.setGpa(Double.parseDouble(temp[4]));
						add(student, filename, programSwitch.getSwitch());
					}

				}
				reader.close();
			} catch (IOException e) {
				return false;
			}
			return true;
		}
	}
	
	public boolean checkEmpty(String filename) {
		return ReadAndWriteFile.checkEmpty(filename);
	}

	/**
	 * <p>
	 * <p>
	 * <b><i>add</i></b>
	 * <p>
	 * public void add({@link StudentModel} student, {@link String} filename, int
	 * flag)
	 * <p>
	 * This method is used to add student information into an arraylist one by one.
	 * <p>
	 * 
	 * @param student  - the object that is added to the arraylist.
	 *                 <p>
	 * @param filename - file name that the dev guy wants to store data at
	 *                 <p>
	 * @param flag     - works like a separator, if flag equals zero then it adds
	 *                 students because of users' inputs. If flag equals one then it
	 *                 adds students from the file that stores student information.
	 */
	public void add(StudentModel student, String filename, int flag) {
		// if the file is empty then the first ID is 2016000
		if (getListSize() < 1 && ReadAndWriteFile.checkEmpty(filename) == false) {
			student.setId(ID);
			studentList.add(0, student);
		} else {
			int index = studentList.size();
			if (flag == 0) {
				int id = studentList.get(0).getId();
				for (int i = 0; i < studentList.size(); i++) {
					int temp = studentList.get(i).getId();
					if (temp > id) {
						id = temp;
					}
				}
				student.setId(id + 1);
				studentList.add(index, student);
			} else if (flag == 1) {
				studentList.add(index, student);
			} else {
				return;
			}
		}
	}

	/**
	 * <p>
	 * <p>
	 * <b><i>findId</i></b>
	 * <p>
	 * public int findId(int choice)
	 * <p>
	 * This method is used to find the index of a student that has a specific id.
	 * <p>
	 * 
	 * @param id - id that users type in to find.
	 * @return i - the index in which that id has been found. Return -1 if that id
	 *         is not found in the arraylist.
	 */
	public int findId(int id) {
		for (int i = 0; i < studentList.size(); i++) {
			if (id == studentList.get(i).getId())
				return i;
		}
		return -1;
	}

	/**
	 * <p>
	 * <p>
	 * <b><i>findName</i></b>
	 * <p>
	 * public ArrayList<<E>{@link Integer}> findName({@link String} str)
	 * <p>
	 * This method is used to find the name of a student in an arraylist that stores
	 * student information.
	 * <p>
	 * 
	 * @param str - name of a student typed by users.
	 * @return <b>idList</b> - a list that contains indexes of students that have
	 *         the same name as param str in the arraylist.
	 */
	public ArrayList<Integer> findName(String str) {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < studentList.size(); i++) {
			if (str.equals(studentList.get(i).getName()))
				idList.add(i);
		}
		return idList;
	}

	/**
	 * <b><i>setStudent</i></b>
	 * <p>
	 * public void setStudent(int index, {@link StudentModel} student)
	 * <p>
	 * This method is used to change student information in the arraylist that
	 * stores it.
	 * 
	 * @param index   - index of the student in the arraylist that needs to change.
	 *                <p>
	 * @param student - student that users want to change information.
	 *                <p>
	 */
	public void setStudent(int index, StudentModel student) {
		studentList.set(index, student);
	}

	/**
	 * <b><i>setStudent</i></b>
	 * <p>
	 * public {@link StudentModel} getStudent(int index)
	 * <p>
	 * This method is used to get a student in a specific index stored in an
	 * arraylist
	 * <p>
	 * 
	 * @param index - index of the student in the arraylist that needs to change.
	 *              <p>
	 * @return Student object at an index.
	 */
	public StudentModel getStudent(int index) {
		return studentList.get(index);
	}

	/**
	 * <b><i>getListSize</i></b>
	 * <p>
	 * public int getListSize()
	 * <p>
	 * 
	 * @return size of the arraylist that stores student information.
	 */
	public int getListSize() {
		return studentList.size();
	}

	/**
	 * <b><i>getList</i></b>
	 * <p>
	 * public ArrayList<<E>{@link StudentModel}> getList()
	 * <p>
	 * 
	 * @return Arraylist that stores student information.
	 */
	public ArrayList<StudentModel> getList() {
		return studentList;
	}

	/**
	 * <b><i>delete</i></b>
	 * <p>
	 * public void delete(int idx)
	 * <p>
	 * This method is used to delete an element in an arraylist by index.
	 * <p>
	 * 
	 * @param idx - index of the element that users want to delete.
	 */
	public void delete(int idx) {
		if (studentList.size() == 1) {
			studentList.remove(0); 
		} else if (idx == studentList.size() - 1) {
			studentList.remove(studentList.get(idx));
		} else {
			Iterator<StudentModel> iterator = studentList.iterator();
			int temp = 0;
			while (iterator.hasNext() || temp < studentList.size()) { 
				if (iterator.next().equals(studentList.get(idx))) { 
					try {
						studentList.set(temp, studentList.get(temp + 1)); // before = after
						if (temp + 1 == studentList.size() - 1) { // if finish copying
							studentList.remove(temp + 1); // delete the last index which is abundant
						} 
					} catch (IndexOutOfBoundsException e) {
						System.out.println();
					}
				}
				temp = temp + 1;
			}
		}
	}

	/**
	 * <b><i>sort</i></b>
	 * <p>
	 * public boolean sort(int k)
	 * <p>
	 * This method is used to sort elements in an arraylist in terms of different
	 * types depending on the users (name, id, gpa, ...) For now it only supports
	 * sorting in terms of name or gpa.
	 * 
	 * @param k - a temp parameter to choose what to sort, k = 0 => sort gpa; k = 1
	 *          => sort name.
	 *          <p>
	 * @return false if there's nothing to sort; true if sorting has succeeded.
	 */
	public boolean sort(int k) {
		if (studentList.size() == 1)
			return false;
		else {
			quickSortM(studentList, 0, studentList.size() - 1, k);
			return true;
		}
	}

	/**
	 * <b><i>swap</i></b>
	 * <p>
	 * public void swap(ArrayList<<E>{@link StudentModel}> a, int i, int j)
	 * <p>
	 * This method is used to swap two elements in an arraylist.
	 * <p>
	 * 
	 * @param a - the arraylist which has two elements to swap.
	 *          <p>
	 * @param i - element with index i.
	 *          <p>
	 * @param j - element with index j.
	 *          <p>
	 */
	public void swap(ArrayList<StudentModel> a, int i, int j) {
		StudentModel temp = new StudentModel();
		temp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
	}

	/**
	 * <b><i>partitionM</i></b>
	 * <p>
	 * public int partitionM(ArrayList<<E>{@link StudentModel}> list, int low, int
	 * high, int k)
	 * <p>
	 * This method is used to find the median pivot for quicksort.
	 * 
	 * @param list - the list that needs to be sorted.
	 *             <p>
	 * @param low  - the first index of the list.
	 *             <p>
	 * @param high - the last index of the list.
	 *             <p>
	 * @param k    - a temp param to check if the users want to sort name or gpa.
	 *             <p>
	 * @return the position in which it starts swapping.
	 */
	public int partitionM(ArrayList<StudentModel> list, int low, int high, int k) {
		int i = low - 1;
		if (k == 0) { // sort gpa
			double pivot = list.get(high).getGpa();
			for (int j = low; j <= high - 1; j++) {
				if (list.get(j).getGpa() <= pivot) {
					i++;
					swap(list, i, j);
				}
			}
		}
		if (k == 1) { // sort name
			String pivot = list.get(high).getName();
			for (int j = low; j <= high - 1; j++) {
				if (list.get(j).getName().compareTo(pivot) <= 0) {
					i++;
					swap(list, i, j);
				}
			}
		}
		swap(list, i + 1, high);
		return (i + 1);
	}

	/**
	 * <b><i>partitionDuc</i></b>
	 * <p>
	 * public int partitionDuc(ArrayList<<E>{@link StudentModel}> list, int low, int
	 * high, int k)
	 * <p>
	 * This method is used to find the sort the list one time.
	 * 
	 * @param list - the list that needs to be sorted.
	 *             <p>
	 * @param low  - the first index of the list.
	 *             <p>
	 * @param high - the last index of the list.
	 *             <p>
	 * @param k    - a temp param to check if the users want to sort name or gpa.
	 *             <p>
	 * @return the position of the pivot after sorting.
	 */
	public int partitionDuc(ArrayList<StudentModel> list, int low, int high, int k) {
		int mid = (low + high) / 2;
		if (k == 0) { // sort gpa
			if (list.get(high).getGpa() < list.get(low).getGpa()) {
				swap(list, high, low);
			}
			if (list.get(mid).getGpa() < list.get(low).getGpa()) {
				swap(list, mid, low);
			}
			if (list.get(high).getGpa() < list.get(mid).getGpa()) {
				swap(list, high, mid);
			}
		}
		if (k == 1) { // sort name
			if (list.get(high).getName().compareTo(list.get(low).getName()) < 0) {
				swap(list, high, low);
			}
			if (list.get(mid).getName().compareTo(list.get(low).getName()) < 0) {
				swap(list, mid, low);
			}
			if (list.get(high).getName().compareTo(list.get(mid).getName()) < 0) {
				swap(list, high, mid);
			}
		}
		swap(list, high, mid);
		return partitionM(list, low, high, k);
	}

//quick sort algo
	/**
	 * <b><i>quickSortM</i></b>
	 * <p>
	 * public void quickSortM(ArrayList<<E>{@link StudentModel}> list, int low, int
	 * high, int k)
	 * <p>
	 * This method is used to sort list by calling recursively itself,
	 * {@link #partitionDuc(ArrayList, int, int, int)} &
	 * {@link #partitionM(ArrayList, int, int, int)}
	 * 
	 * @param list - the list that needs to be sorted.
	 *             <p>
	 * @param low  - the first index of the list.
	 *             <p>
	 * @param high - the last index of the list.
	 *             <p>
	 * @param k    - a temp param to check if the users want to sort name or gpa.
	 */
	public void quickSortM(ArrayList<StudentModel> list, int low, int high, int k) {
		if (low < high) {
			int pi = partitionDuc(list, low, high, k);
			quickSortM(list, low, pi - 1, k);
			quickSortM(list, pi + 1, high, k);
		}
	}

	/**
	 * <b><i>search</i></b>
	 * <p>
	 * public ArrayList<<E>{@link StudentModel}> search({@link Object} value)
	 * <p>
	 * This method is used to search an element in an arraylist in terms of
	 * different types (name, gpa, ...). For now it only supports searching name or
	 * id.
	 * 
	 * @param value is an {@link Object} type, for now it only supports
	 *              {@link Integer} and {@link String}.
	 * @return an arraylist that contains indexes of either id (Integer) or name
	 *         (String) of the arraylist that stores data.
	 */
	public ArrayList<Integer> search(Object value) {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		//there are other cases for Float, Double, etc... 
		if (value instanceof Integer) {
			String temp = value.toString(); //use string for all to synchronize the method
			for (int i = 0; i < studentList.size(); i++) {
				String temp2 = String.valueOf(studentList.get(i).getId()); // check id
				if (temp.equals(temp2) || checkingString(temp2, temp)) {
					idList.add(i);
				}
			}
		} else if (value instanceof String) {
			String temp = (String) value;
			for (int i = 0; i < studentList.size(); i++) {
				String temp2 = studentList.get(i).getName();
				if (temp.equals(temp2) || checkingString(temp2, temp)) {
					idList.add(i);
				}
			}
		} else {
			return null;
		}
		return idList;
	}

	/**
	 * <b><i>checkingString</i></b>
	 * <p>
	 * private boolean checkingString({@link String} str, {@link String} pat)
	 * <p>
	 * This method is used to check name or id for {@link #search(Object value)}
	 * <p>
	 * 
	 * @param str - the id (that has been used {@link #toString()} or name of an
	 *            element in the arraylist that stores data.
	 *            <p>
	 * @param pat - the input that users type in to check
	 *            <p>
	 * @return false if input is longer than the length of the str, or it is
	 *         different from the first characters of str; true if otherwise.
	 */
	private boolean checkingString(String str, String pat) {
		if (pat.length() > str.length()) {
			return false;
		} else {
			if (pat.equals(str.substring(0, pat.length()))) {
				return true;
			} else {
				return false;
			}
		}
	}
}