package com.abdo.billingservice;

import com.abdo.billingservice.entities.Bill;
import com.abdo.billingservice.entities.ProductItem;
import com.abdo.billingservice.feign.CustomerRestClient;
import com.abdo.billingservice.feign.ProductItemRestClient;
import com.abdo.billingservice.model.Customer;
import com.abdo.billingservice.model.Product;
import com.abdo.billingservice.repositories.BillRepository;
import com.abdo.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;


import java.util.Collection;
import java.util.Date;


@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billingRepository ,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient) {
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            Bill bill = billingRepository.save(new Bill(null, new Date(), null, customer.getId(), null));
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts(0, 20);
            productPagedModel.forEach(p -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1+ (int)(Math.random()*100));
                productItem.setBill(bill);
                productItem.setProductId(p.getId());
                productItemRepository.save(productItem);
            });

        };
    }

}
