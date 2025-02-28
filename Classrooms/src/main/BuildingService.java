import api.GebaeudeResource;
import jpa.Building;

import javax.ws.rs.core.Response;
import java.util.List;

public class BuildingService {

    private GebaeudeResource gebaeudeResource;

    public BuildingService() {
        this.gebaeudeResource = new GebaeudeResource();
    }

    public List<Building> getAllBuildings() {
        return gebaeudeResource.getAllBuilding();
    }

    public Response createBuilding(Building building) {
        return gebaeudeResource.createBuilding(building);
    }

    public Building getBuildingById(Integer id) {
        return gebaeudeResource.getBuilding(id);
    }

    public Response updateBuilding(Integer id, Building building) {
        return gebaeudeResource.updateBuilding(id, building);
    }

    public Response deleteBuilding(Integer id) {
        return gebaeudeResource.deleteBuilding(id);
    }

    public static void main(String[] args) {
        BuildingService service = new BuildingService();

        Building newBuilding = new Building();
        newBuilding.kuerzel = "HQ";
        newBuilding.name = "Headquarters";

        Response response = service.createBuilding(newBuilding);
        System.out.println("Response Status: " + response.getStatus());
    }
}