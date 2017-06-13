package com.ansel.testall.snakeyaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class yamlTest {
	public void loadYaml() throws FileNotFoundException {
		Yaml yaml = new Yaml();
		File f = new File("test.yaml");
		Map result = (Map) yaml.load(new FileInputStream(f));
		System.out.println(result);
	}

	public void dumpYaml() throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("name", "Silenthand Olleander");
	    data.put("race", "Human");
	    data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });
	    Yaml yaml = new Yaml();
	    File tmpfile = new File("/swagger-doc/ss.yaml");
		BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));
	    yaml.dump(data, writer);
		writer.flush();
		writer.close();
	}
}
