package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.DepartmentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentUserRepository extends JpaRepository<DepartmentUser, Long> {

    @Modifying
    @Query(value = "UPDATE tb_department_user " +
            "SET department_id = ?2 " +
            "WHERE department_id = ?1 ", nativeQuery = true)
    void changeDepartmentToAllUsers(Long oldDepartmentId, Long newDepartmentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_department_user " +
            "SET department_id = ?3 " +
            "WHERE user_id = ?1 " +
            "AND department_id = ?2 ", nativeQuery = true)
    void changeDepartmentByUserId(Long userId, Long oldDepartmentId, Long newDepartmentId);

    @Query(value = "WITH separate_deps AS ( " +
            "SELECT du.user_id AS real_user_id FROM tb_department_user du " +
            "JOIN tb_department d ON du.department_id = d.id " +
            "WHERE  d.is_deleted = false " +
            "GROUP BY du.user_id " +
            "HAVING count(du.user_id) = 1 " +
            ") " +
            "SELECT real_user_id FROM separate_deps sd " +
            "JOIN tb_department_user du ON du.user_id = sd.real_user_id " +
            "WHERE department_id = ?", nativeQuery = true)
    List<Long> findAllUserIdsByDepartmentId(Long id); // this query finds users that are only in that one department

}
