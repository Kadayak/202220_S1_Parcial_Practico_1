package co.edu.uniandes.dse.parcialejemplo.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public HabitacionEntity addHabitacion(Long idHabitacion, Long idHotel)
    {
        log.info("Inicia el proceso de asociarle una habitacion al hotel de id = {0}", idHotel);
    }
    
}
