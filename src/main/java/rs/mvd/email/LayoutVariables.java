package rs.mvd.email;

public enum LayoutVariables {

    PICTURE_URL("picture_url"),
    TITLE("title"),
    BUTTON_TEXT("button_text"),
    TENANT_NAME("tenantName"),
    FULL_TENANT_URL("fullTenantUrl"),
    BODY("body"),
    DISPLAY_LINK("displayLink"),
    LINK("link");

    private String value;

    LayoutVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
