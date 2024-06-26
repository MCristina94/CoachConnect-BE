package com.digitalhouse.CoachConnectBE.controller.tutoria;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.NuevoTutoriaDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.TutoriaDisponibilidadDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.TutoriaResultadoDto;
import com.digitalhouse.CoachConnectBE.entity.DiaReservado;
import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import com.digitalhouse.CoachConnectBE.service.ITutoriaService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(RoutePaths.TUTORIA)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class TutoriaController {
    public static final int CANTIDAD_POSIBLE_DE_IMAGENES = 5;
    private final ITutoriaService tutoriaService;

    @GetMapping()
    public ResponseEntity<List<TutoriaResultadoDto>> listar() {
        return ResponseEntity.ok(tutoriaService.listarTodos()
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @PostMapping()
    public ResponseEntity<TutoriaResultadoDto> guardar(@RequestBody NuevoTutoriaDto nuevoTutoriaDto) {
        log.debug("Se recibio: " + nuevoTutoriaDto.getNombre() + " para guardar en tutoria");

        if (nuevoTutoriaDto.getFotos().size() != CANTIDAD_POSIBLE_DE_IMAGENES) {
            return ResponseEntity.badRequest().body(null);
        }

        Tutoria tutoriaux = Mapper.map(nuevoTutoriaDto);

        Tutoria tutoria = tutoriaService.guardar(tutoriaux);
        return ResponseEntity.ok(Mapper.map(tutoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutoriaResultadoDto> actualizar(@RequestBody NuevoTutoriaDto tutoriaDto, @PathVariable Long id) {
        log.info("Se recibio tutoria para actualizar el tutoria con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        if (tutoriaDto.getFotos().size() != CANTIDAD_POSIBLE_DE_IMAGENES) {
            return ResponseEntity.badRequest().body(null);
        }

        Tutoria tutoria = Mapper.map(tutoriaDto, id);
        tutoria = tutoriaService.actualizar(tutoria);
        return ResponseEntity.ok(Mapper.map(tutoria));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el tutoria con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        tutoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<TutoriaResultadoDto>> obtenerTutoriasDisponibles(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        List<Tutoria> tutoriasDisponibles = tutoriaService.obtenerTutoriasDisponibles(fechaInicio, fechaFin);

        return ResponseEntity.ok(tutoriasDisponibles
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutoriaDisponibilidadDto> obtenerDisponibilidadTutoria(@PathVariable Long id) {
        Pair<Tutoria, List<DiaReservado>> tutoriaConDisponibilidad = tutoriaService.obtenerTutoriaConDisponibilidad(id);

        return ResponseEntity.ok().body(Mapper.map(tutoriaConDisponibilidad.a, tutoriaConDisponibilidad.b));
    }
}