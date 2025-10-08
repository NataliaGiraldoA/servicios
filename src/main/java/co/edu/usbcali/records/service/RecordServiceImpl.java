package co.edu.usbcali.records.service;

import co.edu.usbcali.records.domain.Category;
import co.edu.usbcali.records.domain.Record;
import co.edu.usbcali.records.dto.RecordRequestDTO;
import co.edu.usbcali.records.dto.RecordResponseDTO;
import co.edu.usbcali.records.mapper.RecordMapper;
import co.edu.usbcali.records.repository.CategoryRepository;
import co.edu.usbcali.records.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> getRecordsString() {
        List<Record> records = recordRepository.findAll();
        List<String> recordsString = new ArrayList<>();
        for (Record record : records) {
            recordsString.add(record.getTitle());
        }
        return recordsString;
    }


    @Override
    @Transactional(readOnly = true)
    public List<RecordResponseDTO> getRecordsResponseDTO() {
        return RecordMapper.EntityToDTOList(recordRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RecordResponseDTO saveRecord(RecordRequestDTO recordRequestDTO) throws Exception {
        //validacion de negocio, que ninguno de los campos sea nulo

        if(recordRequestDTO == null){
            throw new Exception("RecordRequestDTO is null");
        }
        if(recordRequestDTO.getTitle() == null ||  recordRequestDTO.getTitle().isBlank()){
            throw new Exception("RecordRequestDTO.title is null");
        }
        if(recordRequestDTO.getArtist() == null ||  recordRequestDTO.getArtist().isBlank()){
            throw new Exception("RecordRequestDTO.artist is null");
        }
        if(recordRequestDTO.getGenre() == null ||  recordRequestDTO.getGenre().isBlank()){
            throw new Exception("RecordRequestDTO.genre is null");
        }
        if(recordRequestDTO.getReleaseYear() == null){
            throw new Exception("RecordRequestDTO.releaseYear is null");
        }
        if(recordRequestDTO.getPrice() == null || recordRequestDTO.getPrice() <= 0){
            throw new Exception("RecordRequestDTO.price is null or less than or equal to 0");
        }
        if(recordRequestDTO.getStockQuantity() == null || recordRequestDTO.getStockQuantity() < 0){
            throw new Exception("RecordRequestDTO.stockQuantity is null or less than 0");
        }


        //Otras validaciones
        // Validación de dependencia con Category
        // Validar si existe la categoria por ID
        Category category = categoryRepository.findById(recordRequestDTO.getCategoryId())
                .orElseThrow( () -> new Exception("Category not found"));

        //Convertir a entity desde el requestDTO
        Record record = RecordMapper.requestDTOtoEntity(recordRequestDTO);

        //Agregar la categoria al record para que sea guardado
        record.setCategory(category);

        //persistir el record en la base de datos
        record = recordRepository.save(record);

        return RecordMapper.EntityToDTO(record);

    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RecordResponseDTO updateRecord(Integer id, RecordRequestDTO recordRequestDTO) throws Exception {
        if (id == null) {
            throw new Exception("Record id is required");
        }
        Record existingRecord = recordRepository.findById(id)
                .orElseThrow(() -> new Exception("Record not found with id: " + id));

        existingRecord.setTitle(recordRequestDTO.getTitle());
        existingRecord.setArtist(recordRequestDTO.getArtist());
        existingRecord.setGenre(recordRequestDTO.getGenre());
        existingRecord.setReleaseYear(recordRequestDTO.getReleaseYear());
        existingRecord.setPrice(recordRequestDTO.getPrice());
        existingRecord.setStockQuantity(recordRequestDTO.getStockQuantity());
        existingRecord.setDescription(recordRequestDTO.getDescription());
        existingRecord.setImage(recordRequestDTO.getImage());
        existingRecord.setUpdatedAt(LocalDateTime.now());
        existingRecord.setCategory(categoryRepository.findById(recordRequestDTO.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found with id: " + recordRequestDTO.getCategoryId())));

        Record updatedRecord = recordRepository.save(existingRecord);
        return RecordMapper.EntityToDTO(updatedRecord);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RecordResponseDTO updateRecordPartial(Integer id, RecordRequestDTO recordRequestDTO) throws Exception {
        Record existingRecord = recordRepository.findById(id)
                .orElseThrow(() -> new Exception("Record not found with id: " + id));
        if(recordRequestDTO.getTitle() != null){
            existingRecord.setTitle(recordRequestDTO.getTitle());
        }
        if(recordRequestDTO.getArtist() != null){
            existingRecord.setArtist(recordRequestDTO.getArtist());
        }
        if(recordRequestDTO.getGenre() != null){
            existingRecord.setGenre(recordRequestDTO.getGenre());
        }
        if(recordRequestDTO.getReleaseYear() != null){
            existingRecord.setReleaseYear(recordRequestDTO.getReleaseYear());
        }
        if(recordRequestDTO.getPrice() != null){
            existingRecord.setPrice(recordRequestDTO.getPrice());
        }
        if(recordRequestDTO.getStockQuantity() != null){
            existingRecord.setStockQuantity(recordRequestDTO.getStockQuantity());
        }
        if(recordRequestDTO.getDescription() != null){
            existingRecord.setDescription(recordRequestDTO.getDescription());
        }
        if(recordRequestDTO.getImage() != null){
            existingRecord.setImage(recordRequestDTO.getImage());
        }
        if(recordRequestDTO.getCategoryId() != null){
            existingRecord.setCategory(categoryRepository.findById(recordRequestDTO.getCategoryId())
                    .orElseThrow(() -> new Exception("Category not found with id: " + recordRequestDTO.getCategoryId())));
        }
        existingRecord.setUpdatedAt(LocalDateTime.now());
        Record updatedRecord = recordRepository.save(existingRecord);
        return RecordMapper.EntityToDTO(updatedRecord);
    }
}
