package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, Long> {

    Optional<OrganizationUser> findById(Long id);

    @Query(value = "SELECT user_id FROM tb_organization_user " +
//            "JOIN tb_user u ON ou.user_id = u.id " +
            "WHERE organization_id = ?1 " +
//            "WHERE u.is_deleted = false " +
            "GROUP BY user_id " +
            "HAVING count(user_id) = 1", nativeQuery = true)
    List<Long> findAllUserIdsByOrganizationId(Long id); //todo: try this to exclude deleted users

    @Query(value = "SELECT organization_id FROM tb_organization_user " +
            "WHERE user_id = ?1 " +
            "GROUP BY organization_id " +
            "HAVING count(organization_id) = 1", nativeQuery = true)
    List<Long> findOrganizationIdByUserId(Long userId);

    @Query(value = "SELECT organization_user_id FROM tb_organization_user " +
            "WHERE user_id = ?1 ", nativeQuery = true)
    List<Long> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_organization_user " +
    "SET organization_id = ?3 " +
    "WHERE user_id =?1 " +
    "AND organization_id = ?2 ", nativeQuery = true)
    void changeOrganization(Long userId, Long oldOrganizationId, Long newOrganizationId);

}
