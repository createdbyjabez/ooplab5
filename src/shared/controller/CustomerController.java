package shared.controller;

import shared.model.Customer;
import shared.rmi.RmiClient;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;




public class CustomerController {


    public void saveCustomer(Customer customer) {
        try {
            RmiClient.getService().saveCustomer(customer);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not save customer through RMI.", e);
        }
    }


    public void removeCustomer(Customer customer) {
        if (customer != null) {
            try {
                RmiClient.getService().deleteCustomer(customer.getId());
            } catch (RemoteException e) {
                throw new IllegalStateException("Could not delete customer through RMI.", e);
            }
        }
    }


    public List<Customer> getCustomers() {
        try {
            return RmiClient.getService().getCustomers();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}