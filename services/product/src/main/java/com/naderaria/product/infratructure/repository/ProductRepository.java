package com.naderaria.product.infratructure.repository;

import com.naderaria.product.api.dto.response.ResProductPageItemDto;
import com.naderaria.product.infratructure.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
                select new com.naderaria.product.api.dto.response.ResProductPageItemDto(p.id,p.name,p.description,
                            c.name,pr.amount,p.statusType,inv.quantity)
                from Product as p
                join p.category as c
                join p.price as pr
                join p.inventory as inv
            """,
            countQuery = """
                     select count(p) from Product p
                    """)
    Page<ResProductPageItemDto> findAllProducts(Pageable pageable);


    boolean existsByCategoryId(@PathVariable("categoryId") Long categoryId);

   /* @Query(value = """
                    select new com.naderaria.ecommerce_platform.product.api.dto.internal.IntProductSummeryDto(p.id,p.name,p.price,p.stockQuantity)
                    from Product as p
                    where p.id in(:productIds)
            """)
    Optional<List<ProductSummeryDto>> getProductsNameMap(@Param("productIds") List<Long> productIds);*/

}

