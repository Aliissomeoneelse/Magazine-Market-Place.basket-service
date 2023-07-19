package com.company.basketservice.repository;

import com.company.basketservice.module.Loaner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

;

@Repository
@RequiredArgsConstructor
public class LoanerRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Loaner> getLoaner(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt("size");
        }
        String strQuery = "select l from Loaner l where 1=1 ";
        String countQuery = "select count(l.id) from Loaner l where 1=1 ";
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
        if (params.containsKey("id")) {
            stringBuilder.append(" And l.id = :id");
        }
        if (params.containsKey("baskets")) {
            stringBuilder.append(" And l.baskets = :baskets");
        }
        if (params.containsKey("totalPrice")) {
            stringBuilder.append(" And l.totalPrice = :totalPrice");
        }
        if (params.containsKey("status")) {
            stringBuilder.append(" And l.status = :status");
        }
        return stringBuilder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter("id", params.get("id"));
        }
        if (params.containsKey("baskets")) {
            query.setParameter("baskets", params.get("baskets"));
        }
        if (params.containsKey("totalPrice")) {
            query.setParameter("totalPrice", params.get("totalPrice"));
        }
        if (params.containsKey("status")) {
            query.setParameter("status", params.get("status"));
        }
    }
}
