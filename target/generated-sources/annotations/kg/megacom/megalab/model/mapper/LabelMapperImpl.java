package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.entity.Label;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T22:44:38+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class LabelMapperImpl implements LabelMapper {

    @Override
    public LabelDto toDto(Label entity) {
        if ( entity == null ) {
            return null;
        }

        LabelDto labelDto = new LabelDto();

        return labelDto;
    }

    @Override
    public Label toEntity(LabelDto dto) {
        if ( dto == null ) {
            return null;
        }

        Label label = new Label();

        return label;
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
