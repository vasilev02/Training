package softuni.library.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.library.services.CharacterService;


@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final CharacterService characterService;

    public ExportController(CharacterService characterService) {

        this.characterService = characterService;
    }


    @GetMapping("/characters-by-criteria")
    public ModelAndView exportEmployeesByCriteria(){

        String characters = this.characterService.findCharactersInBookOrderedByLastNameDescendingThenByAge();
        return super.view("export/characters-by-criteria.html","characters", characters);
    }
}
