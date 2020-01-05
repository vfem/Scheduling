package org.vfem.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleEntryRepository scheduleEntryRepository;

    /*
    @GetMapping(path="/all")
    public @ResponseBody Iterable<ScheduleEntry> getAllScheduleEntries() {
        // This returns a JSON or XML with the users
        return scheduleEntryRepository.findAll();
    }*/

    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("allEntries", scheduleEntryRepository.findAll());
        return "all";
    }

    @GetMapping("/schedule")
    public String scheduleForm(Model model){
        model.addAttribute("scheduleEntry", new ScheduleEntry());
        return "schedule";
    }

    @PostMapping("/schedule")
    public String scheduleSubmit(@ModelAttribute ScheduleEntry scheduleEntry){
        scheduleEntryRepository.save(scheduleEntry);
        return "result";
    }
}
