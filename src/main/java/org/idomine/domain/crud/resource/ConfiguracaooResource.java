package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Configuracao;
import org.idomine.domain.crud.reporitory.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConfiguracaooResource
{
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @GetMapping("/configuracoes")
    public Iterable<Configuracao> listaAll()
    {
        return configuracaoRepository.findAll();
    }
 
    @PutMapping("/configuracoes/put")
    @Transactional
    public ResponseEntity<Configuracao> update(@RequestBody Configuracao configuracao)
    {
        Configuracao c = configuracaoRepository.save(configuracao);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
 
}