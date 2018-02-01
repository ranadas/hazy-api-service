package com.rdas.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
@Data
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer personId;
    private String company;
}
