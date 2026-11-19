package in.strikes.L36_springSecurityDemo.service;

import in.strikes.L36_springSecurityDemo.entity.Role;
import in.strikes.L36_springSecurityDemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
