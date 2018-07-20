package org.idomine.domain.crud.service;

import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_APP_PROPERTIES;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_ENTITY;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_POM;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_README;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_REPOSITORY;
import static org.idomine.domain.crud.service.helper.TemplateBackendHelper.BACKEND_RESOURCE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.idomine.domain.crud.model.Artefato;
import org.idomine.domain.crud.model.Elemento;
import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.model.vo.TipoField;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.idomine.domain.crud.service.helper.FreeMarkerEngine;
import org.idomine.domain.crud.service.helper.GeradorCrudHelper;
import org.idomine.domain.crud.service.helper.TemplateBackendHelper;
import org.idomine.domain.crud.service.helper.TemplateImportsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerationService
{
    @Autowired
    private FreeMarkerEngine freeMarkerEngine;
    @Autowired
    private ProjetoRepository projetoRepository;

    public void backendAllToOutput(Long id)
    {
        Projeto projeto = projetoRepository.findById(id).get();
        backendAllToOutput(projeto);
    }

    public void backendAllToOutput(Projeto projeto)
    {
        readmeToOutput(projeto);
        backendPomToOutput(projeto);
        backendReadmeToOutput(projeto);
        backendAppPropertiesToOutput(projeto);
        backendApplicationToOutput(projeto);
        backendEntityToOutput(projeto);
        
        frontReadmeToOutput(projeto);
        frontJsonsToOutput(projeto);
    }

    private void frontJsonsToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_ANGULAR_JSON, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_ANGULAR_JSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_DBJSON, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_DBJSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_PACKAGE_JSON, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_PACKAGE_JSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_TSCONFIG_JSON, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_TSCONFIG_JSON, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_TSLINT_JSON, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_TSLINT_JSON, null));
        
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_INDEX, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_INDEX, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_TSCONFIG_SPEC, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_TSCONFIG_SPEC, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_TSCONFIG_APP, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_TSCONFIG_APP, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_TEST, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_TEST, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_STYLE, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_STYLE, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_INDEX, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_INDEX, null));
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_MAIN, freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_MAIN, null));

        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY+"config.service.ts", freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY+"config.service.ts", model(projeto) ));

        frontendSecurityFiles(projeto);
        frontendPages(projeto);
        frontendShared(projeto);
        fromendAppModule(projeto);
        
        frontendErp(projeto);
    }

    private void frontendErp(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {
                String art = artefato.getClassName();
                String arq = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_ERP + art+"/"+art + ".ts";
                //criar dir ante de criar arq...
                //GeradorCrudHelper.output(arq, backendEntityToString(artefato));
            }
        } 
        
    }

    private void fromendAppModule(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP;
        try
        {
            GeradorCrudHelper.copyFile( new File(o+"app.module.ts"), new File(d+"app.module.ts"));
            GeradorCrudHelper.copyFile( new File(o+"app-rotas.module.ts"), new File(d+"app-rotas.module.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    private void frontendShared(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_SHARED;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_SHARED;
        try
        {
            GeradorCrudHelper.copyFile( new File(o+"material.module.ts"), new File(d+"material.module.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void frontendPages(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_PAGES;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_PAGES;
        try
        {
            GeradorCrudHelper.copyFile( new File(o+"base/base.component.html"), new File(d+"base/base.component.html"));
            GeradorCrudHelper.copyFile( new File(o+"base/base.component.ts"), new File(d+"base/base.component.ts"));

            GeradorCrudHelper.copyFile( new File(o+"erro/erro.component.html"), new File(d+"erro/erro.component.html"));
            GeradorCrudHelper.copyFile( new File(o+"erro/erro.component.css"), new File(d+"erro/erro.component.css"));
            GeradorCrudHelper.copyFile( new File(o+"erro/erro.component.ts"), new File(d+"erro/erro.component.ts"));

            GeradorCrudHelper.copyFile( new File(o+"home/home.component.html"), new File(d+"home/home.component.html"));
            GeradorCrudHelper.copyFile( new File(o+"home/home.component.css"), new File(d+"home/home.component.css"));
            GeradorCrudHelper.copyFile( new File(o+"home/home.component.ts"), new File(d+"home/home.component.ts"));

            GeradorCrudHelper.copyFile( new File(o+"login/login.component.html"), new File(d+"login/login.component.html"));
            GeradorCrudHelper.copyFile( new File(o+"login/login.component.scss"), new File(d+"login/login.component.scss"));
            GeradorCrudHelper.copyFile( new File(o+"login/login.component.ts"), new File(d+"login/login.component.ts"));

            GeradorCrudHelper.copyFile( new File(o+"sidenav/sidenav.component.css"), new File(d+"sidenav/sidenav.component.css"));
            GeradorCrudHelper.copyFile( new File(o+"sidenav/sidenav.component.ts"), new File(d+"sidenav/sidenav.component.ts"));

            GeradorCrudHelper.copyFile( new File(o+"sobre/sobre.component.html"), new File(d+"sobre/sobre.component.html"));
            GeradorCrudHelper.copyFile( new File(o+"sobre/sobre.component.css"), new File(d+"sobre/login.component.css"));
            GeradorCrudHelper.copyFile( new File(o+"sobre/sobre.component.ts"), new File(d+"sobre/sobre.component.ts"));
}
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void frontendSecurityFiles(Projeto projeto)
    {
        String d = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY;
        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_INFRA_SECURITY;
        try
        {
            GeradorCrudHelper.copyFile( new File(o+"admin.guard.ts"), new File(d+"admin.guard.ts"));
            GeradorCrudHelper.copyFile( new File(o+"auth.guard.ts"), new File(d+"auth.guard.ts"));
            GeradorCrudHelper.copyFile( new File(o+"authentication.service.ts"), new File(d+"authentication.service.ts"));
            GeradorCrudHelper.copyFile( new File(o+"guest.guard.ts"), new File(d+"guest.guard.ts"));
            GeradorCrudHelper.copyFile( new File(o+"index.ts"), new File(d+"index.ts"));
            GeradorCrudHelper.copyFile( new File(o+"jwt.interceptor.ts"), new File(d+"jwt.interceptor.ts"));
            GeradorCrudHelper.copyFile( new File(o+"message.service.ts"), new File(d+"message.service.ts"));
            GeradorCrudHelper.copyFile( new File(o+"resource.service.ts"), new File(d+"resource.service.ts"));
            GeradorCrudHelper.copyFile( new File(o+"user.service.ts"), new File(d+"user.service.ts"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void frontReadmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.FRONTEND_README, frontendReadmeToString(projeto));
    }

    private String frontendReadmeToString(Projeto projeto)
    {
        return freeMarkerEngine.process(TemplateBackendHelper.FRONTEND_README, model(projeto));
    }

    private void backendApplicationToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_APPLICATION, backendApplicationToString(projeto));
    }

    public String backendApplicationToString(Projeto projeto)
    {
        return freeMarkerEngine.process(TemplateBackendHelper.BACKEND_APPLICATION, model(projeto));
    }

    public void backendEntityToOutput(Projeto projeto)
    {
        if (projeto.getArtefatos() != null)
        {
            for (Artefato artefato : projeto.getArtefatos())
            {
                String arq = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_ENTITY_PATH + artefato.getClassName() + ".java";
                String rep = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_REPOSITORY_PATH + artefato.getClassName() + "Repository.java";
                String res = projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_RESOURCE_PATH + artefato.getClassName() + "Resource.java";
                GeradorCrudHelper.output(arq, backendEntityToString(artefato));
                GeradorCrudHelper.output(rep, backendRepositoryToString(artefato));
                GeradorCrudHelper.output(res, backendResourceToString(artefato));
            }
        }
    }

    public String backendEntityToString(Artefato artefato)
    {
        Map<String, Object> model = model(artefato);
        model.put("import", imports(artefato));
        return freeMarkerEngine.process(BACKEND_ENTITY, model);
    }

    public String backendEntityToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        Map<String, Object> model = model(artefato);
        model.put("import", imports(artefato));
        return freeMarkerEngine.process(BACKEND_ENTITY, model);
    }

    public String backendRepositoryToString(Artefato artefato)
    {
        return freeMarkerEngine.process(BACKEND_REPOSITORY, model(artefato));
    }

    public String backendRepositoryToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return freeMarkerEngine.process(BACKEND_REPOSITORY, model(artefato));
    }

    public String backendResourceToString(Artefato artefato)
    {
        return freeMarkerEngine.process(BACKEND_RESOURCE, model(artefato));
    }

    public String backendResourceToString(Projeto projeto, Long artefatoId)
    {
        Artefato artefato = projeto.getArtefatos().get(artefatoId.intValue());
        return freeMarkerEngine.process(BACKEND_RESOURCE, model(artefato));
    }

    public void backendPomToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_POM, backendPomToString(projeto));
    }

    public String backendPomToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_POM, model(projeto));
    }

    public void readmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.README, readmeToString(projeto));
    }

    public String readmeToString(Projeto projeto)
    {
        return freeMarkerEngine.process(TemplateBackendHelper.README, model(projeto));
    }

    public void backendReadmeToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_README, backendReadmeToString(projeto));
    }

    public String backendReadmeToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_README, model(projeto));
    }

    public void backendAppPropertiesToOutput(Projeto projeto)
    {
        GeradorCrudHelper.output(projeto.getOutputDirectory() + "/" + TemplateBackendHelper.BACKEND_APP_PROPERTIES, backendAppPropertiesToString(projeto));
    }

    public String backendAppPropertiesToString(Projeto projeto)
    {
        return freeMarkerEngine.process(BACKEND_APP_PROPERTIES, model(projeto));
    }

    private Map<String, Object> model(Object o)
    {
        Map<String, Object> model = new HashMap<>();
        model.put(o.getClass().getSimpleName().toLowerCase(), o);
        return model;
    }

    private Object imports(Artefato artefato)
    {
        boolean iDate = false;

        if (artefato.getElementos() != null)
            for (Elemento e : artefato.getElementos())
            {
                iDate = (e.getTipoField() == TipoField.Date || e.getTipoField() == TipoField.Time || e.getTipoField() == TipoField.DateTime);
            }

        TemplateImportsHelper importe = TemplateImportsHelper
                .builder()
                .idate(iDate)
                .ilist(false).build();
        return importe;
    }

}
