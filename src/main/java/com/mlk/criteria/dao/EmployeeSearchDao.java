package com.mlk.criteria.dao;

import com.mlk.criteria.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {

    private final EntityManager em;

    public List<Employee> findAllBySimpleQuery(String firstname, String lastname, String email) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        // select  * from employee
        Root<Employee> root = criteriaQuery.from(Employee.class);

        /*
            ##### prepare WHERE clause #####
        */

        // WHERE firstname like '%m%'
        Predicate firstnamePredicate = criteriaBuilder
                .like(root.get("firstname"), "%" + firstname + "%");

        // WHERE lastname like '%s%'
        Predicate lastnamePredicate = criteriaBuilder
                .like(root.get("lastname"), "%" + lastname + "%");

        // WHERE email like '%s%'
        Predicate emailPredicate = criteriaBuilder
                .like(root.get("email"), "%" + email + "%");

        Predicate firstnameOrLastnamePredicate = criteriaBuilder.or(
                firstnamePredicate,
                lastnamePredicate
        );

        Predicate andEmailPredicate = criteriaBuilder.and(
                firstnameOrLastnamePredicate,
                emailPredicate
        );

        // Final query ==> select * from employee where firstname like '%m%'
        //                                              or lastname like '%s%'
        //                                              AND email like '%sm%'
        criteriaQuery.where(andEmailPredicate);

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    private List<Employee> findAllByCriteria(SearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        List<Predicate> predicates = new ArrayList<>();
        // select from employee
        Root<Employee> root = criteriaQuery.from(Employee.class);
        if (request.getFirstname() != null) {
            Predicate firstnamePredicate = criteriaBuilder.like(root.get("firstname"), "%" + request.getFirstname() + "%");
            predicates.add(firstnamePredicate);
        }
        if (request.getLastname() != null) {
            Predicate lastnamePredicate = criteriaBuilder.like(root.get("lastname"), "%" + request.getLastname() + "%");
            predicates.add(lastnamePredicate);
        }
        if (request.getEmail() != null) {
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%");
            predicates.add(emailPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
