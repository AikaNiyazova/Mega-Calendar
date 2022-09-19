package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findAllByUserId(Long userId);

}
