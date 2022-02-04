package ru.happy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.happy.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
