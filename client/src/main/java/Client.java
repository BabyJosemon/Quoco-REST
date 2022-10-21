import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class Client {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ClientInfo> request2 = new HttpEntity<>(clients[0]);
        for(int i=0;i< clients.length;i++){
            HttpEntity<ClientInfo> request = new HttpEntity<>(clients[i]);
            ClientApplication clientApplication = new ClientApplication();
            clientApplication = restTemplate.postForObject("http://localhost:8084/applications", request, ClientApplication.class);
            displayProfile(clients[i]);
            for(Quotation quotation:clientApplication.getQuotations()){
                displayQuotation(quotation);
            }
        }
        /*String url="";
        List<String> ports = new ArrayList<String>();
        ports.add("8081");//Auldfellas
        ports.add("8082");//DodgyDrivers
        ports.add("8083");//GirlPower
        for(int i=0;i< ports.size();i++){
            url="http://localhost:"+ports.get(i)+"/quotations";
            Quotation quotation = restTemplate.postForObject(url, request, Quotation.class);
            displayProfile(clients[0]);
            displayQuotation(quotation);
        }*/

    }

    /**
     * Display the client info nicely.
     *
     * @param info
     */
    public static void displayProfile(ClientInfo info) {
        System.out.println("|=================================================================================================================|");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println(
                "| Name: " + String.format("%1$-29s", info.getName()) +
                        " | Gender: " + String.format("%1$-27s", (info.getGender() ==ClientInfo.MALE?"Male":"Female")) +
                        " | Age: " + String.format("%1$-30s", info.getAge())+" |");
        System.out.println(
                "| License Number: " + String.format("%1$-19s", info.getLicenseNumber()) +
                        " | No Claims: " + String.format("%1$-24s", info.getNoClaims() +" years") +
                        " | Penalty Points: " + String.format("%1$-19s", info.getPoints())+" |");
        System.out.println("|                                     |                                     |                                     |");
        System.out.println("|=================================================================================================================|");
    }

    /**
     * Display a quotation nicely - note that the assumption is that the quotation will follow
     * immediately after the profile (so the top of the quotation box is missing).
     *
     * @param quotation
     */
    public static void displayQuotation(Quotation quotation) {
        Locale italian = new Locale("it", "IT", "EURO");
        Locale.setDefault(italian);
        Currency euros=Currency.getInstance(italian);
        NumberFormat nf = NumberFormat.getCurrencyInstance(italian);
        System.out.println(
                "| Company: " + String.format("%1$-26s", quotation.getCompany()) +
                        " | Reference: " + String.format("%1$-24s", quotation.getReference()) +
                        " | Price: " + euros.getDisplayName() + ": " + nf.format(quotation.getPrice())+" |");
        System.out.println("|=================================================================================================================|");
    }

    /**
     * Test Data of the clients
     */
    public static final ClientInfo[] clients = {
            new ClientInfo("Josemon Baby", ClientInfo.MALE, 24, 0, 5, "PQR254/1"),
            new ClientInfo("Michael Scott", ClientInfo.MALE, 44, 0, 2, "ABC123/4"),
            new ClientInfo("Jim Halpert", ClientInfo.MALE, 30, 10, 0, "HMA304/9"),
            new ClientInfo("Angela Martin", ClientInfo.FEMALE, 44, 5, 3, "COL123/3"),
            new ClientInfo("Dwight K. Shrute", ClientInfo.MALE, 30, 4, 7, "QUN987/4"),
            new ClientInfo("Pam Beasly", ClientInfo.FEMALE, 28, 5, 2, "XYZ567/9")
    };
}
