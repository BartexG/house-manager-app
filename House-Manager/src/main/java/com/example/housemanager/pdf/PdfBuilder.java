package com.example.housemanager.pdf;

import com.example.housemanager.model.entities.Dweller;
import com.example.housemanager.services.DwellerService;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PdfBuilder extends AbstractPdfView{

    @Autowired
    private DwellerService dwellerService;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String[] stringList = (String[]) model.get("stringList");

        Table tableSellerBuyer = new Table(2);

        tableSellerBuyer.addCell(createDefaultCell(""));
        tableSellerBuyer.addCell(createDefaultCell("Miejscowosc, Data:"));
        tableSellerBuyer.addCell(createDefaultCell(""));
        tableSellerBuyer.addCell(createDefaultCell("25.01.2021r."));

        tableSellerBuyer.addCell(createDefaultCell("Sprzedawca:"));
        tableSellerBuyer.addCell(createDefaultCell("Nabywca:"));
        tableSellerBuyer.addCell(createDefaultCell("Kielecka Wspoldzielnia Mieszkaniowa 'House Manager':" ));
        tableSellerBuyer.addCell(createDefaultCell(stringList[1] + " " + stringList[2]));

        tableSellerBuyer.setWidth(100);
        tableSellerBuyer.setPadding(10);

        Table table = new Table(6);

        table.addCell(createDefaultCell("ID mieszkania"));
        table.addCell(createDefaultCell("ID nabywcy"));
        table.addCell(createDefaultCell("Lp."));
        table.addCell(createDefaultCell("Nazwa uslugi"));
        table.addCell(createDefaultCell("Ilosc jednostkowa"));
        table.addCell(createDefaultCell("Cena (PLN)"));

        table.addCell(createDefaultCell(stringList[0]));
        table.addCell(createDefaultCell(stringList[3]));
        table.addCell(createDefaultCell("1"));
        table.addCell(createDefaultCell("Wynajem mieszkania"));
        table.addCell(createDefaultCell("1"));
        table.addCell(createDefaultCell("500zl"));

        table.setWidth(100);
        table.setPadding(9);
        document.add(tableSellerBuyer);
        document.add(table);
    }

    private Cell createDefaultCell(String text) {
        Cell defaultCell = new Cell(text);
        defaultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        defaultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return defaultCell;
    }

    private String getName(Long id) {
        System.out.println("ID: " + id);
        Dweller dweller = this.dwellerService.findOne(id);
        System.out.println("OK");
        return dweller.getName() + " " + dweller.getLastName();
    }

    public String getFlatId(Long id) {
        System.out.println("OK3");
        return String.valueOf(this.dwellerService.getDwellerFlatID(id));
    }
}
