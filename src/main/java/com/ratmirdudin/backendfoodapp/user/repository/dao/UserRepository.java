package com.ratmirdudin.backendfoodapp.user.repository.dao;

import com.ratmirdudin.backendfoodapp.user.repository.domain.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(value = "user-entity-graph")
    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(value = "user-entity-graph")
    @Query(value = "select * from users order by random() limit :limit", nativeQuery = true)
    List<UserEntity> findAllOrderByRandom(@Param("limit") Long limit);
}
