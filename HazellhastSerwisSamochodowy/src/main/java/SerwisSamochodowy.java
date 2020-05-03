import java.io.Serializable;
import java.util.Map;

public class SerwisSamochodowy implements Serializable {

    private static final long serialVersionUID = 1L;

    private String repairManager;
    private int timeRepair;
    private int numberTeam;
    private String describe;

    public SerwisSamochodowy(){}

    public SerwisSamochodowy(String repairManager, int timeRepair, int numberTeam, String describe) {
        this.repairManager = repairManager;
        this.timeRepair = timeRepair;
        this.numberTeam = numberTeam;
        this.describe = describe;
    }

    public String getRepairManager() {
        return repairManager;
    }
    public String getDescribe() {
        return describe;
    }
    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }
    public void setTimeRepair(int timeRepair) {
        this.timeRepair = timeRepair;
    }
    public void setNumberTeam(int numberTeam) {
        this.numberTeam = numberTeam;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "SerwisSamochodowy{" +
                "repairManager='" + repairManager + '\'' +
                ", timeRepair=" + timeRepair +
                ", numberTeam=" + numberTeam +
                ", describe='" + describe + '\'' +
                '}';
    }
}
