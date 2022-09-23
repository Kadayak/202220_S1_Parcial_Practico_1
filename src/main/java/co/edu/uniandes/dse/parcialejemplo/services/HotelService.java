package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Transactional
    public HotelEntity createHoteles(HotelEntity hotel) throws IllegalOperationException 
    {
        log.info("Inicia proceso de crear una hotel.");
        List<HotelEntity> existehotel = repository.findByNombre(hotel.getNombre());
        if(!existehotel.isEmpty())
        {
            throw new IllegalOperationException("Error: ya existe una hotel con ese nombre");
        }
        else if(hotel.getNumero_estrellas() < 2 || hotel.getNumero_estrellas() > 6)
        {
            throw new IllegalOperationException("El hotel no tiene un numero valido de estrellas");
        }
        else
        {
            log.info("Se ha creado exitosamente la hotel " + hotel.getNombre());
            return repository.save(hotel);
        }

    }
    
}
