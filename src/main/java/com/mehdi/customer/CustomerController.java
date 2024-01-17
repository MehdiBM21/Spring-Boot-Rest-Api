package com.mehdi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    public static void main(String[] args){
        SpringApplication.run(CustomerController.class, args);
    }

    @GetMapping("/getAll")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @PostMapping("/post")
    public void addNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }
    @DeleteMapping(path = "delete/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
    }
    @PutMapping(path = "put/{customerId}")
    public void putCustomer(@PathVariable Integer customerId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String email){
        customerService.putCustomer(customerId, name, email);
    }


}
