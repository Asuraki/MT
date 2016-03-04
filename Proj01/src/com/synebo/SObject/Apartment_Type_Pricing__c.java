package com.synebo.SObject;

/**
 * Created by User on 02.03.2016.
 */
public class Apartment_Type_Pricing__c extends  com.sforce.soap.enterprise.sobject.Apartment_Type_Pricing__c {

//Deposit_Price_without_VAT__c
    /**
     * element : Deposit_Price_without_VAT__c of type {http://www.w3.org/2001/XMLSchema}date
     * java type: java.util.Calendar
     */
    private static final com.sforce.ws.bind.TypeInfo Deposit_Price_without_VAT__c__typeInfo =
            new com.sforce.ws.bind.TypeInfo("urn:sobject.enterprise.soap.sforce.com","Deposit_Price_without_VAT__c","http://www.w3.org/2001/XMLSchema","currency",0,1,true);

    private boolean Deposit_Price_without_VAT__c__is_set = false;

    private Double Deposit_Price_without_VAT__c;

    public Double getDeposit_Price_without_VAT__c() {
        return Deposit_Price_without_VAT__c;
    }

    public void setDeposit_Price_without_VAT__c(Double Deposit_Price_without_VAT__c) {
        this.Deposit_Price_without_VAT__c = Deposit_Price_without_VAT__c;
        Deposit_Price_without_VAT__c__is_set = true;
    }

    protected void setDeposit_Price_without_VAT__c(com.sforce.ws.parser.XmlInputStream __in,
                                                 com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        __in.peekTag();
        if (__typeMapper.isElement(__in, Deposit_Price_without_VAT__c__typeInfo)) {
            setDeposit_Price_without_VAT__c((Double) __typeMapper.readObject(__in, Deposit_Price_without_VAT__c__typeInfo, Double.class));
        }
    }

//Deposit_Price_with_VAT__c
    /**
     * element : Deposit_Price_with_VAT__c of type {http://www.w3.org/2001/XMLSchema}date
     * java type: java.util.Calendar
     */
    private static final com.sforce.ws.bind.TypeInfo Deposit_Price_with_VAT__c__typeInfo =
            new com.sforce.ws.bind.TypeInfo("urn:sobject.enterprise.soap.sforce.com","Deposit_Price_with_VAT__c","http://www.w3.org/2001/XMLSchema","currency",0,1,true);

    private boolean Deposit_Price_with_VAT__c__is_set = false;

    private Double Deposit_Price_with_VAT__c;

    public Double getDeposit_Price_with_VAT__c() {
        return Deposit_Price_with_VAT__c;
    }

    public void setDeposit_Price_with_VAT__c(Double Deposit_Price_with_VAT__c) {
        this.Deposit_Price_with_VAT__c = Deposit_Price_with_VAT__c;
        Deposit_Price_with_VAT__c__is_set = true;
    }

    protected void setDeposit_Price_with_VAT__c(com.sforce.ws.parser.XmlInputStream __in,
                                                   com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        __in.peekTag();
        if (__typeMapper.isElement(__in, Deposit_Price_with_VAT__c__typeInfo)) {
            setDeposit_Price_with_VAT__c((Double) __typeMapper.readObject(__in, Deposit_Price_with_VAT__c__typeInfo, Double.class));
        }
    }

//Holding_Price_without_VAT__c
    /**
     * element : Holding_Price_without_VAT__c of type {http://www.w3.org/2001/XMLSchema}date
     * java type: java.util.Calendar
     */
    private static final com.sforce.ws.bind.TypeInfo Holding_Price_without_VAT__c__typeInfo =
            new com.sforce.ws.bind.TypeInfo("urn:sobject.enterprise.soap.sforce.com","Holding_Price_without_VAT__c","http://www.w3.org/2001/XMLSchema","currency",0,1,true);

    private boolean Holding_Price_without_VAT__c__is_set = false;

    private Double Holding_Price_without_VAT__c;

    public Double getHolding_Price_without_VAT__c() {
        return Holding_Price_without_VAT__c;
    }

