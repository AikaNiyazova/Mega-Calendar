package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.response.ReadUserProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            "RETURNING department_id" +
            ") " +
            "UPDATE tb_user u " +
            "SET is_deleted = true " +
            "FROM pos, u " +
            "JOIN tb_department_user du " +
            "ON u.id = du.user_id " +
            "WHERE du.department_id = pos.department_id",
            nativeQuery = true)
    void deleteUsersAndPositions(Long departmentId);

    Boolean existsByEmailAndIsDeletedFalse(String email);

    Boolean existsByIdAndIsDeletedFalse(Long id);

    User findByEmailAndIsDeletedFalse(String email);

    @Query(value = "SELECT * " +
    "FROM tb_user u " +
    "JOIN tb_organization_user ou " +
    "ON u.id = ou.user_id " +
    "WHERE ou.organization_id = ?1 ", nativeQuery = true)
    List<User> findAllByOrganizationId(Long organizationId);

    @Query(value = "SELECT * " +
            "FROM tb_user u " +
            "JOIN tb_department_user du " +
            "ON u.id = du.user_id " +
            "WHERE du.department_id = ?1 ", nativeQuery = true)
    List<User> findAllByDepartmentId(Long departmentId);

    @Query(value = "SELECT * " +
            "FROM tb_user u " +
            "JOIN tb_position_user pu " +
            "ON u.id = pu.user_id " +
            "WHERE pu.position_id = ?1 ", nativeQuery = true)
    List<User> findAllByPositionId(Long positionId);

    @Query(value = "SELECT * FROM tb_user " +
            "WHERE (last_name || ' ' || first_name || ' ' || patronymic) ILIKE '%' || ? || '%' ",
//            "WITH u AS (" +
//            "SELECT id, photo_path, " +
//            "concat(last_name, ' ', first_name, ' ', patronymic) AS full_name, " +
//            "msisdn, email, status " +
//            "FROM tb_user" +
//            ") " +
//            "SELECT * FROM u " +
//            "WHERE full_name ILIKE '%' || ? || '%' ",
            nativeQuery = true)
    List<User> findAllByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_user SET role_id = ?2 WHERE id = ?1 ", nativeQuery = true)
    void changeRole(Long userId, Long roleId);
}
