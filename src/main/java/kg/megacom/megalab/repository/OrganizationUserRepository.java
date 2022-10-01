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

    @Query(value = "WITH separate_orgs AS ( " +
            "SELECT ou.user_id AS real_user_id FROM tb_organization_user ou " +
            "JOIN tb_organization o ON ou.organization_id = o.id " +
            "WHERE  o.is_deleted = false " +
            "GROUP BY ou.user_id " +
            "HAVING count(ou.user_id) = 1 " +
            ") " +
            "SELECT real_user_id FROM separate_orgs so " +
            "JOIN tb_organization_user ou ON ou.user_id = so.real_user_id " +
            "WHERE organization_id = ?", nativeQuery = true)
    List<Long> findAllUserIdsByOrganizationId(Long id); // this query finds users that are only in that one organization

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
