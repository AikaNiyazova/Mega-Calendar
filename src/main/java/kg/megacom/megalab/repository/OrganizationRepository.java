package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByIdAndIsDeletedFalse(Long id);

    @Modifying
    @Query(value = "WITH dep AS (" +
            "UPDATE tb_department " +
            "SET is_deleted = true " +
            "WHERE organization_id = ?1 " +
            "RETURNING id" +
            ")" +
            "UPDATE tb_position pos " +
            "SET is_deleted = true " +
//            "FROM dep " +
            "WHERE dep.id = pos.department_id", nativeQuery = true)
    void deleteOrganizationAndRelatedEntities(Long organizationId);

}
