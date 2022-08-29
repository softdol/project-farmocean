package com.ezen.farmocean.cs.service;

import java.util.List;

import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.dto.CsBoard;

public interface BoardService {
	
	public List<BoardCate> getGateList();
	public List<CsBoard> getBoardList(Integer page, Integer pagesize);
	public Integer setBoardIns(CsBoard csboard);
	public void setBoardCount(Integer board_idx);
	public CsBoard getBoardInfo(Integer board_idx);
	public Integer getBoardCount();

}
