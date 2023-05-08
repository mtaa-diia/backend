package com.doklad.api.customers.mappers;


import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.models.Document;
import com.doklad.api.developers.v1.dto.UserDataDTOId;
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
        DocumentDTO documentDTO = modelMapper.map(document, DocumentDTO.class);
        documentDTO.setUser(new UserDataDTOId(document.getUser().getId()));
        return documentDTO;
    }

    public Document convertToEntity(DocumentDTO documentDTO) {
        return modelMapper.map(documentDTO, Document.class);
    }
}
