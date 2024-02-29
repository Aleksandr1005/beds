package ru.sanitas.log.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "SAN_LOG")
@Data
public class Log {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private LocalDateTime dateTime;

    @Column(name = "BED")
    private String bed;

    @Column(name = "PALACE")
    private String palace;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "OBJ_NAME")
    private String object;

}
