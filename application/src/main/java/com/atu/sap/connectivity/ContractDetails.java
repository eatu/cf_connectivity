package com.atu.sap.connectivity;

import lombok.Data;
import com.sap.cloud.sdk.result.ElementName;

@Data
public class ContractDetails
{
    @ElementName( "ContractID" )
    private String ContractID;

    @ElementName( "ContractAccountID" )
    private String ContractAccountID;

    @ElementName( "DivisionID" )
    private String DivisionID;

    @ElementName( "PremiseID" )
    private String PremiseID;

    @ElementName( "Description" )
    private String Description;
}