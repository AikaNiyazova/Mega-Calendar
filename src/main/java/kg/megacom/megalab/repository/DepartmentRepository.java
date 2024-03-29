package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByIdAndIsDeletedFalse(Long id);

    List<Department> findAllByOrganizationId(Long organizationId);

//    @Query(value = "SELECT * FROM tb_department " +
//            "WHERE organization_id = ? " +
//            "AND is_deleted = false", nativeQuery = true)
//    List<Department> findAllByOrganizationIdAndIsDeletedFalse(Long organizationId);

}
