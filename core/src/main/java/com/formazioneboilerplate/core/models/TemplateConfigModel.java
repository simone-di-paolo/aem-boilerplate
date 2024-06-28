package com.formazioneboilerplate.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.formazioneboilerplate.core.service.TemplateConfigurationsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TemplateConfigModel {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateConfigModel.class);

    @Inject
    private Page currentPage;

    @Inject
    private TemplateConfigurationsService templateConfigurationsService;

    private String nameTemplate;
    private List<String> listTemplates;
    private List<String> risultato;

    @PostConstruct
    protected void init() {
        LOG.info("## Template Config Model - init started ###");
        risultato = new ArrayList<String>();
        PageManager pageManager = currentPage.getPageManager();
        nameTemplate = currentPage.getTemplate().getProperties().get("jcr:title", String.class);
        listTemplates = templateConfigurationsService.getTemplateConfigPaths();
        for (String s : listTemplates) {
            if (s.equals(nameTemplate)) {
                Iterator<Page> children1 = currentPage.getAbsoluteParent(1).listChildren();
                while(children1.hasNext()){
                    Page child1 = children1.next();
                    risultato.add(child1.getName());
                    Iterator<Page> children2 = currentPage.getAbsoluteParent(2).listChildren();
                    while(children2.hasNext()) {
                        Page child2 = children2.next();
                        risultato.add(child2.getName());
                        Iterator<Page> children3 = currentPage.getAbsoluteParent(3).listChildren();
                        while (children3.hasNext()) {
                            Page child3 = children3.next();
                            risultato.add(child3.getName());
                        }
                    }
                    break;
                }
                break;
            }else{
                LOG.info("## Template not found ##");
                //stampa="Error";
            }
        }
        LOG.info("## Template Config Model - init finished ###");
    }
    public String getNameTemplate() {
        return nameTemplate;
    }

    public List<String> getListTemplates() {
        return listTemplates;
    }

    public List<String> getRisultato() {
        return risultato;
    }

}

/*for(Iterator<Page> children = currentPage.getAbsoluteParent(1).listChildren(); children.hasNext();) {
                   Page child = children.next();
                   risultato.add(child.getName());
                }*/
