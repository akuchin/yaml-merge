import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.Yaml

class Main {
    public static void main(String[] args) {
        def merge = Main.class.getResource("demo/spec.yaml").file

        def yaml = new Yaml()
        yaml.addTypeDescription(new TypeDescription(MapFromFile.class, "!file"))
        def data = yaml.load(new FileReader(merge))
        println(data)
    }
}
