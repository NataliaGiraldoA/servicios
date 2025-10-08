package co.edu.usbcali.records.controller;


import co.edu.usbcali.records.dto.CategoryRequestDTO;
import co.edu.usbcali.records.dto.RecordRequestDTO;
import co.edu.usbcali.records.dto.RecordResponseDTO;
import co.edu.usbcali.records.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/record")
@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/all")
    public List<String> getRecordsString(){
        return recordService.getRecordsString();
    }

    @GetMapping("/all/object")
    public List<RecordResponseDTO> getRecordsResponseDTO(){
        return recordService.getRecordsResponseDTO();
    }

    @PostMapping("/create")
    public ResponseEntity<RecordResponseDTO> saveRecord(@RequestBody RecordRequestDTO recordRequestDTO) throws Exception {
        return new  ResponseEntity<>(recordService.saveRecord(recordRequestDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<RecordResponseDTO> updateRecord(
            @PathVariable Integer id,
            @RequestBody RecordRequestDTO recordRequestDTO
    ) throws Exception {
        return new ResponseEntity<>(recordService.updateRecord(id, recordRequestDTO), HttpStatus.OK);
    }
    @PatchMapping("/updatePartial/{id}")
    public ResponseEntity<RecordResponseDTO> updateRecordPartial(
            @PathVariable Integer id,
            @RequestBody RecordRequestDTO recordRequestDTO
    ) throws Exception {
        return new ResponseEntity<>(recordService.updateRecordPartial(id, recordRequestDTO), HttpStatus.OK);
    }
}
