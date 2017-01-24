package demo.spring.jpa.project.web.controller;

import demo.spring.jpa.config.StringTrimmerEditor;
import demo.spring.jpa.devel.service.DeveloperService;
import demo.spring.jpa.domain.Developer;
import demo.spring.jpa.domain.Project;
import demo.spring.jpa.project.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ashrafhasan
 * @since 1/23/17
 */
@Controller
@RequestMapping("/project")
@SessionAttributes(value = ProjectController.COMMAND_NAME)
public class ProjectController {

    protected static final String COMMAND_NAME = "project";

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private ProjectService projectService;

    @InitBinder(COMMAND_NAME)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam("develId") long develId, ModelMap modelMap) {

        Developer developer = developerService.find(develId);
        /* This list will be accessed after developer get detached.
           So we need to access it before it get detached. It can also be done using Hibernate.initialize*/
        developer.getFeatureList().size();
        modelMap.addAttribute(COMMAND_NAME, new Project(developer));

        return "project-name";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam("id") long id, ModelMap modelMap) {

        Project project = projectService.find(id);
        /* This list will be accessed after developer get detached.
           So we need to access it before it get detached. It can also be done using developer.getFeatureList().size()*/
        Hibernate.initialize(project.getDeveloper().getFeatureList());
        modelMap.addAttribute(COMMAND_NAME, project);

        return "project-name";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {

        modelMap.addAttribute("projectList", projectService.search());

        return "projectList";
    }

    @RequestMapping(value = "/index", params = "_action_show_desc", method = RequestMethod.POST)
    public String showDescription(@ModelAttribute(COMMAND_NAME) @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project-name";
        }

        return "project-desc";
    }

    @RequestMapping(value = "/index", params = "_action_save", method = RequestMethod.POST)
    public String save(@ModelAttribute(COMMAND_NAME) @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project-desc";
        }

        projectService.save(project);

        return "redirect:/form/devel/list";
    }

    @RequestMapping(value = "/index", params = "_action_update", method = RequestMethod.POST)
    public String update(@ModelAttribute(COMMAND_NAME) @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project-desc";
        }

        projectService.update(project);

        return "redirect:/form/devel/list";
    }
}
