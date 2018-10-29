package com.brewingmadscientists.plc.model;

import com.brewingmadscientists.plc.model.modbus.Register;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "name")
public class Control {
    private String name;
    private String type;
    private Register register;
}
