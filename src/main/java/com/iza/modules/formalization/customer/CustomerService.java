package com.iza.modules.formalization.customer;

import com.iza.core.infra.business.exceptions.RecordIdentificationNotFoundException;
import com.iza.core.infra.business.exceptions.RecordNotFoundException;
import com.iza.core.infra.business.exceptions.RequiredFillingException;
import com.iza.modules.formalization.customer.domain.CustomerEntity;
import com.iza.modules.formalization.customer.domain.CustomerRequest;
import com.iza.modules.formalization.customer.domain.CustomerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.iza.modules.Entitys.CUSTOMER;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public Integer save(CustomerRequest request) {
        //regras de inclusao
        return save(null, request);
    }
    public Integer update(Integer id,CustomerRequest request) {
        //regras de alteração
        return save(id, request);
    }
    private Integer save(Integer id, CustomerRequest request){

        if(request.getName() == null || request.getName().isBlank())
            throw new RequiredFillingException("Name");

        //aqui se o campo id for informado tenta buscar do contrário cria uma nova entidade
        CustomerEntity entity = Optional.ofNullable(id).isPresent() ? findEntity(id) : new CustomerEntity();

        //copia os dados vindo do request para a entidade
        //pode usar o record, fique a vontade
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();

    }
    public CustomerResponse find(Integer id){
        return convert(findEntity(id));
    }

    //aplicar a paginação com base nos filtros necessários
    public List<CustomerResponse> search(){
        List<CustomerResponse> response = repository.findAll()
                .stream().map(this::convert).collect(Collectors.toList());

        return response;
    }
    private CustomerEntity findEntity(Integer id){
        return repository.findById(id).orElseThrow(()-> new RecordIdentificationNotFoundException(CUSTOMER.getName(), id));
    }
    //voce escolhar outras formas de converter entity para dto
    private CustomerResponse convert(CustomerEntity entity){
        CustomerResponse response = new CustomerResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
