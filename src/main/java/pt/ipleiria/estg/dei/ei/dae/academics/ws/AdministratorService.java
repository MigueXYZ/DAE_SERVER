package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    public void createNewAdministrator(AdministratorDTO administratorDTO) {
        administratorBean.create(
                administratorDTO.getUsername(),
                administratorDTO.getPassword(),
                administratorDTO.getName(),
                administratorDTO.getEmail()
        );
        Administrator newAdministrator = administratorBean.find(administratorDTO.getUsername());
    }

    //get administrator by username
    @GET
    @Path("{username}")
    public AdministratorDTO getAdministrator(@PathParam("username") String username) {
        return AdministratorDTO.from(administratorBean.find(username));
    }
}
