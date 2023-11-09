package jakarta.rest;

import domain.modelo.restaurant.RestaurantOrder;
import domain.servicios.RestaurantOrderServices;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(Constantes.PEDIDOS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestOrders {
    private final RestaurantOrderServices serviciosPedidos;

    @Inject
    public RestOrders(RestaurantOrderServices serviciosPedidos) {
        this.serviciosPedidos = serviciosPedidos;
    }


    @GET
    public List<RestaurantOrder> getAllPedidos() {
        return serviciosPedidos.getAll();
    }

    @GET
    @Path(Constantes.ID)
    public RestaurantOrder getPedido(@PathParam(Constantes.ID_SINGLE) String id) {
        return serviciosPedidos.get(Integer.parseInt(id));
    }

    @POST
    public Response addPedido(RestaurantOrder pedido) {
        serviciosPedidos.add(pedido);
        return Response.status(Response.Status.CREATED).entity(pedido).build();
    }

    @PUT
    public RestaurantOrder updatePedido(RestaurantOrder pedido, @QueryParam(Constantes.ID_SINGLE) String id) {
        serviciosPedidos.update(pedido);
        return serviciosPedidos.get(pedido.getId());
    }

    @DELETE
    @Path(Constantes.ID)
    public Response deletePedido(@PathParam(Constantes.ID_SINGLE) String id) {
//        if (serviciosPedidos.delete(Integer.parseInt(id)) == 1){
//            return Response.status(Response.Status.NO_CONTENT).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(ApiError.builder()
//                            .message("usuario no encontrado")
//                            .fecha(LocalDateTime.now())
//                            .build())
//                    .build();
//        }
        return null;
    }

}
