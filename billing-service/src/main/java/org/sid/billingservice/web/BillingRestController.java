package com.abdo.billingservice.web;

import com.abdo.billingservice.entities.Bill;
import com.abdo.billingservice.feign.CustomerRestClient;
import com.abdo.billingservice.feign.ProductItemRestClient;
import com.abdo.billingservice.model.Customer;
import com.abdo.billingservice.model.Product;
import com.abdo.billingservice.repositories.BillRepository;
import com.abdo.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;
    
    public BillingRestController(BillRepository billRepository ,
                                 ProductItemRepository productItemRepository,
                                CustomerRestClient customerRestClient,
                                ProductItemRestClient productItemRestClient) {

        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }


    @GetMapping(path = "/fullBill/{id}")
    public Bill getBillById(@PathVariable(name = "id") Long id) {
        Bill bill =  billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi -> {
            Product product = productItemRestClient.getProductById(pi.getProductId());
            pi.setProductName(product.getName());
        });
        return bill;

    }

}
