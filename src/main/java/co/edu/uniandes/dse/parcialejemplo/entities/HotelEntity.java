package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Getter
@Setter
@Entity
public class HotelEntity extends BaseEntity{

    private String nombre;

    private String direccion;

    private Integer numero_estrellas;

    private Long id;

    @PodamExclude
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.PERSIST)
    private List<HabitacionEntity> habitaciones = new ArrayList<>();
    
}
