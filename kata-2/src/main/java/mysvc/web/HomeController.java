package mysvc.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyustiz on 10/29/18 for project mysvc.
 */
@RestController
class HomeController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
