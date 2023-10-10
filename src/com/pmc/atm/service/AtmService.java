package com.pmc.atm.service;

import com.pmc.atm.dao.AtmDao;
import com.pmc.atm.model.Atm;

public class AtmService {
    private AtmDao atmDao;

    public AtmService() {
        atmDao = new AtmDao();
    }

    public boolean isAtmValidate(int atmId, String pwd) {
        Atm atm = atmDao.getAtmByIDAndPwd(atmId, pwd);

        return atm != null && atm.getPwd().equals(pwd);
    }
}
