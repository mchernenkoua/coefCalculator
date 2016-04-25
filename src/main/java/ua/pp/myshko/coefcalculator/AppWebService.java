package ua.pp.myshko.coefcalculator;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * @author M. Chernenko
 */
@Path("/")
public class AppWebService {
    
    RequestProcessor requestProcessor = RequestProcessorImpl.getInstance();

    @GET
    @Path("/get")
    @Produces("application/xml")
    public Response get(@QueryParam("v1") int v1) {

        String result = "";
        try {
            result = String.valueOf(requestProcessor.get(v1));
            return Response.ok()
                    .entity("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                            "<result>" + result + "</result>")
                    .build();
        } catch (IOException e) {
            // just return an empty answer?..
        }
        return Response.ok()
                .entity("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<result>" + result + "</result>")
                .build();
    }

    @POST
    @Path("/post")
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response post(Params params) {

        boolean result = requestProcessor.post(params.getV2(), params.getV3(), params.getV4());
        return Response.ok()
                .entity("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<result>" + ((result) ? "1" : "0") + "</result>")
                .build();
    }
}
