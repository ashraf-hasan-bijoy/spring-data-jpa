package demo.spring.jpa.devel.web.controller;

import demo.spring.jpa.config.StringTrimmerEditor;
import demo.spring.jpa.devel.service.DeveloperService;
import demo.spring.jpa.domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ashrafhasan
 * @since 1/16/17
 */
@Controller
@RequestMapping("/devel")
@SessionAttributes(value = DeveloperController.COMMAND_NAME)
public class DeveloperController {

    protected static final String COMMAND_NAME = "devel";

    @Autowired
    private DeveloperService developerService;

    @InitBinder(COMMAND_NAME)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {

        modelMap.addAttribute(COMMAND_NAME, new Developer());
        return "createDevel";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam("id") long id, ModelMap modelMap) {

        modelMap.addAttribute(COMMAND_NAME, developerService.find(id));
        return "showDevel";
    }

    @RequestMapping(value = "/index", params = "_action_save", method = RequestMethod.POST)
    public String save(@ModelAttribute(COMMAND_NAME) @Valid Developer developer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "createDevel";
        }

        developerService.save(developer);

        return "redirect:/form/devel/list";
    }

    @RequestMapping(value = "/index", params = "_action_update", method = RequestMethod.POST)
    public String update(@ModelAttribute(COMMAND_NAME) @Valid Developer developer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "showDevel";
        }

        developerService.update(developer);

        return "redirect:/form/devel/list";
    }

    @RequestMapping(value = "/index", params = "_action_delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute(COMMAND_NAME) Developer developer) {

        developerService.delete(developer);

        return "redirect:/form/devel/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {

        modelMap.addAttribute("develList", developerService.search());

        return "develList";
    }
}
