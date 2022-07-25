package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.DepartmentUserDto;
import kg.megacom.megalab.model.entity.DepartmentUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentUserService {

    void changeDepartment(Long oldDepartmentId, Long newDepartmentId);
    void changeDepartmentByUserId(Long userId, Long oldDepartmentId, Long newDepartmentId);
    List<Long> findAllUserIdsByDepartmentId(Long departmentId);
    void save(DepartmentUserDto departmentUserDto);

}
