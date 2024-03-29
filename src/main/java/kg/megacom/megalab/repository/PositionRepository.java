package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByIdAndIsDeletedFalse(Long id);

    List<Position> findAllByDepartmentId(Long departmentId);

//    @Query(value = "SELECT * FROM tb_position " +
//            "WHERE department_id = ? " +
//            "AND is_deleted = false", nativeQuery = true)
//    List<Position> findAllByDepartmentIdAndIsDeletedFalse(Long departmentId);

    @Modifying
    @Query(value = "UPDATE tb_position " +
            "SET department_id = ?2 " +
            "WHERE department_id = ?1", nativeQuery = true)
    void changeDepartmentInPosition(Long oldDepartmentId, Long newDepartmentId);

    @Modifying
    @Query(value = "UPDATE tb_position " +
            "SET is_deleted = true " +
            "WHERE department_id = ?1", nativeQuery = true)
    void deletePositionsByDepartmentId(Long departmentId);

}
