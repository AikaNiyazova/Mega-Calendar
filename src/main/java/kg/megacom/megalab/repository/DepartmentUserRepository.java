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

    @Query(value = "SELECT user_id FROM tb_department_user " +
//            "JOIN tb_user u ON du.user_id = u.id " +
            "WHERE department_id = ?1 " +
//            "WHERE u.is_deleted = false " +
            "GROUP BY user_id " +
            "HAVING count(user_id) = 1", nativeQuery = true)
    List<Long> findAllUserIdsByDepartmentId(Long id); //todo: try this to exclude deleted users

}
