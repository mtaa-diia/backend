package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.UserRepo;
import com.doklad.api.customers.utility.enums.StatusType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class DocumentDataService {

    private final Faker faker;
    private final UserRepo userRepo;

    @Autowired
    public DocumentDataService(Faker faker, UserRepo userRepo) {
        this.faker = faker;
        this.userRepo = userRepo;
    }

    @Transactional
    public List<Document> generateDocumentNumber(int count) {
        int number = faker.number().numberBetween(1, 1000);
        List<Document> documents = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        Optional<User> user = userRepo.findById((long) number);

        if(user.isEmpty())
            return Collections.emptyList();

        IntStream.range(1, count).forEach( i -> {
            String content = faker.lorem().paragraph();
            String description = faker.lorem().sentence();
            String title = faker.lorem().sentence();
            Status status = new Status(StatusType.PENDING);


            while (description.length() >= 255 || title.length() >= 255)
                title = faker.lorem().sentence();

            while (description.length() >= 999)
                description = faker.lorem().sentence();

            Document document = new Document(content, description, title, status, user.get());
            documents.add(document);
        });

        return documents;
    }
}
