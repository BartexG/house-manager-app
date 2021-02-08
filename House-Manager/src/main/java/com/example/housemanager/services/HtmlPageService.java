package com.example.housemanager.services;

import com.example.housemanager.model.entities.BlockOfFlats;
import com.example.housemanager.model.entities.Dweller;
import com.example.housemanager.model.entities.Flat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtmlPageService {

    public String jsonBlockOfFlatsListReturn(List<BlockOfFlats> blockOfFlatsList) {
        String html = htmlPage() +
                "<h2 class='w3-text-green'>Oto wyniki wyszukiwań!</h2>\n" +
                "<p></p>\n" +
                "<table id='table'>\n" + "<tr>\n" +
                "<th>ID</th>\n" + "<th>Numer bloku</th>\n" + "<th>ID mieszkań</th>\n" + "<th>ID lokatorów</th>\n" + "<th>Lokatorzy</th>\n" +
                "</tr>\n";
        for(int i = 0; i <= blockOfFlatsList.size()-1; i++) {
            String id = String.valueOf(blockOfFlatsList.get(i).getId());
            String blockNumber = String.valueOf(blockOfFlatsList.get(i).getBlockNumber());
            List<Flat> flats = blockOfFlatsList.get(i).getFlats();
            String flatsID = "";
            String dwellersID = "";
            String dwellers = "";
            for(int j = 0; j <= flats.size()-1; j++) {
                flatsID += String.valueOf(flats.get(j).getId());
                if(j != flats.size()-1) {
                    flatsID+=", ";
                }
                List<Dweller> dwellersList = flats.get(j).getDwellers();
                for(int k = 0; k <= dwellersList.size()-1; k++) {
                    dwellersID += String.valueOf(dwellersList.get(k).getId());
                    dwellers += dwellersList.get(k).getName() + " " + dwellersList.get(k).getLastName();
                    if(k != dwellersList.size()) {
                        dwellersID+=", ";
                        dwellers+=", ";
                    }
                }
            }

            html+="<tr>\n" + "<th>" + id + "</th>\n" +
                    "<th>" + blockNumber + "</th>\n" +
                    "<th>" + flatsID + "</th>\n" +
                    "<th>" + dwellersID + "</th>\n" +
                    "<th>" + dwellers + "</th>\n" +
                    "</tr>\n";
        }
        html+="</table>\n" + "</body>\n" + "</html>";
        return html;
    }

    public String notFound() {
        return htmlPage() + "<h2 class='w3-text-green'>Nie znaleziono żadnych pasujących wyników!</h2>";
    }

    public String alreadyPayed() {
        return htmlPage() + "<h2 class='w3-text-green'> Wystąpił błąd! Mieszkanie w tym miesiącu zostało już opłacone. Opłata nie została pobrana.</h2>";
    }

    public String errorDelete() {
        return htmlPage() + "<h2 class='w3-text-green'>Niestety wystąpił błąd przy usuwaniu obiektu z bazy danych! Sprawdź jeszcze raz dane</h2>";
    }

    public String errorInMails() {
        return htmlPage() + "<h2 class='w3-text-green'>Niestety wystąpił błąd podczas wysyłania emaili. Prawdopodobnie jeden z lokatorów nie ma wprowadzonego adresu email.</h2>";
    }

    public String errorCreateDweller() {
        return htmlPage() + "<h2 class='w3-text-green'>Wystąpił błąd! Błędne ID mieszkania</h2>";
    }

    public String errorCreateFlat() {
        return htmlPage() + "<h2 class='w3-text-green'>Wystąpił błąd! Nie znaleziono bloku o podanym numerze</h2>";
    }

    public String errorEdit() {
        return htmlPage() + "<h2 class='w3-text-green'>Wystąpił błąd podczas edycji bazy danych! Sprawdź jeszcze raz dane</h2>";
    }

    public String jsonFlatListReturn(List<Flat> flatList) {
        String html = htmlPage() +
                "<h2 class='w3-text-green'>Oto wyniki wyszukiwań!</h2>\n" +
                "<p></p>\n" +
                 "<table id='table'>\n" + "<tr>\n" +
                "<th>ID</th>\n" + "<th>Numer mieszkania</th>\n" + "<th>Status mieszkania</th>\n" + "<th>Status płatności</th>\n" +
                "<th>Numer bloku</th>\n" + "<th>ID lokatorów</th>\n" + "<th>Lokatorzy</th>\n" +
                "</tr>\n";
        for(int i = 0; i <= flatList.size()-1; i++) {
            String id = String.valueOf(flatList.get(i).getId());
            String flatNumber = String.valueOf(flatList.get(i).getNumber());
            String status = flatList.get(i).getStatus();
            String payed = String.valueOf(flatList.get(i).isPayedForMonth());
            String blockNumber = String.valueOf(flatList.get(i).getBlockOfFlats().getBlockNumber());
            List<Dweller> dwellersList = flatList.get(i).getDwellers();
            String dwellersID = "";
            String dwellers = "";

            for(int j = 0; j <= dwellersList.size()-1; j++) {
                dwellersID += String.valueOf(dwellersList.get(j).getId());
                dwellers += dwellersList.get(j).getName() + " " + dwellersList.get(j).getLastName();

                if(j != dwellersList.size()-1) {
                    dwellersID+=", ";
                    dwellers+=", ";
                }
            }
            html+="<tr>\n" + "<th>" + id + "</th>\n" +
                    "<th>" + flatNumber + "</th>\n" +
                    "<th>" + status + "</th>\n" +
                    "<th>" + payed + "</th>\n" +
                    "<th>" + blockNumber + "</th>\n" +
                    "<th>" + dwellersID + "</th>\n" +
                    "<th>" + dwellers + "</th>\n" +
                    "</tr>\n";
        }
        html+="</table>\n" + "</body>\n" + "</html>";
        return html;
    }

    public String jsonDwellerListReturn(List<Dweller> dwellerList) {
        String html = htmlPage() +
                "<h2 class='w3-text-green'>Oto wyniki wyszukiwań!</h2>\n" +
                "<p></p>\n" +
                 "<table id='table'>\n" + "<tr>\n" +
                "<th>ID</th>\n" + "<th>Imię lokatora</th>\n" + "<th>Nazwisko lokatora</th>\n" + "<th>ID mieszkania</th>\n" +
                "<th>Numer mieszkania</th>\n" + "<th>Numer bloku</th>\n" + "<th>Adres email</th>\n" +
                "</tr>\n";
        for(int i = 0; i <= dwellerList.size()-1; i++) {
            String id = String.valueOf(dwellerList.get(i).getId());
            String name = dwellerList.get(i).getName();
            String lastName = dwellerList.get(i).getLastName();
            String flatID = String.valueOf(dwellerList.get(i).getFlat().getId());
            String flatNumber = String.valueOf(dwellerList.get(i).getFlat().getNumber());
            String blockNumber = String.valueOf(dwellerList.get(i).getFlat().getBlockOfFlats().getBlockNumber());
            String email = String.valueOf(dwellerList.get(i).getEmail());

            html+="<tr>\n" + "<th>" + id + "</th>\n" +
                    "<th>" + name + "</th>\n" +
                    "<th>" + lastName + "</th>\n" +
                    "<th>" + flatID + "</th>\n" +
                    "<th>" + flatNumber + "</th>\n" +
                    "<th>" + blockNumber + "</th>\n" +
                    "<th>" + email + "</th>\n" +
                    "</tr>\n";
        }
        html+="</table>\n" + "</body>\n" + "</html>";
        return html;
    }

    public String pdfInvoice() {
        String html = htmlPage() +
                "<h2 class='w3-text-green'>Dziękujemy za płatność. Oto faktura do wydruku!</h2>\n" +
                "<p></p>\n" +
                "<spring:url value='/generatepdf' var='pdfURL'>\n" +
                "<a href='/generatepdf'>Pobierz fakturę</a></br>\n";
        return html;
    }

    public String emailsSend() {
        String html = htmlPage() + "<h2 class='w3-text-green'>Pomyślnie wysłano emaile do osób z zaległymi płatnościami</h2>";
        return html;
    }


    private String htmlPage() {
        return "<html>\n" + "<header><title>HM-House Manager-Search Results</title></header>\n" +
                "<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>\n" +
                "<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Raleway'>\n" +
                "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>\n" +
                "<style>\n" + "body,h1,h2,h3,h4,h5,h6 {font-family: 'Raleway', Arial, Helvetica, sans-serif}\n" +
                "table, th, td {border: 1px solid black;border-collapse: collapse}\n" + "</style>\n" +
                "<body class='w3-content w3-border-left w3-border-right'>\n"+
                "<nav class='w3-sidebar w3-light-grey w3-collapse w3-top' style='z-index:3;width:260px' id='mySidebar'>\n" +
                "<div class='w3-container w3-display-container w3-padding-16'>\n" +
                "<h3>Menu strony</h3>\n" +
                "<div class='w3-bar-block'>\n" +
                "<form method='get' action='/'> \n" +
                "<button class='w3-bar-item w3-button w3-padding-16'><i class='fa fa-home'></i> Powrót na stronę główną</button>\n" +
                "</form>\n" +
                "<form method='get' action='/id-search'>\n" +
                "<button class='w3-bar-item w3-button w3-padding-16'><i class='fa fa-search'></i> Wyszukaj po ID</button>\n" +
                "</form>\n" +
                "<form method='get' action='/search'>\n" +
                "<button class='w3-bar-item w3-button w3-padding-16'><i class='fa fa-search'></i> Wyszukaj po danych</button>\n" +
                "</form>\n" +
                "</div>\n" +
                "</nav>\n" +
                "<div class='w3-main w3-white' style='margin-left:260px'>" +
                "<div class='w3-container' id='apartment'>";
    }

    public String htmlCreated() {
        return htmlPage()+"<h2 class='w3-text-green'>Pomyślnie dodano obiekt do bazy danych!</h2>\n" +
                "<p></p>\n";
    }
    public String htmlDeleted() {
        return htmlPage()+"<h2 class='w3-text-green'>Pomyślnie usunięto obiekt z bazy danych!</h2>\n" +
                "<p></p>\n";
    }
    public String htmlEdited() {
        return htmlPage()+"<h2 class='w3-text-green'>Pomyślnie edytowano obiekt w bazie danych!</h2>\n" +
                "<p></p>\n";
    }
}
