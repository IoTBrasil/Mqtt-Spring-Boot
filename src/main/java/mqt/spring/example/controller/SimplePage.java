package mqt.spring.example.controller;

/**
 * Created by wahrons on 25/03/17.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SimplePage {

    private List<String> messages;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String listOfMessages() {
        return String.join("\n", messages);
    }

}
