package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByIdAndIsDeletedFalse(Long id);
}
