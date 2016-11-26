package com.vvs.training.hospital.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.library.datamodel.Author;
import com.epam.training.library.services.AuthorService;
import com.epam.training.library.web.model.AuthorModel;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Inject
    private AuthorService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AuthorModel>> getAll() {
        List<Author> all = service.getAll();

        List<AuthorModel> converted = new ArrayList<>();
        for (Author author : all) {
            converted.add(entity2model(author));
        }

        return new ResponseEntity<List<AuthorModel>>(converted,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public ResponseEntity<AuthorModel> getById(
            @PathVariable Long authorId) {
        Author author = service.get(authorId);
        return new ResponseEntity<AuthorModel>(entity2model(author),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createNewAuthor(
            @RequestBody AuthorModel authorModel) {
        service.create(model2entity(authorModel));
        return new ResponseEntity<Void>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{authorId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateAuthor(
            @RequestBody AuthorModel authorModel,
            @PathVariable Long authorId) {
        Author author = model2entity(authorModel);
        author.setId(authorId);
        service.update(author);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long authorId) {
        service.delete(authorId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    private AuthorModel entity2model(Author author) {
        AuthorModel e = new AuthorModel();
        e.setFirstName(author.getFirstName());
        e.setId(author.getId());
        e.setLastName(author.getLastName());
        return e;
    }

    private Author model2entity(AuthorModel authorModel) {
        Author e = new Author();
        e.setFirstName(authorModel.getFirstName());
        e.setId(authorModel.getId());
        e.setLastName(authorModel.getLastName());
        return e;
    }

}
