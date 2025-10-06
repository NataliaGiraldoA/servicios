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
        //Otras validaciones
        // Validación de dependencia con Category
        // Validar si existe la categoria por ID
        Category category = categoryRepository.findById(recordRequestDTO.getCategoryId()).orElseThrow( () -> new Exception("Category not found"));

        //Convertir a entity desde el requestDTO
        Record record = RecordMapper.requestDTOtoEntity(recordRequestDTO);

        //Agregar la categoria al record para que sea guardado
        record.setCategory(category);

        //persistir el record en la base de datos
        record = recordRepository.save(record);

        return RecordMapper.EntityToDTO(record);

    }
}
