package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		TodoUtil.loadList(l, "todolist.txt");
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("리스트를 제목순으로 정렬하였습니다");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("리스트를 제목역순으로 정렬하였습니다");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("리스트를 생성순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("리스트를 생성역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;
				
			case "find" :
				String find = sc.next();
				TodoUtil.find(l, find.trim());
				break;
				
			case "find_cate":
				String find_cate = sc.next();
				TodoUtil.find_cate(l, find_cate.trim());
				break;

			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				TodoUtil.saveList(l, "todolist.txt");
				break;
				
			default:
				System.out.println("정확한 명령어를 입력하십시오 - 관리 명령어 사용법 ( help )");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
