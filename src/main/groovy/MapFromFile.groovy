

import org.yaml.snakeyaml.Yaml

class MapFromFile extends HashMap {

    public MapFromFile(String files){
        def parts = files.split("\\|")
        def f1 = this.getClass().getResource(parts[0]).file
        def f2 = this.getClass().getResource(parts[1]).file
        def data = new Yaml().load(new FileReader(f1))
        def second = new Yaml().load(new FileReader(f2))

        this.putAll(merge(data, second))
    }

    def merge(Map lhs, Map rhs) {
        return rhs.inject(lhs.clone()) { map, entry ->
            if (map[entry.key] instanceof Map && entry.value instanceof Map) {
                map[entry.key] = merge(map[entry.key], entry.value)
            } else {
                map[entry.key] = entry.value
            }
            return map
        }
    }
}
