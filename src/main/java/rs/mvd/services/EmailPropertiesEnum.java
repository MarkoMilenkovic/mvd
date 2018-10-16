package rs.mvd.services;

public enum EmailPropertiesEnum {

    PICTURE_URL("picture_url"),
    TITLE("title"),
    BUTTON_TEXT("button_text"),
    TENANT_NAME("tenantName"),
    FULL_TENANT_NAME("fullTenatName"),
    FULL_TENANT_URL("fullTenantUrl"),
    BODY("body");

    private String value;

    EmailPropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
