package mqt.spring.example.controller;

/**
 * Created by wahrons on 25/03/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SimplePage {

    @Autowired
    @Qualifier("cache")
    private List<String> messages;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String listOfMessages() {
        return String.join("\n", messages);
    }

}
