package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department, DepartmentDto> {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

}
