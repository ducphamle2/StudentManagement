package com.duc.student.view;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import com.duc.student.controller.StudentController;
import com.duc.student.io.CheckingInput;
import com.duc.student.model.StudentModel;

/**
 * 
 * @author Duc Pham Le
 * @version 3.3
 *          <p>
 *          <p>
 */
public class StudentView {

	StudentController schoolManager = null;
	InfoAdjustment infoController = null;
	private UserInput userInput = null;
	public Scanner input = null;
	private String fileName = null;

	public StudentView() {
		init();
	}
	
	
	/**
	 * init(): to initialize instance variables.
	 * 
	 */
	private void init() {
		schoolManager = new StudentController();
		infoController = new InfoAdjustment();
		userInput = new UserInput();
		input = new Scanner(System.in);
		fileName = "D:\\test.txt"; // store student info.
	}

	/**
	 * <p>
	 * <b><i>getOuterMenu</i></b>
	 * <p>
	 * public void getOuterMenu()
	 * <p>
	 * This method is used to call {@link UserMenu#setOuterMenu()}
	 */
	public void getOuterMenu() {
		UserMenu outerMenu = new UserMenu();
		outerMenu.setOuterMenu();
	}

	/**
	 * <p>
	 * <b><i>getInnerMenu</i></b>
	 * <p>
	 * public void getInnerMenu()
	 * <p>
	 * This method is used to call {@link UserMenu#setInnerMenu()}
	 */
	private void getInnerMenu() {
		UserMenu innerMenu = new UserMenu();
		innerMenu.setInnerMenu();
	}

	/**
	 * <p>
	 * <b><i>getInfo</i></b>
	 * <p>
	 * private void getInfo(int index)
	 * <p>
	 * This method is used to call {@link InfoAdjustment#getInfo(int index)}
	 * 
	 * @param index - index of an element in the arraylist that stores data.
	 */
	private void getInfo(int index) {
		infoController.getInfo(index);
	}

	/**
	 * <p>
	 * <b><i>getAllInfo</i></b>
	 * <p>
	 * private void getAllInfo()
	 * <p>
	 * This method is used to call {@link InfoAdjustment#getAllInfo()}
	 * 
	 */
	public void getAllInfo() {
		infoController.getAllInfo();
	}

	/**
	 * <p>
	 * <b><i>readAllInfo</i></b>
	 * <p>
	 * private void readAllInfo()
	 * <p>
	 * This method is used to call
	 * {@link StudentControllerV3#readInfo(String filename, String filename2)}
	 * 
	 */
	public void readAllInfo() {
		if (!schoolManager.readInfo(fileName)) {
			System.out.println("Failed to read data.");
			System.out.print("Some reasons might have occured: ");
			System.out.println("lack of data / no file.");
		}
	}

	/**
	 * <p>
	 * <b><i>setInfo</i></b>
	 * <p>
	 * public void setInfo()
	 * <p>
	 * This method is used to call {@link InfoAdjustment#setInfo()}
	 * 
	 */
	public void setInfo() {
		infoController.setInfo();
	}

	/**
	 * <p>
	 * <b><i>changeInfo</i></b>
	 * <p>
	 * private void changeInfo()
	 * <p>
	 * This method is used to call {@link InfoAdjustment#changeInfo()}
	 * 
	 */
	public void changeInfo() {
		infoController.changeInfo();
	}

	/**
	 * 
	 * @author Duc Pham Le
	 * @category This class is created for UI methods.
	 */
	private class UserMenu {
		private void setOuterMenu() {
			System.out.println("\n\n---STUDENT MANAGEMENT---");
			System.out.println("1) Add student.");
			System.out.println("2) Edit student by id.");
			System.out.println("3) Delete student by id.");
			System.out.println("4) Sort student by gpa.");
			System.out.println("5) Sort student by name.");
			System.out.println("6) Show student.");
			System.out.println("7) Search student by id or name. ");
			System.out.println("8) Exit.");
		}

