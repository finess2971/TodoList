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
				+ "[항목 추가]\n"
				+ "제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 있는 제목은 사용할 수 없습니다");
			return;
		}
		System.out.print("내용 > ");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 제목을 입력하십시오 > ");		
		
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
				+ "[항목 수정]\n"
				+ "수정할 항목의 제목을 입력하십시오 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 항목입니다");
			return;
		}

		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 제목은 사용할 수 없습니다");
			return;
		}
		
		System.out.print("새 내용 > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 수정되었습니다");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("전체 목록");
		for (TodoItem item : l.getList()) {
			System.out.println("제목: " + item.getTitle() + "  내용:  " + item.getDesc() + " - " + item.getCurrent_date());
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
