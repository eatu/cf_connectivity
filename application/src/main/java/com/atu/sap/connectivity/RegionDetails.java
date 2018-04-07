package com.atu.sap.connectivity;

import lombok.Data;
import com.sap.cloud.sdk.result.ElementName;

@Data
public class RegionDetails {

    @ElementName( "RegionID" )
    private String RegionID;

    @ElementName( "RegionDescription" )
    private String RegionDescription;
}

