package com.pmc.atm.service;

import com.pmc.atm.dao.AtmDao;
import com.pmc.atm.model.Atm;

public class AtmService {

	AtmDao atmDao = new AtmDao();

	public Atm getAtmDetail(String name) {
		return atmDao.getAtmDetail(name);
	}
}
