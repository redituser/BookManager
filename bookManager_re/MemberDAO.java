package bookManager_re;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	 void insertBook(String author, String title , int publisher) {
		  
		  	//DB 연결 (객체 불러오기)
		  	JDBConnection jdbc = new JDBConnection();
		  
	        // 중복 확인 쿼리
	        String checkSql = "SELECT COUNT(*) FROM BOOK WHERE title = ?";
	        PreparedStatement checkStmt = null;
	       

	        // 데이터 삽입 쿼리
	        String insertSql = new StringBuilder()
	            .append("INSERT INTO BOOK(bookid, author, title, publisher) ")
	            .append("VALUES (book_seq.nextval, ?, ?, ?)")
	            .toString();
	    	       

	        try {
	            // 중복 확인
	            checkStmt = jdbc.conn.prepareStatement(checkSql);
	            checkStmt.setString(1, title);
	            jdbc.rs = checkStmt.executeQuery();//? rs변수를 초기화 문제인이유
	            jdbc.rs.next();
	            int count = jdbc.rs.getInt(1);

	            if (count > 0) {
	                System.out.println("ID가 이미 존재합니다: " + title);
	                return;
	            }

	            // PreparedStatement 객체 생성 (new 필요 없음)
	            jdbc.pstmt = jdbc.conn.prepareStatement(insertSql); // 연결된 디비에 insertsql에 저장된 쿼리를 보낸다

	            // SQL문 매개변수에 값 추가	          
	            jdbc.pstmt.setString(1, author);
	            jdbc.pstmt.setString(2, title);
	            jdbc.pstmt.setInt(3, publisher);

	            // SQL문 실행
	            int result = jdbc.pstmt.executeUpdate(); // executeUpdate는 한 번만 호출

	            if (result > 0) {
	                System.out.println(result + "행이 추가되었습니다");
	            }

	        }catch(SQLException e) {
	        	e.printStackTrace();
	        }
	        
	        jdbc.close();
	        
	        
	        
	        }
	  
	  

	
	public Book selectBook(int bookid) {
	
		Book book = null; // 객체 북값에 저장하기위해 일단 불러오고 값을 할당하기전인 null 상태로만듬
		
		//DB 연결 (객체 불러오기)
	  	JDBConnection jdbc = new JDBConnection();
	  	//항상 일일이 연결메서드를 불러왔는데 이젠 JDBConnetion 덕에 일일히 선언 할 필요 x
	  	
		String sql = new StringBuilder()
				.append("select * from book where bookid = ?")
				.toString();			
		 
		try {
			jdbc.pstmt = jdbc.conn.prepareStatement(sql);
			jdbc.pstmt.setInt(1, bookid); // SQL 쿼리의 첫 번째(1) 파라미터를 bookid 로 설정합니다
			jdbc.rs = jdbc.pstmt.executeQuery(); //쿼리문 실행 
			
			if (jdbc.rs.next()) {
				
			book = new Book( //왜이렇게 선언해야하지
					 jdbc.rs.getInt("bookid") 
					,jdbc.rs.getString("author") 
					,jdbc.rs.getString("title") 
					,jdbc.rs.getInt("publisher")
					);	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
		jdbc.close();		
		return book;
	}
	
	
	
	
	 	
		public void modifyBook (int bookid , String author,String title , int publisher) {
		
			
			//DB 연결 (객체 불러오기)
		  	JDBConnection jdbc = new JDBConnection();
		  	

	        String sql = new StringBuilder().
	        		append("update book ").
	        		append("set author = ? , title = ? , publisher=?").
	        		append("where bookid = ?").
	        		toString();
	        
	        try {
				jdbc.pstmt = jdbc.conn.prepareStatement(sql);
				 // SQL 쿼리의 첫 번째(1) 파라미터를 bookid 로 설정합니다
				jdbc.pstmt.setString(1, author);
				jdbc.pstmt.setString(2, title);
				jdbc.pstmt.setInt(3, publisher);
				jdbc.pstmt.setInt(4, bookid);
				
				
				int result = jdbc.pstmt.executeUpdate(); //쿼리문 실행 
					
				if (result > 0) {
		            System.out.println("책 정보가 성공적으로 수정되었습니다.");
		        } else {
		            System.out.println("책 정보 수정에 실패했습니다. 책 ID를 확인해주세요.");
		        }
				
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
	        
	        jdbc.close();
	        
		}
		

		
		public void deleteBook(int bookid) {
			
			
			//DB 연결 (객체 불러오기)
		  	JDBConnection jdbc = new JDBConnection();
		  	
		  	
		  	 String sql = new StringBuilder().
		  			 append("delete from book").
		  			 append(" where bookid = ?"). //공백추가
		  			 toString();
		  	 
		  	 
		  	 try {
		  		 //쿼리를 실행할 sql을 보내고
		  		jdbc.pstmt = jdbc.conn.prepareStatement(sql);
		  		 //명령문
		  		jdbc.pstmt.setInt(1,bookid);
		  		 
		  		//쿼리문 업데이트 확인 (영향받은 행의수 반환)
		  	     int result = jdbc.pstmt.executeUpdate();
		  		 
		  		 
		  		  
		  		if (result > 0) {
		            System.out.println(result + "행이 삭제되었습니다");
		        } else {
		            System.out.println("삭제할 행이 없습니다");
		        }
		  		  
		  		  
		  		  	} catch (SQLException e) {
		  		  		
				e.printStackTrace();
			}
		  	 
		  
		  	jdbc.close();
			
			
			
		}
		
		
		
		
		public List<Book> selectAllBook(){
			List<Book> bookList = new ArrayList<>();
			
			
			JDBConnection jdbc = new JDBConnection();
			
			String sql = "select * from book";
			
			
			try {				
				//sql 보내고
				jdbc.pstmt = jdbc.conn.prepareStatement(sql);
				//sql 실행문
				jdbc.rs = jdbc.pstmt.executeQuery(sql);
				
				while(jdbc.rs.next()) {
					Book book = new Book(
					jdbc.rs.getInt("bookId"),
					jdbc.rs.getString("author"),
					jdbc.rs.getString("title"),
					jdbc.rs.getInt("publisher")
					);
					bookList.add(book);
				}
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				jdbc.close();
			}
			
			return bookList;
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		public void allselectBook(ArrayList<Book> bookList ) {
//			
//			//DB 연결 (객체 불러오기)
//		  	JDBConnection jdbc = new JDBConnection();
//		  	
//		  	String sql = new StringBuilder().
//		  			append("select * from book").toString();
//		  	
//		  
//		  	
//		  	
//		  	try {
//				jdbc.pstmt = jdbc.conn.prepareStatement(sql);
//				jdbc.rs = jdbc.pstmt.executeQuery();
//				while(jdbc.rs.next()) {
//					
//					Book book = new Book( //왜이렇게 선언해야하지
//							 jdbc.rs.getInt("bookid") 
//							,jdbc.rs.getString("author") 
//							,jdbc.rs.getString("title") 
//							,jdbc.rs.getInt("publisher")
//							);	
//					bookList.add(book);
//					
//					}
//					
//				
//		  		
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		  			
//		  	jdbc.close();
//		  	
//		  	
//			
//			
//		}
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	  
	  
