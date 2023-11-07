package jakarta.rest;

import domain.servicios.ServiciosObjetos;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/objetos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestObjetos {
//    private ServiciosObjetos serviciosObjetos;
//
//    @Inject
//    public RestObjetos(ServiciosObjetos serviciosObjetos) {
//        this.serviciosObjetos = serviciosObjetos;
//    }
//
//    @GET
//    public List<Object> getAll(){
//
//    }
//
//    @GET
//    @Path(Constantes.ID)
//    public Object get(@PathParam(Constantes.ID_SINGLE) String id){
//
//    }
//
//    @POST
//    public Response add(Object object){
//        return Response.status(Response.Status.ACCEPTED).entity(object).build();
//    }
//
//    @PUT
//    public Object update(Object object, @QueryParam(Constantes.ID_SINGLE) String id){
//
//    }
//
//    @DELETE
//    @Path(Constantes.ID)
//    public Response delete(@PathParam(Constantes.ID_SINGLE) String id){
//
//    }

}
