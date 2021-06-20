package com.exercise.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PBX")
public class Pbx extends com.exercise.core.entities.Pbx {
    //In order to have only one PBX table
}
