package com.company.basketservice.repository;

import com.company.basketservice.module.Basket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@RequiredArgsConstructor
public class BasketRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Basket> getBasket(Map<String,String> params){
        int size = 10, page = 0;
        if(params.containsKey("page")){
            page = Integer.parseInt("page");
        }
        if(params.containsKey("size")){
            size = Integer.parseInt("size");
        }
        String strQuery = "select b from Basket b where 1=1 ";
        String countQuery = "select count(b.id) from Basket b where 1=1 ";
        StringBuilder buildParam = builderParams(params);
        Query query = entityManager.createQuery(strQuery + buildParam);
        Query queryTwo = entityManager.createQuery(countQuery + buildParam);

        setParams(query, params);
        setParams(queryTwo, params);

        query.setFirstResult(size * page);
        query.setMaxResults(size);
        long totalElement = Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElement);
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if(params.containsKey("id")){
            stringBuilder.append(" And b.id = :id");
        }
        if(params.containsKey("prodMass")){
            stringBuilder.append(" And b.prodMass = :prodMass");
        }
        if(params.containsKey("prodPrice")){
            stringBuilder.append(" And b.prodPrice = :prodPrice");
        }
        if(params.containsKey("totalPrice")){
            stringBuilder.append(" And b.totalPrice = :totalPrice");
        }
        if(params.containsKey("products")){
            stringBuilder.append(" And b.products = :products");
        }
        return stringBuilder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter("id", params.get("id"));
        }
        if (params.containsKey("prodMass")) {
            query.setParameter("prodMass", params.get("prodMass"));
        }
        if (params.containsKey("prodPrice")) {
            query.setParameter("prodPrice", params.get("prodPrice"));
        }
        if (params.containsKey("totalPrice")) {
            query.setParameter("totalPrice", params.get("totalPrice"));
        }
        if (params.containsKey("products")) {
            query.setParameter("products", params.get("products"));
        }
    }
}