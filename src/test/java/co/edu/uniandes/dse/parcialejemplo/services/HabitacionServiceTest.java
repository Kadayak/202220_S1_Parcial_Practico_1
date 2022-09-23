package co.edu.uniandes.dse.parcialejemplo.services;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HabitacionService.class)

class HabitacionServiceTest {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HabitacionEntity> listaHabitaciones = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        clearData();
        insertData();
    }

    private void clearData() 
    {
        entityManager.getEntityManager().createQuery("Delete from HabitacionEntity").executeUpdate();
    }

    private void insertData()
    {
        for(Integer i = 0; i < 5; i++)
        {
            HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
            entityManager.persist(habitacionEntity);
            listaHabitaciones.add(habitacionEntity); 
        }
    }

    @Test
    void testCreateHabitacion() throws IllegalOperationException
    {
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
        HabitacionEntity result = habitacionService.createHabitacion(newEntity);
        assertNotNull(result);

        HabitacionEntity entity = entityManager.find(HabitacionEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getIdentificacion(), entity.getIdentificacion());
        assertEquals(newEntity.getN_banios(), entity.getN_banios());
        assertEquals(newEntity.getN_camas(), entity.getN_camas());
        assertEquals(newEntity.getN_personas(), entity.getN_personas());
    }
    @Test
    void testAddInvalidHabitacion()
    {
        assertThrows(IllegalOperationException.class, () -> {
            HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
            habitacion.setN_banios(1);
            habitacion.setN_personas(4);
            habitacionService.createHabitacion(habitacion);
        });
    }
}
