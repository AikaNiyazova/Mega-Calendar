package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.request.CreateLabelRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabelService {

    LabelDto create(CreateLabelRequest request);

    LabelDto findById(Long id);

    List<LabelDto> findAll();

//    List<LabelDto> findAllByUserId(Long userId); //todo: think later!

    LabelDto update(LabelDto labelDto);

    MessageResponse delete(Long id);

    LabelDto save(LabelDto labelDto);

}
