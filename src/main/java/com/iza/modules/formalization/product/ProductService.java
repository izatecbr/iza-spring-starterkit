package com.iza.modules.formalization.product;

import com.iza.core.infra.business.exceptions.RecordIdentificationNotFoundException;
import com.iza.modules.formalization.product.domain.ProductEntity;
import com.iza.modules.formalization.product.domain.ProductRequest;
import com.iza.modules.formalization.product.domain.ProductResponse;
import com.iza.core.infra.business.exceptions.RecordNotFoundException;
import com.iza.core.infra.business.exceptions.RequiredFillingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.iza.modules.Entitys.PRODUCT;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public Integer save(ProductRequest request) {
        //regras de inclusao
        return save(null, request);
    }
    public Integer update(Integer id,ProductRequest request) {
        //regras de alteração
        return save(id, request);
    }
    private Integer save(Integer id, ProductRequest request){

        if(request.getName() == null || request.getName().isBlank())
            throw new RequiredFillingException("Name");

        //aqui se o campo id for informado tenta buscar do contrário cria uma nova entidade
        ProductEntity entity = Optional.ofNullable(id).isPresent() ? findEntity(id) : new ProductEntity();

        //copia os dados vindo do request para a entidade
        //pode usar o record, fique a vontade
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();

    }
    public ProductResponse find(Integer id){
        return convert(findEntity(id));
    }

    //aplicar a paginação com base nos filtros necessários
    public List<ProductResponse> search(){
        List<ProductResponse> response = repository.findAll()
                .stream().map(this::convert).collect(Collectors.toList());

        return response;
    }

    private ProductEntity findEntity(Integer id){
        return repository.findById(id).orElseThrow(()-> new RecordIdentificationNotFoundException(PRODUCT.getName(), id));
    }

    //voce escolhar outras formas de converter entity para dto
    private ProductResponse convert(ProductEntity entity){
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
