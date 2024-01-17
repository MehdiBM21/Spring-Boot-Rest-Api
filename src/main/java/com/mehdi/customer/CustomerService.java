package com.mehdi.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()) {
            System.out.println("EROORRR");
            throw new IllegalStateException("Email already taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new IllegalStateException("no customer with this id");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    //lets you use setters to update the customer (without any query)
    public void putCustomer(Integer customerId, String name, String email) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new IllegalStateException("no customer with this id"));
        if(!name.isEmpty() || !name.equals(customer.getName())) {
            customer.setName(name);
        }
        if(!email.isEmpty() || !email.equals(customer.getName())) {
            Optional<Customer>customerOptional = customerRepository.findCustomerByEmail(email);
            if(customerOptional.isPresent()) {
                throw new IllegalStateException("email already taken");
            }
            customer.setEmail(email);
        }
    }
}
