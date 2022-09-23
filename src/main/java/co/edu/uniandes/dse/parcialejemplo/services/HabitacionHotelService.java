package co.edu.uniandes.dse.parcialejemplo.services;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public HotelEntity createHabitacion(Long idHabitacion, Long idHotel) throws EntityNotFoundException
    {
        log.info("Inicia el proceso de asociarle una habitacion al hotel de id = {0}", idHotel);
        Optional<HabitacionEntity> habitacionEntity = habitacionRepository.findById(idHabitacion);  
        Optional<HotelEntity> hotelEntity =  hotelRepository.findById(idHotel);

        if(habitacionEntity.isEmpty())
        {
            throw new EntityNotFoundException("La habitacion con el id = {" + Long.toString(idHabitacion) + "} no existe.");
        }

        if(hotelEntity.isEmpty())
        {
            throw new EntityNotFoundException("El hotel con el id = {" + Long.toString(idHotel) + "} no existe.");
        }

        hotelEntity.get().getHabitaciones().add(habitacionEntity.get());
        log.info("Termina el proceso de asociarle una habitacion al hotel de id = {0}", idHotel);
        return hotelEntity.get();
    }
    
}
