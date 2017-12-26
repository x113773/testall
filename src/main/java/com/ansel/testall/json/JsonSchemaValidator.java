package com.ansel.testall.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JsonSchemaValidator {
	public static Map<String, Object> validateJsonByFgeByJsonNode(JsonNode jsonNode, JsonNode schemaNode) {
		Map<String, Object> result = new HashMap<String, Object>();
		ProcessingReport report = null;
		report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schemaNode, jsonNode);
		if (report.isSuccess()) {
			// 校验成功
			result.put("message", "校验成功！");
			result.put("success", true);
			return result;
		} else {
			System.out.println("校验失败！");
			Iterator<ProcessingMessage> it = report.iterator();
			String ms = "";
			while (it.hasNext()) {
				ProcessingMessage pm = it.next();
				if (!LogLevel.WARNING.equals(pm.getLogLevel())) {
					ms += pm;
				}

			}
			result.put("message", "校验失败！" + ms);
			result.put("success", false);
			return result;
		}
	}

	public static JsonNode getJsonNodeFromString(String jsonStr) {
		JsonNode jsonNode = null;
		try {
			jsonNode = JsonLoader.fromString(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonNode;
	}

	public static JsonNode getJsonNodeFromFile(String filePath) {
		JsonNode jsonNode = null;
		try {
			jsonNode = new JsonNodeReader().fromReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonNode;
	}

}
