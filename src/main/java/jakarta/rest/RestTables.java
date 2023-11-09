package jakarta.rest;

import domain.modelo.restaurant.RestaurantTable;
import domain.servicios.RestaurantTableServices;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(Constantes.MESAS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestTables {
    private final RestaurantTableServices mesaServicios;

    @Inject
    public RestTables(RestaurantTableServices mesaServicios) {
        this.mesaServicios = mesaServicios;
    }
    @GET
    public List<RestaurantTable> getAll(){
        return mesaServicios.getAll();
    }

    @GET
    @Path(Constantes.ID)
    public RestaurantTable get(@PathParam(Constantes.ID_SINGLE) String id){
        return mesaServicios.get(Integer.parseInt(id));
    }

    @POST
    public Response add(RestaurantTable mesa){
        mesaServicios.add(mesa);
        return Response.status(Response.Status.CREATED).entity(mesa).build();
    }

    @PUT
    public RestaurantTable update(RestaurantTable table, @QueryParam(Constantes.ID_SINGLE) String id){
        mesaServicios.update(table);
        return mesaServicios.get(table.getTableNumber());
    }

    @DELETE
    @Path(Constantes.ID)
    public Response delete(@PathParam(Constantes.ID_SINGLE) String id){
        return null;
    }
}
