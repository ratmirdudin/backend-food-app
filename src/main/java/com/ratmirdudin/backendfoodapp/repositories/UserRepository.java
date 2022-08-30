package com.ratmirdudin.backendfoodapp.repositories;

import com.ratmirdudin.backendfoodapp.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "user-entity-graph")
    Optional<User> findByUsername(String username);

    @EntityGraph(value = "user-entity-graph")
    @Query(value = "select * from t_users order by random() limit :limit", nativeQuery = true)
    List<User> findAllOrderByRandom(@Param("limit") Long limit);
}
