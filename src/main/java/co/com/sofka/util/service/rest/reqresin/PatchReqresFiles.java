package co.com.sofka.util.service.rest.reqresin;

public enum PatchReqresFiles {
    LIST_RESOURCES(System.getProperty("user.dir")
            + "\\src\\test\\resources\\fileS\\services\\rest\\reqresin\\response\\resources.Json"),
    USUARIO(System.getProperty("user.dir")
            + "\\src\\test\\resources\\fileS\\services\\rest\\reqresin\\response\\usuarioCreado.Json");

    private final String value;

    PatchReqresFiles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
