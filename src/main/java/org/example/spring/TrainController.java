package org.example.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.db.database;
import java.util.List;
import java.util.Map;


@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/train")
    public String getTrains(Model model) {
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        return "trains";
    }
  @PostMapping("/executeQuery")
  public String executeQuery(@RequestParam("sqlQuery") String sqlQuery, Model model) {
     List<Map<String, Object>> trains = database.executeQuery(sqlQuery);
      model.addAttribute("trains", trains);
    return "trains";
   }

}
