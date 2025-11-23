package com.webflex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflex.model.Tutorial;
import com.webflex.repository.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository repo;

    public Flux<Tutorial> findAll() {
        return repo.findAll();
    }

    public Mono<Tutorial> findById(String id) {
        return repo.findById(id);
    }

    public Flux<Tutorial> findByTitleContaining(String title) {
        return repo.findByTitleContaining(title);
    }

    public Flux<Tutorial> findByPublished(Boolean published) {
        return repo.findByPublished(published);
    }

    public Mono<Tutorial> save(Tutorial t) {
        return repo.save(t);
    }

    public Mono<Tutorial> update(String id, Tutorial t) {
        return repo.findById(id)
                .flatMap(existing -> {
                    existing.setTitle(t.getTitle());
                    existing.setDescription(t.getDescription());
                    existing.setPublished(t.getPublished());
                    return repo.save(existing);
                });
    }

    public Mono<Void> deleteById(String id) {
        return repo.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return repo.deleteAll();
    }
}