    public void setHolding_Price_without_VAT__c(Double Holding_Price_without_VAT__c) {
        this.Holding_Price_without_VAT__c = Holding_Price_without_VAT__c;
        Holding_Price_without_VAT__c__is_set = true;
    }

    protected void setHolding_Price_without_VAT__c(com.sforce.ws.parser.XmlInputStream __in,
                                                   com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        __in.peekTag();
        if (__typeMapper.isElement(__in, Holding_Price_without_VAT__c__typeInfo)) {
            setHolding_Price_without_VAT__c((Double) __typeMapper.readObject(__in, Holding_Price_without_VAT__c__typeInfo, Double.class));
        }
    }

//Holding_Price_with_VAT__c
    /**
     * element : Holding_Price_with_VAT__c of type {http://www.w3.org/2001/XMLSchema}date
     * java type: java.util.Calendar
     */
    private static final com.sforce.ws.bind.TypeInfo Holding_Price_with_VAT__c__typeInfo =
            new com.sforce.ws.bind.TypeInfo("urn:sobject.enterprise.soap.sforce.com","Holding_Price_with_VAT__c","http://www.w3.org/2001/XMLSchema","currency",0,1,true);

    private boolean Holding_Price_with_VAT__c__is_set = false;

    private Double Holding_Price_with_VAT__c;

    public Double getHolding_Price_with_VAT__c() {
        return Holding_Price_with_VAT__c;
    }

    public void setHolding_Price_with_VAT__c(Double Holding_Price_with_VAT__c) {
        this.Holding_Price_with_VAT__c = Holding_Price_with_VAT__c;
        Holding_Price_with_VAT__c__is_set = true;
    }

    protected void setHolding_Price_with_VAT__c(com.sforce.ws.parser.XmlInputStream __in,
                                                   com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        __in.peekTag();
        if (__typeMapper.isElement(__in, Holding_Price_with_VAT__c__typeInfo)) {
            setHolding_Price_with_VAT__c((Double) __typeMapper.readObject(__in, Holding_Price_with_VAT__c__typeInfo, Double.class));
        }
    }





    /**
     */
    @Override
    public void write(javax.xml.namespace.QName __element,
                      com.sforce.ws.parser.XmlOutputStream __out, com.sforce.ws.bind.TypeMapper __typeMapper)
            throws java.io.IOException {
        __out.writeStartTag(__element.getNamespaceURI(), __element.getLocalPart());
        __typeMapper.writeXsiType(__out, "urn:sobject.enterprise.soap.sforce.com", "Apartment_Type_Pricing__c");
        writeFields(__out, __typeMapper);
        __out.writeEndTag(__element.getNamespaceURI(), __element.getLocalPart());
    }

    protected void writeFields(com.sforce.ws.parser.XmlOutputStream __out, com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException {
        super.writeFields(__out, __typeMapper);
        __typeMapper.writeObject(__out, Deposit_Price_without_VAT__c__typeInfo, Deposit_Price_without_VAT__c, Deposit_Price_without_VAT__c__is_set);
        __typeMapper.writeObject(__out, Deposit_Price_with_VAT__c__typeInfo, Deposit_Price_with_VAT__c, Deposit_Price_with_VAT__c__is_set);
        __typeMapper.writeObject(__out, Holding_Price_without_VAT__c__typeInfo, Holding_Price_without_VAT__c, Holding_Price_without_VAT__c__is_set);
        __typeMapper.writeObject(__out, Holding_Price_with_VAT__c__typeInfo, Holding_Price_with_VAT__c, Holding_Price_with_VAT__c__is_set);
    }

    @Override
    public void load(com.sforce.ws.parser.XmlInputStream __in,
                     com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        __typeMapper.consumeStartTag(__in);
        loadFields(__in, __typeMapper);
        __typeMapper.consumeEndTag(__in);
    }

    protected void loadFields(com.sforce.ws.parser.XmlInputStream __in,
                              com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        super.loadFields(__in, __typeMapper);
        setDeposit_Price_without_VAT__c(__in, __typeMapper);
        setDeposit_Price_with_VAT__c(__in, __typeMapper);
        setHolding_Price_without_VAT__c(__in, __typeMapper);
        setHolding_Price_with_VAT__c(__in, __typeMapper);
    }


}