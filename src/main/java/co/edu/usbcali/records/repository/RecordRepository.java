package co.edu.usbcali.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.usbcali.records.domain.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record,Integer> {
}
