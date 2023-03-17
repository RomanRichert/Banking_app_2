package com.richert.banking_app.repository;

import com.richert.banking_app.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    List<Agreement> findByAccountId(String id);
}