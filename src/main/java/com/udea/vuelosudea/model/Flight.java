package com.udea.vuelosudea.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(description = "Todos los detalles del vuelo")
@Entity
public class Flight implements Serializable {

    @ApiModelProperty(notes = "La BD genera el Id del vuelo")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @Column(name = "idFlight")
    private Long idFlight;

    @ApiModelProperty(notes = "Nombre del Avion")
    @Column(name = "nombreAvion", nullable = false, length = 80)
    private @NonNull String nombreAvion;

    @ApiModelProperty(notes = "Numero del vuelo")
    @Column(name = "numeroVuelo", nullable = false, length = 80)
    private @NonNull String numeroVuelo;

    @ApiModelProperty(notes = "Aeropuerto Origen")
    @Column(name = "origen", nullable = false, length = 80)
    private @NonNull String origen;

    @ApiModelProperty(notes = "Aeropuerto Destino")
    @Column(name = "destino", nullable = false, length = 80)
    private @NonNull String destino;

    @ApiModelProperty(notes = "Numero de sillas")
    @Column(name = "capacidad", nullable = false, length = 20)
    private @NonNull int capacidad;

    @ApiModelProperty(notes = "Rating")
    @Column(name = "rating", nullable = false, length = 80)
    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    @Max(value = 5, message = "El valor debe ser mayor o igual a 5")
    private @NonNull int rating;

    @ApiModelProperty(notes = "Plan de Vuelo")
    @Column(name = "planvuelo", nullable = false, length = 80)
    private @NonNull long planvuelo;

    @ApiModelProperty(notes = "Cumplido")
    private Boolean cumplido;
}