package com.pmc.atm.service;

import com.pmc.atm.dao.AccountDao;
import com.pmc.atm.dao.CustomerDao;
import com.pmc.atm.model.Account;
import com.pmc.atm.model.Customer;

public class CustomerServices {
    private CustomerDao customerDao;

    public CustomerServices() {
        customerDao = new CustomerDao();
    }

    public boolean addNewCustomer(String customerName, int accountId) {
        boolean status = false;

        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setAccountId(accountId);

        if(customerDao.isNewCustomerAdded(customer)) {
            status = true;
        } else {
            System.out.println("Something went wrong..");
        }
        return status;
    }
}
