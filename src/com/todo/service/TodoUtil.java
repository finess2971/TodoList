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
		String category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 추가]\n"
				+ "카테고리 > ");
		
		category = sc.next().trim();
		
		System.out.print("제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 있는 제목은 사용할 수 없습니다");
			return;
		}
		
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine();
		
		System.out.print("D-Day를 입력하십시오 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하십시오 > ");		
		
		Scanner sc = new Scanner(System.in);
		int title = sc.nextInt();
		int i=0;
		
		if (l.sizeOf()<title) {
			System.out.println("존재하지 않는 번호입니다");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			i++;
			if (title == i) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하십시오 > ");
		
		int title = sc.nextInt();
		int i=0;
		
		if (l.sizeOf()<title) {
			System.out.println("존재하지 않는 번호입니다");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			i++;
			if (i==title) {
				l.deleteItem(item);
			}
		}
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.next().trim();
		
		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 제목은 사용할 수 없습니다");
			return;
		}
		
		System.out.print("새 내용 > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 D-Day > ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		l.addItem(t);
		System.out.println("항목이 수정되었습니다");
	}

	public static void listAll(TodoList l) {
		int i = l.sizeOf();

		System.out.println("[전체 목록, 총 " + i +"개]");
		i=0;
		for (TodoItem item : l.getList()) {
			i++;
			System.out.println(i + ". [" + item.getCate() +"] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue() + " - " + item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			FileWriter f = new FileWriter(filename);
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
					item = new TodoItem(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
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
	
	public static void find(TodoList l, String find) {
		int i=0;
		
		for (TodoItem item : l.getList()) {
			i++;
			if(item.getTitle().contains(find)) {
				System.out.println(i + ". [" + item.getCate() +"] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue() + " - " + item.getCurrent_date());
			} else if(item.getDesc().contains(find)) {
				System.out.println(i + ". [" + item.getCate() +"] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue() + " - " + item.getCurrent_date());
			} 
		}
	}
	
	public static void find_cate(TodoList l, String find) {
		int i=0;
		
		for (TodoItem item : l.getList()) {
			i++;
			if(item.getCate().contains(find)) {
				System.out.println(i + ". [" + item.getCate() +"] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue() + " - " + item.getCurrent_date());
			}
		}
	}
	
	public static void ls_cate(TodoList l) {
		HashSet<String> s = new HashSet<String>();
		
		for(TodoItem item : l.getList()) {
			s.add(item.getCate());
		}
		
		Iterator<String> iter = s.iterator();
		
		while(iter.hasNext()) {
			System.out.print(iter.next());
			if(iter.hasNext()) {
				System.out.print(" / ");
			}
		}
	}
}
