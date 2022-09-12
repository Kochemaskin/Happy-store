package ru.happy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.happy.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
