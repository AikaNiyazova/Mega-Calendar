package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.response.ReadUserProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsDeletedFalse(Long id);

    @Query(value = "SELECT photo_path, TRIM(concat(last_name, ' ', first_name, ' ', patronymic)) AS full_name, " +
            "email, msisdn, organization_name, department_name, position_name, status\n" +
            "FROM tb_user u\n" +
            "        JOIN tb_position_user pu on u.id = pu.user_id\n" +
            "        JOIN tb_position p on pu.position_id = p.id\n" +
            "        JOIN tb_department d on p.department_id = d.id\n" +
            "        JOIN tb_organization o on d.organization_id = o.id\n" +
            "WHERE u.id = ?1\n" /*+
            "AND o.is_deleted = false"*/, nativeQuery = true)
    Optional<ReadUserProfileResponse> readProfile(Long id);

    @Modifying
    @Query(value = "WITH pos AS (" +
            "UPDATE tb_position " +
            "SET is_deleted = true " +
            "WHERE department_id = ?1 " +
//            "RETURNING department_id" +
            ") " +
            "UPDATE tb_user u " +
            "SET is_deleted = true " +
            "FROM u " +
            "JOIN tb_department_user du " +
            "ON u.id = du.user_id " +
            "WHERE du.department_id = ?1", nativeQuery = true)
    void deleteUsersAndPositions(Long departmentId);

    Boolean existsByEmail(String email);
    boolean existsById(Long id);

}
