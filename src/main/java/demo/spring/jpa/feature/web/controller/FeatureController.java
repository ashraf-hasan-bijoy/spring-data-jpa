package demo.spring.jpa.feature.web.controller;

import demo.spring.jpa.config.StringTrimmerEditor;
import demo.spring.jpa.devel.service.DeveloperService;
import demo.spring.jpa.domain.Developer;
import demo.spring.jpa.domain.Feature;
import demo.spring.jpa.feature.service.FeatureService;
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
@RequestMapping("/feature")
@SessionAttributes(value = FeatureController.COMMAND_NAME)
public class FeatureController {

    protected static final String COMMAND_NAME = "feature";

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private FeatureService featureService;

    @InitBinder(COMMAND_NAME)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam("develId") long develId, ModelMap modelMap) {

        Developer developer = developerService.find(develId);
        modelMap.addAttribute(COMMAND_NAME, new Feature(developer));

        return "feature";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam("id") long id, ModelMap modelMap) {
        modelMap.addAttribute(COMMAND_NAME, featureService.find(id));

        return "feature";
    }

    @RequestMapping(value = "/index", params = "_action_save", method = RequestMethod.POST)
    public String save(@ModelAttribute(COMMAND_NAME) @Valid Feature feature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "feature";
        }

        featureService.save(feature);

        return "redirect:/form/devel/list";
    }

    @RequestMapping(value = "/index", params = "_action_update", method = RequestMethod.POST)
    public String update(@ModelAttribute(COMMAND_NAME) @Valid Feature feature, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "feature";
        }

        featureService.update(feature);

        return "redirect:/form/devel/list";
    }

    @RequestMapping(value = "/index", params = "_action_delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute(COMMAND_NAME) Feature feature) {
        featureService.delete(feature);

        return "redirect:/form/devel/list";
    }
}
