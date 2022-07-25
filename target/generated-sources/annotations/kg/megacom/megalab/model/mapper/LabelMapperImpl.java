package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.entity.Label;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-24T23:14:25+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class LabelMapperImpl implements LabelMapper {

    @Override
    public LabelDto toDto(Label entity) {
        if ( entity == null ) {
            return null;
        }

        LabelDto.LabelDtoBuilder labelDto = LabelDto.builder();

        labelDto.id( entity.getId() );
        labelDto.labelName( entity.getLabelName() );

        return labelDto.build();
    }

    @Override
    public Label toEntity(LabelDto dto) {
        if ( dto == null ) {
            return null;
        }

        Label.LabelBuilder label = Label.builder();

        label.id( dto.getId() );
        label.labelName( dto.getLabelName() );

        return label.build();
    }

    @Override
    public List<LabelDto> toDtoList(List<Label> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LabelDto> list = new ArrayList<LabelDto>( entityList.size() );
        for ( Label label : entityList ) {
            list.add( toDto( label ) );
        }

        return list;
    }

    @Override
    public List<Label> toEntityList(List<LabelDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Label> list = new ArrayList<Label>( dtoList.size() );
        for ( LabelDto labelDto : dtoList ) {
            list.add( toEntity( labelDto ) );
        }

        return list;
    }
}
