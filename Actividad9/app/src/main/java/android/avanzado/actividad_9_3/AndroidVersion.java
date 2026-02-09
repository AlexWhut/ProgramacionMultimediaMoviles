package android.avanzado.actividad_9_3;

public class AndroidVersion {
    private String name;
    private int imageResId;
    private String description;

    public AndroidVersion(String name, int imageResId, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
