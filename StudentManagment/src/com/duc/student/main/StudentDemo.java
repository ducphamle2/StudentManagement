package com.duc.student.main;

import com.duc.student.io.CheckingInput;
import com.duc.student.view.StudentView;

public class StudentDemo {
	private static void init() {
		StudentView studentView = new StudentView();
		int choice = 0;
		studentView.readAllInfo();
		do {
			studentView.getOuterMenu();
			switch (choice = (int) CheckingInput.inputCheckObject(0, studentView.input)) {
			case 1:
				studentView.input.nextLine();
				studentView.setInfo();
				break;
			case 2:
				studentView.getAllInfo();
				studentView.changeInfo();
				break;
			case 3:
				studentView.getAllInfo();
				studentView.deleteStudent();
				break;
			case 4:
				studentView.checkSort(0);
				break;
			case 5:
				studentView.checkSort(1);
				break;
			case 6:
				studentView.getAllInfo();
				break;
			case 7:
				studentView.getAllInfo();
				studentView.input.nextLine();
				studentView.searchStudent();
				break;
			case 8:
				studentView.getWriteFileUsingFormatter();
				studentView.byePrint();
				break;
			default:
				break;
			}
		} while (choice != 8);
	}

	public static void main(String[] args) {
		init();
	}
}
