package wf.garnier.spring.security.seven.mfa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
class TodoController {

	private final Map<String, TodoItem> todos = new HashMap<String, TodoItem>();

	@GetMapping(value = "/todo", produces = MediaType.TEXT_HTML_VALUE)
	public String todoPage() {
		return "todo";
	}

	@GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<TodoItem> todoApi() {
		return todos.values();
	}

	@PostMapping("/todo")
	@ResponseStatus(HttpStatus.CREATED)
	public void addTodo(@RequestParam String text) {
		var todoItem = new TodoItem(text);
		todos.put(todoItem.id(), todoItem);
	}

	record TodoItem(String id, String text) {
		public TodoItem(String text) {
			this(UUID.randomUUID().toString(), text);
		}
	}

}
