package model;

import dao.MemosAddDAO;

public class MemoAddLogic {
	public boolean execute(MemoAdd memoadd,Form form) {
		MemosAddDAO dao = new MemosAddDAO();
		MemoAdd memoadd2 = dao.insert(memoadd,form);
		return memoadd2 != null;
	}

}
