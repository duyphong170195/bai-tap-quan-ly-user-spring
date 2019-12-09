/**
 * Copyright(C) 2018 Luvina Software
 * Common.java, 12/03/2018 NguyenDuyPhong
 */

package com.example.baitapquanlyuser.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Lớp này dùng để tái sử dụng những phương thức.
 * 
 * @author NguyenDuyPhong
 *
 */

public class Common {
	
	/**
	 * Lấy danh sách các trang cần hiển thị ở chuỗi paging theo trang hiện tại.
	 * @param totalUser : Số user hiện có
	 * @param limit : số user hiển thị trên 1 trang
	 * @param currentPage : Trang hiện tại
	 * @return list trang theo trang hiện tại
	 */
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage, int limitPage){
		ArrayList<Integer> listPaging = new ArrayList<Integer>();
		//tính tổng số page
		int totalPage = getTotalPage(totalUser, limit);
		//Lấy trang đầu tiên trong một offset
		int firstPage = getFirstPage(currentPage, limitPage);
		//Lấy trang cuối trong một offset
		int endPage = getEndPage(currentPage, limitPage, totalPage);

		for(int i = firstPage ; i <= endPage ; i++){
			listPaging.add(i);
		}

		return listPaging;

	}
	/**
	 * Lấy trang đầu tiên của listPaging được giới hạn bởi limitPage
	 * @param currentPage :Trang hiện tại
	 * @param limitPage: số trang hiển thị trong listPaging
	 * @return : trang đầu tiên trong listPaging
	 */
	public static int getFirstPage(int currentPage, int limitPage) {
		//Lấy ra vị trí cụm mà current page thuộc nó
		int offset = (currentPage - 1) / limitPage;
		//Lấy ra trang đầu tiên
		int firstPage = offset * limitPage + 1;
		return firstPage;
	}
	
	/**
	 * Lấy trang cuối của listPaging được giới hạn bởi limitPage
	 * @param currentPage : Trang hiện tại
	 * @param limitPage : Số trang hiển thị
	 * @param totalPage : tổng số trang của tất cả user
	 * @return : trang cuối thuộc một offset theo trang hiện tại
	 */
	public static int getEndPage(int currentPage, int limitPage, int totalPage) {
		int firstPage = getFirstPage(currentPage, limitPage);
		int endPage = (firstPage + limitPage) - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		return endPage;
	}
	
	/**
	 * Lấy vị trí offset user theo trang hiện tại
	 * @param currentPage : 
	 * @param limit
	 * @return vị trí offset theo trang
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;
	}
	
//	/**
//	 *  Lấy số lượng bản ghi hiển thị trên một trang.
//	 * @return số lượng bản ghi trên một trang
//	 */
//	public static int getLimitUser(){
//		return Common.toInteger(ConfigProperties.getData(CommonConstant.LIMIT_USER));
//	}
//
//	/**
//	 * Lấy số trang cho phép hiển thị trên một trang.
//	 * @return
//	 */
//	public static int getLimitPage(){
//		return Common.toInteger(ConfigProperties.getData(CommonConstant.LIMIT_PAGE));
//	}
	/**
	 * Lấy tổng số trang
	 * @param totalUser : tổng số user (bảng user innner join với bảng mst_group) 
	 * @param limit : số lượng bản ghi hiển thị trên một trang
	 * @return tổng số trang.
	 */
	public static int getTotalPage(int totalUser, int limit) {
		return (int) Math.ceil((double) totalUser / limit);
	}
	
	/**
	 * Lấy danh sách năm
	 * @param fromYear : Năm bắt đầu
	 * @param toYear : Năm kết thúc
	 * @return : danh sách năm bắt đầu  -> năm kết thúc
	 */
	public static List<Integer> getListYear(int fromYear, int toYear) {
		ArrayList<Integer> listYear = new ArrayList<Integer>();
		
		for (int i = fromYear; i < toYear; i++) {
			listYear.add(i);
		}
		
		return listYear;
	}
	
	/**
	 * Lấy danh sách tháng
	 * @return : danh sách tháng từ 1 -> 12
	 */
	public static List<Integer> getListMonth() {
		ArrayList<Integer> listMonth = new ArrayList<Integer>();
		
		for (int month = 1; month < 13; month++) {
			listMonth.add(month);
		}
		
		return listMonth;
	}
	
	/**
	 * 
	 * Lấy danh sách ngày
	 * @return : danh sách ngày từ 1 --> 31
	 */
	public static List<Integer> getListDay() {
		ArrayList<Integer> listDay = new ArrayList<Integer>();
		
		for (int day = 1; day < 32; day++) {
			listDay.add(day);
		}
		
		return listDay;
	}
	
	/**
	 * Lấy Date khi có dữ liệu năm , ngày , tháng
	 * @param year : Năm 
	 * @param month :Tháng
	 * @param day : Ngày
	 * @return : Date với năm tháng ngày
	 */
	public static Date getDate(int year, int month, int day) {
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		Calendar calendar = new GregorianCalendar(year, month - 1, day);
		return calendar.getTime();
	}
	
	/**
	 * Lấy năm hiện tại
	 * @return : năm hiện tại
	 */
	public static int getYearNow() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		return currentYear;
	}
	
	/**
	 * Lấy tháng hiện tại
	 * @return : tháng hiện tại
	 */
	public static int getMonthNow() {
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		return currentMonth;
	}
	
	/**
	 * Lấy ngày hiện tại
	 * @return : ngày hiện tại
	 */
	public static int getDayNow() {
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		return currentDay;
	}
	
	/**
	 * Kiểm tra một chuỗi xem có rỗng hay không.(Check the String is empty ?)
	 * 
	 * @param sNumber
	 *            chuỗi đầu vào này sẽ được kiểm tra có rỗng hay không ?
	 * @return true nếu chuỗi rỗng, false nếu chuỗi không rỗng
	 * 
	 */
	public static boolean isEmpty(String sNumber) {
		if (sNumber.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Kiểm tra một chuỗi xem có chuyển sang kiểu số nguyên được hay không ?
	 * 
	 * @param sNumber
	 *            :Chuỗi đầu vào này sẽ được kiểm tra có chuyển được sang kiểu
	 *            số nguyên hay không ?
	 * @return true nếu chuyển sang được kiểu số nguyên, false không chuyển sang
	 *         được kiểu số nguyên.
	 */
	public static boolean isNumber(String sNumber) {
		try {
			boolean match = sNumber.matches("\\d+\\s*");
			return match;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	/**
	 * Kiểm tra độ dài một xâu có vượt độ dài cho phép hay không ?
	 * 
	 * @param xau
	 *            này sẽ được kiểm tra.
	 * @param maxLength:Độ
	 *            dài cho phép.
	 * @return true nếu đồ dài xâu nhỏ hơn hoặc bằng độ dài cho phép, false nếu
	 *         độ dài xâu lớn hơn độ dài cho phép
	 */
	public static boolean maxLength(String xau, int maxLength) {
		if (xau.trim().length() <= maxLength) {
			return true;
		}
		return false;
	}
	
	/**
	 * Kiểm tra 1 chuỗi có nằm ngoài đoạn hay không ? (range1,range2)
	 * 
	 * @param xau:Một chuỗi .
	 * @param range1:Giá trị nhỏ nhất trong đoạn.
	 * @param range2:Giá trị lớn nhất trong đoạn.
	 * @return true nếu chuỗi số nằm trong đoạn.
	 * 			false nếu chuỗi số không nằm trong đoạn.
	 */
	public static boolean checkOutsize(String xau, int range1, int range2) {
		if (!isEmpty(xau)) {
			int lengthString = xau.trim().length();
			if (lengthString >= range1 && lengthString <= range2) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Kiểm tra xem định đạng của một xâu có thỏa mãn những điều kiện sau :
	 * 1.Ký tự đầu tiên không được là số, các ký tự đặc biết khác ngoại trừ ký tự '_'.
	 * 2.không nhận các ký tự khác ngoài các ký tự sau : a-z , A-Z , _ , 0-9
	 * @param xau : chuỗi được kiểm tra.
	 * @return
	 * true: nếu  thỏa mãn 2 điều kiện trên.
	 * false : nếu không thỏa mãn 2 điều kiện trên
	 */
	public static boolean checkDinhDangUserName(String xau) {
		
		if (xau.matches("[a-zA-Z_][a-zA-Z0-9_]+")) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Kiểm tra xem một chuỗi có thỏa mãn những điều kiện sau :
	 * 1.Ký tự đầu tiên có thể là số, các chữ , hoặc các ký tự '-','_','+'
	 * 2.Tiếp theo có thể có hoặc không : ký tự '.' hoặc các chữ và số (nhiều lần)
	 * 3.Tiếp theo bắt buộc phải có ký tự '@'
	 * 4.Tiếp theo là các ký tự chữ hoặc số hoặc '-' nhiều lần
	 * 5.Tiếp theo có thể có : ký tự '.' (1 lần ) và theo sau đó là A-Z hoặc a-z hoặc 0-9 nhiều lần
	 * 6.Tiếp theo bắt buộc phải có: ký tự '.' và 2 ký tự A-Z hoặc a-z 2 lần
	 * 7.Dấu $ là kết thúc chuỗi
	 * @param xau được kiểm tra
	 * @return
	 * true: Đúng định dạng
	 * false: Sai định dạng
	 */
	public static boolean checkDinhDangEmail(String xau) {
		if (xau.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Kiểm tra định dạng số điện thoại thỏa mãn điều kiện sau :
	 * 1.0-9 hoặc 1 lần hoặc 4 lần
	 * 2.bắt buộc phải có ký tự '-'
	 * 3.0-9 hoặc 1 lần hoặc 4 lần
	 * 4.bắt buộc phải có ký tự '-'
	 * 1.0-9 hoặc 1 lần hoặc 4 lần
	 * 2.bắt buộc phải có ký tự '-'
	 * @param xau : xâu để kiểm tra.
	 * @return
	 * true: số điện thoại đúng định dạng
	 * false : Số điện thoại sai định dạng
	 */
	public static boolean checkDinhDangTelephone(String xau) {
		if (xau.matches("([0-9]{1,4})-([0-9]{1,4})-([0-9]{1,4})")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Kiểm tra một xâu theo một khuôn mẫu
	 * @param xau : xâu được kiểm tra
	 * @param pattern : khuôn mẫu
	 * @return 
	 * true: nếu xâu đúng theo như khuôn mẫu
	 * false:nếu xâu không đúng khuôn mẫu
	 */
	public static boolean checkFormatByPattern(String xau, String pattern) {
		if (xau.matches(pattern)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Kiểm tra ký tự byte cho password
	 * @param password chuỗi cần kiểm tra
	 * @return true nếu là ký tự 1 byte, false nếu không phải là ký tự 1 byte
	 */
	public static boolean checkOneBytePassword(String password) {
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (c < 0 || c > 255) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Kiểm tra ngày, tháng , năm có hợp lệ hay không ?
	 * @param year :năm
	 * @param month : tháng
	 * @param day : ngày
	 * @return
	 * true : ngày, tháng ,năm hợp lệ
	 * false : ngày. tháng. năm không hợp lệ
	 */
	public static boolean checkExistedDate(int year, int month, int day) {
		int ngayDung = 0;
		
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				ngayDung = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				ngayDung = 30;
				break;
			
			case 2:
				
				if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
					ngayDung = 29;
				} else {
					ngayDung = 28;
				}
				
				break;
		}
		if (day <= ngayDung) {
			return true;
		}
		return false;
	}
	
	/**
	 * replace các kí tự đặc biệt trong giá trị search SQL
	 * @param data : chuỗi ban đầu
	 * @return : chuỗi đã được replace
	 */
	public static String replaceWildcard(String data) {
		if(data == null) {
			return "";
		}
		data = //data.replace("\"", "\"").
				data.replace("\\", "\\\\").replace("_", "\\_").replace("%", "\\%");
		return data;
	}
	
	/**
	 * thay thế các kí tự đặc biệt trong thẻ HTML
	 * @param data :dữ liệu 
	 * @return : một chuỗi không còn các kí tự đặc biệt HTML
	 */
	public static String replaceSpecialCharacterHTML(String data) {
		data = data.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
		return data;
	}
	
	/**
	 * Kiểm tra xem date1 có lớn hơn date2 hay không ?
	 * @param date1 :Giá trị date truyền vào lớn hơn
	 * @param date2 :Giá trị date truyền vào nhỏ hơn
	 * @return 
	 * true : Nếu date1 > date2
	 * false :Nếu date1 < date2
	 */
	
	public static boolean dateBiggerdate(Date date1, Date date2) {
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date1);
		cal2.setTime(date2);
		
		if (date1.after(date2)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Kiểm tra xâu có phải chữ katakana hay không ?
	 * @param xau : chữ katakana
	 * @return
	 * true: Nếu xâu là chữ katakana
	 * false :Nếu xâu không phải chữ katakana 
	 */
	public static boolean checkKana(String xau) {
		if (xau.matches("[ァ-ン]+")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Kiểm tra có phải số halfsize hay không ?
	 * @param xau : Chuỗi 
	 * @return
	 * true :chuỗi là số halfsize
	 * fales : chuỗi không phải là số halfsize
	 */
	public static boolean checkHalfSize(String xau) {
		if (xau.matches("[0-9]+")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Convert năm, tháng, ngày sang String
	 * @param year : Năm muốn convert
	 * @param month : Tháng muốn convert
	 * @param day : Ngày muốn convert
	 * @return chuỗi string có định dạng yyyy/MM/dd
	 */
	public static String convertToString(int year, int month, int day) {
		StringBuilder sDate = new StringBuilder();
		sDate.append(year);
		sDate.append("/");
		sDate.append(month);
		sDate.append("/");
		sDate.append(day);
		
		return sDate.toString();
	}
	
	/**
	 * Chuyển date sang ArrayInteger
	 * @param date : Kiểu Date
	 * @return:
	 * mảng array Date với :
	 * array[0] = year;
	 * array[1] = month;
	 * array[2] = day;
	 */
	public static ArrayList<Integer> toArrayInteger(Date date) {
		ArrayList<Integer> dateArray = new ArrayList<Integer>();
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			dateArray.add(year);
			dateArray.add(month);
			dateArray.add(day);
		} else {
			//Nếu kiểu date là rỗng thì sét ngày tháng năm hiện tại để khi hiển thị trên màn hình ADM003
			//Vẫn giữ được ngày tháng năm hiện tại
			dateArray.add(Common.getYearNow());
			dateArray.add(Common.getMonthNow());
			dateArray.add(Common.getDayNow());
		}
		return dateArray;
		
	}
	
	/**
	 * chuyển đổi kiểu util.date sang sql.date
	 * @param date
	 * @return
	 */
	public static java.sql.Date convertDateUtilToDateSql(Date date) {
		if (date != null) {
			java.sql.Date dateSql = new java.sql.Date(date.getTime());
			return dateSql;
		}
		return null;
	}
	
	/**
	 * Chuyển đối kiểu một chuỗi số sang kiểu số nguyên
	 * 
	 * @param sNumber:một
	 *            chuôi số nguyên
	 * @return Nếu chuỗi là một chuỗi số thì trả về số nguyên, ngược lại trả về
	 *         0
	 */
	public static int toInteger(String sNumber) {
		int value = 0;
		try {
			value = Integer.parseInt(sNumber.trim());
		} catch (NumberFormatException e) {
			value = 0;
		} catch (NullPointerException e) {
			value = 0;
		}
		
		return value;
	}
	
	/**
	 * Chuyển đối một số nguyên sang một chuỗi 
	 * 
	 * @param number:một chuỗi số nguyên
	 * @return Nếu chuỗi là một chuỗi số thì trả về số nguyên, ngược lại trả về
	 *         0
	 */
	public static String toString(int number) {
		String sNumber;
		if (number == 0) {
			sNumber = "";
		} else {
			sNumber = String.valueOf(number);
		}
		
		return sNumber;
	}
}
