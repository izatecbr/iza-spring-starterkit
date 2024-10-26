package com.iza.modules.formalization.customer;

import com.iza.core.infra.http.Response;
import com.iza.core.infra.http.ResponseFactory;
import com.iza.core.infra.http.ResponseMessage;
import com.iza.modules.formalization.customer.domain.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static com.iza.modules.Entitys.CUSTOMER;
@RestController
@RequestMapping("/customers")
public class CustomerWebservice {
    @Autowired
    private CustomerService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response post(@RequestBody CustomerRequest request) {
        return ResponseFactory.create(service.save(request), ResponseMessage.create(CUSTOMER.getName())) ;
    }
    @PutMapping("/{id}")
    public Response put(@PathVariable("id") Integer id, @RequestBody CustomerRequest request) {
        return ResponseFactory.ok(service.update(id,request), ResponseMessage.update(CUSTOMER.getName())) ;
    }
    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") Integer id) {
        return ResponseFactory.ok(service.find(id), ResponseMessage.update(CUSTOMER.getName())) ;
    }
    @GetMapping
    public Response getAll(){
        return ResponseFactory.ok(service.search());
    }
}
