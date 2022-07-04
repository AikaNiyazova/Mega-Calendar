package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.DepartmentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentUserRepository extends JpaRepository<DepartmentUser, Long> {

    @Modifying
    @Query(value = "UPDATE tb_department_user " +
            "SET department_id = ?2 " +
            "WHERE department_id = ?1", nativeQuery = true)
    void changeDepartment(Long oldDepartmentId, Long newDepartmentId);

    @Query(value = "SELECT user_id FROM tb_department_user " +
            "WHERE department_id = ?1", nativeQuery = true)
    List<Long> findAllUserIdsByDepartmentId(Long id);

}
