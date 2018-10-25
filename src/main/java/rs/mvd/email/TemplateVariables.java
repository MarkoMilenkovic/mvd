package rs.mvd.email;

public enum TemplateVariables {

    FIRST_NAME("first_name"),
    CODE("code"),
    TENANT("tenant"),
    GROUP_NAME("group_name"),
    URL("url"),
    TOPIC_TITLE("topicTitle"),
    GROUP_TITLE("groupTitle"),
    LINK_CONCLUSION("linkConclusios"),
    EXPIRY_DATE("expiryDate"),
    LINK_GROUP("linkGroup"),
    GROUP("group"),
    URL_EXPLORE("urlExplore"),
    LINK_DIRECT_MESSAGES("linkDirectMessage"),
    COUNT_OF_DM_NOT_READ("countOfDmNotRead"),
    LINK_PREFERENCES("linkPreferences"),
    FULL_TENANT_NAME("fullTenatName");

    private String value;

    TemplateVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
