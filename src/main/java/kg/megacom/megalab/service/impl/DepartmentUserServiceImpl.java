package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.DepartmentUserDto;
import kg.megacom.megalab.model.mapper.DepartmentUserMapper;
import kg.megacom.megalab.repository.DepartmentUserRepository;
import kg.megacom.megalab.service.DepartmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentUserServiceImpl implements DepartmentUserService {

    private final DepartmentUserRepository departmentUserRepository;

    @Autowired
    public DepartmentUserServiceImpl(DepartmentUserRepository departmentUserRepository) {
        this.departmentUserRepository = departmentUserRepository;
    }

    @Override
    public void changeDepartment(Long oldDepartmentId, Long newDepartmentId) {
        departmentUserRepository.changeDepartmentToAllUsers(oldDepartmentId, newDepartmentId);
    }

    @Override
    public void changeDepartmentByUserId(Long userId, Long oldDepartmentId, Long newDepartmentId) {
        departmentUserRepository
                .changeDepartmentByUserId(userId, oldDepartmentId, newDepartmentId);
    }

    @Override
    public List<Long> findAllUserIdsByDepartmentId(Long departmentId) {
        return departmentUserRepository.findAllUserIdsByDepartmentId(departmentId);
    }

    @Override
    public void save(DepartmentUserDto departmentUserDto) {
        departmentUserRepository.save(DepartmentUserMapper.INSTANCE.toEntity(departmentUserDto));
    }

}
