package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.response.FindAllUsersForMobileResponse;
import kg.megacom.megalab.model.response.FindAllUsersForWebResponse;
import kg.megacom.megalab.model.response.ReadUserProfileResponse;
import kg.megacom.megalab.model.response.UserProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsDeletedFalse(Long id);

    @Query(value = "SELECT u.id, u.photo_path, TRIM(concat(u.last_name, ' ', u.first_name, ' ', u.patronymic)) AS full_name, " +
            "u.email, u.msisdn, o.organization_name, d.department_name, p.position_name, u.status\n" +
            "FROM tb_user u\n" +
            "        JOIN tb_position_user pu on u.id = pu.user_id\n" +
            "        JOIN tb_position p on pu.position_id = p.id\n" +
            "        JOIN tb_department d on p.department_id = d.id\n" +
            "        JOIN tb_organization o on d.organization_id = o.id\n" +
            "WHERE u.id = ?1\n" +
            "AND u.is_deleted = false\n" +
            "AND d.is_deleted = false", nativeQuery = true)
    List<UserProfileResponse> readProfile(Long id);

    @Query(value = "SELECT u.id, u.photo_path, TRIM(concat(u.last_name, ' ', u.first_name, ' ', u.patronymic)) AS full_name, " +
            "p.position_name, d.department_name, u.email\n" +
            "FROM tb_user u\n" +
            "        JOIN tb_position_user pu on u.id = pu.user_id\n" +
            "        JOIN tb_position p on pu.position_id = p.id\n" +
            "        JOIN tb_department d on p.department_id = d.id\n" +
            "WHERE u.is_deleted = false\n" +
            "AND d.is_deleted = false", nativeQuery = true)
    List<FindAllUsersForWebResponse> findAllUsersForWebResponse();

    @Query(value = "SELECT u.id, u.photo_path, TRIM(concat(u.last_name, ' ', u.first_name, ' ', u.patronymic)) AS full_name, " +
            "p.position_name\n" +
            "FROM tb_user u\n" +
            "        JOIN tb_position_user pu on u.id = pu.user_id\n" +
            "        JOIN tb_position p on pu.position_id = p.id\n" +
            "        JOIN tb_department d on p.department_id = d.id\n" +
            "WHERE u.is_deleted = false\n" +
            "AND d.is_deleted = false", nativeQuery = true)
    List<FindAllUsersForMobileResponse> findAllUsersForMobileResponse();

    Boolean existsByEmailAndIsDeletedFalse(String email);
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


    // orgId: 42
    // depId: 9
    // posId: 8
}
