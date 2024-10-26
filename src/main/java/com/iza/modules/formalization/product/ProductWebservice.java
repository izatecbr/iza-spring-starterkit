package com.iza.modules.formalization.product;

import com.iza.modules.formalization.product.domain.ProductRequest;
import com.iza.core.infra.http.Response;
import com.iza.core.infra.http.ResponseFactory;
import com.iza.core.infra.http.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static com.iza.modules.Entitys.PRODUCT;

@RestController
@RequestMapping("/products")
public class ProductWebservice {

    @Autowired
    private ProductService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response post(@RequestBody ProductRequest request) {
        return ResponseFactory.create(service.save(request), ResponseMessage.create(PRODUCT.getName())) ;
    }
    @PutMapping("/{id}")
    public Response put(@PathVariable("id") Integer id, @RequestBody ProductRequest request) {
        return ResponseFactory.ok(service.update(id,request), ResponseMessage.update(PRODUCT.getName())) ;
    }
    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") Integer id) {
        return ResponseFactory.ok(service.find(id), ResponseMessage.update(PRODUCT.getName())) ;
    }
    @GetMapping
    public Response getAll(){
        return ResponseFactory.ok(service.search());
    }
}

