package org.vfem.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleEntryRepository scheduleEntryRepository;

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

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        ScheduleEntry entry = scheduleEntryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("entry", entry);
        return "update-schedule";
    }

    @PostMapping("/update/{id}")
    public String updateEntry(@PathVariable("id") Integer id, @ModelAttribute ScheduleEntry scheduleEntry, Model model){
        scheduleEntryRepository.save(scheduleEntry);
        model.addAttribute("allEntries", scheduleEntryRepository.findAll());
        return "all";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable("id") Integer id, Model model) {
        ScheduleEntry user = scheduleEntryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        scheduleEntryRepository.delete(user);
        model.addAttribute("allEntries", scheduleEntryRepository.findAll());
        return "all";
    }

}
