package kg.megacom.megalab.model.mapper;

import kg.megacom.megalab.model.dto.DepartmentUserDto;
import kg.megacom.megalab.model.entity.DepartmentUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentUserMapper extends BaseMapper<DepartmentUser, DepartmentUserDto> {

    DepartmentUserMapper INSTANCE = Mappers.getMapper(DepartmentUserMapper.class);

}
