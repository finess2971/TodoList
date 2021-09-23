package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� �߰�]\n"
				+ "���� > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("�̹� �ִ� ������ ����� �� �����ϴ�");
			return;
		}
		System.out.print("���� > ");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ������ �Է��Ͻʽÿ� > ");		
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ������ �Է��Ͻʽÿ� > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �׸��Դϴ�");
			return;
		}

		System.out.print("�� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� ������ ����� �� �����ϴ�");
			return;
		}
		
		System.out.print("�� ���� > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����ϴ�");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("��ü ���");
		for (TodoItem item : l.getList()) {
			System.out.println("����: " + item.getTitle() + "  ����:  " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			FileWriter f = new FileWriter(filename, true);
			for (TodoItem item : l.getList()) {
				f.write(item.toSaveString());
			}
			f.flush();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringTokenizer st;
			TodoItem item;
			try {
				String line = br.readLine();
				while(line!=null) {
					st = new StringTokenizer(line,"##");
					item = new TodoItem(st.nextToken(),st.nextToken());
					item.setCurrent_date(st.nextToken());
					l.addItem(item);
					line = br.readLine();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
