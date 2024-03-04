package com.example.segundoejercicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class ClienteController {

    @Autowired
    private ClienteRepository repositorio;


    //INSERTAR UN NUEVO CLIENTE
    @PostMapping("/insertar")
    public Cliente nuevo(@RequestBody Cliente cliente){

        return repositorio.save(cliente);

    }


    //DEVUELVE UN SOLO CLIENTE A TRAVÉS DE SU ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id){
        if( repositorio.existsById(id)){
            return new ResponseEntity<Cliente>( repositorio.findById(id).get(), HttpStatus.OK );
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }


    //DEVOLVER CLIENTES POR ESTADO ACTIVO Y VENTAS MAYORES A PARÁMETROS
    @GetMapping("/{estado}")
    public List<Cliente> getClienteByEstadoAndTotal(@PathVariable String estado,@PathVariable Double total){


        return null;
    }


    //ESTADISTICAS
    @GetMapping("/estadisticas")
    public String estadisticas() {

        double total = repositorio.calcularTotalVentas();
        double promedio = repositorio.calcularPromedioVentas();
        long inactivos = repositorio.contarTotalMayorCeroInactivos();

        return "El numero total de ventas es: " + total + "\n" +
                "El promedio de las ventas es: " + promedio + "\n" +
                "Clientes inactivos con ventas mayores a cero: " + inactivos;
    }


}
