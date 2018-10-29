package com.brewingmadscientists.plc.repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Address")
    private String plcAddress;
    @JsonProperty("Modbus Address")
    private String modbusAddress;
    @JsonProperty("Address Comment")
    private String description;
    @JsonProperty("Data Type")
    private String type;
    @JsonProperty("Function Code")
    private String functionCode;
    @JsonProperty("Nickname")
    private String nickName;
    @JsonProperty("Initial Value")
    private String initialValue;
    @JsonProperty("Retentive")
    private String retentive;

}
