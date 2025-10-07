package cs544;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs544.message.Book;

@Controller
public class BookController {

	@Resource
	private BookService bookService;

	@GetMapping("/")
	public String redirectRoot() {
		return "redirect:/books";
	}

	@GetMapping("/books")
	public String getAll(Model model) {
		model.addAttribute("books", bookService.getAll());
		return "bookList";
	}

	@PostMapping("/books")
	public String add(@Valid Book book, BindingResult result, 
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
			flash.addFlashAttribute("book", book);
			return "redirect:/books/add";
		} else {
			bookService.add(book);
			return "redirect:/books";
		}
	}

	@GetMapping("/books/add")
	public String viewAdd(Model model) {
		model.addAttribute("msg", "Add");
		if (!model.containsAttribute("book")) {
			model.addAttribute("book", new Book());
		}
		return "bookDetail";
	}

	@GetMapping("/books/{id}")
	public String get(@PathVariable int id, Model model) {
		if (!model.containsAttribute("book")) {
			model.addAttribute("book", bookService.get(id));
		}
		model.addAttribute("msg", "Update");
		return "bookDetail";
	}

	@PostMapping("/books/{id}")
	public String update(@Valid Book book, BindingResult result,  
			@PathVariable int id, RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
			flash.addFlashAttribute("book", book);
			return "redirect:/books/{id}";
		} else {
			bookService.update(book); 
			return "redirect:/books";
		}
	}

	@PostMapping("/books/delete")
	public String delete(int bookId) {
		bookService.delete(bookId);
		return "redirect:/books";
	}
}