		private void setInnerMenu() {
			System.out.println("\n");
			System.out.println("1) Change name (type below). ");
			System.out.println("2) Change age (type below). ");
			System.out.println("3) Change address (type below). ");
			System.out.println("4) Change gpa (type below). ");
			System.out.println("5) Finish.");
		}
	}

	/**
	 * 
	 * @author Duc Pham Le
	 * @category This class is created for adjusting info of objects
	 *           ({@link StudentModel})
	 *
	 */
	private class InfoAdjustment {
		/**
		 * <b><i>setInfo</i></b>
		 * <p>
		 * private void setInfo()
		 * <p>
		 * This method is used to receive inputs from users to get student info (name,
		 * address, age, gpa) and print them out. It calls
		 * {@link StudentControllerV3#add(StudentModel student, String filename, int flag)}
		 * to add students into the arraylist.
		 * <p>
		 * It calls
		 * {@link StudentControllerV3#writeInfo(StudentModel student, String filename) }
		 * to write data into the file that stores student information.
		 */
		private void setInfo() {
			StudentModel student = new StudentModel();
			StudentController.SWITCH programSwitch = StudentController.SWITCH.OFF;
			System.out.println("Enter name: ");
			student.setName(userInput.checkInfo(""));
			System.out.println("Enter address: ");
			student.setAddress(userInput.checkInfo(""));
			System.out.println("Enter age: ");
			student.setAge(Integer.parseInt(userInput.checkInfo(0)));
			System.out.println("Enter GPA: ");
			student.setGpa(Double.parseDouble(userInput.checkInfo(0.0)));
			schoolManager.add(student, fileName, programSwitch.getSwitch());
			System.out.println("You have finished adding your information.");
			System.out.println("Here is your info: ");
			getInfo(schoolManager.getListSize() - 1);
		}

		/**
		 * <b><i>getInfo</i></b>
		 * <p>
		 * private void getInfo(int index)
		 * <p>
		 * This method is used to print out information of a student by index.
		 * 
		 * @param index - index of the student in the arraylist.
		 */
		private void getInfo(int index) {
			System.out.println(schoolManager.getStudent(index).toString());
			System.out.println();
		}

		/**
		 * <b><i>getAllInfo</i></b>
		 * <p>
		 * private void getAllInfo()
		 * <p>
		 * This method is used to print all student information in the arraylist.
		 */
		private void getAllInfo() {
			if (schoolManager.getListSize() < 1) {
				System.out.println("No students to show.");
			} else {
				System.out.println("\n---STUDENT LIST---\n");
				for (int i = 0; i < schoolManager.getListSize(); i++) {
					System.out.println(schoolManager.getStudent(i).toString());
					System.out.println();
				}
			}
		}

		/**
		 * <b><i>changeInfo</i></b>
		 * <p>
		 * private void changeInfo()
		 * <p>
		 * This method is used to receive input from users that want to change
		 * information of a student. It calls {@link UserInput#checkInfo(Object value)}
		 * to get & check input validation.
		 * <p>
		 * After getting input, it calls
		 * {@link StudentControllerV3#setStudent(int index, StudentModel student)} to
		 * change that student information, and
		 * {@link StudentControllerV3#replaceInfo(String filename)} to change data in
		 * the file that stores student info.
		 */
		private void changeInfo() {
			if (schoolManager.getListSize() < 1) {
				System.out.println("No students to change.");
			} else {
				int choice = 0;
				int index = userInput.getInput(null);
				StudentModel student = schoolManager.getStudent(index);
				getInfo(index);
				do {
					getInnerMenu();
					choice = Integer.parseInt(userInput.checkInfo(0));
					switch (choice) {
					case 1:
						System.out.println("Change your name here: ");
						student.setName(userInput.checkInfo(""));
						System.out.println("Your name has been changed successfully");
						break;
					case 2:
						System.out.println("Change your age here: ");
						student.setAge(Integer.parseInt(userInput.checkInfo(0)));
						System.out.println("Your age has been changed successfully");
						break;
					case 3:
						System.out.println("Change your address here: ");
						student.setAddress(userInput.checkInfo(""));
						System.out.println("Your address has been changed successfully");
						break;
					case 4:
						System.out.println("Change your gpa here: ");
						student.setGpa(Double.parseDouble(userInput.checkInfo(0.0)));
						System.out.println("Your gpa has been changed successfully");
						break;
					case 5:
						byePrint();
						break;
					default:
						System.out.println("Wrong input. Try again!");
						break;
					}
				} while (choice != 5);
				schoolManager.setStudent(index, student);
			}
		}
	}

