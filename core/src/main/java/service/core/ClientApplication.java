package service.core;

import java.util.ArrayList;

public class ClientApplication {

    private int applicationID;
    private ClientInfo clientInfo;
    private ArrayList<Quotation> quotations;

    public ClientApplication(int applicationID, ClientInfo clientInfo, ArrayList<Quotation> quotations) {
        this.applicationID = applicationID;
        this.clientInfo = clientInfo;
        this.quotations = quotations;
    }

    public ClientApplication() {
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public ArrayList<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(ArrayList<Quotation> quotations) {
        this.quotations = quotations;
    }
}
