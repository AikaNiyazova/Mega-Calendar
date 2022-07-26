package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.entity.Position;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T22:44:38+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class PositionMapperImpl implements PositionMapper {

    @Override
    public PositionDto toDto(Position entity) {
        if ( entity == null ) {
            return null;
        }

        PositionDto positionDto = new PositionDto();

        return positionDto;
    }

    @Override
    public Position toEntity(PositionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Position position = new Position();

        return position;
    }

    @Override
    public List<PositionDto> toDtoList(List<Position> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PositionDto> list = new ArrayList<PositionDto>( entityList.size() );
        for ( Position position : entityList ) {
            list.add( toDto( position ) );
        }

        return list;
    }

    @Override
    public List<Position> toEntityList(List<PositionDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Position> list = new ArrayList<Position>( dtoList.size() );
        for ( PositionDto positionDto : dtoList ) {
            list.add( toEntity( positionDto ) );
        }

        return list;
    }
}
