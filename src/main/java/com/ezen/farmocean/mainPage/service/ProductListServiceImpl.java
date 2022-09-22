package com.ezen.farmocean.mainPage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.mapper.ProductListMapper;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.service.ProdImgServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductListServiceImpl implements ProductListService{
	
	@Autowired
	private ProductListMapper prodListMapper;
	
	@Autowired
	ProdImgServiceImpl iService;
	@Autowired
	ProductListService service;
	
	@Autowired
	HttpSession session;
	
	
	@Override
	public List<Product> getProcNewList() {
		
		List<Product> list = prodListMapper.getProcNewList();
		
		List<Product> imgList = prodListMapper.getProcNewList();
		
		for(Product p : imgList) {
			List<ProdImg> iList = iService.getImgsByProdIdx(p.getProd_idx());
//			log.info(p.getProd_idx());
			if (iList.size() > 0) {
				p.setImg_url(iList.get(0).getImg_url());
//				log.info(p.getImg_url());
				
			}
		}
		return imgList;
	}

	@Override
	public List<Product> getProcPopList() {
		
		List<Product> list = prodListMapper.getProcPopList();
		
		List<Product> imgList = prodListMapper.getProcPopList();
		
		for(Product p : imgList) {
			List<ProdImg> iList = iService.getImgsByProdIdx(p.getProd_idx());
//			log.info(p.getProd_idx());
			if (iList.size() > 0) {
				p.setImg_url(iList.get(0).getImg_url());
//				log.info(p.getImg_url());
				
			}
		}
		return imgList;
	}

	@Override
	public List<Product> getProcBidsList() {
		List<Product> list = prodListMapper.getProcBidsList();
		
		List<Product> imgList = prodListMapper.getProcBidsList();
		
		for(Product p : imgList) {
			List<ProdImg> iList = iService.getImgsByProdIdx(p.getProd_idx());
//			log.info(p.getProd_idx());
			if (iList.size() > 0) {
				p.setImg_url(iList.get(0).getImg_url());
//				log.info(p.getImg_url());
				
			}
		}
		return imgList;
	}

	// 닉네임
	@Override
	public List<Member> getMemberNick(String member_id) {
		
		return prodListMapper.getMemberNick(member_id);
	}
	
	// 선택한 회원 정보 불러오기
	@Override
	public List<Member> getMember(String id) {

		return prodListMapper.getMemberList(id);
	}

	
}
