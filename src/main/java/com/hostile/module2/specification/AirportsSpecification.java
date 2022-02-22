package com.hostile.module2.specification;

import com.hostile.module2.entity.Airport;
import com.hostile.module2.util.ParseQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class AirportsSpecification implements Specification<Airport> {
    private Map<String, Object> filters;

    public AirportsSpecification(Map<String, Object> filters) {
        this.filters = filters;
    }

    public AirportsSpecification(String filters) {
        this.filters = ParseQuery.parse(filters);
    }

    @Override
    public Predicate toPredicate(Root<Airport> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = getPredicates(root, query, criteriaBuilder);
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public List<Predicate> getPredicates(Root<Airport> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(filters)
                .orElseGet(Collections::emptyMap)
                .forEach((key, value) -> {
                    if (value.toString().isEmpty()) return;
                    Predicate predicate;
                    switch (key) {
                        case "query":
                            Predicate listPredicates[] = Arrays.stream(value.toString().split(" "))
                                    .map(String::trim)
                                    .map(String::toLowerCase)
                                    .filter(s -> !s.isEmpty())
                                    .map(s ->
                                            criteriaBuilder.like(
                                                    criteriaBuilder.lower(
                                                            criteriaBuilder.concat(root.get("city"), criteriaBuilder.concat(root.get("iata"), root.get("name")))
                                                    ),
                                                    "%" + s + "%"
                                            )
                                    )
                                    .toArray(Predicate[]::new);
                            predicate = criteriaBuilder.or(listPredicates);
                            break;
                        default:
                            return;
                    }
                    if (predicate != null)
                        predicates.add(predicate);
                });
        return predicates;
    }



}