	/**
	 * 
	 * @author Duc Pham Le
	 * @category This class is created to receive and check input from users.
	 */
	private class UserInput {

		/**
		 * <b><i>checkInfo</i></b>
		 * <p>
		 * private String checkInfo({@link Object} value)
		 * <p>
		 * This method is used to check if users have type the correct input type or
		 * not. It also calls method
		 * {@link CheckingInputV2#checkInput(String str, Object value)}
		 * 
		 * @param value - {@link Object} type in order to clarify the input of users,
		 *              which for now only helps {@link Integer} and {@link String}
		 *              wrapper classes.
		 * @return The string that users have typed in
		 */
		private String checkInfo(Object value) {
			boolean flag = true;
			String str = null;
			do {
				str = input.nextLine();
				if (CheckingInput.checkInput(str, value) == null) {
					if (value instanceof String) {
						System.out.println("Wrong input. This part must have a char other than num");
					} else if (value instanceof Integer || value instanceof Double) {
						System.out.println("Wrong input. Numbers only.");
					}
					flag = false;
					continue;
				} else {
					flag = true;
				}
			} while (flag == false);
			return str;
		}

		/**
		 * <b><i>getInput</i></b>
		 * <p>
		 * private int getInput({@link Object} value)
		 * <p>
		 * This method is used to get ID or name input from users and print it out. It
		 * checks if the input is valid or not (only ID or name) by calling
		 * {@link CheckingInputV2#checkingNumeric(String str)} and
		 * {@link #checkInfo(Object value)}
		 * 
		 * @param value - {@link Object} type for using either {@link Integer} or
		 *              {@link String}
		 * @return index of the element that is identical from user input (ID or name)
		 */
		private int getInput(Object value) {
			String choice = null;
			int index = 0;
			input.nextLine();
			do {
				System.out.println("Enter a student ID or name here: ");
				choice = checkInfo(value);
				if (CheckingInput.checkingNumeric(choice) == true) {
					index = schoolManager.findId(Integer.parseInt(choice));
					if (index == -1) {
						System.out.println("No id found. Try again!");
						continue;
					} else {
						return index;
					}
				} else {
					schoolManager.findName(choice);
					if (schoolManager.findName(choice).size() < 1) {
						System.out.println("No name found. Try again!");
						continue;
					} else if (schoolManager.findName(choice).size() == 1) {
						index = schoolManager.findName(choice).get(0);
					} else {
						System.out.format("Names that start with %s: \n\n", choice);
						for (int i = 0; i < schoolManager.search(choice).size(); i++) {
							getInfo(schoolManager.search(choice).get(i));
						}
						do {
							System.out.println("Enter the right id among those names.");
							String idinput = checkInfo(0);
							for (int i = 0; i < schoolManager.findName(choice).size(); i++) {
								int temp = schoolManager.findName(choice).get(i);
								int temp2 = Integer.parseInt(idinput); // em chia tnay trong cho gon.
								if (temp2 == schoolManager.getList().get(temp).getId()) {
									index = temp;
									break;
								} else {
									index = -1;
								}
							}
							if (index == -1)
								System.out.println("No id found.");
						} while (index == -1);
					}
				}
			} while (index == -1 || schoolManager.findName(choice).size() < 1);
			return index;
		}
		
