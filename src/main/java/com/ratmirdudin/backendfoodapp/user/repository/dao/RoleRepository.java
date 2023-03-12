package com.ratmirdudin.backendfoodapp.user.repository.dao;

import com.ratmirdudin.backendfoodapp.user.enums.RoleEnum;
import com.ratmirdudin.backendfoodapp.user.repository.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum roleEnum);
}
