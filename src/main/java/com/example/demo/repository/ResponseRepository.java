package com.example.demo.repository;

import com.example.demo.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    Optional<List<Response>> findAllBySuccessIsFalse();

    Optional<Response> findByCep(String cep);
}
