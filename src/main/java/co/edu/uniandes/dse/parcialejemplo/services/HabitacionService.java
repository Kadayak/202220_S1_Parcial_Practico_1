package co.edu.uniandes.dse.parcialejemplo.services;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    @Transactional
    public HabitacionEntity createHabitacion (HabitacionEntity habitacion) throws IllegalOperationException
    {
        log.info("Inicia proceso de crear una habitacion.");
        if(habitacion.getN_personas() >= habitacion.getN_banios())
        {
            throw new IllegalOperationException("La habitacion no cumple el requerimiento de n_personas y n_banios.");
        }
        else
        {
            return repository.save(habitacion);
        }
    }
    
}
