package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.request.CreateLabelRequest;
import org.springframework.stereotype.Service;

@Service
public interface LabelService {

    LabelDto create(CreateLabelRequest request);

    LabelDto findById(Long id);

    LabelDto update(LabelDto labelDto);

    void delete(Long id);

    LabelDto save(LabelDto labelDto);

}
