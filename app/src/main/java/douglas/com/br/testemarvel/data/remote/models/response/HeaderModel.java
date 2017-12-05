package douglas.com.br.testemarvel.data.remote.models.response;

/**
 * Created by douglaspanacho on 04/12/2017.
 */

public class HeaderModel {
    String sectionName;

    public HeaderModel(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }
}
