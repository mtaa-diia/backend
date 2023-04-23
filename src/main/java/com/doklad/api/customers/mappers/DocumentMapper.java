package com.doklad.api.customers.mappers;


import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.models.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public DocumentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DocumentDTO convertToDto(Document document) {
        return modelMapper.map(document, DocumentDTO.class);
    }

    public Document convertToEntity(DocumentDTO documentDTO) {
        return modelMapper.map(documentDTO, Document.class);
    }
}
