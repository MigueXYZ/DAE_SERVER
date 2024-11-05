package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.AdministratorDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.AdministratorBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;

import java.util.List;

@Path("administrator")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class AdministratorService {
    @EJB
    private AdministratorBean administratorBean;

    @GET
    @Path("/")
    public List<AdministratorDTO> getAllAdministrators() {
        return AdministratorDTO.from(administratorBean.findAll());
    }

    //create a new administrator
    @POST
    @Path("/")
    public Response createNewAdministrator(AdministratorDTO administratorDTO) {
        administratorBean.create(
                administratorDTO.getUsername(),
                administratorDTO.getPassword(),
                administratorDTO.getName(),
                administratorDTO.getEmail()
        );
        Administrator newAdministrator = administratorBean.find(administratorDTO.getUsername());

        return Response.status(Response.Status.CREATED)
                .entity(AdministratorDTO.from(newAdministrator))
                .build();
    }

    //get administrator by username
    @GET
    @Path("{username}")
    public AdministratorDTO getAdministrator(@PathParam("username") String username) {
        return AdministratorDTO.from(administratorBean.find(username));
    }
}
