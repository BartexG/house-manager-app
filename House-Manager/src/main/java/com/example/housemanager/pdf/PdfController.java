package com.example.housemanager.pdf;

import com.example.housemanager.services.DwellerService;
import com.example.housemanager.services.FlatsService;
import com.example.housemanager.services.HtmlPageService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
public class PdfController {

    private Long id = 26L;

    @Autowired
    private DwellerService dwellerService;

    @Autowired
    private FlatsService flatsService;

    @Autowired
    private HtmlPageService htmlPageService;

    @GetMapping(value = "/generatepdf")
    public ModelAndView invoice() throws DocumentException, FileNotFoundException {
        String[] model = new String[4];
        model[0] = this.dwellerService.findOne(id).getFlat().getId().toString();
        model[1] = this.dwellerService.findOne(id).getName();
        model[2] = this.dwellerService.findOne(id).getLastName();
        model[3] = id.toString();
        return new ModelAndView(new PdfBuilder(),"stringList", model);
    }

    @PostMapping(value = "/payed-{id}")
    public String paid(@RequestParam(value = "id") Long id) throws IOException {
        setId(id);
        if(this.dwellerService.findOne(id).getFlat().isPayedForMonth() == true) {
            return this.htmlPageService.alreadyPayed();
        }
        else {
            this.flatsService.editPayStatus(this.dwellerService.findOne(id).getFlat().getId(),true);
            return this.htmlPageService.pdfInvoice();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }
}
