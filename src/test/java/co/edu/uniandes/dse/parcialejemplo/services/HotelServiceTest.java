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

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelService.class)
class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HotelEntity> listaHoteles = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        clearData();
        insertData();
    }

    private void clearData() 
    {
        entityManager.getEntityManager().createQuery("Delete from HotelEntity").executeUpdate();
    }

    private void insertData()
    {
        for(Integer i = 0; i < 5; i++)
        {
            HotelEntity hotelEntity = factory.manufacturePojo(HotelEntity.class);
            entityManager.persist(hotelEntity);
            listaHoteles.add(hotelEntity); 
        }
    }

    @Test
    void testCreateHotel() throws IllegalOperationException
    {
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
        HotelEntity result = hotelService.createHoteles(newEntity);
        assertNotNull(result);

        HotelEntity entity = entityManager.find(HotelEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getDireccion(), entity.getDireccion());
        assertEquals(newEntity.getNumero_estrellas(), entity.getNumero_estrellas());
    }

    @Test
    void testAddInvalidHotel()
    {
        assertThrows(IllegalOperationException.class, () -> {
            String nombre = listaHoteles.get(1).getNombre();
            HotelEntity hotel = factory.manufacturePojo(HotelEntity.class);
            hotel.setNombre(nombre);
            hotelService.createHoteles(hotel);
        });
    }
    
}
