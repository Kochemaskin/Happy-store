package ru.happy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.happy.entities.Role;
import ru.happy.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    public Role getRoleForNewUser(){
        return roleRepository.getOne(1L);
    }

}
