package com.atu.sap.connectivity;

import com.google.gson.Gson;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.s4hana.connectivity.ErpEndpoint;

@WebServlet("/contracts")
public class ContractServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(ContractServlet.class);

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("ContractsServlet->INFO" + "ContractsServlet is running!");
        response.getWriter().write("Hello ContractsServlet!");
        try {
            final ErpEndpoint endpoint = new ErpEndpoint();
            final List<ContractDetails> costCenters = ODataQueryBuilder
                    .withEntity("/sap/opu/odata/sap/ERP_UTILITIES_UMC", "Contracts")
                    .select("ContractID", "ContractAccountID", "DivisionID", "PremiseID", "Description")
                    .build()
                    .execute(new ErpConfigContext("ErpQueryEndpoint"))
                    .asList(ContractDetails.class);

            System.out.println("ContractsServlet->INFO" + "Query executed!");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(costCenters));

        } catch(final ODataException e) {
            logger.error(e.getMessage(), e);
            System.out.println("ContractsServlet->ERROR" + "Query error:" + e.getMessage() );
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(e.getMessage());
        }
    }
}
