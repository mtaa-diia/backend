package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.DocumentRepo;
import com.doklad.api.customers.repo.UserRepo;
import com.doklad.api.customers.utility.enums.StatusType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class DocumentDataService {

    private final Faker faker;

    private final UserDataService userDataService;
    private final DocumentRepo documentRepo;

    @Autowired
    public DocumentDataService(Faker faker, UserDataService userDataService, DocumentRepo documentRepo, UserRepo userRepo){
        this.faker = faker;
        this.userDataService = userDataService;
        this.documentRepo = documentRepo;
    }

    @Transactional
    public List<Document> generateDocumentNumber(int count) {

        long countUsers = userDataService.count();
        long number = faker.number().numberBetween(1, countUsers);
        List<Document> documents = new ArrayList<>();

        Optional<User> user = userDataService.findById(number);


        IntStream.range(0, count).forEach(i -> {
            String content = faker.lorem().paragraph();
            String description = faker.lorem().sentence();
            String title = faker.lorem().sentence();
            Status status = new Status(StatusType.PENDING);
            Document document = new Document();

            while (description.length() >= 255 || title.length() >= 255)
                title = faker.lorem().sentence();

            while (description.length() >= 999)
                description = faker.lorem().sentence();

            document = new Document(content, description, title, status, user.get());
            documents.add(document);
        });

        documents.forEach(document -> document.setUser(user.get()));
        user.get().setDocuments(documents);
        userDataService.update(user.get());


        return documents;
    }

    @Transactional
    public void save(Document document) {
        document.setStatus(new Status(StatusType.PENDING));
        document.setCreatedAt(new Date());
        document.setUpdatedAt(new Date());

        documentRepo.save(document);
    }
}
