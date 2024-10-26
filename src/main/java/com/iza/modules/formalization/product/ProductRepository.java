package com.iza.modules.formalization.product;

import com.iza.modules.formalization.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
