package com.bosch.feedforward.services.Impl;

import com.bosch.feedforward.entity.Role;
import com.bosch.feedforward.repository.RoleRepository;
import com.bosch.feedforward.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(UUID id) {
        Optional<Role> roleFound = this.roleRepository.findById(id);
        if(roleFound.isPresent()){
            return roleFound.get();
        }

        return null;
    }

    @Override
    public Page<Role> getAllRoles(Pageable page) {
        return this.roleRepository.findAll(page);
    }

    @Override
    public List<Role> createRoles(List<Role> roles) {
        return this.roleRepository.saveAll(roles);
    }

    @Override
    public void deleteRole(Role role) {
        Optional<Role> roleFound = this.roleRepository.findById(role.getId());

        if(roleFound.isPresent()){
            this.roleRepository.deleteById(roleFound.get().getId());
        }
    }
}
