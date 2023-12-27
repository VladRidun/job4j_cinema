package ru.job4j.cinema.service.file;

import net.jcip.annotations.ThreadSafe;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.mapper.FileMapper;
import ru.job4j.cinema.repository.file.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleFileService implements FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper = Mappers.getMapper(FileMapper.class);

    public SimpleFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(fileMapper
                .getModelFromEntityCustom(fileOptional
                        .get(), readFileAsBytes(fileOptional.get().getPath())));
    }
}
