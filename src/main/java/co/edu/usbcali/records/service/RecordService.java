package co.edu.usbcali.records.service;

import co.edu.usbcali.records.dto.RecordRequestDTO;
import co.edu.usbcali.records.dto.RecordResponseDTO;

import java.util.List;

public interface RecordService {
    List<String> getRecordsString();
    List<RecordResponseDTO> getRecordsResponseDTO();
    RecordResponseDTO saveRecord(RecordRequestDTO recordRequestDTO) throws Exception;
    RecordResponseDTO updateRecord(Integer id, RecordRequestDTO recordRequestDTO) throws Exception;
    RecordResponseDTO updateRecordPartial(Integer id, RecordRequestDTO recordRequestDTO) throws Exception;
}
