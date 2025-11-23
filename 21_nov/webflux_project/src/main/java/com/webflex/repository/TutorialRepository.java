package com.webflex.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.webflex.model.Tutorial;
import reactor.core.publisher.Flux;

public interface TutorialRepository extends ReactiveMongoRepository<Tutorial, String> {
    Flux<Tutorial> findByTitleContaining(String title);
    Flux<Tutorial> findByPublished(boolean published);
}
