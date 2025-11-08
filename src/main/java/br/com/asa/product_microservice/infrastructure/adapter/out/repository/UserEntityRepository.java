package br.com.asa.product_microservice.infrastructure.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserDetails> findByUsername(@Param("username") String username);

}
