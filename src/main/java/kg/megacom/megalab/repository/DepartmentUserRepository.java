package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.DepartmentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentUserRepository extends JpaRepository<DepartmentUser, Long> {
}
