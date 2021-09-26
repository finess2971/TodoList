package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList 관리 명령어 사용법>");
        System.out.println("1. 리스트 항목 추가 (add)");
        System.out.println("2. 리스트 항목 삭제 (del)");
        System.out.println("3. 리스트 항목 수정 (edit)");
        System.out.println("4. 리스트 전체 목록 (ls)");
        System.out.println("5. 리스트 제목순 정렬 (ls_name_asc)");
        System.out.println("6. 리스트 제목역순 정렬 (ls_name_desc)");
        System.out.println("7. 리스트 생성순 정렬 (ls_date)");
        System.out.println("8. 리스트 생성역순 정렬 (ls_date_desc)");
        System.out.println("9. 카테고리 목록 (ls_cate)");
        System.out.println("10. 제목, 내용 검색 (find)");
        System.out.println("11. 카테고리 검색 (find_cate)");
        System.out.println("12. ToDoList 관리 명령어 사용법 (help)");
        System.out.println("13. 프로그램 종료 (exit)");
    }
    
    public static void prompt()
    {
    	System.out.println();
    	System.out.print("명령어 입력 >");
    }
}
