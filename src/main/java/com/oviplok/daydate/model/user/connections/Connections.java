package com.oviplok.daydate.model.user.connections;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Map;

@Data
@Embeddable
public class Connections {
    private Map<String, Boolean> left;
    private Map<String, String> matches;
    private Map<String, Boolean> right;
}
