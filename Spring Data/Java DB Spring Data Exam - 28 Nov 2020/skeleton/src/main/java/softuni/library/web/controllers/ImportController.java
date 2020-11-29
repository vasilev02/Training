package softuni.library.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.library.services.AuthorService;
import softuni.library.services.BookService;
import softuni.library.services.CharacterService;
import softuni.library.services.LibraryService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final LibraryService libraryService;
    private final CharacterService characterService;

    @Autowired
    public ImportController(AuthorService authorService, BookService bookService, LibraryService libraryService, CharacterService characterService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.libraryService = libraryService;
        this.characterService = characterService;
    }


    /* ============================================================================================================================
     *
     *     JSON
     * */
    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.authorService.areImported(),
                this.bookService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    // Authors
    @GetMapping("/authors")
    public ModelAndView importAuthors() throws IOException {
        String fileContent = this.authorService.readAuthorsFileContent();

        return super.view("json/import-authors", "authors", fileContent);
    }

    @PostMapping("/authors")
    public ModelAndView importAuthorsConfirm() throws IOException, JAXBException {
        System.out.println(this.authorService.importAuthors());
        return super.redirect("/import/json");
    }


    // Books
    @GetMapping("/books")
    public ModelAndView importBooks() throws IOException {
        String fileContent = this.bookService.readBooksFileContent();

        return super.view("json/import-books", "books", fileContent);
    }

    @PostMapping("/books")
    public ModelAndView importBooksConfirm() throws IOException {
        System.out.println(this.bookService.importBooks());
        return super.redirect("/import/json");
    }

    /* ============================================================================================================================
    *
    *     XML
    * */
    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.libraryService.areImported(),
                this.characterService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }

    // Libraries
    @GetMapping("/libraries")
    public ModelAndView importLibraries() throws IOException {
        String fileContent = this.libraryService.readLibrariesFileContent();
        return super.view("xml/import-libraries", "libraries", fileContent);
    }

    @PostMapping("/libraries")
    public ModelAndView importLibrariesConfirm() throws JAXBException, IOException {
        System.out.println(this.libraryService.importLibraries());

        return super.redirect("/import/xml");
    }

    // Characters
    @GetMapping("/characters")
    public ModelAndView importCharacters() throws IOException {
        String fileContent = this.characterService.readCharactersFileContent();

        return super.view("xml/import-characters", "characters", fileContent);
    }

    @PostMapping("/characters")
    public ModelAndView importCharactersConfirm() throws JAXBException, FileNotFoundException, IOException {
        System.out.println(this.characterService.importCharacters());

        return super.redirect("/import/xml");
    }


}
