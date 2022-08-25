package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageService {
	
	// ���� ��ü
	public List<MessageBox> getList();

	// ���� ���� ���� �� ��
	public List<MessageBox> getMyList(String id);
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMySendList(String id);
	
	// ȸ�� ���� �ҷ�����
	public List<SellMember> getSell(String id);
	
	public List<BuyMember> getBuy(String id);
	
	// ȸ�� ���� �����ϱ�

}