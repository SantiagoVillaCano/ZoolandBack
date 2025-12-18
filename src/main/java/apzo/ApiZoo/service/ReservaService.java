package apzo.ApiZoo.service;

import apzo.ApiZoo.dto.ReservaDTO;
import apzo.ApiZoo.dto.ReservaResponseDTO;
import apzo.ApiZoo.entity.ReservaEntity;
import apzo.ApiZoo.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaResponseDTO crear(ReservaDTO dto) {
        ReservaEntity entity = new ReservaEntity();
        entity.setFechaIng(dto.getFechaIng());
        entity.setFechaComp(dto.getFechaComp() != null ? dto.getFechaComp() : java.time.LocalDate.now());
        entity.setNumPersonas(dto.getNumPersonas());
        entity.setValorPorPersona(dto.getValorPorPersona());
        entity.setEmail(dto.getEmail());
        // valor_total se calcula automáticamente en MySQL (columna STORED)

        entity = repository.save(entity);

        return mapToResponse(entity);
    }

    public List<ReservaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ReservaResponseDTO buscarPorId(Long id) {
        ReservaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return mapToResponse(entity);
    }

    public ReservaResponseDTO actualizar(Long id, ReservaDTO dto) {
        ReservaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        entity.setFechaIng(dto.getFechaIng());
        entity.setFechaComp(dto.getFechaComp());
        entity.setNumPersonas(dto.getNumPersonas());
        entity.setValorPorPersona(dto.getValorPorPersona());
        entity.setEmail(dto.getEmail());
        // valor_total vuelve a calcularse en BD

        entity = repository.save(entity);
        return mapToResponse(entity);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private ReservaResponseDTO mapToResponse(ReservaEntity e) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setIdReserva(e.getIdReserva());
        dto.setFechaIng(e.getFechaIng());
        dto.setFechaComp(e.getFechaComp());
        dto.setNumPersonas(e.getNumPersonas());
        dto.setValorPorPersona(e.getValorPorPersona());
        dto.setValorTotal(e.getValorTotal());
        dto.setEmail(e.getEmail());
        return dto;
    }
}