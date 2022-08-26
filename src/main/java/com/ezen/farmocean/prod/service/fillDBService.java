package com.ezen.farmocean.prod.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class fillDBService {
	
	
	static public List<String> imgList = new ArrayList<>();
	static public Map<String, String> pInfo = new HashMap<>();
	static public HashSet<String> cateListSub = new HashSet<>();
	private static Set<String> nameSet = new HashSet<>();
	
	static HikariConfig config = new HikariConfig();
	static {
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@3.39.84.37:1521:XE"); // ���� DB�ϱ� ip�ּ� �ű� �ɷ�
		config.setUsername("project");
		config.setPassword("project!@");
	}
	static HikariDataSource ds = new HikariDataSource(config);
	static List<String> cateList = getHttpHTMLList("https://www.esingsing.co.kr");
	
	
	static String[] cates = {"���, �����, ����", "ī�� 9�� ī�� 8�̶� �ߺ��Ǵ� ��" , "��, ����, ����, ��", "�丶��, ������", "����, ����, ��ȭ��", "���, ����, ��", "����, Ű��, �ٷ�", "�Ž�, �챸", "���纣��, �ƷδϾ�", "������, ������, ����", "����, ������", "�ڵ�, ����", "��Ÿ ����", "���� ������ǰ", 
								"����, ������", "����, ����, ����, ��", "����, �ǰ���, �¾���", "����, ���ӹ���, ��, ����", "ȣ��, ����, ����", "��Ʈ, �ݶ��, ������ī", "���, ����, ����", "����, ��ũ��, ������", "��Ÿ ä��", "ä�� ������ǰ",
								"�ñ�ġ, ����, ����", "���縮, �볪��", "��巹, �÷���", "��Ÿ ����", "���� ������ǰ", "ǥ��, ����, ��Ÿ��", "��Ȳ, ����, ������", "����, ����", "��Ÿ ����", "���� ������ǰ", 
								"������", "����, ���", "��, ����, ���, ����", "��, ����, �͸�", "��, ������", "����, ����, ���", "��Ÿ �", "� ������ǰ", "��, ����, ��", "����, ȣ��", "��Ÿ �߰�", "�߰� ������ǰ",
								"��, �ϴø�, Ħ", "��������, ���", "������, ����", "�λ�, ȫ��, ���", "��Ÿ �Ѹ�", "�Ѹ� ���� ��ǰ", 
								"��, �ξ�����, ������", "ȿ��, ����, �ǰ���", "�и�, ����, �ǰ���", "��Ÿ ����",
								"����, ��, ������", "��, ����, �ٴ尡��", "����, ��¡��, ����, �öѱ�", "�ٽ���, �Ҷ�, �۰�", "�̿�, ��, �ٽø�", "��ġ, õ�Ͽ�", "��Ÿ �ػ깰", "�ػ깰 ������ǰ", "��ġ, �ӿ���, ��ġ", "ȫ��, �����, �ξ�", "���, ����, �췰, ��", "����, ����, ����", "���ޱ�, ��ġ, ����", "�Ʊ�, ���繬", "��Ÿ ����", "���� ������ǰ"};
	
	static String[] address = {"������ ����", "��� �Ǽ�", "���� �س�", "������ ȫõ","�泲 ����", "û���� ���", "����", "���� ����", "���� ����", "��� ����", "�泲 ���� ��õ��"};
	
	static int phoneNumBase = 10000000;
	static int accountNumBase1 = 100;
	static int accountNumBase2 = 10000;
	static long idPwNum = 0;
	
	public static Set<String> getNameSet() {
		return nameSet;
	}

	public static void setNameSet(Set<String> idSet) {
		fillDBService.nameSet = nameSet;
	}

	
	public static void getHttpHTML(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		boolean startB = false;
		boolean priceB = false;
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((line = rd.readLine()) != null) {
				if(line.contains("id=\"at-main\"")) {
					startB = true;
				}
				
				if(line.contains("<div class=\"print-hide view-icon view-padding\">")) {
					startB = false;
				}
				
				if(startB) {
					
					String chkValue = "<img alt=";
					
					if(line.contains(chkValue)) {
						imgList.add(line.substring(line.indexOf(" src=\"") + 6, line.indexOf("\" data-im")));
						imgList.add(line.substring(line.indexOf("mage=\"") + 6, line.indexOf("\" data-desc")));						
					}
					
					chkValue = "<i class=\"fa fa-user-circle\"></i>";
					
					if(line.contains(chkValue)) {
						String insertValue = line.replace(chkValue, "");
						insertValue = insertValue.replace("</div>", "");
						pInfo.put("sellMember", insertValue.trim());
					}
					
					chkValue = "<h1 itemprop=\"headline\" content=\"";
					if(line.contains(chkValue)) {
						String insertValue = line.substring(chkValue.length() + 5, line.indexOf("\">"));
						pInfo.put("title", insertValue);
					}
					
					chkValue = "<b>���� :</b>";
					if(line.contains(chkValue)) {
						String insertValue = "";
						if(line.contains("<a href=\"")) {						
							insertValue = line.substring(chkValue.length() + 5, line.indexOf("<a href=\""));
						}else if(line.contains("<del>")) {
							insertValue = line.substring(line.indexOf("<del>") + 5, line.indexOf("</del>"));
						}
						
						
						pInfo.put("phone", insertValue.trim());
					}
					
					chkValue = "<b>�Ա� :</b>";
					if(line.contains(chkValue)) {
						String insertValue = "";
						if(line.contains("</div>")) {						
							insertValue = line.substring(chkValue.length() + 5, line.indexOf("</div>"));
						}else if(line.contains("<del>")) {
							insertValue = line.substring(line.indexOf("<del>") + 5, line.indexOf("</del>"));
						}
						pInfo.put("account", insertValue.trim());
					}
					
					chkValue = "<div style=\"margin-bottom:10px\">";
					if(line.contains(chkValue)) {						
						pInfo.put("detail", line);
					}
					
					if(priceB) {
						pInfo.put("price", line.replace("</div>","").trim());
						priceB = false;
					}
					
					if(line.contains("<div class=\"col-sm-12 mitem-title\">")) {
						priceB = true;
					}
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getHttpHTMLList(String urlToRead) {
		List<String> cateList = new ArrayList<>();
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String getUrl = "";
		String conVal = "https://www.esingsing.co.kr/bbs/board.php?bo_table=";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((line = rd.readLine()) != null) {
				
				if(line.contains(conVal)) {
					getUrl = line.trim();
					getUrl = getUrl.substring(getUrl.indexOf("https://"), getUrl.length());
					if(getUrl.substring(conVal.length() + 2, conVal.length() + 3).equals("\"")) {
						getUrl = getUrl.substring(0, conVal.length() + 2);
						cateList.add(getUrl);
					}else if(getUrl.substring(conVal.length() + 3, conVal.length() + 4).equals("\"")) {
						getUrl = getUrl.substring(0, conVal.length() + 3);
						cateList.add(getUrl);
					}
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cateList;
	}
	
	
	public static List<String> getHttpHTMLListSub(String urlToRead) {
		List<String> cateList = new ArrayList<>();
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String getUrl = "";
		String conVal = urlToRead + "&amp;wr_id";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			while ((line = rd.readLine()) != null) {
				
				if(line.contains(conVal)) {
					getUrl = line.replace("&amp;", "&");					
					getUrl = getUrl.replace("'", "");
					getUrl = getUrl.substring(getUrl.indexOf("https://"), getUrl.length());
					getUrl = getUrl.substring(0, getUrl.indexOf("\""));
					
					cateListSub.add(getUrl);
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cateList;
	}
	
	
	
	public static String randomSell() {
		int ran = (int)(Math.random() * 2);
		
		if(ran == 0) {
			return "�Ǹ���";
		} else {
			return "�Ǹ�����";
		}
	}

	public static Timestamp getRandomDeadLine(String sell) {

		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Integer ran = (int)(Math.random() * 185);
		
		if(sell.equals("�Ǹ���")) {			
			cal.add(Calendar.DATE, ran);
			String randomFutureDL = null; 
			randomFutureDL = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(randomFutureDL);
			return ts;
			
		} else if(sell.equals("�Ǹ�����")) {
			cal.add(Calendar.DATE, ran * -1);
			String randomPastDL = null; 
			randomPastDL = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(randomPastDL);			
			return ts;	
		}
		
		return null;
	}

	
	public static void getSellNameSet(int i) {		
		int cnt = 0;
		boolean loop = true;
		for(String s : cateList) {
			
			if(cnt > i) {	//	8��° ���� ����깰 ��ǰ ������ �����Ŵ� ���ݺҷ����� �κ��̶� �ǳʶ�
				if(loop) {	// �׽�Ʈ���� ī�װ��� �ϳ��� �������� ��(���� Ǯ�� ���� ���������� �ҷ��ü� �ִ� ��ǰ ���� �� �ҷ���)
					System.out.println((cnt+1) + "��° ī�װ��� ���� ��..");
					getHttpHTMLListSub(s);
					loop = false;
				}
			}else {
				cnt++;
			}
		}
		
		int prodCnt = 0;
		for(String url : cateListSub) {
			
			if(prodCnt < 5) {
				System.out.println(++prodCnt + "��° ��ǰ ó�� ��");

				imgList = new ArrayList<>();
				pInfo = new HashMap<>();			
				getHttpHTML(url);

				
				String seller = pInfo.get("sellMember");//��� ���߿� sell_id�� ��ü���� ��
				System.out.println(seller);
				nameSet.add(seller);
			}
			cateListSub = new HashSet<>();
		}
		//return idSet;
	}
	
	
	public static String getAccountNum() {		
		String frontPN = "010-";
		
		if(++phoneNumBase > 99999998) {
			phoneNumBase = 10000000;
		}
		String base = "" + phoneNumBase;	
		String fullPN = frontPN + base.substring(0, 4) + "-" + base.substring(4);
		return fullPN;
	}
	
	public static String getPhoneNum() {	
		String frontAN = "110-";
		if(++accountNumBase1 > 998) { 
			accountNumBase1 = 100;
		}
		if(++accountNumBase2 > 99998) {
			accountNumBase2 = 10000;
		}
		return frontAN + accountNumBase1 + "-" + accountNumBase2;
	}
	
	
	public static String getIdPw() {
		String sample = "sample";
		++idPwNum;
		return sample + idPwNum;
	}
	
	
	public static String randomAddress() {
		int ranIndex = (int)(Math.random() * address.length);
		return address[ranIndex];
	}
	
	
	public static int fillDB(int i) {
		int storedProdNum = 0;
		
		List<String> cateList = getHttpHTMLList("https://www.esingsing.co.kr");		
		int cnt = 0;
		boolean loop = true;
		for(String s : cateList) {
			
			if(cnt > i) {	//	8��° ���� ����깰 ��ǰ ������ �����Ŵ� ���ݺҷ����� �κ��̶� �ǳʶ�
				if(loop) {	// �׽�Ʈ���� ī�װ��� �ϳ��� �������� ��(���� Ǯ�� ���� ���������� �ҷ��ü� �ִ� ��ǰ ���� �� �ҷ���)
					getHttpHTMLListSub(s);
					loop = false;
				}
			}else {
				cnt++;
			}
		}
		
		int prodCnt = 0;
		for(String url : cateListSub) {
			
			if(prodCnt < 5) { //��ǰ �� �� ���� �� ���� ���� �ڡڡڡڡڡڡڡڡ�
				System.out.println(++prodCnt + "��° prod ó��ing");
				Timestamp randomDeadline = null; //getRandomDeadLine("�Ǹ�����");
				
				imgList = new ArrayList<>();
				pInfo = new HashMap<>();
				
				getHttpHTML(url);
				String seller = pInfo.get("sellMember"); //��� ���߿� sell_id(ex. sample1)�� ��ü���� ��
				String name = pInfo.get("title");
				String content = pInfo.get("detail");
				String priceStr = pInfo.get("price").replaceAll("\\,", "");
				Integer price = Integer.parseInt(priceStr.substring(0, priceStr.indexOf('��')));
				String sell = randomSell();
				randomDeadline = getRandomDeadLine(sell);		
				String IdPw = getIdPw();
				
				String sql1 = "INSERT INTO prod_detail(prod_idx, sell_id, prod_name, prod_info, prod_price, prod_sell, prod_sell_deadline)"
								+ "	VALUES( prod_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
				
				String sql2 = "SELECT prod_idx FROM prod_detail WHERE prod_name = ?";
	
				String sql3 = "INSERT INTO prod_img(img_idx, prod_idx, img_url) VALUES(img_idx_seq.nextval, ?, ?)";
														
				String sql4 = "insert into sell_member(sell_id, sell_pw, sell_name, sell_email, sell_phonenum, sell_accountnum, sell_address, sell_image)"
								+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
				
				String sql5 = "select sell_name from sell_member where sell_name = ?";
				try (
						Connection conn = ds.getConnection();
					
						PreparedStatement pstmt = conn.prepareStatement(sql1);				
						PreparedStatement pstmt2 = conn.prepareStatement(sql2);
						PreparedStatement pstmt3 = conn.prepareStatement(sql3);
						PreparedStatement pstmt4 = conn.prepareStatement(sql4);
						PreparedStatement pstmt5 = conn.prepareStatement(sql5);
				){
					
					conn.setAutoCommit(false);

					// sell_name �ߺ� �Ÿ���
					pstmt5.setString(1, seller);
					ResultSet rs1 = pstmt5.executeQuery();
					if(!rs1.next()) {
							// sell_member ���̺� ä��� �ڵ� (�긦 ���� �ؾ� prod_detail���� sell_id ���� ����)	
							pstmt4.setString(1, IdPw);
							pstmt4.setString(2, IdPw);
							pstmt4.setString(3, seller);
							pstmt4.setString(4, IdPw + "@agri.com");
							pstmt4.setString(5, getPhoneNum());
							pstmt4.setString(6, getAccountNum());
							pstmt4.setString(7, randomAddress());
							pstmt4.setString(8, "sample_img.jpg");
							pstmt4.executeUpdate();
							conn.commit();						
					}
								
					
					// prod_detail ���̺� ä��� �ڵ�
					pstmt.setString(1, IdPw);// �긦 �ƿ� sell_id�� �־�� �ڴ�
					pstmt.setString(2, name);
					pstmt.setNString(3, content);
					pstmt.setInt(4, price);
					pstmt.setString(5, sell);
					pstmt.setTimestamp(6, randomDeadline);
					pstmt.executeUpdate();
					conn.commit();

					
					
					// ��ǰ �̸��� �ش��ϴ� prod_idx �������� �ڵ� (prod_img ���̺��� �־�� ��)
					pstmt2.setString(1, name);
					ResultSet rs2 = pstmt2.executeQuery();
					rs2.next();
					Integer p_idx = rs2.getInt("prod_idx");
					//System.out.println(p_idx);
					
					
					// prod_img ���̺� ä��� �ڵ�
					int imgCnt = 0;
					for(String s : imgList) {
						++imgCnt;
						if(imgCnt < 4) { // �̹��� �� �� ���� �� ���� ���� �ڡڡڡڡڡ�
							System.out.println(s);
							pstmt3.setInt(1, p_idx);
							pstmt3.setString(2, s);
							pstmt3.executeUpdate();
							conn.commit();		
						}
					}
					++storedProdNum;					
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
			}	
			cateListSub = new HashSet<>();
		}
		//return "DB(prod_detail, sell_member, prod_img) ä��� ��";
		System.out.println("DB(prod_detail, sell_member, prod_img) ä��� ��");
		return storedProdNum;
	}
	
	
	
	
	
	public static String fillCate(int cateIdx, int storedProdNum) {
		
		String sql = "update prod_detail set prod_cate = ? where prod_cate is null";
		String cate = cates[cateIdx];
		System.out.println(cate);
		
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			conn.setAutoCommit(false);
			pstmt.setString(1, cate);
			pstmt.executeUpdate();
			conn.commit();			
		} catch (SQLException e) {
			System.out.println("ī�� ä��� ���� >>> " + e.getMessage());
			//e.printStackTrace();
		} 
		return "ī�� ä��� �Ϸ�";
	}
	
	public static void main(String[] args) {

		//DB�� ��ǰ����, �̹��� ������� ��. cnt>i �ϱ� ī��8(cnt=7)���� �������� i = 6����. �� ī�� ���� 85��(cnt=84)
		for(int i = 6; i < 16; ++i) {
			int cateNum = (i+2);
			int cateIdx = (cateNum - 8);
			
			System.out.println(cateNum + "��° ī�װ��� DB ���� �۾� ����");
			
			int storedProdNum = fillDB(i);
			System.out.println(cateNum + "�� �� ī�װ��� / ��ǰ " + storedProdNum + "�� DB�� ���� ��");			
			
			System.out.println(fillCate(cateIdx, storedProdNum));
		}

		
		//sell_id �� ���� �Ƶ� �� ���� ��(Ŭ���� �ۿ��� fillDBService. ���̰� fillDBService.getIdSet()���� �ؽü� ���� ��)
//		for(int i = 6; i <9; ++i) { //���÷� ī��8(cnt=7) ���� ī��10(cnt=9)������ �Ǹ��� name �ߺ� ���� �̾Ƽ� �ؽü¿� �����ϰ� ��. ī�� �̴� ������ if(cnt>i). 
//			getSellIdSet(i);	
//		}
//		System.out.println(idSet);

		
//		sell_member ���̺� ä��� �޼���� ��� ����
//		for(int i = 0; i < 105; ++i) {
//			System.out.println(getAccountNum());
//			System.out.println(getPhoneNum());
//			
//			String idPw = getIdPw();
//			System.out.println(idPw);
//			System.out.println(getIdPw() + "@agri.com");
//			System.out.println(randomAddress());
//			System.out.println("_________________________");
//		}

	}

	
}