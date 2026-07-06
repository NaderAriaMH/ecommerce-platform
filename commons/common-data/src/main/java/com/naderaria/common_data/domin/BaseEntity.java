package com.naderaria.common_data.domin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence-generator")
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    public BaseEntity(Long id){
        setId(id);
    }

}
