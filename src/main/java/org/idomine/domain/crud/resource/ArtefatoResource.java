package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Artefato;
import org.idomine.domain.crud.reporitory.ArtefatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArtefatoResource
{
    @Autowired
    private ArtefatoRepository artefatoRepository;

    @GetMapping("/artefatos")
    public Iterable<Artefato> listaAll()
    {
        return artefatoRepository.findAll();
    }

    @PostMapping("/artefatos/add")
    @Transactional
    public ResponseEntity<Artefato> add(@RequestBody Artefato artefato)
    {
        Artefato a = artefatoRepository.save(artefato);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PutMapping("/artefatos/put")
    @Transactional
    public ResponseEntity<Artefato> update(@RequestBody Artefato artefato)
    {
        Artefato a = artefatoRepository.save(artefato);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("/artefatos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        artefatoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/artefatos/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(artefatoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/artefatos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(artefatoRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/artefatos/search")
    public ResponseEntity<?> searchByParam( @RequestParam(value="id" ,required=false) Long id, @RequestParam(value="nome" ,required=false) String nome)
    {
        return new ResponseEntity<>(artefatoRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}