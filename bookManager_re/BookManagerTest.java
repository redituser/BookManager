package bookManager_re;

import java.util.ArrayList;
import java.util.List;

public class BookManagerTest {
public static void main(String[] args) {
	
	
	MemberDAO md = new MemberDAO();
	//md.insertBook("이수빈","스무살 이수빈",15000); //정상작동
	//md.selectBook(2); //정상작동	
	//md.modifyBook(3,"임수빈","아미타불",40000);//일단 수정은 가능한데 무결성 오류 뜸 
	//md.deleteBook(2); //정상작동
	List<Book> bookList = md.selectAllBook();
	
	for(Book book : bookList ) {
		System.out.println(book.toString());
	}
	
	
}	

}