		/**
		 * <b><i>writeFileUsingFormatter</i></b>
		 * <p>
		 * private void writeFileUsingFormatter()
		 * <p>
		 * This method is used to write student data into a file before
		 * closing the program.
		 */
		private void writeFileUsingFormatter() {
			Formatter formatter = null;
			char c = '|';
			try {
				formatter = new Formatter(fileName);
				schoolManager.deleteInfoInFile(fileName);
				formatter.format("|%-10s%c%-15s%c%-5s%c%-15s%c%-5s%c%s", "ID", c, 
				"NAME", c, "AGE", c, "ADDRESS", c, "GPA", c, System.lineSeparator());
				for (int i = 0; i < schoolManager.getList().size(); i++) {
					StudentModel temp = schoolManager.getList().get(i);
					formatter.format("|%-10d%c%-15s%c%-5d%c%-15s%c%-5.2f%c%s", 
					temp.getId(), c, temp.getName(), c, temp.getAge(), c, 
					temp.getAddress(), c, temp.getGpa(), c, System.lineSeparator());
				}
				formatter.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void byePrint() {
		System.out.println("Bye!");
	}
	
	/**
	 * <b><i>getWriteFileUsingFormatter</i></b>
	 * <p>
	 * public void getWriteFileUsingFormatter()
	 * <p>
	 * This method is used to call out the method
	 *  {@link UserInput#writeFileUsingFormatter()}.
	 */
	public void getWriteFileUsingFormatter() {
		UserInput temp = new UserInput();
		temp.writeFileUsingFormatter();
	}

	/**
	 * <b><i>deleteStudent</i></b>
	 * <p>
	 * public void deleteStudent()
	 * <p>
	 * This method is used to delete a student from the list. It calls the method
	 * {@link StudentControllerV3#delete(int idx)} to delete
	 */
	public void deleteStudent() {
		if (schoolManager.getListSize() < 1) {
			System.out.println("No students to delete.");
		} 
		else {
			String temp = null;
			int id = userInput.getInput(null);
			System.out.print("\nAre you sure to delete this student info ? ");
			System.out.print("Type 'y' to delete, 'n' to " + "not delete and exit this function: ");
			temp = input.nextLine();
			if (temp.equalsIgnoreCase("y")) {
				schoolManager.delete(id);
				System.out.println("\nDelete student successfully");
			} 
			else {
				System.out.println("\nExit...");
				return;
			}
		}
	}

	/**
	 * <b><i>searchStudent</i></b>
	 * <p>
	 * public void searchStudent()
	 * <p>
	 * This method is used to search a student in the arraylist that stores student
	 * information. It calls {@link CheckingInputV2#checkingNumeric(String str)} to
	 * check user input and {@link StudentControllerV3#search(Object value)} to
	 * search.
	 */
	public void searchStudent() {
		if (schoolManager.getListSize() < 1) {
			System.out.println("No students to search.");
		} else {
			System.out.println("Type name or id here to search: ");
			String str = userInput.checkInfo(null);
			if (CheckingInput.checkingNumeric(str)) {
				Integer value = Integer.parseInt(str);
				if (schoolManager.search(value).size() < 1)
					System.out.println("No id found.");
				else {
					System.out.format("IDs that start with %d: \n\n", value);
					for (int i = 0; i < schoolManager.search(value).size(); i++) {
						getInfo(schoolManager.search(value).get(i));
					}
				}
			} else {
				if (schoolManager.search(str).size() < 1)
					System.out.println("No name found.");
				else {
					System.out.format("Names that start with %s: \n\n", str);
					for (int i = 0; i < schoolManager.search(str).size(); i++) {
						getInfo(schoolManager.search(str).get(i));
					}
				}
			}
		}
	}

	/**
	 * <b><i>checkSort</i></b>
	 * <p>
	 * public void checkSort(int k)
	 * <p>
	 * This method is used to check if sorting algorithm has been successful or not.
	 * It calls methods {@link StudentControllerV3#sort(int k)} to sort elements and
	 * {@link StudentControllerV3#repaceInfo(String filename)} to rewrite the file
	 * that stores data due to sorting.
	 * 
	 * @param k - same as k in {@link StudentControllerV3#sort(int k)}}
	 */
	public void checkSort(int k) {
		if (schoolManager.getListSize() < 1) {
			System.out.println("No students to sort.");
		} else {
			if (schoolManager.sort(k) == false) {
				System.out.println("Cannot sort because there's only one student");
			} else {
				System.out.println("Sorted successfully");
			}
		}
	}
}