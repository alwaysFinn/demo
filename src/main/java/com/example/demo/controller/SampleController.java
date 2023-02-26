package com.example.demo.controller;

import com.example.demo.dto.SampleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @GetMapping("/ex1")
    public  void ex1() {
        log.info("ex1...................................");
    }

    @GetMapping({"/ex2", "/exLink"})
    public void exModel(Model model){
        List<SampleDto> list = IntStream.rangeClosed(1, 20).asLongStream().
                mapToObj(i -> {
                    SampleDto dto = SampleDto.builder()
                            .sno(i)
                            .first("First.." + i)
                            .last("last.." + i)
                            .regTime(LocalDateTime.now())
                            .build();
                    return dto;
                }).collect(Collectors.toList());
        model.addAttribute("list", list);
    }

    @GetMapping({"/exInline"})
    public String exInline(RedirectAttributes redirectAttributes){

        log.info("exInline.........................");

        SampleDto dto = SampleDto.builder()
                .sno(100L)
                .first("First..100")
                .last("Last..100")
                .regTime(LocalDateTime.now())
                .build();
        redirectAttributes.addFlashAttribute("result", "success");//일회성 즉 성공했을때만 (낭비 X)
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";
    }

    @GetMapping("/ex3")
    public void ex3() {
        log.info("ex3");
    }

    @GetMapping("/exLayout1")
    public void exLayout1(){
        log.info("exLayout................");
    }


}