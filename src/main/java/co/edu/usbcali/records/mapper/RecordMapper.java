package co.edu.usbcali.records.mapper;

import co.edu.usbcali.records.dto.RecordRequestDTO;
import co.edu.usbcali.records.domain.Record;
import co.edu.usbcali.records.dto.RecordResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


public class RecordMapper {

    public static Record requestDTOtoEntity(RecordRequestDTO recordRequestDTO) {
        return Record.builder()
                .title(recordRequestDTO.getTitle())
                .artist(recordRequestDTO.getArtist())
                .genre(recordRequestDTO.getGenre())
                .releaseYear(recordRequestDTO.getReleaseYear())
                .price(recordRequestDTO.getPrice())
                .stockQuantity(recordRequestDTO.getStockQuantity())
                .description(recordRequestDTO.getDescription())
                .image(recordRequestDTO.getImage())
                .createdAt(recordRequestDTO.getCreatedAt())
                .updatedAt(recordRequestDTO.getUpdatedAt())
                .build();
    }

    public static RecordResponseDTO EntityToDTO(Record record) {
        return RecordResponseDTO.builder()
                .recordId(record.getRecordId())
                .title(record.getTitle())
                .artist(record.getArtist())
                .genre(record.getGenre())
                .releaseYear(record.getReleaseYear())
                .price(record.getPrice())
                .stockQuantity(record.getStockQuantity())
                .description(record.getDescription())
                .image(record.getImage())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdatedAt())
                .categoryName((record.getCategory() != null) ? record.getCategory().getName() : null)
                .categoryId((record.getCategory() != null) ? record.getCategory().getId() : null)
                .build();
    }

    public static List<RecordResponseDTO> EntityToDTOList(List<Record> records) {
        return records.stream().map(RecordMapper::EntityToDTO).toList();
    }
}
