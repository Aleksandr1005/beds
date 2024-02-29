package ru.sanitas.log.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sanitas.log.domain.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    @Query("select log " +
            "from Log log " +
            "where (:palace is null or length(:palace) = 0 or log.palace like concat('%', :palace, '%'))" +
            "  and (:bed is null or length(:bed) = 0 or log.bed like concat(:bed,'%'))" +
            "  and (:object is null or length(:object) = 0 or log.object like concat('%', :object,'%'))" +
            "  and (:status is null or length(:status) = 0 or log.status like concat('%', :data,'%')) " +
            "  and (log.dateTime between :dateTimeFrom and :dateTimeTo) " +
            "order by log.dateTime desc ")
    List<Log> findAll(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, String palace, String bed,
                              String object, String status, Pageable pageable);

}
