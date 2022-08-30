package com.ratmirdudin.backendfoodapp.repositories;

import com.ratmirdudin.backendfoodapp.enums.RoleEnum;
import com.ratmirdudin.backendfoodapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum roleEnum);
}
