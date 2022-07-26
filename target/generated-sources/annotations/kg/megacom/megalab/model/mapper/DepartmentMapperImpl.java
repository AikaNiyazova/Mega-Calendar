package kg.megacom.megalab.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.entity.Department;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T22:44:38+0600",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto toDto(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        return departmentDto;
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Department department = new Department();

        return department;
    }

    @Override
    public List<DepartmentDto> toDtoList(List<Department> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DepartmentDto> list = new ArrayList<DepartmentDto>( entityList.size() );
        for ( Department department : entityList ) {
            list.add( toDto( department ) );
        }

        return list;
    }

    @Override
    public List<Department> toEntityList(List<DepartmentDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Department> list = new ArrayList<Department>( dtoList.size() );
        for ( DepartmentDto departmentDto : dtoList ) {
            list.add( toEntity( departmentDto ) );
        }

        return list;
    }
}
