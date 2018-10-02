package com.brewingmadscientists.plc.repositories;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "modbusAddress")
@Entity
@Table(name = "plc_registers")
public class RegisterEntity {
    @Id
    private long id;
    private String description;
    private int modbusAddress;
    private String type;
}
