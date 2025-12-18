package apzo.ApiZoo.service;

import apzo.ApiZoo.dto.UserDTO;
import apzo.ApiZoo.dto.UsuarioResponseDTO;
import apzo.ApiZoo.entity.UserEntity;
import apzo.ApiZoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UsuarioResponseDTO crear(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setNombre(dto.getNombre());
        entity.setContrasena(dto.getContrasena());
        entity = repository.save(entity);
        return mapToResponse(entity);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToResponse(entity);
    }

    public UsuarioResponseDTO actualizar(Long id, UserDTO dto) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        entity.setNombre(dto.getNombre());
        entity.setContrasena(dto.getContrasena());
        entity = repository.save(entity);
        return mapToResponse(entity);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private UsuarioResponseDTO mapToResponse(UserEntity e) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setContrasena(e.getContrasena());
        return dto;
    }
}