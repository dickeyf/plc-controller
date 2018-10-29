package com.brewingmadscientists.plc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "name")
public class Component {
    private String name;
    private String type;
    private String description;

    @JsonIgnore
    private Map<String, Control> controls = new HashMap<>();
    @JsonIgnore
    private Map<String, Probe> probes = new HashMap<>();
    @JsonIgnore
    private Map<String, Component> components = new HashMap<>();

    public void addComponent(String ref, Component component) {
        components.put(ref, component);
    }

    public void addControl(String ref, Control control) {
        controls.put(ref, control);
    }

    public void addProbe(String ref, Probe probe) {
        probes.put(ref, probe);
    }

    public Component getComponent(String ref) {
        return components.get(ref);
    }

    public Control getControl(String ref) {
        return controls.get(ref);
    }

    public Probe getProbe(String ref) {
        return probes.get(ref);
    }
}
