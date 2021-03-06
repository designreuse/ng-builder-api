/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
 
package org.idomine.domain.crud.resource;

<#if artefato.hasDateType() >
import java.util.Date;
</#if>

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.${artefato.className};

<#list artefato.elementos as field >
<#if field.persistence >
<#if field.selectDB() >
import org.idomine.domain.crud.model.${field.nome};
import org.idomine.domain.crud.repository.${field.nome}Repository;
</#if>
</#if>
</#list>	

import org.idomine.domain.crud.repository.${artefato.className}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
public class ${artefato.className}Resource
{
    @Autowired
    private ${artefato.className}Repository ${artefato.classFolder}Repository;

    <#list artefato.elementos as field >
    <#if field.persistence >
    <#if field.selectDB() >
    @Autowired
    private ${field.nome}Repository ${field.nome?lower_case}Repository;
    </#if>
    </#if>
    </#list>	    

    @GetMapping("/${artefato.resourceName}")
    public Iterable<${artefato.className}> listaAll()
    {
        return ${artefato.classFolder}Repository.findAll();
    }

    @GetMapping("/${artefato.resourceName}/count")
    public Long count()
    {
        return ${artefato.classFolder}Repository.count();
    }

    @PostMapping("/${artefato.resourceName}")
    @Transactional
    public ResponseEntity<${artefato.className}> add(@RequestBody ${artefato.className} obj)
    {
        <#list artefato.elementos as field >
        <#if field.persistence >
        <#if field.selectDB() >
        ${"if"} ( obj.get${field.nome}() != null )
        obj.set${field.nome}(${field.nome?lower_case }Repository.findById(obj.get${field.nome}().getId()).get());
        </#if>
        </#if>
        </#list>	    
        ${artefato.className} newObj = ${artefato.classFolder}Repository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/${artefato.resourceName}")
    @Transactional
    public ResponseEntity<${artefato.className}> update(@RequestBody ${artefato.className} obj)
    {
        <#list artefato.elementos as field >
        <#if field.persistence >
        <#if field.selectDB() >
        ${"if"} ( obj.get${field.nome}() != null )
        obj.set${field.nome}(${field.nome?lower_case}Repository.findById(obj.get${field.nome}().getId()).get());
        </#if>
        </#if>
        </#list>	    
        ${artefato.className} newObj = ${artefato.classFolder}Repository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/${artefato.resourceName}/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        ${artefato.classFolder}Repository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/${artefato.resourceName}/{id}")
    public ${artefato.className} searchId(@PathVariable Long id)
    {
        return ${artefato.classFolder}Repository.findById(id).get();
    }

  <#list artefato.elementos as field >
  	<#if field.persistence >
  	<#if field.notSelectDB() >
    @GetMapping("/${artefato.resourceName}/search/${field.nome}/{${field.nome}}")
    public ResponseEntity<?> searchPathVariable${field.nome?cap_first}(@PathVariable <#if field.selectDB() > ${field.nome} <#else> ${field.tipoField} </#if> ${field.nome})
    {
        return new ResponseEntity<>(${artefato.classFolder}Repository.findBy${field.nome?cap_first}(${field.nome}), HttpStatus.OK);
    }
    </#if>
    </#if>
  </#list>	

}
