package model;

import dao.MemosUpdateformDAO;

public class MemoUpdateLogic {
	public boolean update(MemoUpdate memoupdate,Form form){
		MemosUpdateformDAO dao = new MemosUpdateformDAO();
		MemoUpdate memoupdate2 = dao.update(memoupdate,form);
		return memoupdate2 !=null;
	}
}
