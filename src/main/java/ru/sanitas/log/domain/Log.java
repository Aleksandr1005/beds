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

    @Column(name = "ACTION")
    private String action;

    @Column(name = "TABLE_NAME")
    private String table;

    @Column(name = "MEDECINS_NAME")
    private String employee;

    @Column(name = "REC_ID")
    private Long recordId;

    @Column(name = "OBJ_NAME")
    private String object;

    @Column(name = "LOG_DATA")
    private String data;
}
