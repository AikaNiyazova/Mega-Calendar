package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.entity.Label;
import kg.megacom.megalab.model.mapper.LabelMapper;
import kg.megacom.megalab.model.request.CreateLabelRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.LabelRepository;
import kg.megacom.megalab.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public LabelDto create(CreateLabelRequest request) {
        Label label = Label
                .builder()
                .labelName(request.getLabelName())
                .build();
        return LabelMapper.INSTANCE.toDto
                (labelRepository.save(label));
    }

    @Override
    public LabelDto findById(Long id) {
        return LabelMapper.INSTANCE.toDto
                (labelRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException
                                ("Label with id=" + id + " not found")));
    }

    @Override
    public LabelDto update(LabelDto labelDto) {
        return labelRepository.findById(labelDto.getId())
                .map(label -> {
                    label.setLabelName(labelDto.getLabelName());
                    labelRepository.save(label);

                return LabelMapper.INSTANCE.toDto(label);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Label with id=" + labelDto.getId() + " not found"));
    }

    @Override
    public MessageResponse delete(Long id) {
        labelRepository.deleteById(id);
        return MessageResponse.of("Label with id=" + id + " is deleted");
    }

    @Override
    public LabelDto save(LabelDto labelDto) {
        return null;
    }
}
