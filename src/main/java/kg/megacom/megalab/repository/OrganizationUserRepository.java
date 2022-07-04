package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.OrganizationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, Long> {

    @Query(value = "SELECT user_id FROM tb_organization_user " +
            "WHERE organization_id = ?1 " +
            "GROUP BY user_id " +
            "HAVING count(user_id) = 1", nativeQuery = true)
    List<Long> findAllUserIdsByOrganizationId(Long id);

}
