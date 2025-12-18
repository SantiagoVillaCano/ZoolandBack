package apzo.ApiZoo.service;

import apzo.ApiZoo.dto.AnimalDTO;
import apzo.ApiZoo.dto.AnimalResponseDTO;
import apzo.ApiZoo.entity.AnimalEntity;
import apzo.ApiZoo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalResponseDTO crear(AnimalDTO dto) {
        AnimalEntity entity = new AnimalEntity();
        entity.setNombreAnimal(dto.getNombreAnimal());
        entity.setDetalles(dto.getDetalles());
        entity.setDescripcion(dto.getDescripcion());
        entity.setHabitad(dto.getHabitad());
        entity.setClima(dto.getClima());
        entity.setImg(dto.getImg());

        entity = repository.save(entity);
        return mapToResponse(entity);
    }

    public List<AnimalResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AnimalResponseDTO buscarPorId(Long id) {
        AnimalEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        return mapToResponse(entity);
    }

    public AnimalResponseDTO actualizar(Long id, AnimalDTO dto) {
        AnimalEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        entity.setNombreAnimal(dto.getNombreAnimal());
        entity.setDetalles(dto.getDetalles());
        entity.setDescripcion(dto.getDescripcion());
        entity.setHabitad(dto.getHabitad());
        entity.setClima(dto.getClima());
        entity.setImg(dto.getImg());

        entity = repository.save(entity);
        return mapToResponse(entity);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private AnimalResponseDTO mapToResponse(AnimalEntity e) {
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setIdAnimal(e.getIdAnimal());
        dto.setNombreAnimal(e.getNombreAnimal());
        dto.setDetalles(e.getDetalles());
        dto.setDescripcion(e.getDescripcion());
        dto.setHabitad(e.getHabitad());
        dto.setClima(e.getClima());
        dto.setImg(e.getImg());

        return dto;
    }
}