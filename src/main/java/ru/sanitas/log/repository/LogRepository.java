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
            "where (:action is null or length(:action) = 0 or log.action like concat('%', :action, '%'))" +
            "  and (:table is null or length(:table) = 0 or log.table like concat(:table,'%'))" +
            "  and (:object is null or length(:object) = 0 or log.object like concat('%', :object,'%'))" +
            "  and (:data is null or length(:data) = 0 or log.data like concat('%', :data,'%')) " +
            "  and (:employee is null or length(:employee) = 0 or log.employee like concat(:employee, '%')) " +
            "  and (:recordId is null or log.recordId = :recordId) " +
            "  and (log.dateTime between :dateTimeFrom and :dateTimeTo) " +
            "order by log.dateTime desc ")
    List<Log> findAll(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo, String action, String table,
                              String object, String employee, String data, Long recordId, Pageable pageable);

    @Query("select log " +
            "from Log log " +
            "where (log.table like 'Пациенты' and log.data like '%PRIMEHANIE%' and log.recordId = :patientId)" +
            "   or (log.table like 'Примечание о долге' and log.recordId = :debtId) " +
            " and (log.dateTime >= getdate() - 1900) " +
            "order by log.dateTime desc")
    List<Log> findNotes(Long patientId, Long debtId, Pageable pageable);

    @Query("select log " +
            "from Log log " +
            "where log.table like 'Отправитель документов' and log.object like :object% " +
            "and (log.dateTime >= getdate() - 1900) " +
            "order by log.dateTime desc")
    List<Log> findEmails(String object);
}
