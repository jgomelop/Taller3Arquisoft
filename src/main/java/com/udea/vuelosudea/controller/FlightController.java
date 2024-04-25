package com.udea.vuelosudea.controller;

import com.udea.vuelosudea.exception.InvalidRating;
import com.udea.vuelosudea.exception.ModelNotFoundException;
import com.udea.vuelosudea.model.Flight;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.udea.vuelosudea.service.FlightService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
@CrossOrigin("*")
@Api(value = "Sistema de gestion de vuelos", description = "Operaciones para los vuelos")
public class FlightController {

    @Autowired
    FlightService flightService;

    @ApiOperation(value = "Add Vuelo")
    @PostMapping("/save")
    public long save(
            @ApiParam(value = "Objeto vuelo que se guarda en la BD", required = true)
            @RequestBody Flight flight
    ) throws InvalidRating {
        if (flight.getRating() > 5) throw new InvalidRating("Id debe ser menor o igual a 5");
        flightService.save(flight);
        return flight.getIdFlight();
    }

    @ApiOperation(value = "Ver la lista de vuelos disponibles", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de vuelos exitosa"),
            @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
            @ApiResponse(code=403, message = "El acceso al recurso que usted intenta alcanzar es prohibido"),
            @ApiResponse(code=404, message = "El acceso al recurso que usted intenta alcanzar no se encuentra")

    })
    @GetMapping("/listAll")
    public Iterable<Flight> listAllFlights(){return flightService.list();}

    @ApiOperation(value = "Obtener el vuelo por un ID")
    @GetMapping("/list/{id}")
    public Flight listFlightById(
            @ApiParam(value = "El Id del vuelo que se desea consultar", required = true)
            @PathVariable("id") int id){
        Optional<Flight> flight = flightService.listId(id);
        if(flight.isPresent()){

            return flight.get();
        }
        throw new ModelNotFoundException("ID de vuelo Invalido");
    }

    @ApiOperation(value = "Dame los mejores vuelos")
    @GetMapping("/topFlights")
    public ResponseEntity<List<Flight>> viewBestFlights(){
        List<Flight> list= flightService.viewBestFlight();
        return new ResponseEntity<List<Flight>>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public Flight updateFlight(@RequestBody Flight flight){
        return flightService.update(flight);
    }

    @DeleteMapping("{id}")
    public String deleteFlight(@PathVariable long id){
        return flightService.delete(id);
    }
}