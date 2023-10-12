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

    public Atm getAtmUsingNameAndPwd(String atmName, String pwd) {
        Atm atm = atmDao.getAtmByNameAndPwd(atmName, pwd);

        return atm;
    }

    public boolean addNewATM(String atmName, String atmPwd, int atmBalance) {
        boolean status = false;

        Atm atm = new Atm();
        atm.setName(atmName);
        atm.setPwd(atmPwd);
        atm.setBalance(atmBalance);

        if(atmDao.isNewATMAdded(atm)){
            status = true;
        }
        return status;
    }

//    update atm
    public boolean updateATM(Atm atm) {
        boolean status = false;

        if(atmDao.isAtmUpdated(atm)){
            status = true;
        }
        return status;
    }
}
