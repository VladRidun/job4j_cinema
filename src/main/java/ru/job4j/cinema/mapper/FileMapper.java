package ru.job4j.cinema.mapper;

import org.mapstruct.Mapper;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.model.File;

@Mapper(componentModel = "spring")
public interface FileMapper {
    default FileDto getModelFromEntityCustom(File file, byte[] content) {
        FileDto fileDto = new FileDto();
        fileDto.setName(file.getName());
        fileDto.setContent(content);
        return fileDto;
    }
}
