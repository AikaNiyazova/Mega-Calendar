package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.DepartmentUserDto;
import kg.megacom.megalab.model.entity.DepartmentUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T15:01:09+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class DepartmentUserMapperImpl implements DepartmentUserMapper {

    @Override
    public DepartmentUserDto toDto(DepartmentUser entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentUserDto departmentUserDto = new DepartmentUserDto();

        return departmentUserDto;
    }

    @Override
    public DepartmentUser toEntity(DepartmentUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        DepartmentUser departmentUser = new DepartmentUser();

        return departmentUser;
    }

    @Override
    public List<DepartmentUserDto> toDtoList(List<DepartmentUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DepartmentUserDto> list = new ArrayList<DepartmentUserDto>( entityList.size() );
        for ( DepartmentUser departmentUser : entityList ) {
            list.add( toDto( departmentUser ) );
        }

        return list;
    }

    @Override
    public List<DepartmentUser> toEntityList(List<DepartmentUserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DepartmentUser> list = new ArrayList<DepartmentUser>( dtoList.size() );
        for ( DepartmentUserDto departmentUserDto : dtoList ) {
            list.add( toEntity( departmentUserDto ) );
        }

        return list;
    }
}
